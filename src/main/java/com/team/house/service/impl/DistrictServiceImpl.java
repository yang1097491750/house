package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)  //挂起事务,不其于事务执行
    public PageInfo<District> getAllDistrict(Page page) {
        //1.开启分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //2.查询所有
        //封装条件实体类
        DistrictExample districtExample=new DistrictExample();
        List<District> list=districtMapper.selectByExample(districtExample);
        //3.获取分页相关的属性
        PageInfo<District> pageInfo=new PageInfo<District>(list);
        return pageInfo;
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }


    @Override
    @Transactional
    public int delDistrict(Integer id) {
       // try {
            //删除区域的同时，删除街道   先删除外键，再删除主键
            //1.删除区域下的街道     DELETE FROM street WHERE district_id=id
            streetMapper.delStreetByDistrict(id);

           // int i=1,j=0;
           // i=i/j;

            //2.删除区域
            districtMapper.deleteByPrimaryKey(id);
       // }catch (Exception e) {
       //     System.out.println(e);
        //    return -1;
       // }
        return 1;
    }

    @Override
    public int delMoreDistrict(Integer[] ids) {
        return districtMapper.delMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
