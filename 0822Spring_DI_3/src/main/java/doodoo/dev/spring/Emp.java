package doodoo.dev.spring;

public class Emp {
	private int empno;
	private String name, dept;
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public void print() {
		System.out.println("empno:"+empno);
		System.out.println("name:"+name);
		System.out.println("dept:"+dept);
	}
}