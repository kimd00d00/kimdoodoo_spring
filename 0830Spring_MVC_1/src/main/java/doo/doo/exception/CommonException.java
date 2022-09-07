package doo.doo.exception;
import java.io.IOException;
import java.sql.SQLException;

//예외처리
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice(basePackages= {"doo.doo.web"}) //doo.doo.web안에 있는 모든 콘트롤러가 에러 날 때 확인해라 
public class CommonException {
	@ExceptionHandler(RuntimeException.class) //RuntimeException ->변환이나 nullpointerException같은거
	public void runtime(RuntimeException ex) {
		System.out.println("===Runtime Exception 발생!===");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("===SQL Exception 발생!===");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("===IO Exception 발생!===");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("=== Exception 발생!===");
		System.out.println(ex.getMessage());
	}
	
}
