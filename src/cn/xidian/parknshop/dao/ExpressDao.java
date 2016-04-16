package cn.xidian.parknshop.dao;

import cn.xidian.parknshop.beans.Express;

public interface ExpressDao {
	Express getExpressByOrderNo(long orderNo);
}
