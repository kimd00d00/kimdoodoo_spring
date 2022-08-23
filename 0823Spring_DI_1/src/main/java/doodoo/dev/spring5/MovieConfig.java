package doodoo.dev.spring5;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/*
 * xml 대신! 자바로 환경설정을 한다
 * Spring5는 순수 자바로만 생성하도록 한다.
 */
@Configuration //붙여주어야 함
@ComponentScan(basePackages= {"doodoo.dev.spring5"}) //xml의 태그를 어노테이션화! 
//app.xml의 <context:component-scan base-package="doodoo.dev.spring5"/> 를 대체함
public class MovieConfig {
	@Bean("ds") //<bean id="">를 대체함
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver"); // p:driverClassName=""
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE"); //p:url=""
		ds.setUsername("hr"); //p:username=""
		ds.setPassword("happy"); //p:password=""
		return ds;
	}
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		ssf.setConfigLocation(new ClassPathResource("Config.xml")); //classpath:...와 같음
		return ssf.getObject();
	}
	//이제 DAO가 ssf를 받아줄 건데... -> MovieDAO에서!
}