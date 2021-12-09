package com.mst.csuserservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Molin
 * @date 2021/11/29  21:15
 * class description: 用户领域权限实体
 */
@Table(name = "permission")
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permission extends BaseEntity {

    /**
     * 主键.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父级权限Id.
     */
    private Long parentId;

    /**
     * 权限码.
     */
    private String code;

    /**
     * 权限名称.
     */
    private String name;

    /**
     * 权限介绍.
     */
    private String introduction;

    /**
     * 权限分类.
     */
    private Integer category;

    /**
     * url规则.
     */
    private Long uri;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Permission that = (Permission) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
