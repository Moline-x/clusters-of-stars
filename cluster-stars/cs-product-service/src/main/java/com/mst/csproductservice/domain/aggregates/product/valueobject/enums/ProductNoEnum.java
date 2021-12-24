package com.mst.csproductservice.domain.aggregates.product.valueobject.enums;


/**
 * @author Molin
 * @date 2021/12/24  16:05
 * class description: 商品编码构建枚举
 */
public enum ProductNoEnum {

    /**
     * 生成编码随机算子.
     */
    PRODUCT_NO_SEED(9999, "随机种算子"),
    PRODUCT_NO_APPEND(1, "附加");

    /**
     * 值.
     */
    private final int code;

    /**
     * 构造方法
     * @param code code
     * @param msg  messages
     */
    ProductNoEnum(int code, String msg) {
        this.code = code;
    }

    /**
     * 获取code.
     * @return code
     */
    public int getCode() {
        return code;
    }

}
