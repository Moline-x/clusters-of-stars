package com.mst.csproductservice.domain.aggregates.product.valueobject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Molin
 * @date 2021/12/24  16:47
 * class description: 值对象——商品状态枚举
 */
@AllArgsConstructor
public enum ProductStatusEnum {

    /**
     * 商品状态.
     */
    DRAFTED(1000111, "草稿"),
    AUDIT_PENDING(1000112, "待审核"),
    LISTED(1000113, "已上架"),
    UNLISTED(1000114, "已下架");

    @Getter
    private Integer code;

    @Getter
    private String remark;

    /**
     * 根据code拿枚举.
     * @param  code code
     * @return ProductStatus enum
     */
    public static ProductStatusEnum of(Integer code) {
        ProductStatusEnum[] values = ProductStatusEnum.values();
        for (ProductStatusEnum val : values) {
            if (val.code.equals(code)) {
                return val;
            }
        }
        return null;
    }
}
