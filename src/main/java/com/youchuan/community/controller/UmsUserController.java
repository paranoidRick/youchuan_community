package com.youchuan.community.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.youchuan.community.common.api.ApiResult;
import com.youchuan.community.model.dto.LoginDTO;
import com.youchuan.community.model.dto.RegisterDTO;
import com.youchuan.community.model.entity.UmsUser;
import com.youchuan.community.service.impl.IUmsUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.youchuan.community.jwt.JwtUtil.USER_NAME;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ums/user")
public class UmsUserController {

    @Autowired
    private IUmsUserServiceImpl iUmsUserService;

    /**
     * 注册
     *
     * @param registerDTO 接收参数
     * @return
     */
    @PostMapping("/register")
    private ApiResult<Map<String,Object>> register(@Valid @RequestBody RegisterDTO registerDTO) {
        UmsUser user = iUmsUserService.executeRegister(registerDTO);
        if(ObjectUtil.isEmpty(user))
            return ApiResult.failed("账号注册失败!");
        Map<String,Object> map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);
    }


    @PostMapping("/login")
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = iUmsUserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "登录成功");
    }

    @GetMapping("/info")
    public ApiResult<UmsUser> getUser(@RequestHeader(value = USER_NAME) String userName) {
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

}
