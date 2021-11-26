package com.mst.csuserservice.domain.model;

import com.mst.csuserservice.domain.enums.AccountCategory;
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
 * @date 2021/11/26  9:32
 * class description: 账户实体
 */
@Table(name = "t_account")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account extends BaseEntity {

    /**
     * 主键，账户ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户Id.
     */
    private Long userId;

    /**
     * 登录账号.
     */
    private String openCode;

    /**
     * 账号类别.
     */
//    @Enumerated(EnumType.ORDINAL)
    private Integer category;

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
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
