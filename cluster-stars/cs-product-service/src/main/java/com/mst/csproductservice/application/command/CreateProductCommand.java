package com.mst.csproductservice.application.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Molin
 * @date 2021/12/24  17:31
 * class description: 创建商品命令.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductCommand {

    private String name;
    private String currencyCode;
    private BigDecimal price;
    private Integer categoryId;
    private String remark;
    private Boolean allowAcrossCategory;

    public static CreateProductCommand of(String name, String currencyCode, BigDecimal price, Integer categoryId,
                                          String remark, Boolean allowAcrossCategory) {
        return new CreateProductCommand(name, currencyCode, price, categoryId, remark, allowAcrossCategory);
    }
}
