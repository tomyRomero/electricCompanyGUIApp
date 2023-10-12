import java.io.Serializable;
public class Customer implements Serializable
{


    private int objID;
    private String fName;
    private String lName;
    private String userName;
    private String passWord;
    private int meterNum;
    private int balance;
    private boolean adminCheck;
    private boolean metterStatus;
    private double[] Consumption;


    public Customer(String firstName,String lastName,boolean meterStatus,String user,String pass,boolean checkAdmin,int ID ,int meterNumber, int balanceDue)
    {
        objID=ID;
        fName=firstName;
        lName=lastName;
        adminCheck=checkAdmin;
        userName=user;
        passWord=pass;
        meterNum=meterNumber;
        balance=balanceDue;
        metterStatus=meterStatus;

    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setMetterStatus(boolean metterStatus) {
        this.metterStatus = metterStatus;
    }


    public String getlName() {
        return lName;
    }

    public int getBalance() {
        return balance;
    }

    public int getMeterNum() {
        return meterNum;
    }

    public int getObjID() {
        return objID;
    }

    public String getfName() {
        return fName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getUserName() {
        return userName;
    }

    public Boolean getMeterStatus()
    {
        return metterStatus;

    }

    public String toString()
    {

        String data=this.fName+this.lName;

        return data;
    }

    public void setConsumption(double[] consumption) {
        Consumption = consumption;
    }

    public double[] getConsumption() {
        return Consumption;
    }
}
