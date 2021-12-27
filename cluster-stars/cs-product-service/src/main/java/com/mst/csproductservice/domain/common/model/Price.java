package com.mst.csproductservice.domain.common.model;

import com.mst.csproductservice.constant.ProductConstant;
import com.mst.csproductservice.domain.exception.InvalidParameterException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Molin
 * @date 2021/12/24  17:01
 * class description: 值对象——售价
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Price implements Serializable {

    /**
     * 货币.
     */
    @Column(name = "currency_code", length = 3)
    private Currency currency;

    /**
     * 价格.
     */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    /**
     * 价格构建方法.
     * @param  currencyCode currency code
     * @param  value        value
     * @return Price
     */
    public static Price of(String currencyCode, BigDecimal value) {
        checkArgument(!StringUtils.isEmpty(currencyCode), ProductConstant.CURRENCY_NOT_NULL);
        Currency currency;
        try {
            currency = Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(String.format("【%s】"+ProductConstant.CURRENCY_NOT_INVALID+"", currencyCode));
        }
        checkArgument(value != null, ProductConstant.PRICE_NOT_NULL);
        checkArgument(value.compareTo(BigDecimal.ZERO) > 0, ProductConstant.PRICE_LARGER_ZERO);
        return new Price(currency, value);
    }
}
