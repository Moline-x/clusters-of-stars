package com.mst.csproductservice.domain.aggregates.product.converter;

import com.mst.csproductservice.domain.aggregates.product.valueobject.ProductNumber;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;
import org.hibernate.type.descriptor.spi.JdbcRecommendedSqlTypeMappingContext;

/**
 * @author Molin
 * @date   2021-12-24 19:46
 * 适配器.
 */
public class ProductNumberDescriptor extends AbstractTypeDescriptor<ProductNumber> {

    protected ProductNumberDescriptor() {
        super(ProductNumber.class, new ImmutableMutabilityPlan<>());
    }

    @Override
    public String toString(ProductNumber value) {
        return null;
    }

    @Override
    public ProductNumber fromString(String string) {
        return null;
    }

    @Override
    public <X> X unwrap(ProductNumber value, Class<X> type, WrapperOptions options) {
        return null;
    }

    @Override
    public <X> ProductNumber wrap(X value, WrapperOptions options) {
        return null;
    }

    @Override
    public SqlTypeDescriptor getJdbcRecommendedSqlType(JdbcRecommendedSqlTypeMappingContext context) {
        return null;
    }


}
