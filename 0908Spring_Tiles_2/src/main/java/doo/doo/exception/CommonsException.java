package doo.doo.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//공통으로 적용하는 예외 처리
//Runtime -> NullPointer, numberformat, classcast
//SQL
//IO
//Exception
@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("RUNTIME EXCEPTION 발생!");
		ex.printStackTrace();
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("SQL EXCEPTION 발생!");
		ex.printStackTrace();
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("IO EXCEPTION 발생!");
		ex.printStackTrace();
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("EXCEPTION 발생!");
		ex.printStackTrace();
	}
}
