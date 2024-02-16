package com.cz.czapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.czapicommon.model.entity.InterfaceInfo;

/**
* @author 李钟意
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-02-10 21:05:27
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     * @param interfaceInfo
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
