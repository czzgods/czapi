package com.cz.czapicommon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.czapicommon.model.entity.UserInterfaceInfo;

/**
* @author 李钟意
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-02-13 20:50:43
*/
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     * @param interfaceId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceId,long userId);
}
