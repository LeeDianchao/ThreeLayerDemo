package threelayerdemo;

//�������ݻ�ȡ�����÷������ࡣ
//����Ҫ���ڽ����ݴ�һ���ഫ�ݵ���һ���ࡣ
//��ֱ����ҵ���߼���ͱ�ʾ�������ӡ�
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
