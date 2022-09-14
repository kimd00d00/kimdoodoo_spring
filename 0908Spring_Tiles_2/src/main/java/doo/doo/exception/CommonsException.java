package doo.doo.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//�������� �����ϴ� ���� ó��
//Runtime -> NullPointer, numberformat, classcast
//SQL
//IO
//Exception
@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("RUNTIME EXCEPTION �߻�!");
		ex.printStackTrace();
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("SQL EXCEPTION �߻�!");
		ex.printStackTrace();
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("IO EXCEPTION �߻�!");
		ex.printStackTrace();
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("EXCEPTION �߻�!");
		ex.printStackTrace();
	}
}
