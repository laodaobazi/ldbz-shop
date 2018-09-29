package cn.ldbz.admin.vo;


import cn.ldbz.pojo.TbManageUser;

import java.text.SimpleDateFormat;

/**
 * 后台用户展示VO
 */
public class ManageUserVO extends TbManageUser {

    private String joinus;

    public String getJoinus() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy MM");

        return format.format(this.getCreated()).toString() + "加入公司";
    }

}
