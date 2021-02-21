package com.youchuan.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youchuan.community.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
