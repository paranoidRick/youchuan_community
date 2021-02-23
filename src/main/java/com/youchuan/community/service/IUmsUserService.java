package com.youchuan.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youchuan.community.model.dto.LoginDTO;
import com.youchuan.community.model.dto.RegisterDTO;
import com.youchuan.community.model.entity.UmsUser;

public interface IUmsUserService extends IService<UmsUser> {

    /**
     * 注册
     */
    UmsUser executeRegister(RegisterDTO dto);

    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    UmsUser getUserByUsername(String username);
    /**
     * 用户登录
     *
     * @param dto
     * @return 生成的JWT的token
     */
    String executeLogin(LoginDTO dto);
}
