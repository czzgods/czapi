package com.cz.czapicommon.service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.cz.czapicommon.model.entity.User;


/**
 * 用户服务
 *
 * @author cz
 */
public interface InnerUserService {
    /**
     * 数据库中查询已分配给用户秘钥
     * @param accessKsy
     * @param secretKey
     * @return
     */
    User getInvokeUser(String accessKsy, String secretKey);
}
