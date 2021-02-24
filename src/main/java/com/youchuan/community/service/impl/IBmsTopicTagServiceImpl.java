package com.youchuan.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youchuan.community.mapper.BmsTopicTagMapper;
import com.youchuan.community.model.entity.BmsTopicTag;
import com.youchuan.community.service.IBmsTopicTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class) // 当方法出现异常的情况录入数据库的数据会进行事务回滚, 数据回到原里的样子
public class IBmsTopicTagServiceImpl extends ServiceImpl<BmsTopicTagMapper, BmsTopicTag> implements IBmsTopicTagService {
    @Override
    public List<BmsTopicTag> selectByTopicId(String topicId) {
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsTopicTag::getTopicId, topicId);
        return this.baseMapper.selectList(wrapper);
    }
}
