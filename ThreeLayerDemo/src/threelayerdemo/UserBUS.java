package threelayerdemo;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

//Business Logic Layer
//业务逻辑层（WorkLogic Lead，BUS）是表示层和DAO之间的桥梁。
//从表现层接收的所有用户值都被传递给BUS。
//从DAO接收的结果是以数据表格式的行数据，转换为值对象（VO）。
public class UserBUS {
	private UserDAO _userDAO;

    /// <constructor>
    /// Constructor UserBUS
    /// </constructor>
    public UserBUS()
    {
        _userDAO  = new UserDAO();
        
    }

    /// <method>
    /// Get User Email By Firstname or Lastname and return VO
    /// </method>
    public Vector getUserEmailByName(String name)
    {
    	Vector result=new Vector();
        
        ResultSet dataTable = null;

        dataTable = _userDAO.searchByName(name);
        try
		{
        	while (dataTable.next()) 
    		{
            	UserVO userVO = new UserVO();
            	userVO.setidUser(dataTable.getInt("idUser"));
            	userVO.setfirstname(dataTable.getString("firstname"));
            	userVO.setlastname(dataTable.getString("lastname"));
            	userVO.setemail(dataTable.getString("email"));
            	result.add(userVO);
    		}
		}
        catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "错误:"+e.getStackTrace().toString(), "UserBUS消息", JOptionPane.ERROR_MESSAGE);
			System.out.print("UserBUS错误:"+e.getStackTrace().toString());
		}
        return result;
    }

    /// <method>
    /// Get User Email By Id and return DataTable
    /// </method>
    public Vector getUserById(String _id)
    {
        Vector result=new Vector();
        
        ResultSet dataTable = null;

        dataTable = _userDAO.searchById(_id);
       
        try
		{
        	while (dataTable.next()) 
    		{
            	UserVO userVO = new UserVO();
            	userVO.setidUser(dataTable.getInt("idUser"));
            	userVO.setfirstname(dataTable.getString("firstname"));
            	userVO.setlastname(dataTable.getString("lastname"));
            	userVO.setemail(dataTable.getString("email"));
            	result.add(userVO);
    		}
		}
        catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "错误:"+e.getStackTrace().toString(), "UserBUS消息", JOptionPane.ERROR_MESSAGE);
			System.out.print("UserBUS错误:"+e.getStackTrace().toString());
		}
        return result;
    }

}
