package com.wy.producerservice.generation;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class CodeGeneration {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/producer-service/src/main/java");
        //是否覆盖文件
        gc.setFileOverride(false);
        //不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);
        //XML二级缓存
        gc.setEnableCache(false);
        //XML ResultMap
        gc.setBaseResultMap(true);
        //XML columList
        gc.setBaseColumnList(false);
        //作者
        gc.setAuthor("wangtoye");
        //是否打开输出目录
        gc.setOpen(false);
        //自定义文件命名，注意%s 会自动填充表实体属性
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("ArImzDOO");
        dsc.setUrl("jdbc:mysql://dev-db.wangtoye.net:3306/wytest?useUnicode=true&characterEncoding=utf-8" +
                "&zeroDateTimeBehavior=convertToNull&autoReconnect=true&tinyInt1isBit=false&serverTimezone=GMT%2B8");
        mpg.setDataSource(dsc);

        // 3.策略配置
        StrategyConfig strategy = new StrategyConfig();
        //此处可以修改您的表前缀
        strategy.setTablePrefix();
        //表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //需要生成的表
        strategy.setInclude("user");
        //使用lombok
        strategy.setEntityLombokModel(true);
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        strategy.setLogicDeleteFieldName("del_flag");
        mpg.setStrategy(strategy);

        // 4.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wy.producerservice");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("serviceImpl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml(null);
        mpg.setPackageInfo(pc);


        // 5.自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            String templatePath = "/templates/mapper.xml.vm";

            @Override
            public void initMap() {
                // 自定义输出配置 会被优先输出
                List<FileOutConfig> focList = new ArrayList<>();
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名，如果Entity设置了前后缀，此处注意 xml 的名称会跟着发生变化
                        return projectPath + "/producer-service/src/main/resources/mapper/" + tableInfo.getEntityName() +
                                "Mapper" + StringPool.DOT_XML;
                    }
                });
                setFileOutConfigList(focList);
            }
        };
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //执行生成
        mpg.execute();
    }
}