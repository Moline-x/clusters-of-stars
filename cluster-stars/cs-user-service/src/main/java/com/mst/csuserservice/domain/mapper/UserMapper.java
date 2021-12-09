package com.mst.csuserservice.domain.mapper;

import com.mst.csuserservice.controller.cqe.command.UserUnBindCommand;
import com.mst.csuserservice.domain.model.LoginLog;
import com.mst.csuserservice.domain.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Molin
 * @date 2021/12/3  9:41
 * class description: 用户领域用户Mapper
 */
@Component
public interface UserMapper {

    /**
     * 根据用户id或角色id完成解除关联操作.
     * @param  command  user id or (user id and role id)
     * @param  deleted  flag to delete
     * @return remove count
     */
    int unBindUserAndRole(@Param("command") UserUnBindCommand command, @Param("deleted") int deleted);

    /**
     * 根据用户id列表删除用户.
     * @param  idList  user id list
     * @param  deleted flag to delete
     * @return remove count
     */
    int removeUser(@Param("userIds") List<Long> idList, @Param("deleted") int deleted);

    /**
     * 根据用户id列表删除相关账户.
     * @param  idList   user id list
     * @param  deleted  flag to delete
     * @return remove count
     */
    int removeAccount(@Param("userIds") List<Long> idList, @Param("deleted") int deleted);

    /**
     * 查询所有用户.
     * @return user list
     */
    List<User> findAllUsers();

    /**
     * 插入登录日志.
     * @param  loginLog login log
     * @return save count
     */
    int saveLoginLog(@Param("log") LoginLog loginLog);
}
