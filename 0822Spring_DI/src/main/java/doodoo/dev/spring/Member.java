package doodoo.dev.spring;

public class Member {
	private int mno;
	private String name;
	private String addr;
	
	public Member(int mno, String name, String addr) {
		this.mno = mno;
		this.name = name;
		this.addr = addr;
	}
	public void print() {
		System.out.println("mno:"+mno+",name:"+name);
	}
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
