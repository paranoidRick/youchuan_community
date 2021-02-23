package com.youchuan.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youchuan.community.common.exception.ApiAsserts;
import com.youchuan.community.jwt.JwtUtil;
import com.youchuan.community.mapper.UmsUserMapper;
import com.youchuan.community.model.dto.LoginDTO;
import com.youchuan.community.model.dto.RegisterDTO;
import com.youchuan.community.model.entity.UmsUser;
import com.youchuan.community.service.IUmsUserService;
import com.youchuan.community.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class IUmsUserServiceImpl extends ServiceImpl<UmsUserMapper,UmsUser> implements IUmsUserService {

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Override
    public UmsUser executeRegister(RegisterDTO dto) {
        // 查询是否有相同的用户名
        String userName = dto.getName();
        String email = dto.getEmail();
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUser::getUsername, userName)
                .or()
                .eq(UmsUser::getEmail, userName);
        UmsUser umuser = this.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(umuser)) {
            ApiAsserts.fail("账号或邮箱已存在");
        }

        // 否则注册
        UmsUser umsUser = UmsUser.builder()
                .username(userName)
                .alias(userName)
                .password(MD5Utils.getPwd(dto.getPass()))
                .email(email)
                .createTime(new Date())
                .status(true)
                .build();

        umsUserMapper.insert(umsUser);
        return umsUser;
    }

    @Override
    public UmsUser getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, username));
    }

    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            UmsUser user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if(!encodePwd.equals(user.getPassword()))
            {
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
        } catch (Exception e) {
            log.warn("用户不存在or密码验证失败=======>{}", dto.getUsername());
        }
        return token;
    }
}
