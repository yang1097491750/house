package com.team.house.util;

import com.team.house.util.Page;


//用户查询条件实
public class UserConditioin  extends Page {
   private String name;
   private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
