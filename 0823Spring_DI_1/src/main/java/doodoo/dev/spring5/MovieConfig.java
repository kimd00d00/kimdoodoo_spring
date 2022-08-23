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
 * xml ���! �ڹٷ� ȯ�漳���� �Ѵ�
 * Spring5�� ���� �ڹٷθ� �����ϵ��� �Ѵ�.
 */
@Configuration //�ٿ��־�� ��
@ComponentScan(basePackages= {"doodoo.dev.spring5"}) //xml�� �±׸� ������̼�ȭ! 
//app.xml�� <context:component-scan base-package="doodoo.dev.spring5"/> �� ��ü��
public class MovieConfig {
	@Bean("ds") //<bean id="">�� ��ü��
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
		ssf.setConfigLocation(new ClassPathResource("Config.xml")); //classpath:...�� ����
		return ssf.getObject();
	}
	//���� DAO�� ssf�� �޾��� �ǵ�... -> MovieDAO����!
}