package com.mst.csproductservice.domain.aggregates.product;

import com.google.common.base.Objects;
import com.mst.csproductservice.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

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
    private String productNo;

    /**
     * 商品名称.
     */
    @Column(name = "product_name", length = 64, nullable = false)
    private String name;

    /**
     * 商品价格.
     */
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * 分类id.
     */
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    /**
     * 商品状态.
     */
    @Column(name = "product_status", nullable = false)
    private Integer productStatus;

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
     * @param  productNo            product no
     * @param  name                 product name
     * @param  price                product price
     * @param  categoryId           category id
     * @param  productStatus        product status
     * @param  remark               remark
     * @param  allowAcrossCategory  allow across category or not
     * @return Product
     */
    public static Product of(String productNo, String name, BigDecimal price, Integer categoryId
            , Integer productStatus, String remark, Boolean allowAcrossCategory) {
        return new Product(null, productNo, name, price, categoryId, productStatus, remark, allowAcrossCategory);
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
