package com.cz.czapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.czapi.model.entity.Sentence;
import com.cz.czapi.service.impl.SentenceService;
import com.cz.czapi.mapper.SentenceMapper;
import org.springframework.stereotype.Service;

/**
* @author 李钟意
* @description 针对表【sentence(毒鸡汤)】的数据库操作Service实现
* @createDate 2024-02-17 10:04:35
*/
@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper, Sentence>
    implements SentenceService{

}




