package doo.doo.exception;
import java.io.IOException;
import java.sql.SQLException;

//����ó��
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice(basePackages= {"doo.doo.web"}) //doo.doo.web�ȿ� �ִ� ��� ��Ʈ�ѷ��� ���� �� �� Ȯ���ض� 
public class CommonException {
	@ExceptionHandler(RuntimeException.class) //RuntimeException ->��ȯ�̳� nullpointerException������
	public void runtime(RuntimeException ex) {
		System.out.println("===Runtime Exception �߻�!===");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("===SQL Exception �߻�!===");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("===IO Exception �߻�!===");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("=== Exception �߻�!===");
		System.out.println(ex.getMessage());
	}
	
}
