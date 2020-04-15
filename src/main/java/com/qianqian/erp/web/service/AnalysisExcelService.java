package com.qianqian.erp.web.service;

import cn.hutool.core.date.DateUtil;
import com.qianqian.erp.mapper.BomMapper;
import com.qianqian.erp.util.excel.ExcelUtil;
import com.qianqian.erp.vo.ExcelTypeVo;
import com.qianqian.erp.vo.ResultMap;
import com.qianqian.erp.web.entity.Bom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 解析bom -- service
 * @Author: fk
 * @Date: 2020/3/2 18:35
 */
@Service
@Transactional
public class AnalysisExcelService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BomMapper bomMapper;

    /**
     * @Author: fk
     * @Description: 根据类型解析excel入库
     * @Date: 23:39 2020/3/13
     * @Param: [vo]
     * @return: com.qianqian.erp.vo.ResultMap
     **/
    public ResultMap analysisExcel(ExcelTypeVo vo) throws Exception {
        ResultMap result = new ResultMap();
        File file = new File(vo.getFilePath());
        if (!file.exists()) {
            result.setSuccess(false);
            result.setMessage("文件不存在！");
            return result;
        }
        List<Map<String, String>> datas = ExcelUtil.analysisExcel(file);
        if ("1".equals(vo.getType())) {// bom
            result = analysisBomDatas(datas);
        }
        return result;
    }

    /**
     * @Author: fk
     * @Description: 解析bom数据保存入库
     * @Date: 23:42 2020/3/13
     * @Param: [datas]
     * @return: com.qianqian.erp.vo.ResultMap
     **/
    public ResultMap analysisBomDatas(List<Map<String, String>> datas) {
        ResultMap resultMap = new ResultMap();
        try {
            logger.info(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  开始爬取数据");
            try {
                List<Bom> list = new ArrayList<>();
                for (Map<String, String> map : datas) {
                    Bom bom = new Bom();
                    bom.setCol1(map.get("0"));
                    bom.setCol2(map.get("1"));
                    if(null != map.get("2")) {
                        bom.setCol3(map.get("2") + "," + map.get("3"));
                    } else {
                        bom.setCol3(map.get("3"));
                    }
                    bom.setProduct_code(map.get("4"));
                    bom.setProduct_name(map.get("5"));
                    bom.setMachine_type(map.get("6"));
                    bom.setTexture(map.get("7"));
                    bom.setSpecification(map.get("8"));
                    bom.setGoushu(map.get("9"));
                    bom.setNeed_count(map.get("10"));
                    bom.setRemark(map.get("25"));
                    bom.setTechnology(map.get("26"));
                    bom.setSupplier(map.get("27"));
                    bom.setCreateTime(new Date());
                    list.add(bom);
                }
                bomMapper.batchInsert(list);
                logger.info(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  成功导出excel");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  导出excel失败！", e);
            }
        } catch (Exception e) {
            logger.error(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  爬取数据失败！", e);
            resultMap.setSuccess(false);
        }
        return resultMap;
    }

}
