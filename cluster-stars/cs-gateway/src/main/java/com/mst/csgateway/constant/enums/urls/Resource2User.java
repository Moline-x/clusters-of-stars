package com.mst.csgateway.constant.enums.urls;

/**
 * @author Molin
 * @date 2021/12/30  14:57
 * class description: 用户资源
 */
public enum Resource2User {

    /**
     * 用户资源.
     */
    LOGIN(ResourceBuilder.NORMAL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.USER.symbol()),
            CommonResource.WILDCARD.apply(CommonResource.PREFIX.symbol()+"login")
    ));

    private final String uri;

    Resource2User(String uri) {
        this.uri = uri;
    }

    public String uri() {
        return uri;
    }
}
