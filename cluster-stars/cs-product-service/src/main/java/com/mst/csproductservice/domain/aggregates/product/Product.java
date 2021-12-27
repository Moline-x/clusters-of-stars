package com.mst.csproductservice.domain.aggregates.product;

import com.google.common.base.Objects;
import com.mst.csproductservice.application.command.CreateProductCommand;
import com.mst.csproductservice.constant.ProductConstant;
import com.mst.csproductservice.domain.aggregates.product.valueobject.ProductNumber;
import com.mst.csproductservice.domain.aggregates.product.valueobject.enums.ProductStatusEnum;
import com.mst.csproductservice.domain.common.BaseEntity;
import com.mst.csproductservice.domain.common.model.Price;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Molin
 * @date 2021/12/24  9:15
 * class description: 商品聚合根
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product extends BaseEntity {

    /**
     * 主键，id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品编码.
     */
    @Column(name = "product_no", length = 32, nullable = false, unique = true)
    @Type(type = "com.mst.csproductservice.domain.aggregates.product.converter.ProductNumber")
    private ProductNumber productNo;

    /**
     * 商品名称.
     */
    @Column(name = "product_name", length = 64, nullable = false)
    private String name;

    /**
     * 商品价格.
     */
    @Embedded
    private Price price;

    /**
     * 分类id.
     */
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    /**
     * 商品状态.
     */
    @Column(name = "product_status", nullable = false)
    private ProductStatusEnum productStatus;

    /**
     * 备注.
     */
    @Column(name = "remark", length = 256)
    private String remark;

    /**
     * 是否允许跨类目.
     */
    @Column(name = "allow_across_category", nullable = false)
    private Boolean allowAcrossCategory;

    /**
     * 构建商品.
     * @param  command create product command
     * @return Product
     */
    public static Product of(CreateProductCommand command) {

        Integer categoryId = command.getCategoryId();
        checkArgument(!StringUtils.isEmpty(command.getName()), ProductConstant.PRODUCT_NAME_NOT_NULL);
        checkArgument(categoryId != null, ProductConstant.CATEGORY_NOT_NULL);
        checkArgument(categoryId > 0, ProductConstant.CATEGORY_LARGER_ZERO);
        checkArgument(categoryId < 10000, ProductConstant.CATEGORY_LESSER_TEN_K);
        checkArgument(command.getAllowAcrossCategory() != null, ProductConstant.ALLOW_ACROSS_CATEGORY_NOT_NULL);
        ProductNumber newProductNo = ProductNumber.of(categoryId);
        ProductStatusEnum defaultStatus = ProductStatusEnum.DRAFTED;
        Price priceWithCurrency = Price.of(command.getCurrencyCode(), command.getPrice());
        return new Product(null, newProductNo, command.getName(), priceWithCurrency,
                categoryId, defaultStatus, command.getRemark(), command.getAllowAcrossCategory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equal(productNo, product.productNo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productNo);
    }
}
