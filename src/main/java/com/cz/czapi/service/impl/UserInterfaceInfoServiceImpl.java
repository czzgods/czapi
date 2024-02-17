package com.cz.czapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.czapi.common.ErrorCode;
import com.cz.czapi.exception.BusinessException;
import com.cz.czapi.mapper.UserInterfaceInfoMapper;
import com.cz.czapi.service.UserInterfaceInfoService;
import com.cz.czapi.model.entity.UserInterfaceInfo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
* @author 李钟意
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-02-13 20:50:43
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Resource
    private RedissonClient redissonClient;
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (add) {
            if(userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <=0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if(userInterfaceInfo.getLeftNum() < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public boolean invokeCount(long interfaceId, long userId) {
        //判断
        if(interfaceId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //分布式锁
        RLock lock = redissonClient.getLock("ADD_INVOKE_COUNT");
        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(0, -1, TimeUnit.MILLISECONDS);
            if(tryLock) {
                LambdaUpdateWrapper<UserInterfaceInfo> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceId);
                updateWrapper.eq(UserInterfaceInfo::getUserId, userId);
                updateWrapper.gt(UserInterfaceInfo::getLeftNum,0);
                updateWrapper.setSql("leftNum = leftNum - 1 , totalNum = totalNum + 1");
                return this.update(updateWrapper);
            }
        } catch (InterruptedException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        } finally {
            if(lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }
}




