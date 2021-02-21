package com.youchuan.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youchuan.community.model.dto.RegisterDTO;
import com.youchuan.community.model.entity.UmsUser;

public interface IUmsUserService extends IService<UmsUser> {

    /**
     * 注册
     */
    UmsUser executeRegister(RegisterDTO dto);
}
