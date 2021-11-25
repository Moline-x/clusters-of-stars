package com.mst.csuserservice.infrastructure.jpa;

import com.mst.csuserservice.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Molin
 * @date   2021-11-19  22:48
 * 基础设施用户持久化仓库
 */
public interface JpaUserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名和密码查找用户.
     * @param   username  username
     * @param   password  password
     * @return  User
     */
    User findByNameAndPassword(String username, String password);
}
