package com.mst.csuserservice.infrastructure;

import com.google.common.collect.Lists;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.infrastructure.jpa.JpaUserRepository;
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

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
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
     * 根据用户名和密码查找用户.
     * @param   username  username
     * @param   password  password
     * @return  Optional User
     */
    @Override
    public Optional<User> findByNameAndPassword(String username, String password) {
        User user = jpaUserRepository.findByNameAndPassword(username, password);
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
}
