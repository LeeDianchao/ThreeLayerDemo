package threelayerdemo;

import java.sql.ResultSet;

//Database Access Layer
//���ݿ���ʲ㣨DAO�����ڴ�ҵ���߼�����յĲ���������ѯ��
//�����䴫�ݸ�dbConnection���Թ�ִ�С�
public class UserDAO {
	private dbConnection conn;

    /// <constructor>
    /// Constructor UserDAO
    /// </constructor>
    public UserDAO()
    {
        conn = new dbConnection();
    }

    /// <method>
    /// Get User Email By Firstname or Lastname and return DataTable
    /// </method>
    public ResultSet searchByName(String _username)
    {
		String [] sqlparameters = _username.split("\\s+");
		//String firstname=sqlparameters[0];
		//String lastname=sqlparameters[1];
        //String query = "select * from usertable where firstname='"+firstname+"' or lastname='"+lastname+"'";
		
		//��ֹSQLע��
        String query= "select * from usertable where firstname=? or lastname=?";
        
        return conn.executeSelectQuery(query,sqlparameters);
    }

    /// <method>
    /// Get User Email By Id and return DataTable
    /// </method>
    public ResultSet searchById(String _id)
    {
    	String [] sqlparameters = _id.split("\\s+");
    	//��ֹSQLע��
        String query = "select * from usertable where idUser=?";
        return conn.executeSelectQuery(query,sqlparameters);
    }
    
    /// <method>
    /// Insert Query
    /// </method>
    public boolean Insert(String [] sqlparameters)
    {
    	//��ֹSQLע��
    	String query = "insert into usertable values(?,?,?,?)";  
    	
        return conn.executeInsertQuery(query,sqlparameters);
    }
    
    /// <method>
    /// Update Query
    /// </method>
    public boolean UpdateQuery(String [] sqlparameters)
    {
    	String query = "";
        return conn.executeUpdateQuery(query,sqlparameters);
    }
}
