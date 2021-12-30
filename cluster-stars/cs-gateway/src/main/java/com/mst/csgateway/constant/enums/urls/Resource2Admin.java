package com.mst.csgateway.constant.enums.urls;

/**
 * @author Molin
 * @date 2021/12/30  13:47
 * class description: 管理员资源
 */
public enum Resource2Admin {

    /**
     * 管理员资源
     */
    FULL_PATH(ResourceBuilder.FULL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.SUPER_ADMIN.symbol()),
            CommonResource.WILDCARD.apply(CommonResource.FULL.symbol())
    )),

    BUILD(ResourceBuilder.NORMAL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.SUPER_ADMIN.symbol()),
            CommonResource.WILDCARD.apply(CommonResource.PREFIX.symbol()+"create")
    )),

    REMOVE(ResourceBuilder.NORMAL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.SUPER_ADMIN.symbol()),
            CommonResource.WILDCARD.apply(CommonResource.PREFIX.symbol()+"remove")
    )),

    UPDATE(ResourceBuilder.NORMAL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.SUPER_ADMIN.symbol()),
            CommonResource.WILDCARD.apply(CommonResource.PREFIX.symbol()+"update")
    )),

    GET(ResourceBuilder.NORMAL_PATH_BUILDER.buildPath(
            CommonResource.PREFIX.apply(CommonResource.SUPER_ADMIN.symbol()),
            CommonResource.WILDCARD.apply(
                    CommonResource.PREFIX.symbol()+"get"+CommonResource.FULL.symbol()
            )
    ));

    private final String uri;

    Resource2Admin(String uri) {
        this.uri = uri;
    }

    public String uri() {
        return uri;
    }
}
