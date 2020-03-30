package com.linilq.manage.core.codegener;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.linilq.manage.core.base.BaseDo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhijian
 * @description
 * @date 2020/3/30
 */
public class CodeGeneratorFixed {

    public static String TemplateType_Freemark = ".ftl";
    public static String TemplateType_Velocity = ".vm";
    public static String TemplateType_Beetl = ".btl";

    public static String TEMPLATE_TYPE = TemplateType_Freemark;
    public static final String MODULE_NAME = "sys";
    public static final String projectPath = "D:\\workspace\\intellij\\manage\\src\\main\\java\\com\\linilq\\manage\\" + MODULE_NAME;
    public static final String ENTITY_OUTPUT = projectPath + "\\entity\\Do\\";
    public static final String SERVICE_OUTPUT = projectPath + "\\service\\";
    public static final String MAPPER_OUTPUT = projectPath + "\\mapper\\";
    public static final String MAPPING_OUTPUT = projectPath + "\\mapping\\";
    public static final String CONTROLLER_OUTPUT = projectPath + "\\controller\\";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig();
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        PackageConfig packageConfig = getPackageConfig();
        // 使用默认模板
        TemplateConfig templateConfig = new TemplateConfig();
        StrategyConfig strategy = getStrategyConfig();

        InjectionConfig injectionConfig = getInjectionConfig();
        autoGenerator.setCfg(injectionConfig);

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setTemplate(templateConfig);
        if (TEMPLATE_TYPE.equals(TemplateType_Beetl)) {
            autoGenerator.setTemplateEngine(new BeetlTemplateEngine());
        } else if (TEMPLATE_TYPE.equals(TemplateType_Velocity)) {
            autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        } else if (TEMPLATE_TYPE.equals(TemplateType_Freemark)) {
            autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        }
        autoGenerator.setStrategy(strategy);

        autoGenerator.execute();
    }

    private static InjectionConfig getInjectionConfig() {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }

        };
//        injectionConfig.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 如果自定义的文件保存路径与packageInfo组装的不一致，那就需要如下处理，避免重复生成
//                if (FileType.OTHER.equals(fileType)) {
//                    checkDir(filePath);
//                    return true;
//                }
//                return false;
//            }
//        });
        FileOutConfig xmlFileOutConfig = new FileOutConfig(ConstVal.TEMPLATE_XML + TEMPLATE_TYPE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return MAPPING_OUTPUT + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;

            }
        };
        FileOutConfig mapperFileOutConfig = new FileOutConfig(ConstVal.TEMPLATE_MAPPER + TEMPLATE_TYPE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return MAPPER_OUTPUT + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;

            }
        };
        FileOutConfig serviceImplFileOutConfig = new FileOutConfig(ConstVal.TEMPLATE_SERVICE_IMPL + TEMPLATE_TYPE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return SERVICE_OUTPUT + "impl\\" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;

            }
        };
        FileOutConfig serviceFileOutConfig = new FileOutConfig(ConstVal.TEMPLATE_SERVICE + TEMPLATE_TYPE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return SERVICE_OUTPUT + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;

            }
        };
        FileOutConfig entityFileOutConfig = new FileOutConfig(ConstVal.TEMPLATE_ENTITY_JAVA + TEMPLATE_TYPE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return ENTITY_OUTPUT + tableInfo.getEntityName() + StringPool.DOT_JAVA;

            }
        };
        FileOutConfig controllerFileOutConfig = new FileOutConfig(ConstVal.TEMPLATE_CONTROLLER + TEMPLATE_TYPE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return CONTROLLER_OUTPUT + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;

            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();

        fileOutConfigList.add(controllerFileOutConfig);
        fileOutConfigList.add(entityFileOutConfig);
        fileOutConfigList.add(serviceFileOutConfig);
        fileOutConfigList.add(serviceImplFileOutConfig);
        fileOutConfigList.add(mapperFileOutConfig);
        fileOutConfigList.add(xmlFileOutConfig);

        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    private static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BaseDo.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id", "create_by", "create_time",
                "update_time", "update_by", "del_flag", "remarks");
        String tablenames[] = {"sys_user", "sys_role", "sys_menu"};
        strategy.setInclude(tablenames);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("sys_");
        return strategy;

    }

    private static DataSourceConfig getDataSourceConfig() {

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/manage?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("rootcdma");
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDbQuery(new MySqlQuery());
//        dataSourceConfig.setSchemaName();  // 单个数据库下标识不同业务表的字段，一个schema name可以标识多张表
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
        return dataSourceConfig;
    }

    /**
     * 自定义文件输出后  这边的大部分配置都无效了
     * @return
     */
    private static GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("D:/workspace/intellij/demo/src/main/java");
        globalConfig.setAuthor("linilq");
        globalConfig.setBaseResultMap(true);
        globalConfig.setIdType(IdType.ASSIGN_UUID);
        globalConfig.setEntityName("%sDo");
        globalConfig.setControllerName("%sDoController");
        globalConfig.setServiceImplName("%sDoServiceImpl");

        globalConfig.setServiceName("%sDoService");
        globalConfig.setMapperName("%sDoMapper");
        globalConfig.setDateType(DateType.TIME_PACK);
//        globalConfig.setSwagger2(true);
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);

        return globalConfig;
    }

    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.linilq.manage");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity.Do");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapping");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setModuleName(MODULE_NAME);

//        packageConfig.setPathInfo();
        return packageConfig;
    }

}
