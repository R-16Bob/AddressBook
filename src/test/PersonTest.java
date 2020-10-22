package test;

import java.sql.ResultSet;
import java.util.List;

import dao.PersonDao;
import pojo.Person;

public class PersonTest {
	public static void main(String[] args) {
		PersonDao dao=new PersonDao();
		//增加记录
		Person p = new Person(200, "李明", "男", "023344", "杭州", "liming@163.com");
		dao.addPerson(p);
		//按姓名查
		List<Person> list=dao.queryPersonByName("李明");
		System.out.println("姓名为李明的记录：");
		for(int i=0;i<list.size();i++) {
			Person p2 = list.get(i);
			System.out.printf("%-5d%-10s%-6s%-10s%-10s%-22s\n", p2.getPid(),p2.getName(),p2.getSex(),
					p2.getTel(),p2.getAddress(),p2.getEmail());
		}
		//删除记录
		dao.deletePersonByID(200);
		//修改张军信息
		dao.updateNameByID(100, "章均");
		dao.updateSexByID(100, "女");
		dao.updateTelByID(100, "031111");
		dao.updateAddressByID(100, "北京");
		dao.updateEmailByID(100, "junzhang@gmail.com");
	}

}
