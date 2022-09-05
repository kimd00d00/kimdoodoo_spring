package doo.doo.config;
//XML을 다 없애버리겠다.

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
/*
	<aop:aspectj-autoproxy/>
	<context:component-scan base-package="doo.doo.*" />
	<mybatis-spring::scan base-package="doo.doo.mapper"/>
 */
@EnableAspectJAutoProxy //aop대신
@ComponentScan(basePackages= {"doo.doo.*"}) //component-scan 대신
@MapperScan(basePackages= {"doo.doo.mapper"})
public class BoardConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		//HandlerMapping, HandlerAdapter 세팅함
		configurer.enable();
	}
	
	/*
	<bean id="viewResolver"
 	 class="org.springframework.web.servlet.view.InternalResourceViewResolver"
	 p:prefix="/"
	 p:suffix=".jsp"
	/>
	 */
	@Bean("viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver ir = new InternalResourceViewResolver();
		ir.setPrefix("/");
		ir.setSuffix(".jsp");
		return ir;
	}
	/*
	 * <bean id="ds" 
		class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@localhost:1521:XE"
		p:username="hr"
		p:password="happy"
	/>
	 */
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	/*
	 * <!-- MyBatis 설정 -->
		<bean id="ssf"
		class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
		p:configLocation="/WEB-INF/config/Config.xml"/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		ssf.setConfigLocation(new ClassPathResource("Config.xml"));
		return ssf.getObject();
	}
}
