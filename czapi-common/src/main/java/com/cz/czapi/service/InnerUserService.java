package com.cz.czapi.service;


import com.cz.czapi.model.entity.User;


/**
 * 用户服务
 *
 * @author cz
 */
public interface InnerUserService {
    /**
     * 数据库中查询已分配给用户秘钥
     * @param accessKsy
     * @return
     */
    User getInvokeUser(String accessKsy);
}
