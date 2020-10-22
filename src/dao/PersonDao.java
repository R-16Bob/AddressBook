package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbutil.SQLHelper;
import pojo.Person;
public class PersonDao {
	// ��� ��ϵ��
	public void addPerson(Person p){ 
		String sql="insert into persons values("+p.getPid()+",'"+
	p.getName()+"','"+p.getSex()+"','"+p.getTel()+"','"+p.getAddress()
	+"','"+p.getEmail()+"')";
        SQLHelper. executeUpdate(sql);
	}	 
	//ͨ���˵ı��ɾ����ϵ��
	public void deletePersonByID(int pid){ 		
		String sql="delete from persons where pid="+pid;
        SQLHelper. executeUpdate(sql);
	}
	//ͨ���˵�����������ϵ�ˣ�����һ������
	public List<Person> queryPersonByName(String name){ 
		List<Person> list=new ArrayList<Person>();
		String sql="select * from persons where name='"+name+"'";
		ResultSet rs=SQLHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Person p = new Person();
				p.setPid(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setSex(rs.getString(3));
				p.setTel(rs.getString(4));
				p.setAddress(rs.getString(5));
				p.setEmail(rs.getString(6));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SQLHelper.closeConnection();
		return list;		
		}
	//�޸���ϵ������
	public void updateNameByID(int pid,String newName){
		String sql="update persons set name='"+newName+"' where pid="+pid;
		SQLHelper.executeUpdate(sql);
	}
	//�޸���ϵ���Ա�
	public void updateSexByID(int pid,String newSex){
		String sql="update persons set sex='"+newSex+"' where pid="+pid;
		SQLHelper.executeUpdate(sql);
	}
	//�޸���ϵ�˵绰
	public void updateTelByID(int pid,String newTel){
		String sql="update persons set tel='"+newTel+"' where pid="+pid;
		SQLHelper.executeUpdate(sql);
	}
	//�޸���ϵ�˵�ַ
	public void updateAddressByID(int pid,String newAdd){
		String sql="update persons set address='"+newAdd+"' where pid="+pid;
		SQLHelper.executeUpdate(sql);
	}		
	//�޸���ϵ���Ա�
	public void updateEmailByID(int pid,String newEmail){
		String sql="update persons set email='"+newEmail+"' where pid="+pid;
		SQLHelper.executeUpdate(sql);
	}
	public List<Person> queryAllPerson(){  //��ѯȫ����¼
		List<Person> list = new ArrayList<Person>();
		String sql="select * from persons ";
		ResultSet rs=SQLHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Person p = new Person();
				p.setPid(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setSex(rs.getString(3));
				p.setTel(rs.getString(4));
				p.setAddress(rs.getString(5));
				p.setEmail(rs.getString(6));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SQLHelper.closeConnection();
		return list;
	}
}
