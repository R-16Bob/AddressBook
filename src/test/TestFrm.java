package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import pojo.Person;
import dao.PersonDao;
public class TestFrm extends JFrame implements ActionListener {
	PersonDao dao;
	List<Person> plist;
	JLabel label[] = new JLabel[6];
	String lname[] = {"���","����","�Ա�","�绰","��ַ","Email"};//��ǩ���ͷ��
	JButton  button[] = new JButton[5];
	String bname[] = {"��ѯ","����","ɾ��","�޸�","��ʾ����"};
	JTextField textField[] = new JTextField[6];	
	JPanel p[] = new JPanel[3];
	JPanel basePanel;
	Box b[] = new Box[3];
	Box box;
	JTable table;
	DefaultTableModel model; //table��ģ��
	//���캯��
	public TestFrm(String title) {  
		dao=new PersonDao();
		for(int i=0;i<3;i++) {  //��ʼ��Panel
			p[i] = new JPanel();
		}
		for(int i=0;i<6;i++) {  //��ʼ����ǩ
			label[i]=new JLabel(lname[i]);
		}
		for(int i=0;i<6;i++) {  //��ʼ���ı���
			textField[i] = new JTextField(15);
			textField[i].addActionListener(this);
		}

		for(int i=0;i<5;i++) {  //��ʼ����ť
			button[i] = new JButton(bname[i]);
			button[i].addActionListener(this);
			p[1].add(button[i]);
		}
		//��ʼ��Box
		box = Box.createVerticalBox();
		for(int i=0;i<3;i++) {
			b[i] = Box.createHorizontalBox();
			b[i].add(label[i*2]);
			b[i].add(box.createHorizontalStrut(30));
			b[i].add(textField[i*2]);
			b[i].add(box.createHorizontalStrut(60));
			b[i].add(label[i*2+1]);
			b[i].add(box.createHorizontalStrut(30));
			b[i].add(textField[i*2+1]);
		}
		box.add(b[0]);
		box.add(b[1]);
		box.add(b[2]);
		p[0].add(box);
		//��ʼ��table
		createTable();
		table.setPreferredScrollableViewportSize(new Dimension(650, 100));  //���ù�������С
		JScrollPane scrollPane = new JScrollPane(table); //�����������
		p[2].add(scrollPane);
		//���ò���
		basePanel = new JPanel(new BorderLayout());
		basePanel.add(p[0],BorderLayout.NORTH);
		basePanel.add(p[1],BorderLayout.CENTER);
		basePanel.add(p[2],BorderLayout.SOUTH);
		setContentPane(basePanel);
		validate();
		//���ô���
		setTitle(title);
		setBounds(100,100,700,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
	}
	public void createTable() {  //�������ݿ���������table
		List<Person> ps=dao.queryAllPerson();
		Object[][] obj= new Object[ps.size()][6]; 
		for(int i=0;i<ps.size();i++) {
			obj[i][0]=ps.get(i).getPid();
			obj[i][1]=ps.get(i).getName();
			obj[i][2]=ps.get(i).getSex();
			obj[i][3]=ps.get(i).getTel();
			obj[i][4]=ps.get(i).getAddress();
			obj[i][5]=ps.get(i).getEmail();
		}
		model = new DefaultTableModel(ps.size(), 6);
		table = new JTable(model);
		model.setColumnIdentifiers(lname);
		for(int i=0;i<ps.size();i++) {
			for(int j=0;j<6;j++) {
				model.setValueAt(obj[i][j], i, j);
			}
		}
	}
	//�����ѯ����ʾ����ʱ����plist�е����ݸ���table
	public void updateTable() {
		int row =model.getRowCount();
		for(int i=0;i<row;i++) {
			model.removeRow(0);
		}
		model.setRowCount(plist.size());
		Object[][] obj= new Object[plist.size()][6]; 
		for(int i=0;i<plist.size();i++) {
			obj[i][0]=plist.get(i).getPid();
			obj[i][1]=plist.get(i).getName();
			obj[i][2]=plist.get(i).getSex();
			obj[i][3]=plist.get(i).getTel();
			obj[i][4]=plist.get(i).getAddress();
			obj[i][5]=plist.get(i).getEmail();
		}
		for(int i=0;i<plist.size();i++) {
			for(int j=0;j<6;j++) {
				model.setValueAt(obj[i][j], i, j);
			}
		}
	}
	//�¼�
	public void actionPerformed(ActionEvent e) {  
		//���²�ѯ��Table����ʾ�ı������ֶ�Ӧ�ļ�¼
		if(e.getSource()==button[0]) {  
			String name=textField[1].getText();
			plist=dao.queryPersonByName(name);
			updateTable();
		}
		else if(e.getSource()==button[1]) {
			//��������
			String add[]=new String[6];
			for(int i=0;i<6;i++) {
				add[i]=textField[i].getText();
			}
			Person p=new Person(Integer.parseInt(add[0]), add[1], add[2], add[3], add[4], add[5]);
			dao.addPerson(p);
			plist=dao.queryAllPerson();
			updateTable();
		}
		else if(e.getSource()==button[2]) {
			//����ɾ��,���ݱ��ɾ��
			int pid=Integer.parseInt(textField[0].getText());
			dao.deletePersonByID(pid);
			plist=dao.queryAllPerson();
			updateTable();
		}
		else if(e.getSource()==button[3]) {
			//�����޸ģ������ı��������޸ı�Ŷ�Ӧ������
			int pid=Integer.parseInt(textField[0].getText());
			String up[]=new String[6];
			for(int i=0;i<6;i++) {
				up[i]=textField[i].getText();
			}
			dao.updateNameByID(pid, up[1]);
			dao.updateSexByID(pid, up[2]);
			dao.updateTelByID(pid, up[3]);
			dao.updateAddressByID(pid, up[4]);
			dao.updateEmailByID(pid, up[5]);
			plist=dao.queryAllPerson();
			updateTable();
		}
		else if(e.getSource()==button[4]) {
			plist=dao.queryAllPerson();
			updateTable();
		}
}
	public static void main(String[] args) {		
		TestFrm test=new TestFrm("�ҵ�ͨѶ¼");
	}

}
