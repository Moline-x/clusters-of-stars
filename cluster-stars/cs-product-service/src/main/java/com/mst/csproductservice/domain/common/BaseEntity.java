package com.mst.csproductservice.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Molin
 * @date 2021/12/24  9:18
 * class description: 商品领域基础类
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public class BaseEntity implements Serializable {

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
