package com.yolo;


import com.yolo.bean.TableInfo;
import com.yolo.builder.BuildTable;
import com.yolo.builder.controllerbuilder.BuildController;
import com.yolo.builder.entitybuilder.BuildBaseJava;
import com.yolo.builder.entitybuilder.BuildBeanPo;
import com.yolo.builder.entitybuilder.BuildBeanQuery;
import com.yolo.builder.mapperbuilder.BuildMapper;
import com.yolo.builder.mapperbuilder.BuildMapperXml;
import com.yolo.builder.servicebuilder.BuildService;
import com.yolo.builder.servicebuilder.impl.BuildServiceImpl;
import com.yolo.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RunApplication {
    private static final Logger logger = LoggerFactory.getLogger(RunApplication.class);
    public static void main(String[] args) {
//        BuildTable.getTables();
        logger.info("开始生成代码......");
        try {
            //构建基础类
            BuildBaseJava.execute();

            List<TableInfo> tableInfoList = new BuildTable().getTables();
            for (TableInfo tableInfo : tableInfoList) {
                //构建bean
                BuildBeanPo.execute(tableInfo);
                //构建query对象
                BuildBeanQuery.execute(tableInfo);
                //构建mapper
                BuildMapper.execute(tableInfo);
                //构建mapper xml
                BuildMapperXml.execute(tableInfo);
                //构建service
                BuildService.execute(tableInfo);
                //构建serviceImpl
                BuildServiceImpl.execute(tableInfo);
                //构建controller
                BuildController.execute(tableInfo);
            }
            logger.info("生成代码成功，代码在->" + PropertiesUtils.getString("path.base"));
        } catch (Exception e) {
            logger.error("生成代码失败，错误信息:", e);
        }
    }
}
