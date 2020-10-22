package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbutil.SQLHelper;
import pojo.Person;
public class PersonDao {
	// 添加 联系人
	public void addPerson(Person p){ 
		String sql="insert into persons values(?,?,?,?,?,?)";
        SQLHelper. executeUpdateByParameters(sql, p.getPid(),p.getName(),p.getSex(),p.getTel(),p.getAddress(),p.getEmail());
	}	 
	//通过人的编号删除联系人
	public void deletePersonByID(int pid){ 		
		String sql="delete from persons where pid=?";
        SQLHelper. executeUpdateByParameters(sql, pid);
	}
	//修改联系人姓名
	public void updateNameByID(int pid,String newName){
		String sql="update persons set name=? where pid=?";
		SQLHelper.executeUpdateByParameters(sql, newName,pid);
	}
	//修改联系人性别
	public void updateSexByID(int pid,String newSex){
		String sql="update persons set sex=? where pid=?";
		SQLHelper.executeUpdateByParameters(sql, newSex,pid);
	}
	//修改联系人电话
	public void updateTelByID(int pid,String newTel){
		String sql="update persons set tel=? where pid=?";
		SQLHelper.executeUpdateByParameters(sql, newTel,pid);
	}
	//修改联系人地址
	public void updateAddressByID(int pid,String newAdd){
		String sql="update persons set address=? where pid=?";
		SQLHelper.executeUpdateByParameters(sql, newAdd,pid);
	}		
	//修改联系人性别
	public void updateEmailByID(int pid,String newEmail){
		String sql="update persons set email=? where pid=?";
		SQLHelper.executeUpdateByParameters(sql, newEmail,pid);
	}
	public List<Person> queryAllPerson(){  //查询全部记录
		List<Person> plist = new ArrayList<Person>();
		String sql="select * from persons ";
		List<Object[]> list=SQLHelper.executeQueryAsList(sql);
		for(Object[] arr:list) {
			Person person=new Person();
			person.setPid(new Integer(arr[0].toString()));
			person.setName(arr[1].toString());
			person.setSex(arr[2].toString());
			person.setTel(arr[3].toString());
			person.setAddress(arr[4].toString());
			person.setEmail(arr[5].toString());
			plist.add(person);
		}
		return plist;
	}
	//通过人的姓名查找联系人，返回一个集合
		public List<Person> queryPersonByName(String name){ 
			List<Person> plist=new ArrayList<Person>();
			String sql="select * from persons where name='"+name+"'";
			List<Object[]> list=SQLHelper.executeQueryAsList(sql);
			for(Object[] arr:list) {
				Person person=new Person();
				person.setPid(new Integer(arr[0].toString()));
				person.setName(arr[1].toString());
				person.setSex(arr[2].toString());
				person.setTel(arr[3].toString());
				person.setAddress(arr[4].toString());
				person.setEmail(arr[5].toString());
				plist.add(person);
			}
			return plist;		
			}
}
