package com.mst.csgateway.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Molin
 * @date 2021/12/28  10:22
 * class description: 网关数据层——权限列表
 */
@Repository
public interface PermissionMapper {

    /**
     * 根据用户Id查找其关联权限码.
     * @param  userId  user id
     * @return permission list
     */
    List<String> findPermissionsByUserId(Long userId);
}
