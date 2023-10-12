import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class AccountCreator extends JFrame
{
    private static String AdminAccess;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField username;
    private JTextField password;
    private JPanel jPanel;
    private static File usersFile=new File("./Users");
    private static final File dataBase=new File("./dataBase");
    private ArrayList<Customer> CustomerArray;

    public AccountCreator(Boolean adminAccess)
    {
        String Admin=Boolean.toString(adminAccess);
        setAdminAccess(Admin);

        setTitle("WAPA");
        setSize(400,400);

        JLabel customer=new JLabel("Create Account");

        add(customer);

        buildAccountPanel();
        add(jPanel);

        setVisible(true);
    }

    public static void setAdminAccess(String adminAccess)
    {
        AdminAccess = adminAccess;
    }

    public static String getAdminAccess()
    {
        return AdminAccess;
    }

    public void buildAccountPanel()
    {
        JLabel FirstName=new JLabel("First Name:");
        firstNameField=new JTextField(10);
        JLabel jLabel=new JLabel("Last Name:");
        lastNameField=new JTextField(10);
        JLabel jLabel1=new JLabel("Username:");
        username=new JTextField(10);
        JLabel jLabel2=new JLabel("Password:");
        password=new JTextField(10);
        JButton jButton=new JButton("Create Account");
        JButton jButton1=new JButton("Back");
        jButton1.addActionListener((new backListener()));
        jButton.addActionListener(new createListener());
        jPanel=new JPanel();

        jPanel.setLayout(new GridLayout(5,2));
        jPanel.add(FirstName);
        jPanel.add(firstNameField);
        jPanel.add(jLabel);
        jPanel.add(lastNameField);
        jPanel.add(jLabel1);
        jPanel.add(username);
        jPanel.add(jLabel2);
        jPanel.add(password);
        jPanel.add(jButton);
        jPanel.add(jButton1);


    }
    private class backListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Boolean adminAcess=Boolean.valueOf(getAdminAccess());
            try
            {
                if (adminAcess)
                {
                    setVisible(false);
                    AdminFrame b=new AdminFrame();
                }
                else
                {
                    setVisible(false);
                    LoginFrame a=new LoginFrame();
                }

            }
            catch (Exception ex)
            {

                System.out.println(ex+ "Caught");
            }

        }
    }
    public static void writeObjectToFile(Customer serObj,int lineNum)
    {

        try {
            String FileName = String.valueOf(lineNum) + ".dat";

            File objFile = new File(dataBase, FileName);


            if (objFile.createNewFile())
            {
                FileOutputStream fileOut = new FileOutputStream(objFile);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(serObj);
                objectOut.close();
                System.out.println("The Object  was successfully written to a file");
            }
            else if (!objFile.createNewFile())
            {
                FileOutputStream fileOut = new FileOutputStream(objFile);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(serObj);
                objectOut.close();
                System.out.println("The Object  was successfully saved to an existing file");
            }
            else
            {
                System.out.println("File Creation Error");
            }

        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public int fileNum(File file)throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    public static void createUserOjects(ArrayList CustomerArray)throws IOException
    {
        Scanner dataReader=new Scanner(usersFile);

        while (dataReader.hasNext())
        {
            String fName=dataReader.next();
            String lName=dataReader.next();
            boolean meter=Boolean.parseBoolean(dataReader.next());
            String user=dataReader.next();
            String pass=dataReader.next();
            boolean adminCheck=Boolean.parseBoolean(dataReader.next());
            int ID=Integer.parseInt(dataReader.next());
            int meterNum=Integer.parseInt(dataReader.next());
            int balance=Integer.parseInt(dataReader.next());

            Customer one = new Customer(fName,lName,meter,user,pass,adminCheck,ID,meterNum,balance);
            writeObjectToFile(one,ID);
            try
            {
                CustomerArray.add(one);
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }

        }
    }

    public ArrayList<Customer> getCustomerArray()
    {
        return CustomerArray;
    }

    private class createListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {


                Random ranNum=new Random();
                int num=ranNum.nextInt(500);

                String user=username.getText();
                String pass=password.getText();
                String fName=firstNameField.getText();
                String lName=lastNameField.getText();
                PrintWriter loginWriter = new PrintWriter(new FileOutputStream(usersFile, true ));
                loginWriter.println(fName+" " +lName+" "+ " false" +" "+ user+ " " +pass+ " " + AdminAccess + " " + fileNum(usersFile) + " " + num + " 200");



                loginWriter.close();
                boolean AdminMessage= Boolean.valueOf(AdminAccess);
                if (AdminMessage)
                {
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Admin Account Created: Welcome Admin "+ fName);
                    AdminFrame main=new AdminFrame();
                }
                else
                {
                    setVisible(false);
                    JOptionPane.showMessageDialog(null,"Account Created/Proceed to Login" + "     Meter Status:Disconnected" + "         Meter #" + num +"       Start up Fee Due: 200");
                    LoginFrame main=new LoginFrame();
                }

                createUserOjects(CustomerArray);



            }
            catch (Exception ex)
            {

                System.out.println(ex+ "Caught");
            }

        }
    }
}

