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
	
	//��ü�� ������ �� ȣ��Ǵ� �޼���
	public void init() {
		System.out.println("===== ����ǥ =====");
	}
	//��ü�� �Ҹ��� �� ȣ��Ǵ� �޼���
	public void destroy() {
		System.out.println("================");
	}
	//------------������� ���������� ó���� ����, �Ʒ��� ����ڿ� ���� ȣ���
	public void print() {
		System.out.println("name:"+name);
		System.out.println("kor:"+kor);
		System.out.println("eng:"+eng);
		System.out.println("math:"+math);
		System.out.printf("avg:%.2f\n",(kor+eng+math)/3.0);
	}
}
