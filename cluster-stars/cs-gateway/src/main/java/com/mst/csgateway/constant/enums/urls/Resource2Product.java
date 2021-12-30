package com.mst.csgateway.constant.enums.urls;

/**
 * @author Molin
 * @date 2021/12/30  15:09
 * class description: 商品资源
 */
public enum Resource2Product {

    /**
     * 商品资源.
     */
    FULL_PATH(ResourceBuilder.FULL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.PRODUCT.symbol()),
            CommonResource.WILDCARD.apply(CommonResource.FULL.symbol())
    )),

    TEST_CONNECTION(ResourceBuilder.NORMAL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.PRODUCT.symbol()),
            CommonResource.WILDCARD.apply(CommonResource.PREFIX.symbol()+"testAction")
    ));

    private final String uri;

    Resource2Product(String uri) {
        this.uri = uri;
    }

    public String uri() {
        return uri;
    }
}
