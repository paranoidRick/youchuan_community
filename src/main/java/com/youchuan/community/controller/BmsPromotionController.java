package com.youchuan.community.controller;

import com.youchuan.community.common.api.ApiResult;
import com.youchuan.community.model.entity.BmsPromotion;
import com.youchuan.community.service.IBmsPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController{

    @Autowired
    private IBmsPromotionService bmsPromotionService;

    @GetMapping("/list")
    public ApiResult getPromotion(){
        List<BmsPromotion> list =bmsPromotionService.list();
        return ApiResult.success(list);
    }

}
