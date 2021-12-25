package com.mst.csuserservice.domain.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.mst.csuserservice.domain.model.PageParam;

import java.util.List;


/**
 * @author Molin
 * @date 2021/12/25  12:52
 * class description: 通用接口
 */
public interface BaseService<P, R> {

    /**
     * 分页查询.
     * @param  param 请求参数dto
     * @return 分页集合
     */
    default PageInfo<R> page(PageParam<P> param) {
        return PageMethod.startPage(param).doSelectPageInfo(() -> list(param.getParam()));
    }

    /**
     * 集合查询.
     * @param  param 查询参数
     * @return 查询响应
     */
    List<R> list(P param);
}
