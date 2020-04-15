package com.qianqian.erp.web.controller;

import com.qianqian.erp.vo.ExcelTypeVo;
import com.qianqian.erp.vo.ResultMap;
import com.qianqian.erp.web.service.AnalysisExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 解析bomexcel--controller
 * @Author: fk
 * @Date: 2020/2/29 14:00
 */
@CrossOrigin(origins = "localhost", maxAge = 3600)
@Controller
@RequestMapping("/")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AnalysisExcelService analysisExcelService;

    /**
     * @Author: fk
     * @Description: 跳转首页
     * @Date: 18:36 2020/3/2
     * @Param: []
     * @return: java.lang.String
     **/
    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }

    /**
     * @Author: fk
     * @Description: 根据输入的路径解析Bomexcel并入库
     * @Date: 16:56 2020/2/29
     * @Param: []
     * @return: java.lang.String
     **/
    @RequestMapping("/analysisExcel")
    @ResponseBody
    public Object analysisExcel(ExcelTypeVo vo) {
        try {
            ResultMap result = analysisExcelService.analysisExcel(vo);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return "解析出错！";
        }
    }

}
