package com.youchuan.community.controller;

import cn.hutool.core.util.ObjectUtil;
import com.youchuan.community.common.api.ApiResult;
import com.youchuan.community.model.dto.RegisterDTO;
import com.youchuan.community.model.entity.UmsUser;
import com.youchuan.community.service.impl.IUmsUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/user")
public class UmsUserController {

    @Autowired
    private IUmsUserServiceImpl umsUserService;

    /**
     * 注册
     *
     * @param registerDTO 接收参数
     * @return
     */
    @PostMapping("/register")
    private ApiResult<Map<String,Object>> register(@Valid @RequestBody RegisterDTO registerDTO) {
        UmsUser user = umsUserService.executeRegister(registerDTO);
        if(ObjectUtil.isEmpty(user))
            return ApiResult.failed("账号注册失败!");
        Map<String,Object> map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);
    }

}
