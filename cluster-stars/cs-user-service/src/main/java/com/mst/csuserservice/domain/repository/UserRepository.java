package com.mst.csuserservice.domain.repository;

import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.model.UserRole;

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
     * 根据手机号和密码查找用户.
     * @param   mobile   mobile
     * @param   password password
     * @return  Optional User
     */
    Optional<User> findByMobileAndPassword(String mobile, String password);

    /**
     * 根据电子邮箱和密码查找用户.
     * @param  email    email
     * @param  password password
     * @return Optional User
     */
    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     * 查找所用用户.
     * @return  user list
     */
    List<User> findAll();

    /**
     * 更新最新登录类型.
     * @param  userId    user id
     * @param  loginType login type
     */
    void updateLoginType(Long userId, int loginType);

    /**
     * 根据用户id查找账户.
     * @param  userId   user id
     * @return Optional Account
     */
    Optional<Account> findByUserId(Long userId);

    /**
     * 保存账户操作.
     * @param  account  账户实体
     * @return Account
     */
    Account saveAccount(Account account);

    /**
     * 保存用户ID和角色ID操作.
     * @param userRole    user id 和 role id的聚合
     */
    void saveRoleUser(UserRole userRole);

    /**
     * 根据手机号和邮箱查找用户.
     * @param  mobile  手机号
     * @param  email   邮箱
     * @return Optional User
     */
    Optional<User> findByMobileAndEmail(String mobile, String email);
}
