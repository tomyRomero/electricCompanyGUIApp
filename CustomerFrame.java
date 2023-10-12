import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class CustomerFrame extends JFrame
{
    private static File usersFile=new File("./Users");

    private static Customer mainAccount;
    private static CustomerFrame first;
    private static CustomerFrame second;
    private static CustomerFrame third;
    private static CustomerFrame fourth;
    private static CustomerFrame fifth;

    private JPanel main;
    private JLabel welcome;
    private JButton viewInformation;
    private JButton payBill;
    private JButton printAverageConsumption;
    private JButton changePassword;
    private JButton exit;
    private JLabel top;
    private JLabel fName;
    private JLabel getfName;
    private JLabel userName;
    private JLabel getUserName;
    private JLabel meterStatus;
    private JLabel getMeterStatus;
    private JLabel meterNum;
    private JLabel getMeterNum;
    private JLabel balanceDue;
    private JLabel getBalanceDue;
    private JButton Back;

    private JLabel AccountInfo;
    private JLabel accountBalance;
    private JLabel getAccountBalance;
    private JLabel input;
    private JTextField payment;
    private JButton Pay;
    private JPanel billPanel;
    private JButton bBack;

    private JPanel conPanel;
    private JLabel Consumption;
    private JLabel averageConsumption;
    private JLabel getConsumption;
    private JButton bbBack;

    private JPanel changePanel;
    private JLabel changeLab;
    private JLabel Password;
    private JTextField passField;
    private JLabel newPass;
    private JTextField newPassField;
    private JButton changeBack;
    private JButton changePasswordb;

    private static double sum;

    private JPanel viewPanel;

    public CustomerFrame()
    {
        mainAccount=LoginFrame.getMainAccount();
        setTitle("WAPA");
        setSize(400,400);
        buildButtonPanel();
        add(main);
        setVisible(true);
    }
    public CustomerFrame(int i)
    {
        mainAccount=LoginFrame.getMainAccount();
        setTitle("WAPA");
        setSize(400,400);
        buildViewPanel();
        add(viewPanel);
        setVisible(true);
    }

    public CustomerFrame(int i,int j)
    {
        mainAccount=LoginFrame.getMainAccount();
        setTitle("WAPA");
        setSize(300,300);
        buildBillPanel();
        add(billPanel);

        setVisible(true);
    }

    public CustomerFrame(int i,int j, int k)
    {
        mainAccount=LoginFrame.getMainAccount();
        setTitle("WAPA");
        setSize(300,300);
        buildAverage();
        add(conPanel);

        setVisible(true);
    }
    public CustomerFrame(int i,int j,int k,int n)
    {
        mainAccount=LoginFrame.getMainAccount();
        setTitle("WAPA");
        setSize(300,300);
        buildChangePanel();
        add(changePanel);

        setVisible(true);

    }

    public void buildChangePanel()
    {
        changeLab=new JLabel("Change Password");
        JPanel sub0=new JPanel();
        sub0.setLayout(new FlowLayout());
        sub0.add(changeLab,CENTER_ALIGNMENT);

        Password=new JLabel("Password: ");
        passField=new JTextField(10);
        JPanel sub1=new JPanel();
        sub1.setLayout(new GridLayout(1,1));
        sub1.add(Password);
        sub1.add(passField);

        newPass=new JLabel("New Password: ");
        newPassField=new JTextField(10);
        JPanel sub2=new JPanel();
        sub2.setLayout(new GridLayout(1,1));
        sub2.add(newPass);
        sub2.add(newPassField);

        changeBack=new JButton("Back");
        changeBack.addActionListener(new changeBackListener());
        changePasswordb=new JButton("Change Password");
        changePasswordb.addActionListener(new changePassListener());
        JPanel sub3=new JPanel();
        sub3.setLayout(new GridLayout(1,1));
        sub3.add(changeBack);
        sub3.add(changePasswordb);

        changePanel=new JPanel();
        changePanel.setLayout(new GridLayout(4,1));
        changePanel.add(sub0);
        changePanel.add(sub1);
        changePanel.add(sub2);
        changePanel.add(sub3);


    }

    public void buildAverage()
    {
        consumptionFiller(mainAccount.getConsumption());
        String averageInfo=Double.toString(averageConsumption(mainAccount.getConsumption()));

        Consumption=new JLabel("Consumption");
        JPanel sub0=new JPanel();
        sub0.setLayout(new FlowLayout());
        sub0.add(Consumption,CENTER_ALIGNMENT);

        averageConsumption=new JLabel("Average Consumption: ");
        getConsumption=new JLabel(averageInfo);
        JPanel sub=new JPanel();
        sub.setLayout(new GridLayout(1,1));
        sub.add(averageConsumption);
        sub.add(getConsumption);

        bbBack=new JButton("Back");
        bbBack.addActionListener(new bbbackListener());

        conPanel=new JPanel();
        conPanel.setLayout(new GridLayout(3,1));
        conPanel.add(sub0);
        conPanel.add(sub);
        conPanel.add(bbBack);


    }

    public void buildBillPanel()
    {
        AccountInfo=new JLabel("Account Payment");
        JPanel aPay=new JPanel();
        aPay.setLayout(new FlowLayout());
        aPay.add(AccountInfo,CENTER_ALIGNMENT);

        accountBalance=new JLabel("Account Balance Due: ");
        String aBal=Integer.toString(mainAccount.getBalance());
        getAccountBalance=new JLabel(aBal);
        JPanel bal=new JPanel();
        bal.setLayout(new GridLayout(1,1));
        bal.add(accountBalance);
        bal.add(getAccountBalance);

        input=new JLabel("Payment Amount");
        payment=new JTextField(10);
        JPanel atext=new JPanel();
        atext.setLayout(new GridLayout(1,1));
        atext.add(input);
        atext.add(payment);

        bBack=new JButton("Back");
        bBack.addActionListener(new bbackListener());
        Pay=new JButton("Pay");
        Pay.addActionListener(new payListener());

        JPanel buttonP=new JPanel();
        buttonP.setLayout(new GridLayout(1,1));
        buttonP.add(bBack);
        buttonP.add(Pay);

        billPanel=new JPanel();
        billPanel.setLayout(new GridLayout(4,1));
        billPanel.add(aPay);
        billPanel.add(bal);
        billPanel.add(atext);
        billPanel.add(buttonP);

    }

    public void buildViewPanel()
    {

        top=new JLabel("Account Information");

        JPanel topP=new JPanel();
        topP.setLayout(new FlowLayout());
        topP.add(top,CENTER_ALIGNMENT);

        fName=new JLabel("Name: ");
        getfName=new JLabel(mainAccount.getfName()+" "+mainAccount.getlName());
        JPanel fname=new JPanel();
        fname.setLayout(new GridLayout(1,1));
        fname.add(fName);
        fname.add(getfName);

        userName=new JLabel("UserName: ");
        getUserName=new JLabel(mainAccount.getUserName());
        JPanel user=new JPanel();
        setLayout(new GridLayout(1,1));
        user.add(userName);
        user.add(getUserName);


        String meterStat;
        meterStatus=new JLabel("Mater Status: ");
        if (mainAccount.getMeterStatus())
        {
            meterStat="Connected";
        }
        else
        {
            meterStat="Disconnected";
        }

        getMeterStatus=new JLabel(meterStat);
        JPanel meterstatus=new JPanel();
        meterstatus.setLayout(new GridLayout(1,1));
        meterstatus.add(meterStatus);
        meterstatus.add(getMeterStatus);

        meterNum=new JLabel("Meter Number: ");
        String meterN=Integer.toString(mainAccount.getMeterNum());
        getMeterNum=new JLabel(meterN);
        JPanel meterP=new JPanel();
        meterP.setLayout(new GridLayout(1,1));
        meterP.add(meterNum);
        meterP.add(getMeterNum);

        balanceDue=new JLabel("Balance Due: ");
        String balanceD=Integer.toString(mainAccount.getBalance());
        getBalanceDue=new JLabel(balanceD);
        JPanel bal=new JPanel();
        bal.setLayout(new GridLayout(1,1));
        bal.add(balanceDue);
        bal.add(getBalanceDue);

        Back=new JButton("Back");
        Back.addActionListener(new backListener());

        viewPanel=new JPanel();
        viewPanel.setLayout(new GridLayout(7,1));
        viewPanel.add(topP);
        viewPanel.add(fname);
        viewPanel.add(meterstatus);
        viewPanel.add(meterP);
        viewPanel.add(bal);
        viewPanel.add(Back);


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

    public void buildButtonPanel()
    {
        Dimension test=new Dimension(1,2);
        welcome=new JLabel("Welcome Customer");
        JPanel label=new JPanel();
        label.setLayout(new FlowLayout());
        label.add(welcome,CENTER_ALIGNMENT);

        viewInformation=new JButton("View Account Information");
        viewInformation.setMaximumSize(test);
        viewInformation.addActionListener(new viewListener());

        payBill=new JButton("Pay Bill");
        payBill.setPreferredSize(test);
        payBill.addActionListener(new billListener());


        printAverageConsumption=new JButton("Average Consumption");
        printAverageConsumption.setPreferredSize(test);
        printAverageConsumption.addActionListener(new averageListener());


        changePassword=new JButton("Change Password");
        changePassword.setPreferredSize(test);
        changePassword.addActionListener(new changeListener());


        exit=new JButton("Exit");
        exit.setPreferredSize(test);
        exit.addActionListener(new exitListener());

        main=new JPanel();
        main.setLayout(new GridLayout(7,1));
        main.add(label);
        main.add(viewInformation);
        main.add(payBill);
        main.add(printAverageConsumption);
        main.add(changePassword);
        main.add(exit);
    }
    private class viewListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                second=new CustomerFrame(1);

            } catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }
    private class billListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                third=new CustomerFrame(1,1);

            } catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    public static void consumptionFiller(double [] Consumption)
    {
        Consumption = new double[10];
        for(int i=0;i<Consumption.length;i++)
        {
            Consumption[i] = randomFill();
        }
        mainAccount.setConsumption(Consumption);
    }

    public static double randomFill()
    {
        Random rand = new Random();
        double randomNum = rand.nextDouble();
        return randomNum;
    }

    public static double averageConsumption(double [] Consumption)
    {
        double temp;
        double temp2;
        for (int i = 0; i <Consumption.length ; i++)
        {
            temp=Consumption[i];
            sum=sum+temp;
        }
        double average=sum/Consumption.length;

        return average;
    }

    private class averageListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                fourth=new CustomerFrame(1,1,1);

            } catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }
    private class changeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                fifth=new CustomerFrame(1,1,1,1);

            } catch (Exception ex)
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
                LoginFrame lframe=new LoginFrame();
                AccountCreator.writeObjectToFile(mainAccount,mainAccount.getObjID());

            } catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    private class backListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                second.setVisible(false);
                first=new CustomerFrame();

            }
            catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    private class bbackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                first=new CustomerFrame();

            }
            catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    private class bbbackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                setVisible(false);
                first=new CustomerFrame();

            }
            catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    private class payListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String funds=payment.getText();
                int fundsDetach=Integer.parseInt(funds);
                int temp=mainAccount.getBalance();
                int newBalance=temp - fundsDetach;
                mainAccount.setBalance(newBalance);

                if (mainAccount.getBalance()==0)
                {
                    payment.setText("0");
                    String balance=Integer.toString(newBalance);
                    getAccountBalance.setText(balance);
                    mainAccount.setMetterStatus(true);
                    JOptionPane.showMessageDialog(null,"Payment Processed");
                }
                else if (mainAccount.getBalance()<0)
                {
                    payment.setText("0");
                    getAccountBalance.setText("0");
                    mainAccount.setBalance(0);
                    mainAccount.setMetterStatus(true);
                    JOptionPane.showMessageDialog(null,"No more Payments Required");

                }
                else
                {
                    payment.setText("0");
                    String balance=Integer.toString(newBalance);
                    getAccountBalance.setText(balance);
                    mainAccount.setMetterStatus(false);
                    JOptionPane.showMessageDialog(null,"Payment Processed");
                }


            }
            catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    private class changeBackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                fifth.setVisible(false);
                first=new CustomerFrame();

            }
            catch (Exception ex)
            {

                System.out.println(ex + "Caught");
            }

        }
    }

    private class changePassListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String oldPass= passField.getText();
                String newPass=newPassField.getText();

                if (oldPass.equals(mainAccount.getPassWord()))
                {
                    modifyFile(usersFile.getAbsolutePath(),oldPass,newPass);
                    mainAccount.setPassWord(newPass);
                    JOptionPane.showMessageDialog(null,"Password Changed");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Invalid Password/Password not Linked to Account:  Try Another");
                }


            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Invalid Password/Password not Linked to Account:  Try Another");
                System.out.println(ex + "Caught");
            }

        }
    }





}