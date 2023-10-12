import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
public class LoginFrame extends JFrame
{
    private static String AdminAccess;
    private static Customer mainAccount;
    private static int objID;
    private JLabel Login;
    private JLabel UserName;
    private JLabel PassWord;
    private JTextField userField;
    private JTextField passField;
    private JButton LoginButton;
    private JButton CreateAccount;
    private JPanel main;
    private JPanel middle;
    private JPanel top;
    private JPanel bottom;
    private static File usersFile=new File("./Users");
    private static final File dataBase=new File("./dataBase");


    public LoginFrame()
    {
        setSize(400,400);
        topPanel();
        middlePanel();
        bottomPanel();
        setTitle("WAPA");
        setLayout(new GridLayout(3,1));
        add(top);
        add(middle);
        add(bottom);
        setVisible(true);
    }

    public static Customer getMainAccount()
    {
        return mainAccount;
    }

    private void topPanel()
    {
        Login=new JLabel("Welcome to WAPA");
        top=new JPanel();
        top.add(Login);

    }
    private void middlePanel()
    {

        UserName=new JLabel("UserName:");
        PassWord=new JLabel("Password:");
        userField=new JTextField(10);
        passField=new JTextField(10);

        middle=new JPanel();

        middle.add(UserName);
        middle.add(userField);
        middle.add(PassWord);
        middle.add(passField);

    }

    private void bottomPanel()
    {
        LoginButton=new JButton("Login");
        CreateAccount=new JButton("New Account");

        bottom=new JPanel();

        LoginButton.addActionListener(new loginListener());
        CreateAccount.addActionListener((new accountListener()));
        bottom.add(CreateAccount);
        bottom.add(LoginButton);
    }

    public static Object dirList(File dir)
    {
        Object one=new Object();
        String[] fileList = dir.list();
        String dirPath = dir.getAbsolutePath();

        for (int i = 0; i < fileList.length; i++)
        {

            File file = new File(dirPath + "/" + fileList[i]);
            if (file.isFile())
            {
                String userID=String.valueOf(objID);

                if (recountSt(file.getName(),userID)==1)
                {
                    one=ReadObjectFromFile(file.getAbsolutePath());
                }
                System.out.println(file.getName());

            } else
            {
                dirList(file);
            }
        }
        return one;
    }

    static int recountSt(String str, String st) //recursion method
    {
        if (str.isEmpty())
        {
            return 0;
        }
        else
        {
            int count = recountSt(str.substring(1), st);
            if (str.startsWith(st))
            {
                count++;
            }
            return count;
        }
    }

    public static Object ReadObjectFromFile(String filepath)
    {

        try
        {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    private class loginListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            try
            {

                boolean loginCheck=false;
                boolean adminCheck=false;

                String user = userField.getText();
                String pass = passField.getText();
                Scanner userReader = new Scanner(usersFile);

                while (userReader.hasNext())
                {
                    if (userReader.next().equals(user) && userReader.next().equals(pass))
                    {
                        adminCheck = Boolean.parseBoolean(userReader.next());
                        objID=Integer.parseInt(userReader.next());
                        loginCheck = true;
                        System.out.println("pass");
                        break;
                    }
                    else
                    {
                        System.out.println("invalid");
                    }
                }

                if (adminCheck && loginCheck)
                {

                    try
                    {
                        mainAccount =(Customer) dirList(dataBase);
                        System.out.println(mainAccount.toString());
                        String info=Integer.toString(mainAccount.getMeterNum());
                        System.out.println(info);
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex);
                    }
                    setVisible(false);
                    AdminFrame first=new AdminFrame();

                }
                else if (loginCheck)
                {
                    try
                    {
                        mainAccount =(Customer) dirList(dataBase);

                        System.out.println(mainAccount.toString());
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex);
                    }
                    setVisible(false);
                    CustomerFrame first=new CustomerFrame();
                } else
                {
                    JOptionPane.showMessageDialog(null,"Invalid Username or Password" );
                }

            }
            catch (Exception ex)
            {
                System.out.println(ex + "error");

            }

        }
    }

    private class accountListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);

                AccountCreator first=new AccountCreator(false);


            }
            catch (Exception ex)
            {
                System.out.println(ex+ "Caught");
            }

        }
    }

    public static void main(String[] args)
    {
        LoginFrame main=new LoginFrame();


    }
}
