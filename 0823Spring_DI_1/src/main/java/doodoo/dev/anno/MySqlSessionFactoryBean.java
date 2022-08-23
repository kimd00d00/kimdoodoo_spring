package doodoo.dev.anno;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
/*
    <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
	 p:dataSource-ref="ds"
	 p:configLocation="classpath:Config.xml"
	/>
 */
@Component("ssf") //@Target(value={TYPE}) 반드시 클래스에서만 사용해야 한다. == 클래스 위에 설정해야 한다
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{
	@Autowired //자동 주입! (객체의 주소값을 주입할 때)
	/* @Target(value={ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER})
	 * ANNOTATION_TYPE : 어노테이션 위에 올라가는 어노테이션
	 * CONSTRUCTOR : 생성자
	 * FIELD : 멤버 변수
	 * METHOD : 메서드
	 * PARAMETER : 매개 변수
	 * 
	 * 자동 주입의 단점 : 같은 유형의 클래스를 주입하면 오류가 난다.
	 * (같은 유형의 클래스일 때는 @Qualifier를 이용해서 특정 객체를 지정한다.)
	 * -> @Autowired+@Qualifier = @Resource - 근데 요새는 잘 안 쓴다. JDK1.8까지만 사용이 가능하기때문에...
	 */
	public void setDataSource(DataSource dataSource) { //datasource 위치에 해당 객체의 주소값을 넣어라
		//MyBasicDataSource가 여기로 들어올 것임
		super.setDataSource(dataSource);
	}
	public MySqlSessionFactoryBean() {
		try {
			Resource res = new ClassPathResource("Config.xml");
			setConfigLocation(res);
		}catch(Exception ex) {}
	}
}