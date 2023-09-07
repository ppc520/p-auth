package com.ppc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ppc
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("createdAt")
    private Date createdAt;

    @TableField("updatedAt")
    private Date updatedAt;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;


}
