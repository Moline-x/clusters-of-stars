package com.mst.csgateway.constant.enums.urls;

/**
 * @author Molin
 * @date 2021/12/30  14:46
 * class description: 资源构建者
 */
enum ResourceBuilder {

    /**
     * 路径构建.
     */
    FULL_PATH_BUILDER {
        @Override
        public String buildPath(String prefix, String context) {
            return prefix + context;
        }
    },
    NORMAL_PATH_BUILDER {
        @Override
        public String buildPath(String prefix, String context) {
            return prefix + prefix + context;
        }
    };

    public abstract String buildPath(String prefix, String context);
}
