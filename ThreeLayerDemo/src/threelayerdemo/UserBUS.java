package threelayerdemo;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

//Business Logic Layer
//ҵ���߼��㣨WorkLogic Lead��BUS���Ǳ�ʾ���DAO֮���������
//�ӱ��ֲ���յ������û�ֵ�������ݸ�BUS��
//��DAO���յĽ���������ݱ��ʽ�������ݣ�ת��Ϊֵ����VO����
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
			JOptionPane.showMessageDialog(null, "����:"+e.getStackTrace().toString(), "UserBUS��Ϣ", JOptionPane.ERROR_MESSAGE);
			System.out.print("UserBUS����:"+e.getStackTrace().toString());
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
			JOptionPane.showMessageDialog(null, "����:"+e.getStackTrace().toString(), "UserBUS��Ϣ", JOptionPane.ERROR_MESSAGE);
			System.out.print("UserBUS����:"+e.getStackTrace().toString());
		}
        return result;
    }

}
