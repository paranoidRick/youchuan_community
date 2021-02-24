package com.youchuan.community.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youchuan.community.mapper.BmsTagMapper;
import com.youchuan.community.mapper.BmsTopicMapper;
import com.youchuan.community.model.entity.BmsPost;
import com.youchuan.community.model.entity.BmsTag;
import com.youchuan.community.model.entity.BmsTopicTag;
import com.youchuan.community.model.vo.PostVO;
import com.youchuan.community.service.IBmsPostService;
import com.youchuan.community.service.IBmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IBmsPostServiceImpl extends ServiceImpl<BmsTopicMapper, BmsPost> implements IBmsPostService {

    @Resource
    private BmsTagMapper bmsTagMapper;
    @Autowired
    private IBmsTopicTagService IBmsTopicTagService;
    @Override
    public Page<PostVO> getList(Page<PostVO> page,String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = IBmsTopicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
        return iPage;
    }
}
