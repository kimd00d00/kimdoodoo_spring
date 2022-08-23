package doodoo.dev.anno;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;
/*
 * <bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
	 p:driverClassName="#{db['driver']}"
	 p:url="#{db['url']}"
	 p:username="#{db['username']}"
	 p:password="#{db['password']}"
	/>
 */
@Component("ds") //�޸� �Ҵ� ��û
public class MyBasicDataSource extends BasicDataSource{
	public MyBasicDataSource() {
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		setUsername("hr");
		setPassword("happy");
	}
}
