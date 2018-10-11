package threelayerdemo;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

//Presentation Layer
//表示层是与用户直接连接的唯一层。
//表示层主要用于获取用户数据，然后将其传递到业务逻辑层以进行进一步的处理。 
public class Presentation extends JFrame implements ActionListener{
	
	private UserBUS _userBUS;
	
	//创建组件
	private JFrame frame = new JFrame("查询");
	private Container c = frame.getContentPane();
	private JTextField input = new JTextField();
	JLabel a1 = new JLabel("输入信息:");
	private JButton ok = new JButton("以作者名查询");
	private JButton cancel = new JButton("以作者ID查询");
	private String authorname;//作者名
	private String id;//作者ID
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
		//顶部---------------------------
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("查询"));
		c.add(titlePanel,"North");
		//中部表单-------------------------
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		
		a1.setBounds(10,20,80,40);;
		fieldPanel.add(a1);
		input.setBounds(80,20,180,40);
		fieldPanel.add(input);
		c.add(fieldPanel,"Center");
		//底部按钮-----------------------
		//设置监听  
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
		Object[] options = { "确定", "取消" };    
		JOptionPane pane2 = new JOptionPane("真想退出吗?", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION, null, options, options[1]);    
		JDialog dialog = pane2.createDialog(this, "警告");    
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
	{            //事件判断
        if(e.getActionCommand()=="以作者名查询")  
        {  
        	//a1.setText("作者名");
        	authorname=input.getText();
        	System.out.println(authorname);
        	Vector<UserVO> rs=_userBUS.getUserEmailByName(authorname);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "消息", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else
        	{
        		new resultJframe().init(rs);
        	}
        	
        }	
        else if(e.getActionCommand()=="以作者ID查询")  
        { 
        	//a1.setText("作者ID");
        	id=input.getText();
        	System.out.println(id);
        	Vector rs=_userBUS.getUserById(id);
        	if(rs.size()==0)
        	{
        		JOptionPane.showMessageDialog(null, "No Match Found!", "消息", JOptionPane.INFORMATION_MESSAGE);
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
		jFrame = new JFrame("查询记录");
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
		column.add("序号");
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