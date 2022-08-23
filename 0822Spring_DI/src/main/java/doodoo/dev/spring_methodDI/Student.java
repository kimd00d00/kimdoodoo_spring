package doodoo.dev.spring_methodDI;

public class Student {
	private int sno;
	private String name;
	private int kor, eng, math;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	//객체가 생성될 때 호출되는 메서드
	public void init() {
		System.out.println("===== 성적표 =====");
	}
	//객체가 소멸할 때 호출되는 메서드
	public void destroy() {
		System.out.println("================");
	}
	//------------여기까진 스프링에서 처리가 가능, 아래는 사용자에 의해 호출됨
	public void print() {
		System.out.println("name:"+name);
		System.out.println("kor:"+kor);
		System.out.println("eng:"+eng);
		System.out.println("math:"+math);
		System.out.printf("avg:%.2f\n",(kor+eng+math)/3.0);
	}
}
