package com.mst.csuserservice.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Molin
 * @date   2021-11-19  22:20
 * 用户领域基础实体
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 9160459103011557025L;

    /**
     * 创建时间.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    @JsonIgnore
    private Date created;

    /**
     * 更新时间.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    @JsonIgnore
    private Date edited;

//    /**
//     * 版本号.
//     */
//    @Version
//    private Long version;

    /**
     * 回调创建时间.
     */
    @PrePersist
    public void preCreate() {
        created = new Date();
        edited = created;
    }

    /**
     * 回调更新时间.
     */
    @PreUpdate
    public void preUpdate() {
        edited = new Date();
    }

}
