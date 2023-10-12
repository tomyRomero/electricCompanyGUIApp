import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminFrame extends JFrame
{
    private static File usersFile=new File("./Users");
    private static final File dataBase=new File("./dataBase");
    private static AdminFrame deleteFrame;

    private Customer info;

    private AdminFrame balance;

    private JPanel mainButtons;
    private JLabel WelcomeAdmin;
    private JButton addAccount;
    private JButton deleteAccount;
    private JButton printList;
    private JButton totalBalance;
    private JButton customersDisconnected;
    private JButton standardDeviation;
    private JButton Exit;

    private JPanel deletePan;
    private JLabel DeleteAccount;
    private JLabel delUser;
    private JLabel delPass;
    private JTextField delUserField;
    private JTextField delPassField;
    private JButton Back;
    private JButton DeleteAccountB;

    private JLabel balanceTotal;
    private JLabel getBalanceTotal;
    private JPanel totalBalPanel;
    private JButton totalBack;



    public AdminFrame()
    {

        setTitle("WAPA");
        setSize(400,400);

        buildButtonPanel();
        add(mainButtons);


        setVisible(true);

    }
    public AdminFrame(int i)
    {
        setTitle("WAPA");
        setSize(350,350);

        buildDelete();
        add(deletePan);


        setVisible(true);
    }

    public AdminFrame(int i,int j)
    {
        setTitle("WAPA");
        setSize(200,200);

       buildTotalBalancePanel();
       add(totalBalPanel);


        setVisible(true);
    }

    public void buildDelete()
    {
        DeleteAccount=new JLabel("Delete Account");
        JPanel sub01=new JPanel();
        sub01.setLayout(new FlowLayout());
        sub01.add(DeleteAccount,CENTER_ALIGNMENT);

        delUser=new JLabel("Account Username ");
        delUserField=new JTextField(10);
        JPanel sub02=new JPanel();
        sub02.setLayout(new GridLayout(1,1));
        sub02.add(delUser);
        sub02.add(delUserField);

        delPass=new JLabel("Account Password: ");
        delPassField=new JTextField(10);
        JPanel sub03=new JPanel();
        sub03.setLayout(new GridLayout(1,1));
        sub03.add(delPass);
        sub03.add(delPassField);

        Back=new JButton("Back");
        Back.addActionListener(new backListener());
        DeleteAccountB=new JButton("Delete Account");
        DeleteAccountB.addActionListener(new deleteInsideListener());
        JPanel sub04=new JPanel();
        sub04.setLayout(new GridLayout(1,1));
        sub04.add(Back);
        sub04.add(DeleteAccountB);

        deletePan=new JPanel();
        deletePan.setLayout(new GridLayout(4,1));
        deletePan.add(sub01);
        deletePan.add(sub02);
        deletePan.add(sub03);
        deletePan.add(sub04);

    }



    public void buildTotalBalancePanel()
    {
        listFilesForFolder(dataBase);

        balanceTotal=new JLabel("Total Customers Balance: ");

        getBalanceTotal=new JLabel();

        JPanel sub01=new JPanel();
        sub01.setLayout(new GridLayout(2,1));
        sub01.add(balanceTotal);
        sub01.add(getBalanceTotal);

        totalBack=new JButton("Back");
        totalBack.addActionListener(new totalBackListener());

        totalBalPanel=new JPanel();
        totalBalPanel.setLayout(new GridLayout(2,2));
        totalBalPanel.add(sub01);
        totalBalPanel.add(totalBack);

    }

    public void buildButtonPanel()
    {
        WelcomeAdmin=new JLabel("Welcome Administrator");
        JPanel sub01=new JPanel();
        sub01.setLayout(new FlowLayout());
        sub01.add(WelcomeAdmin,CENTER_ALIGNMENT);

        addAccount=new JButton("Add Admin Account");
        addAccount.addActionListener(new addAccountListener());

        deleteAccount=new JButton("Delete Account");
        deleteAccount.addActionListener(new deleteListener());


        printList=new JButton("List of Customers with High Consumption");


        totalBalance=new JButton("Total Balance Owed by  All Customers");
        totalBalance.addActionListener(new menuBalanceListener());


        customersDisconnected=new JButton("Disconnected Customers");


        standardDeviation=new JButton("Standard Deviation");


        Exit=new JButton("Exit");
        Exit.addActionListener(new exitListener());

        mainButtons=new JPanel();
        mainButtons.setLayout(new GridLayout(8,1));
        mainButtons.add(sub01);
        mainButtons.add(addAccount);
        mainButtons.add(deleteAccount);
        mainButtons.add(printList);
        mainButtons.add(totalBalance);
        mainButtons.add(customersDisconnected);
        mainButtons.add(standardDeviation);
        mainButtons.add(Exit);


    }


    public static void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);


            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class deleteInsideListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                Boolean deleteCheck;
                Boolean adminCheck;
                int objID;

                String user = delUserField.getText();
                String pass = delPassField.getText();
                Scanner userReader = new Scanner(usersFile);

                while (userReader.hasNext())
                {
                    if (userReader.next().equals(user) && userReader.next().equals(pass))
                    {
                        adminCheck = Boolean.parseBoolean(userReader.next());
                        objID=Integer.parseInt(userReader.next());
                        deleteCheck = true;

                        if (deleteCheck)
                        {
                            String FileName = String.valueOf(objID) + ".dat";
                            File objFile = new File(dataBase, FileName);

                            if (!objFile.createNewFile())
                            {
                                if (objFile.delete())
                                {
                                    modifyFile(usersFile.getAbsolutePath(),user,"null");
                                    modifyFile(usersFile.getAbsolutePath(),pass,"null");
                                    System.out.println("File ID " + FileName + " Was Successfully Deleted");
                                    JOptionPane.showMessageDialog(null,"File ID " + FileName + " Was Successfully Deleted");
                                }

                                else
                                {
                                    System.out.println("File Does Exist: Wasn't Able to be Deleted");
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"File Doesn't Exist");
                                System.out.println("File Doesn't Exist");
                            }

                        }

                    }
                    else
                    {
                        System.out.println("Scanning Files");
                    }



                }
            }
            catch (Exception ex)
            {
                System.out.println(ex + "Caught");
            }
        }
    }

    private class deleteListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                deleteFrame=new AdminFrame(1);
            }
            catch (Exception ex)
            {
                System.out.println(ex + "Caught");
            }
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


    public static Object dirList(File dir,int objID)
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

                if (LoginFrame.recountSt(file.getName(),userID)==1)
                {
                    one=ReadObjectFromFile(file.getAbsolutePath());
                }
                System.out.println(file.getName());

            } else
            {
                dirList(file,objID);
            }
        }
        return one;
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

    public double getBalanceTotal(ArrayList customerArray)
    {
        int objInt=0;
        double totalBalance=0;
        for (int i = 0; i <customerArray.size() ; i++)
        {
             info=(Customer) dirList(dataBase,objInt);
            totalBalance=totalBalance+info.getBalance();
            objInt++;
        }

        return totalBalance;
    }

    private class backListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                deleteFrame.setVisible(false);

                AdminFrame main=new AdminFrame();

            }
            catch (Exception ex)
            {
                System.out.println(ex + "Caught");
            }
        }
    }

    private class totalBackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);

                AdminFrame main=new AdminFrame();

            }
            catch (Exception ex)
            {
                System.out.println(ex + "Caught");
            }
        }
    }



    private class menuBalanceListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);

             balance =new AdminFrame(1,1);
            }
            catch (Exception ex)
            {
                System.out.println(ex + "Caught");
            }

        }
    }
    private class addAccountListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);

                AccountCreator first=new AccountCreator(true);
            }
            catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    private class exitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                LoginFrame main=new LoginFrame();
            }
            catch (Exception ex)
            {
                System.out.println(ex + "Caught");
            }

        }
    }




}
