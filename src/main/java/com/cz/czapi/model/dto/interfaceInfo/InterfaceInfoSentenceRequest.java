package com.cz.czapi.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 *
 * @TableName product
 */
@Data
public class InterfaceInfoSentenceRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;
    private static final long serialVersionUID = 1L;
}