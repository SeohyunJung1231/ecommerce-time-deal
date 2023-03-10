package com.jeong.timedeal.config

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = arrayOf("com.jeong.timedeal.repo")
)
class MysqlConfig {
    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder, dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource).packages("com.jeong.timedeal.entity").properties(
            mutableMapOf(
                Pair("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"),
                Pair("hibernate.show_sql", "true"),
                Pair("hibernate.format_sql", "true"),
                // TODO 네이밍 전략 안먹음. 에러 원인 찾아서 고친 후, entity 에 있는 네이밍 지정 제거
                Pair("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl")
            )
        ).persistenceUnit("mysql").build()
    }
    @Bean
    fun transactionManager(
        entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}