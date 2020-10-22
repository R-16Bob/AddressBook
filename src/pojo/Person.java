package pojo;

public class Person {
    private int    pid;  //编号
	private String name;		// 姓名 
	private String sex;		// 性别 
	private String tel;		// 电话 
	private String address;	// 地址 
	private String email;		//email 
	
	public Person() {
	}
	public Person(int pid, String name, String sex, String tel, String address, String email) {
		this.pid = pid;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.address = address;
		this.email = email;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
} 
