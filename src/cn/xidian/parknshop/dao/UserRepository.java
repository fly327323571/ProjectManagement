package cn.xidian.parknshop.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>,CrudRepository<User, Long> {


}
