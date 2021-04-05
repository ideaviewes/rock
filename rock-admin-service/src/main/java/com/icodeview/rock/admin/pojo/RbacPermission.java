package com.icodeview.rock.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * null
 * @TableName rbac_permission
 */
@TableName(value ="rbac_permission")
@Data
public class RbacPermission implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String icon;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private Integer priority;

    /**
     * 
     */
    private LocalDateTime createdAt;

    /**
     * 
     */
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}