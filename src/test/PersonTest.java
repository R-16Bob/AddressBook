package test;

import java.sql.ResultSet;
import java.util.List;

import dao.PersonDao;
import pojo.Person;

public class PersonTest {
	public static void main(String[] args) {
		PersonDao dao=new PersonDao();
		//���Ӽ�¼
		Person p = new Person(200, "����", "��", "023344", "����", "liming@163.com");
		dao.addPerson(p);
		//��������
		List<Person> list=dao.queryPersonByName("����");
		System.out.println("����Ϊ�����ļ�¼��");
		for(int i=0;i<list.size();i++) {
			Person p2 = list.get(i);
			System.out.printf("%-5d%-10s%-6s%-10s%-10s%-22s\n", p2.getPid(),p2.getName(),p2.getSex(),
					p2.getTel(),p2.getAddress(),p2.getEmail());
		}
		//ɾ����¼
		dao.deletePersonByID(200);
		//�޸��ž���Ϣ
		dao.updateNameByID(100, "�¾�");
		dao.updateSexByID(100, "Ů");
		dao.updateTelByID(100, "031111");
		dao.updateAddressByID(100, "����");
		dao.updateEmailByID(100, "junzhang@gmail.com");
	}

}
