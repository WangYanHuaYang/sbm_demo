package com.wyhy.sbm_demo.Util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: sbm_demo
 * @description: mybatis代码生成器
 * @author: WYHY
 * @create: 2018-09-27 13:21
 **/
public class MybatisAutoGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }

    @Value("${spring.datasource.url}")
    private static String dburl;

    @Value("${spring.datasource.driver-class-name}")
    private static String driver;

    @Value("${spring.datasource.username}")
    private static String username;

    @Value("${spring.datasource.password}")
    private static String password;

    public static void makeTable(String tablename) throws MybatisPlusException {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        String projectPath = System.getProperty("user.dir");
        mpg.setGlobalConfig(new GlobalConfig()
                        .setOutputDir(projectPath + "/src/main/java")//输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setOpen(false)//生成后打开文件夹
                        .setAuthor("wyhy")
                        .setSwagger2(true)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//            .setMapperName("%sMapper")
//            .setXmlName("%sMapper")
//            .setServiceName("%sService")
                        .setServiceImplName("%sService")
//            .setControllerName("%sController")
        );

        // 数据源配置
        mpg.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName(driver)
                .setUrl(dburl)
                .setUsername(username)
                .setPassword(password)
        );

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.wyhy.sbm_demo");
        pc.setEntity("model");
        pc.setServiceImpl("service");
        pc.setMapper("dao");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig()
                .setXml(null)
                .setService(null)
                .setServiceImpl("/templates/ftl/serviceImpl.java")
                .setController("/templates/ftl/controller.java")
                .setEntity("/templates/ftl/entity.java")
        );

        // 策略配置
        mpg.setStrategy(new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        //.setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setInclude(tablename) // 需要生成的表
                        .setRestControllerStyle(true)
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        //.setSuperEntityClass("com.baomidou.demo.TestEntity")// 自定义实体父类
                        //.setSuperEntityColumns(new String[]{"test_id"})// 自定义实体，公共字段
                        //.setTableFillList(tableFillList)
                        //.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")// 自定义 mapper 父类 默认BaseMapper
                        //.setSuperServiceClass("com.baomidou.demo.TestService")// 自定义 service 父类 默认IService
                        //.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")// 自定义 service 实现类父类 默认ServiceImpl
                        //.setSuperControllerClass("com.kichun."+packageName+".controller.AbstractController")// 自定义 controller 父类
                        //.setEntityColumnConstant(true)// 【实体】是否生成字段常量（默认 false）
                        //.setEntityBuilderModel(true)// 【实体】是否为构建者模型（默认 false）
                        .setEntityLombokModel(true)// 【实体】是否为lombok模型（默认 false
                        .setRestControllerStyle(true)
                        //.setEntityBooleanColumnRemoveIsPrefix(true)// Boolean类型字段是否移除is前缀处理
                        .setControllerMappingHyphenStyle(true)
        );

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }
}
