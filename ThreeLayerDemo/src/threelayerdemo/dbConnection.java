package threelayerdemo;

import java.sql.*;
import javax.swing.JOptionPane;

//dbConnection
//该类主要用于对数据库进行选择、更新和删除查询等数据库活动。
//它还检查数据库连接是否打开。
//如果数据库连接未打开，则打开连接并执行数据库查询。
//数据库结果将在该类中被接收并传递到数据表中。
public class dbConnection {

    private ResultSet rs=null;
    private Connection conn=null;
    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=pubs";
	String userName="sa";
	String userPwd="123456";
	
    /// <constructor>
    /// Initialise Connection
    /// </constructor>
    public dbConnection()
    {
    	try
		{
    		Class.forName(driverName);
    		conn=DriverManager.getConnection(dbURL,userName,userPwd);
    		System.out.println("连接数据库成功");
    		rs=null;
		}
    	catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "连接失败:"+e.getStackTrace().toString(), "dbConnection消息", JOptionPane.ERROR_MESSAGE);
			System.out.print("dbConnection连接失败:"+e.getStackTrace().toString());
		}
    }

    /// <method>
    /// Open Database Connection if Closed or Broken
    /// </method>
    private Connection openConnection()
    {
    	try
		{
    		if (conn.isClosed())
    		{
    			Class.forName(driverName);
        		conn=DriverManager.getConnection(dbURL,userName,userPwd);
        		System.out.println("连接数据库成功");
        		rs=null;
    		}
		}
    	catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "连接失败:"+e.getStackTrace().toString(), "dbConnection消息", JOptionPane.ERROR_MESSAGE);
			System.out.print("dbConnection连接失败:"+e.getStackTrace().toString());
		}
        return conn;
    }
    
    
    /// <method>
    /// Select Query
    /// </method>
    public ResultSet executeSelectQuery(String _query,String [] sqlparameters)
    {
        try
        {
        	//采用预编译语句集，内置了处理SQL注入的能力
        	PreparedStatement preState = conn.prepareStatement(_query,Statement.RETURN_GENERATED_KEYS);
        	int i=1;
        	for (String element: sqlparameters) {
        		preState.setString(i, element);
        		i++;
             }
        	
            rs = preState.executeQuery();
        	
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "查询失败:"+e.getStackTrace().toString(), "dbConnection消息", JOptionPane.ERROR_MESSAGE);
			System.out.print("dbConnection查询失败:"+e.getStackTrace().toString());
            return null;
        }
        return rs;
    }

    /// <method>
    /// Insert Query
    /// </method>
    public boolean executeInsertQuery(String _query,String [] sqlparameters)
    {
    	try
        {
    		//采用预编译语句集，内置了处理SQL注入的能力
    		PreparedStatement preState = conn.prepareStatement(_query,Statement.RETURN_GENERATED_KEYS);  
    		int i=1;
        	for (String element: sqlparameters) {
        		preState.setString(i, element);
        		i++;
             }
        	preState.executeUpdate();  
    		
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "插入失败:"+e.getStackTrace().toString(), "dbConnection消息", JOptionPane.ERROR_MESSAGE);
			System.out.print("dbConnection插入失败:"+e.getStackTrace().toString());
            return false;
        }
        return true;
    }

    /// <method>
    /// Update Query
    /// </method>
    public boolean executeUpdateQuery(String _query,String [] sqlparameters)
    {
    	try
        {
        	Statement statement=conn.createStatement();
        	statement.executeUpdate(_query);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "更新失败:"+e.getStackTrace().toString(), "dbConnection消息", JOptionPane.ERROR_MESSAGE);
			System.out.print("dbConnection更新失败:"+e.getStackTrace().toString());
            return false;
        }
        return true;
    }
    
    public void exit()
    {
    	try
    	{
    		conn.close();
    	}
    	catch(Exception e)
		{
    		e.printStackTrace();
		}
    }
}
