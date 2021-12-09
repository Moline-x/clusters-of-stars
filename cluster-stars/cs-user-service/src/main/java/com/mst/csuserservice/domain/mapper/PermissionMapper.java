package com.mst.csuserservice.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mst.csuserservice.domain.model.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Molin
 * @date 2021/11/29  21:46
 * class description: 用户领域权限Mapper
 */
@Component
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户Id查找其关联权限码.
     * @param  userId  user id
     * @return permission list
     */
    List<String> findPermissionsByUserId(Long userId);
}
