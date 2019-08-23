package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UserService;
import com.team.house.util.MD5Utils;
import com.team.house.util.Page;
import com.team.house.util.UserConditioin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getAllRUsers(UserConditioin userConditioin) {
        //开启分页
        PageHelper.startPage(userConditioin.getPage(),userConditioin.getRows());
        //创建条件
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria =usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));  //注册用户
        //传入查询条件
        //判断名称
        if(userConditioin.getName()!=null){
            criteria.andNameLike("%"+userConditioin.getName()+"%");
        }
        //判断电话
        if(userConditioin.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userConditioin.getTelephone()+"%");
        }

        //执行
        List<Users>  list=usersMapper.selectByExample(usersExample);

        PageInfo<Users> pageInfo=new PageInfo<Users>(list);
        return pageInfo;
    }

    @Override
    public int regUser(Users users) {
        //设置注册用户
        users.setIsadmin(new Integer("0"));
        //采用md5对用户输入的密码进行加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    @Override
    public int isUserNameExists(String name) {
        return usersMapper.getUserCount(name);
    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //指定注册用户
        //criteria.andIsadminNotEqualTo(0);
        //指定用户名密码
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));//加密
        //执行
         List<Users>  list=usersMapper.selectByExample(usersExample);
         if(list.size()==0)
             return null;
          else
            return list.get(0);
    }
}
