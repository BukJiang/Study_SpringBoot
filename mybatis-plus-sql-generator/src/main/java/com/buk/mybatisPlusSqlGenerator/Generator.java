package com.buk.mybatisPlusSqlGenerator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * TODO: MyBatis-Plus 代码生成器
 * </p>
 *
 * @author BuK
 * @since 2020-09-20
 */
public class Generator {

    /**
     * 作者
     */
    private final static String AUTHOR = "BuK";
    /**
     * JDBC 地址库
     */
    private final static String JDBC_URL_DB = "127.0.0.1:3306/service_platform";
    /**
     * 用户名
     */
    private final static String JDBC_USERNAME = "root";
    /**
     * 密码
     */
    private final static String JDBC_PASSWORD = "1234567890";
    /**
     * 模块名
     */
    private static final String MODULE_NAME = "oilPrice";
    /**
     * 模块父目录
     */
    private static final String PARENT_MODULE = "ink.bangbu.servicePlatform";
    private static final String ENTITY = "pojo.entity";
    /**
     * 表列表
     */
    private static final String[] TABLE_LIST = new String[]{
            "oil_price_car", "oil_price_bill",
    };

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/mybatis-plus-sql-generator";
        // 1. 生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 2. 开发人员
        gc.setAuthor(AUTHOR);
        // 3. 是否打开输出目录
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 数据库地址
        dsc.setUrl("jdbc:mysql://" + JDBC_URL_DB + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        // 数据库用户名
        dsc.setUsername(JDBC_USERNAME);
        // 数据库密码
        dsc.setPassword(JDBC_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 模块名
        pc.setModuleName(MODULE_NAME);
        // 模块父目录
        pc.setParent(PARENT_MODULE);
        // 实体目录
        pc.setEntity(ENTITY);
        //
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
                // 自定义mapper输入文件名称
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 0. 是否大写命名
        strategy.setCapitalMode(true);
        // 1. 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 2. 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 3. 自定义继承的Entity类全称，带包名
//        strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        // 4. 【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // 5. 自定义继承的Controller类全称，带包名
//        strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        // 6. 需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(TABLE_LIST);
        // 7. 自定义基础的Entity类，公共字段
//        strategy.setSuperEntityColumns("id");
        // 8. 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 9. 表前缀
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 10. 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
