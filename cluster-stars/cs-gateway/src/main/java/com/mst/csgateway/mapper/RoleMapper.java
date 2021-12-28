package com.mst.csgateway.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Molin
 * @date 2021/12/28  10:38
 * class description: 网关数据层——角色列表
 */
@Repository
public interface RoleMapper {

    /**
     * 根据用户Id查询关联角色码.
     * @param  userId user id
     * @return role list
     */
    List<String> findRolesByUserId(Long userId);
}
