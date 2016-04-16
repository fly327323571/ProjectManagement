package cn.xidian.parknshop.dao;

import cn.xidian.parknshop.beans.Commodity;

public interface CartDao {

		public void addItemInCart(Commodity commodity,int count);
}
