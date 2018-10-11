package threelayerdemo;

//具有内容获取和设置方法的类。
//它主要用于将数据从一个类传递到另一个类。
//它直接与业务逻辑层和表示层相连接。
public class UserVO {
	private int _idUser;
    private String _firstname;
    private String _lastname;
    private String _email;

    /// <constructor>
    /// Constructor UserVO
    /// </constructor>
    public UserVO()
    {
        //
        // TODO: Add constructor logic here
        //
    }
    
    public void set(UserVO c)
    {
    	_idUser=c.getidUser();
    	_firstname=c.getfirstname();
    	_lastname=c.getlastname();
    	_email=c.getemail();
    }
    
    public int getidUser()
    {
    	return _idUser;
    }

    public void setidUser(int value)
    {
        _idUser = value;
    }

    public String getfirstname()
    {
    	return _firstname;
    }
        
    public void setfirstname(String value)
    {
    	_firstname = value;
    }

    public String getlastname()
    {
    	return _lastname;
    }
    public void setlastname(String value)
    {
        _lastname = value;
    }    

    public String getemail()
    {
    	return _email;
    }

    public void setemail(String value)
    {
        _email = value;
    }    

}
