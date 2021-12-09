package com.mst.csuserservice.domain.mapper;


import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Molin
 * @date 2021/12/7  16:45
 * class description: 用户领域角色Mapper.
 */
@Component
public interface RoleMapper {

    /**
     * 根据用户Id查询关联角色码.
     * @param  userId user id
     * @return role list
     */
    List<String> findRolesByUserId(Long userId);
}
