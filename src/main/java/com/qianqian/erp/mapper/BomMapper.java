package com.qianqian.erp.mapper;

import com.qianqian.erp.web.entity.Bom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description 开庭公告--Mapper
 * @Author: fk
 * @Date: 2020/2/29 13:15
 */
@Mapper
public interface BomMapper {

    /**
     * @Author: fk
     * @Description: 查询所有数据
     * @Date: 13:32 2020/2/29
     * @Param: []
     * @return: java.util.List<com.spider.web.entity.Bom>
     **/
    List<Bom> findAll();

    /**
     * @Author: fk
     * @Description: 批量插入数据
     * @Date: 14:22 2020/2/29
     * @Param: [list]
     * @return: int
     **/
    int batchInsert(List<Bom> list);

}
