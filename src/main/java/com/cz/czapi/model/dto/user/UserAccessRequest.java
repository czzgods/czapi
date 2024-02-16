package com.cz.czapi.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更换签名
 *
 * @author cz
 */
@Data
public class UserAccessRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}