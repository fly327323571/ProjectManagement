package cn.xidian.parknshop.service;

import cn.xidian.parknshop.beans.Express;

public interface ExpressService {
	Express getExpressByOrderNo(long orderNo);
}
