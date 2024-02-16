package com.cz.czapi.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cz.czapi.common.ErrorCode;
import com.cz.czapi.exception.BusinessException;
import com.cz.czapi.mapper.UserMapper;
import com.cz.czapicommon.model.entity.User;
import com.cz.czapicommon.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getInvokeUser(String accessKsy, String secretKey) {
        if(StringUtils.isAnyBlank(accessKsy,secretKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccessKey,accessKsy);
        queryWrapper.eq(User::getSecretKey,secretKey);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
