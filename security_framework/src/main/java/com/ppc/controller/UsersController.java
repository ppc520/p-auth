package com.ppc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ppc.common.result.R;
import com.ppc.entity.Users;
import com.ppc.entity.vo.UsersVo;
import com.ppc.service.UsersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ppc
 * @since 2023-09-04
 */
@RestController
@Api
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @PostMapping("/login")
    public R login(@RequestBody UsersVo usersVo, HttpServletRequest request, HttpServletResponse response){
        QueryWrapper<Users> qw=new QueryWrapper<>();
        qw.eq("username",usersVo.getUsername())
                        .eq("password",usersVo.getPassword());
        Users one = usersService.getOne(qw);
        if (ObjectUtils.isEmpty(one)){
            response.setStatus(401);
            return R.error();
        }else {
            return new R().data("token",5201314).code(20000);
        }

    }
}

