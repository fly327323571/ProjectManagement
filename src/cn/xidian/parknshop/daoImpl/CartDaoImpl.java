package cn.xidian.parknshop.daoImpl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.dao.CartDao;
import javassist.bytecode.ByteArray;

@Repository("cartDao")
public class CartDaoImpl implements CartDao {

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@Override
	public void addItemInCart(final Commodity commodity, final int count) {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection conn) throws DataAccessException {
				// TODO Auto-generated method stub
				conn.hSet("cart".getBytes(),String.valueOf(commodity.getCommodityNo()).getBytes(), String.valueOf(count).getBytes());
				 	return null;
			}
        });
	}

}
