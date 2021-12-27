package com.mst.csproductservice.constant;

/**
 * @author Molin
 * @date 2021/12/24  15:51
 * class description: 商品领域常量
 */
public final class ProductConstant {

    private ProductConstant() {
    }

    /**
     * 商品编码提示语.
     */
    public static final String PRODUCT_NO_NOT_NULL = "商品编码不能为空!";

    /**
     * 前缀.
     */
    public static final String PREFIX_PRODUCT = "PRODUCT";

    /**
     * 分类转换格式.
     */
    public static final String TYPE_FORMAT = "%04d";

    /**
     * 日期转换格式.
     */
    public static final String PRODUCT_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * 商品分类提示语.
     */
    public static final String CATEGORY_NOT_NULL = "商品分类不能为空!";

    /**
     * 商品分类提示语.
     */
    public static final String CATEGORY_LARGER_ZERO = "商品分类id应大于0";

    /**
     * 商品名称提示语.
     */
    public static final String PRODUCT_NAME_NOT_NULL = "商品名称不能为空!";

    /**
     * 商品分类提示语.
     */
    public static final String CATEGORY_LESSER_TEN_K = "商品分类Id不能超过10000";

    /**
     * 是否允许跨分类提示语.
     */
    public static final String ALLOW_ACROSS_CATEGORY_NOT_NULL = "是否允许跨分类不能为空";

    /**
     * 价格提示语.
     */
    public static final String CURRENCY_NOT_NULL = "币种不能为空!";

    /**
     * 价格提示语.
     */
    public static final String CURRENCY_NOT_INVALID = "不是有效的币种";

    /**
     * 价格提示语.
     */
    public static final String PRICE_NOT_NULL = "售价不能为空!";

    /**
     * 价格提示语.
     */
    public static final String PRICE_LARGER_ZERO = "售价必须大于0";

}
