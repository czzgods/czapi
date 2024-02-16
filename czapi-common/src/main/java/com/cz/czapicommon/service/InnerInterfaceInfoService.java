package com.cz.czapicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.czapicommon.model.entity.InterfaceInfo;

/**
* @author 李钟意
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-02-10 21:05:27
*/
public interface InnerInterfaceInfoService {
    /**
     * 从数据库中查询接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path,String method);
}
