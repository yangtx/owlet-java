package com.xracoon.sys.owlet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackageClasses={
        com.xracoon.sys.owlet.orm.mappers.ArticleMapper.class,
        com.xracoon.sys.owlet.orm.mappersex.ArticleMapperEx.class
})
@Configuration
public class MybatisOrmConfiguration {
}
