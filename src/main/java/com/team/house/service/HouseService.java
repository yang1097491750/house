package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;

/**
 * Created by acer on 2019/8/19.
 */
public interface HouseService {
    //发布出租房信息
    public int addHouse(House house);

    //查询用户发布的出租房
    public PageInfo<House> getHouseByUser(Page page,Integer userid);

    //查询单条出租房信息
    public House getHouse(String id);

    //修改出租房信息
    public int updateHouse(House house);

    /**
     * 修改出租房的状态
     * 删除出租房  状态传1
     * 恢复出租房  状态传0
     * @param id  出租房编号
     * @param state  状态信息
     * @return 影响行数
     */
   public int delHouseState(String id,Integer state);

    /**
     * 通过审核状态查询出租房信息
     *  状态传1 表示已审核
     * 状态传0 表示未审核
     * @return 影响行数
     */
    public PageInfo<House> getHouseByIsPass(Page page,Integer state);

    /**
     * 修改出租房的审核状态
     * 通过审核  状态传1
     * 消息审核  状态传0
     * @param id  出租房编号
     * @param state  状态信息
     * @return 影响行数
     */
    public int PassHouse(String id,Integer state);

    /**
     * 浏览出租房信息
     * @param condition  查询条件
     * @return  所有出租房
     */
    public PageInfo<House> getHouseBySearch(HouseCondition condition);
}
