package com.mst.csuserservice.infrastructure.jpa;

import com.mst.csuserservice.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Molin
 * @date 2021/11/26  15:08
 * class description: 基础设施账户持久化仓库
 */
public interface JpaAccountRepository extends JpaRepository<Account, Long> {

    /**
     * 根据用户id更新最新登录类型.
     * @param  userId    user id
     * @param  loginType login type
     */
    @Modifying
    @Query("update Account set category = ?2 where userId = ?1")
    void updateLoginTypeByUserId(Long userId, int loginType);

    /**
     * 根据用户id查找账户.
     * @param  userId  user id
     * @return Account
     */
    Account findByUserId(Long userId);
}
