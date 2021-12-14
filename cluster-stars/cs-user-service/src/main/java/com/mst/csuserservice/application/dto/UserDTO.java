package com.mst.csuserservice.application.dto;

import com.github.pagehelper.PageInfo;
import com.mst.csuserservice.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Molin
 * @date   2021-11-19  23:15
 * 用户应用层数据传输对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /**
     * 用户实体.
     */
    private User user;
    /**
     * 响应信息.
     */
    private Boolean msg;
    /**
     * token响应结果.
     */
    private Map<String, String> tokenMap;
    /**
     * 权限列表.
     */
    private List<String> permissions;
    /**
     * 用户列表.
     */
    private PageInfo<User> userList;
}
