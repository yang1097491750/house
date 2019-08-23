package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.util.Page;

import java.util.List;


public interface StreetService {
    /**
     * //通过区域查询对应街道
     * @param did  区域编号
     * @return
     */
    public PageInfo<Street> getStreetByDistrict(Integer did, Page page);

    /**
     * //通过区域查询对应街道
     * @param did 区域编号
     *@return
     */
    public List<Street> getStreetByDistrict(Integer did);
}
