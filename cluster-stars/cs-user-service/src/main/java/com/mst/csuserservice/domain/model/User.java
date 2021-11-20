package com.mst.csuserservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.domain.enums.UserState;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Molin
 * @date   2021-11-19  22:30
 * 用户实体
 */
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity {

    /**
     * id，唯一标识.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名.
     */
    @NotNull(message = UserConstant.NAME_NOT_NULL)
    private String name;

    /**
     * 密码.
     */
    @NotNull(message = UserConstant.PWD_NOT_NULL)
    private String password;

    /**
     * 头像地址.
     */
    private String headImgUrl;

    /**
     * 电话.
     */
    private String mobile;

    /**
     * 密码加盐.
     */
    @JsonIgnore
    private String salt;

    /**
     * 用户状态.
     */
    @Enumerated(EnumType.ORDINAL)
    private UserState state;

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
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
