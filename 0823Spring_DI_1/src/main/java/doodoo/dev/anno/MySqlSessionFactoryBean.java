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
@Component("ssf") //@Target(value={TYPE}) �ݵ�� Ŭ���������� ����ؾ� �Ѵ�. == Ŭ���� ���� �����ؾ� �Ѵ�
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{
	@Autowired //�ڵ� ����! (��ü�� �ּҰ��� ������ ��)
	/* @Target(value={ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER})
	 * ANNOTATION_TYPE : ������̼� ���� �ö󰡴� ������̼�
	 * CONSTRUCTOR : ������
	 * FIELD : ��� ����
	 * METHOD : �޼���
	 * PARAMETER : �Ű� ����
	 * 
	 * �ڵ� ������ ���� : ���� ������ Ŭ������ �����ϸ� ������ ����.
	 * (���� ������ Ŭ������ ���� @Qualifier�� �̿��ؼ� Ư�� ��ü�� �����Ѵ�.)
	 * -> @Autowired+@Qualifier = @Resource - �ٵ� ����� �� �� ����. JDK1.8������ ����� �����ϱ⶧����...
	 */
	public void setDataSource(DataSource dataSource) { //datasource ��ġ�� �ش� ��ü�� �ּҰ��� �־��
		//MyBasicDataSource�� ����� ���� ����
		super.setDataSource(dataSource);
	}
	public MySqlSessionFactoryBean() {
		try {
			Resource res = new ClassPathResource("Config.xml");
			setConfigLocation(res);
		}catch(Exception ex) {}
	}
}