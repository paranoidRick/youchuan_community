package com.youchuan.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youchuan.community.common.api.ApiResult;
import com.youchuan.community.model.vo.PostVO;
import com.youchuan.community.service.IBmsPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController{
    @Resource
    private IBmsPostService iBmsPostService;
    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

}
