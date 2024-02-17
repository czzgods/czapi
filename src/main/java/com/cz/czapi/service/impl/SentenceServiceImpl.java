package com.cz.czapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.czapi.mapper.SentenceMapper;
import com.cz.czapi.model.entity.Sentence;
import com.cz.czapi.service.SentenceService;
import org.springframework.stereotype.Service;

/**
* @author 李钟意
* @description 针对表【sentence(毒鸡汤)】的数据库操作Service实现
* @createDate 2024-02-17 10:04:35
*/
@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper, Sentence>
    implements SentenceService {

    @Override
    public Sentence getSentence(long id) {
        LambdaQueryWrapper<Sentence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Sentence::getId,id);
        Sentence sentence = getOne(queryWrapper);
        return sentence;
    }
}




