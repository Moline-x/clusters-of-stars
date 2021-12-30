package com.mst.csgateway.constant.enums.urls;

/**
 * @author Molin
 * @date 2021/12/30  13:53
 * class description: 通用资源
 */
public enum CommonResource {

    /**
     * 通用资源.
     */
    PREFIX("/"){
        @Override
        public String apply(String connectionString) {
            return connectionString;
        }
    },
    WILDCARD("/"){
        @Override
        public String apply(String connectionString) {
            return connectionString;
        }
    },
    FULL("/**"){
        @Override
        public String apply(String connectionString) {
            return connectionString;
        }
    },
    SUPER_ADMIN("/sys_user"){
        @Override
        public String apply(String connectionString) {
            return connectionString;
        }
    },
    USER("/user"){
        @Override
        public String apply(String connectionString) {
            return connectionString;
        }
    },
    PRODUCT("/product"){
        @Override
        public String apply(String connectionString) {
            return connectionString;
        }
    };

    private final String symbol;

    CommonResource(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public abstract String apply(String connectionString);
}
