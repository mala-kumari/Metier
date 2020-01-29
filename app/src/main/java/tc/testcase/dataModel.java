package tc.testcase;

public class dataModel { private int id_;
    private String name,email,mobile,dob,pas;
    public dataModel(){}
    public dataModel(int id_,String nm,String email,String mobile,String dob,String pas)
    {
        this.id_=id_;
        this.name=nm;
        this.email=email;
        this.mobile=mobile;
        this.dob=dob;
        this.pas=pas;
    }
    public dataModel(String nm,String email,String mobile,String dob,String pas)
    {
        this.name=nm;
        this.email=email;
        this.mobile=mobile;
        this.dob=dob;
        this.pas=pas;

    }
    public void setid_(int id_)
    {
        this.id_=id_;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    public void setPas(String pas)
    {
        this.pas=pas;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public  void setMobile(String mobile)
    {
        this.mobile=mobile;
    }
    public void setDob(String dob)
    {
        this.dob=dob;
    }
    public int getId_()
    {
        return this.id_;
    }
    public String getname()
    {
        return this.name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getMobile(){
        return this.mobile;
    }
    public String getDob(){
        return this.dob;
    }
    public String getPas(){
        return this.pas;
    }
}
