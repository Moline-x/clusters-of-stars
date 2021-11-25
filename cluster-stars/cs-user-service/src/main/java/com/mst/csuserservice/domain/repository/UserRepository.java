package com.mst.csuserservice.domain.repository;

import com.mst.csuserservice.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Molin
 * @date   2021-11-19 22:40
 * 用户领域持久化仓库接口
 */
public interface UserRepository {

    /**
     * 保存用户操作.
     * @param   user  user
     * @return  User
     */
    User saveUser(User user);

    /**
     * 根据用户名和密码查找用户.
     * @param   username  username
     * @param   password  password
     * @return  Optional User
     */
    Optional<User> findByNameAndPassword(String username, String password);

    /**
     * 查找所用用户.
     * @return  user list
     */
    List<User> findAll();
}
