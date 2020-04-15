package com.qianqian.erp.web.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description 开庭公告
 * @Author: fk
 * @Date: 2020/2/29 12:07
 */
@Data
public class Bom {

    private String id;// id
    private String col1;// 字段1
    private String col2;// 字段2
    private String col3;// 字段3
    private String product_code;// 部品编号
    private String product_name;// 部品名称
    private String machine_type;// 机型
    private String texture;// 材质
    private String specification;// 规格
    private String goushu;// 构数
    private String need_count;// 机器所需数量
    private String remark;// 备注
    private String technology;// 工程
    private String supplier;// 外协
    private Date createTime;// 创建时间

}
