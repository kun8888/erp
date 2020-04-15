package com.qianqian.erp.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description excelvo类
 * @Author: fk
 * @Date: 2020/3/1 12:32
 */
@Data
public class ExcelVo {

    private String excelName;
    private List<ExcelSheetVo> sheets;
}
