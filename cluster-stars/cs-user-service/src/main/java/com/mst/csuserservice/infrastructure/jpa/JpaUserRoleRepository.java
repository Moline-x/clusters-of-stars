package com.mst.csuserservice.infrastructure.jpa;

import com.mst.csuserservice.domain.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Molin
 * @date 2021/11/29  20:17
 * class description: 基础设施用户角色关系
 */
public interface JpaUserRoleRepository extends JpaRepository<UserRole, Long> {
}
