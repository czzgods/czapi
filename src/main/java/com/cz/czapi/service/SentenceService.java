package com.cz.czapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.czapi.model.entity.Sentence;

/**
* @author 李钟意
* @description 针对表【sentence(毒鸡汤)】的数据库操作Service
* @createDate 2024-02-17 10:04:35
*/
public interface SentenceService extends IService<Sentence> {
    Sentence getSentence(long id);
}
