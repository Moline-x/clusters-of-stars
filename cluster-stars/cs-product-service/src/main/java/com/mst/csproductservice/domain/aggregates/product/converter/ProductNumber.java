package com.mst.csproductservice.domain.aggregates.product.converter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

/**
 * @author Molin
 * @date   2021-12-24 19:44
 * 转换Product number.
 */
public class ProductNumber extends AbstractSingleColumnStandardBasicType<com.mst.csproductservice.domain.aggregates.product.valueobject.ProductNumber> {
    public ProductNumber() {
        super(new VarcharTypeDescriptor(), new ProductNumberDescriptor());
    }

    @Override
    public String getName() {
        return "ProductNumber";
    }

    @Override
    public Object resolve(Object value, SharedSessionContractImplementor session, Object owner, Boolean overridingEager) throws HibernateException {
        return null;
    }

}
