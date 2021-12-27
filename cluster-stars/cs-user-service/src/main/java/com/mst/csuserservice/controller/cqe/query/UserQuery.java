package com.mst.csuserservice.controller.cqe.query;

import lombok.Data;

/**
 * @author Molin
 * @date 2021/12/25  13:27
 * class description: 用户查询对象
 */
@Data
public class UserQuery {

    /**
     * 页码.
     */
    private int pageNum;

    /**
     * 页数.
     */
    private int pageSize;
}
