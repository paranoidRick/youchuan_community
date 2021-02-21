package com.youchuan.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youchuan.community.common.api.ApiResult;
import com.youchuan.community.model.entity.BmsBillboard;
import com.youchuan.community.model.entity.BmsTip;
import com.youchuan.community.service.IBmsBillboardService;
import com.youchuan.community.service.IBmsTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController{

    @Autowired
    private IBmsTipService bmsTipService;

    @GetMapping("/today")
    public ApiResult<BmsTip> getRandomTip(){
        BmsTip tip = bmsTipService.getRandomTip();
        return ApiResult.success(tip);
    }

}
