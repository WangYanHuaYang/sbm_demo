package com.wyhy.sbm_demo.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.statement.delete.Delete;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: sbm_demo
 * @description: mybatis配置类
 * @author: WYHY
 * @create: 2018-09-26 13:10
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.wyhy.sbm_demo.dao")
public class MybatisPlusConfig {


    /**
     * 分页插件 攻击 SQL 阻断解析器 多租户 SQL 解析器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//
////        多租户 SQL 解析器
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId(boolean where) {
//                // 该 where 条件 3.2.0 版本开始添加的，用于分区是否为在 where 条件中使用
//                // 此判断用于支持返回多个租户 ID 场景，具体使用查看示例工程
//                return new LongValue(1L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                // 这里可以判断是否过滤表
//            /*
//            if ("user".equals(tableName)) {
//                return true;
//            }*/
//                return false;
//            }
//        });
//
//        // 攻击 SQL 阻断解析器、加入解析链
//        BlockAttackSqlParser blockAttackSqlParser=new BlockAttackSqlParser() {
//            @Override
//            public void processDelete(Delete delete) {
//                // 如果你想自定义做点什么，可以重写父类方法像这样子
//                if ("user".equals(delete.getTable().getName())) {
//                    // 自定义跳过某个表，其他关联表可以调用 delete.getTables() 判断
//                    return ;
//                }
//                super.processDelete(delete);
//            }
//        };
//
//
//        sqlParserList.add(tenantSqlParser);
//        sqlParserList.add(blockAttackSqlParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);
//        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
//            @Override
//            public boolean doFilter(MetaObject metaObject) {
//                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
//                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
//                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        return paginationInterceptor;
//    }

    /**
     * @Description: 当要更新一条记录的时候，希望这条记录没有被别人更新
     *
     * 乐观锁实现方式：
     *
     * 取出记录时，获取当前version
     * 更新时，带上这个version
     * 执行更新时， set version = newVersion where version = oldVersion
     * 如果version不对，就更新失败
     * 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     *
     * @Param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor
     * @Author: WYHY
     * @Date: 2020/1/16
     */
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
//        return new OptimisticLockerInterceptor();
//    }

    /***
     * Date日期类型转换器
     * @return
     */
    @Bean
    public Converter<String, LocalDateTime> LocalDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = null;
                try {
                    date = LocalDateTime.parse((String) source,df);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

}

