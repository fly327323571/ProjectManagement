package cn.xidian.parknshop.beans;

/**
 * This class use for return json 
 * @author FeiYue
 * */
public class ResultType {

	int code;
	
	Object result;
	
	long totalPageCount;
	
	public ResultType setCode(Integer code){
		this.code = code;
		return this;
	}
	
	public Integer getCode(){
		return code;
	}
	
	public ResultType setResult(Object result){
		this.result = result;
		return this;
	}
	
	public Object getResult(){
		return result;
	}
	
	public ResultType success(){
		code = 0;
		return this;
	}
	
	public ResultType error(){
		code =-1;
		return this;
	}
	
	public void setTotalPageCount(long totalPageCount){
		this.totalPageCount=totalPageCount;
	}
	
	public long getTotalPageCount(){
		return this.totalPageCount;
	}
	
}
