package com.mst.csuserservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Molin
 * @date 2021/11/29  20:11
 * class description: 用户角色关系实体.
 */
@Table(name = "user_role")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserRole extends BaseEntity {

    /**
     * 主键.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户Id.
     */
    private Long userId;

    /**
     * 角色Id.
     */
    private Long roleId;

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
        UserRole userRole = (UserRole) o;
        return id != null && Objects.equals(id, userRole.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
