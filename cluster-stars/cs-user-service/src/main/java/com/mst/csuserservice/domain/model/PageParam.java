package com.mst.csuserservice.domain.model;

import com.github.pagehelper.IPage;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Molin
 * @date 2021/12/25  13:07
 * class description: 参考PageInfo包装/分离/声明分页参数和业务参数
 */
@Data
@Accessors(chain = true)
public class PageParam<T> implements IPage {

    private Integer pageNum = 1;

    private Integer pageSize = 12;

    private String orderBy;

    private T param;

    public PageParam<T> setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
}
