package threelayerdemo;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

//Presentation Layer
//��ʾ�������û�ֱ�����ӵ�Ψһ�㡣
//��ʾ����Ҫ���ڻ�ȡ�û����ݣ�Ȼ���䴫�ݵ�ҵ���߼����Խ��н�һ���Ĵ��� 
public class Presentation extends JFrame implements ActionListener{
	
	private UserBUS _userBUS;
	
	//�������
	private JFrame frame = new JFrame("��ѯ");
	private Container c = frame.getContentPane();
	private JTextField input = new JTextField();
	JLabel a1 = new JLabel("������Ϣ:");
	private JButton ok = new JButton("����������ѯ");
	private JButton cancel = new JButton("������ID��ѯ");
	private String authorname;//������
	private String id;//����ID
	public Presentation()
	{
		_userBUS = new UserBUS();
		frame.setSize(300,200);
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        exit();
		      }
		    });
		c.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//����---------------------------
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("��ѯ"));
		c.add(titlePanel,"North");
		//�в���-------------------------
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		
		a1.setBounds(10,20,80,40);;
		fieldPanel.add(a1);
		input.setBounds(80,20,180,40);
		fieldPanel.add(input);
		c.add(fieldPanel,"Center");
		//�ײ���ť-----------------------
		//���ü���  
		ok.addActionListener(this);
		cancel.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		c.add(buttonPanel,"South");
	}
	
	public void exit() 
	{    
		Object[] options = { "ȷ��", "ȡ��" };    
		JOptionPane pane2 = new JOptionPane("�����˳���?", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION, null, options, options[1]);    
		JDialog dialog = pane2.createDialog(this, "����");    
		dialog.setVisible(true);    
		Object selectedValue = pane2.getValue();    
		if (selectedValue == null || selectedValue == options[1]) 
		{      
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		} 
		else if (selectedValue == options[0]) 
		{      
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}  
	}
	
	public void actionPerformed(ActionEvent e)
	{            //�¼��ж�
        if(e.getActionCommand()=="����������ѯ")  
        {  
        	//a1.setText("������");
        	authorname=input.getText();
        	System.out.println(authorname);
        	Vector<UserVO> rs=_userBUS.getUserEmailByName(authorname);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else
        	{
        		new resultJframe().init(rs);
        	}
        	
        }	
        else if(e.getActionCommand()=="������ID��ѯ")  
        { 
        	//a1.setText("����ID");
        	id=input.getText();
        	System.out.println(id);
        	Vector rs=_userBUS.getUserById(id);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else
        	{
        		new resultJframe().init(rs);
        	}
 
        }             
	}
	
	public static void main(String[] args)
	{
		new Presentation();
	}
	
	
}


class resultJframe {
	private JFrame jFrame;
	private JPanel jPanel;
	private JTable jTable;
	private JScrollPane jScrollPane;
	public void init(Vector rs) {
		jFrame = new JFrame("��ѯ��¼");
		jPanel = new JPanel(new BorderLayout());
		Vector content = new Vector();
		int i=0;
		int size=rs.size();
		while(i<size)
		{
			Vector row = new Vector();
			UserVO uservo=new UserVO();
			uservo.set(((UserVO)rs.get(i)));
			row.add(i+1);
			row.add(uservo.getidUser());
			row.add(uservo.getfirstname());
			row.add(uservo.getlastname());
			row.add(uservo.getemail());
			content.add(row);
			i++;
		}
		Vector column = new Vector();
		column.add("���");
		column.add("ID");
		column.add("firstname");
		column.add("lastname");
		column.add("email");
		jTable = new JTable(content,column);
		jScrollPane = new JScrollPane(jTable);
		jPanel.add(jScrollPane,BorderLayout.CENTER);
		jFrame.setVisible(true);
		jFrame.setSize(500,250);
		jFrame.setContentPane(jPanel);
		jFrame.setLocationRelativeTo(null);
		jFrame.setAlwaysOnTop(true);
	}
	
}