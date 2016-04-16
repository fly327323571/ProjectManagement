package cn.xidian.parknshop.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataDump {
	private Properties ppConfig = new Properties();
	String sql = ""; // 数据库类型
	String username = "";// 操作用户名
	String password = "";// 密码
	String backup_path = "";
	String database = "";
	HttpServletRequest request;

	public DataDump(HttpServletRequest request) throws Exception {
		this.request = request;
		// 原生资源文件
		String file_Raw = request.getSession().getServletContext().getRealPath("/") + "\\WEB-INF\\classes\\applicationContext.xml";
		// config下资源文件
		String file_config = request.getSession().getServletContext().getRealPath("/") + "\\WEB-INF\\config\\config.properties";
		// 读取config文件下的存储位置
		try {
			ppConfig.load(new FileInputStream(file_config));
		} catch (Exception e) {
			request.getSession().getServletContext().log("读取配置文件失败。", e);
		}

		for (Object obj : ppConfig.keySet()) {
			String key = ((String) obj);
			if ("data.Backup".equals(key)) {
				backup_path = (String) ppConfig.get(key);
			}
		}
		DocumentBuilder builder = null;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

		try {
			builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new File(file_Raw));
			Element root = document.getDocumentElement();
			NodeList childNodes = root.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if (node.getNodeName().equals("bean")
						&& "dataSource".equals(node.getAttributes().getNamedItem("id").getNodeValue())) {
					NodeList childNode = node.getChildNodes();
					for (int j = 0; j < childNode.getLength(); j++) {
						Node inNode = childNode.item(j);
						if (inNode instanceof Element) {
							if ("username".equals(inNode.getAttributes().getNamedItem("name").getNodeValue())) {
								username = inNode.getAttributes().getNamedItem("value").getNodeValue();
							} else if ("password".equals(inNode.getAttributes().getNamedItem("name").getNodeValue())) {
								password = inNode.getAttributes().getNamedItem("value").getNodeValue();
							} else if ("url".equals(inNode.getAttributes().getNamedItem("name").getNodeValue())) {
								sql = inNode.getAttributes().getNamedItem("value").getNodeValue();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("sql:    " + sql);
		System.out.println("username:   " + username);
		System.out.println("password:    " + password);
		System.out.println("backup_path:    " + backup_path);
		if (username.equals("") || password.equals("") || sql.equals("")) {

			throw new Exception("can't read applicationContext.xml");
		}
		boolean token1 = false;
		boolean token2 = false;

		// 获取数据库名称
		ok: for (int i = 0; i < sql.length(); i++) {
			if (sql.charAt(i) == '/' && sql.charAt(i + 1) == '/') {
				for (int j = i + 2; j < sql.length(); j++) {
					if (sql.charAt(j) == '/') {
						for (int k = j + 1; k < sql.length(); k++) {
							if (sql.charAt(k) == '?')
								break ok;
							database += sql.charAt(k);
						}
					}
				}
			}
		}
		System.out.println("database: " + database);
		File file = new File(backup_path);
		file.mkdirs();

	}

	/*
	 * 此方法只能在windows下执行
	 */
	public boolean dumpDataBase() {
		String os = System.getProperty("os.name");
		if (!os.toLowerCase().contains("windows")) // 不是windows系统
			return false;
		// 正确性验证
		if (!sql.contains("mysql") || username.equals("") || password.equals("") || backup_path.equals("")) {
			return false;
		}
		String backup_cmd = "mysqldump -u " + username + " -p" + password + " " + database + " >" + backup_path + "\\"
				+ database + ".sql";
		File path = new File(request.getSession().getServletContext().getRealPath("/") + "\\tmp\\");
		path.mkdirs();
		File bat = new File(request.getSession().getServletContext().getRealPath("/") + "\\tmp\\" + "backupSql.bat");

		if (bat.exists())
			bat.delete();

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bat));
			writer.write(backup_cmd);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Process pro = Runtime.getRuntime().exec(bat.getPath());
			BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(), "GBK"));
			String line;
			while ((line = buf.readLine()) != null) {
				Pattern pattern = Pattern.compile("error");
				Matcher match = pattern.matcher(line);
				if (match.find())
					return false;// 出现错误
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bat.exists())
			bat.delete();// 删除使用过的批处理文件
		return true;

	}

	public boolean recoverDateBase() {

		File databaseFile = new File(backup_path + "\\" + database + ".sql");
		if (!databaseFile.exists()) {
			System.out.println("数据库备份文件不存在");
			return false;
		}
		String Recover_cmd = "mysql -u " + username + " -p" + password + " " + database + " <" + backup_path + "\\"
				+ database + ".sql";

		// 将恢复命令写入bat中
		File path = new File(request.getSession().getServletContext().getRealPath("/") + "\\tmp\\");
		path.mkdirs();
		File bat = new File(request.getSession().getServletContext().getRealPath("/") + "\\tmp\\" + "RecoverSql.bat");
		if (bat.exists())
			bat.delete();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bat));
			writer.write(Recover_cmd);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 执行bat文件
		try {
			Process pro = Runtime.getRuntime().exec(bat.getPath());
			BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(), "GBK"));
			String line;
			while ((line = buf.readLine()) != null) {

				Pattern pattern = Pattern.compile("error");
				Matcher match = pattern.matcher(line);
				if (match.find())
					return false;// 出现错误
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bat.exists())
			bat.delete();// 删除使用过的批处理文件

		return true;
	}

}
