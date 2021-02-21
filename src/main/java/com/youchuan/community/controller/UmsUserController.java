package com.youchuan.community.controller;

import com.youchuan.community.common.api.ApiResult;
import com.youchuan.community.model.dto.RegisterDTO;
import com.youchuan.community.model.entity.UmsUser;
import com.youchuan.community.service.impl.IUmsUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ApiResult register(@RequestBody RegisterDTO registerDTO) {
        UmsUser register = umsUserService.executeRegister(registerDTO);
        return ApiResult.success(register);
    }

}
