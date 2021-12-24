package com.mst.csproductservice.domain.aggregates.product.valueobject;

import com.mst.csproductservice.constant.ProductConstant;
import com.mst.csproductservice.domain.aggregates.product.valueobject.enums.ProductNoEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Molin
 * @date 2021/12/24  14:50
 * class description: 值对象——商品编码
 */
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductNumber implements Serializable {

    /**
     * 编码.
     */
    private String value;

    /**
     * 构建商品编码.
     * @param  value 编码
     * @return product number
     */
    public static ProductNumber of(String value) {
        checkArgument(!StringUtils.isEmpty(value), ProductConstant.PRODUCT_NO_NOT_NULL);
        return new ProductNumber(value);
    }

    /**
     * 按策略生成编码.
     * @param  categoryId category id
     * @return product number
     */
    public static ProductNumber of(Integer categoryId) {
        checkArgument(categoryId != null, ProductConstant.CATEGORY_NOT_NULL);
        checkArgument(categoryId > 0, ProductConstant.CATEGORY_LARGER_ZERO);
        return new ProductNumber(generateProductNo(categoryId));
    }

    /**
     * 编码生成策略.
     * @param  categoryId 分类id
     * @return product number
     */
    private static String generateProductNo(Integer categoryId) {

        StringBuilder sb = new StringBuilder(ProductConstant.PREFIX_PRODUCT);
        sb.append(String.format(ProductConstant.TYPE_FORMAT, categoryId));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(ProductConstant.PRODUCT_DATE_FORMAT);
        sb.append(dtf.format(LocalDateTime.now()));
        int random = ThreadLocalRandom.current().nextInt(ProductNoEnum.PRODUCT_NO_SEED.getCode());
        sb.append(String.format(ProductConstant.TYPE_FORMAT, random));
        return sb.toString();
    }
}
