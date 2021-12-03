package com.mst.csuserservice.infrastructure;

import com.google.common.collect.Lists;
import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.model.UserRole;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.infrastructure.jpa.JpaAccountRepository;
import com.mst.csuserservice.infrastructure.jpa.JpaUserRepository;
import com.mst.csuserservice.infrastructure.jpa.JpaUserRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Molin
 * @date   2021-11-19  22:48
 * 用户领域持久化仓库
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    private final JpaAccountRepository jpaAccountRepository;

    private final JpaUserRoleRepository jpaUserRoleRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository,
                              JpaAccountRepository jpaAccountRepository,
                              JpaUserRoleRepository jpaUserRoleRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaAccountRepository = jpaAccountRepository;
        this.jpaUserRoleRepository = jpaUserRoleRepository;
    }

    /**
     * 保存用户操作.
     * @param   user  user
     * @return  User
     */
    @Override
    public User saveUser(User user) {
        return jpaUserRepository.save(user);
    }

    /**
     * 根据手机号和密码查找用户.
     * @param   mobile   mobile
     * @param   password password
     * @return  Optional User
     */
    @Override
    public Optional<User> findByMobileAndPassword(String mobile, String password) {
        User user = jpaUserRepository.findByMobileAndPassword(mobile, password);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    /**
     * 根据电子邮箱和密码查找用户.
     * @param  email    email
     * @param  password password
     * @return Optional User
     */
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = jpaUserRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    /**
     * 查找所用用户.
     * @return  user list
     */
    @Override
    public List<User> findAll() {
        return Optional.of(jpaUserRepository.findAll()).orElse(Lists.newArrayList());
    }

    /**
     * 更新最新登录类型.
     * @param  userId    user id
     * @param  loginType login type
     */
    @Override
    public void updateLoginType(Long userId, int loginType) {
        jpaAccountRepository.updateLoginTypeByUserId(userId, loginType);
    }

    /**
     * 根据用户id查找账户.
     * @param  userId  user id
     * @return Optional account
     */
    @Override
    public Optional<Account> findByUserId(Long userId) {
        Account account = jpaAccountRepository.findByUserId(userId);
        if (account != null) {
            return Optional.of(account);
        }
        return Optional.empty();
    }

    /**
     * 保存账户操作.
     * @param  account  账户实体
     * @return Account
     */
    @Override
    public Account saveAccount(Account account) {
        return jpaAccountRepository.save(account);
    }

    /**
     * 保存用户Id和角色Id操作.
     * @param userRole  user role
     */
    @Override
    public void saveRoleUser(UserRole userRole) {
        jpaUserRoleRepository.save(userRole);
    }

    /**
     * 根据手机号和邮箱查询用户.
     * @param  mobile  手机号
     * @param  email   邮箱
     * @return Optional User
     */
    @Override
    public Optional<User> findByMobileAndEmail(String mobile, String email) {
        User user = jpaUserRepository.findByMobileAndEmail(mobile, email);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    /**
     * 根据id查找用户.
     *
     * @param userId 用户id
     * @return Optional User
     */
    @Override
    public User findUserById(Long userId) {
        return jpaUserRepository.getOne(userId);
    }
}
