package com.mst.csuserservice.controller.cqe.command;

import com.mst.csuserservice.constant.UserConstant;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Molin
 * @date 2021/12/3  9:15
 * class description: 解除用户与角色绑定命令
 */
@Builder
@Getter
public class UserUnBindCommand {

    /**
     * 用户id.
     */
    @NotNull(message = UserConstant.USER_ID_NOT_NULL)
    private List<Long> userIdList;

    /**
     * 角色id.
     */
    @NotNull(message = UserConstant.ROLE_ID_NOT_NULL)
    private Long roleId;
}
