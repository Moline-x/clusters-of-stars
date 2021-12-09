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
     * 根据手机号和密码查找用户.
     * @param   mobile    mobile
     * @param   password  password
     * @return  User
     */
    User findByMobileAndPassword(String mobile, String password);

    /**
     * 根据电子邮箱和密码查找用户.
     * @param  email     email
     * @param  password  password
     * @return User
     */
    User findByEmailAndPassword(String email, String password);

    /**
     * 根据手机号或邮箱查询用户.
     * @param  mobile   mobile
     * @param  email    email
     * @return User
     */
    User findByMobileAndEmail(String mobile, String email);

    /**
     * 根据用户名或邮箱查询用户.
     * @param  name        username
     * @param  password    password
     * @return User
     */
    User findByNameAndPassword(String name, String password);
}
