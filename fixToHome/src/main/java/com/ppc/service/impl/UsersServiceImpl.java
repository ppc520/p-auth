package com.ppc.service.impl;

import com.ppc.entity.Users;
import com.ppc.mapper.UsersMapper;
import com.ppc.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ppc
 * @since 2023-09-07
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
