import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        {
            if ("Nimbus".equals(info.getName()))
            {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        //create frame
        JFrame frame = new JFrame();
        frame.setSize(400, 500);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //add a panel for username and password
        JPanel inputPanel = new JPanel();
        inputPanel.setSize(300, 150);
        inputPanel.setLocation(50, 100);
        //inputPanel.setBorder(BorderFactory.createBevelBorder(1));
        inputPanel.setLayout(null);


        //add labels for username and password
        JLabel username = new JLabel("UserName");
        username.setSize(100, 30);
        username.setFont(new Font("Arial", 14, 14));
        username.setLocation(10, 30);
        //username.setBorder(BorderFactory.createBevelBorder(1));

        inputPanel.add(username);


        JLabel passWord = new JLabel("PassWord");
        passWord.setSize(100, 30);
        passWord.setFont(new Font("Arial", 14, 14));
        passWord.setLocation(10, 70);
        //passWord.setBorder(BorderFactory.createBevelBorder(1));

        inputPanel.add(passWord);


        //add the text field and password field
        JTextField textField = new JTextField("Enter your UserName...");
        textField.setSize(100, 40);
        textField.setLocation(username.getX() + username.getWidth() + 20, username.getY());

        inputPanel.add(textField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(100, 100);
        scrollPane.setLocation(passWord.getX() + passWord.getWidth() + 20, passWord.getY());
        scrollPane.setLayout(null);

        JTextArea textArea = new JTextArea("Enter PassWord");
        textArea.setSize(100, 100);
        textArea.setLocation(passWord.getX() + passWord.getWidth() + 20, passWord.getY());

        scrollPane.add(textArea);
        inputPanel.add(scrollPane);


        //add button
        JButton submit = new JButton("Submit");
        submit.setSize(150 , 40);
        submit.setLocation(115, 300);

        //add tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(250, 450);
        tabbedPane.setLocation(25, 25);

        tabbedPane.addTab("Login", null);
        tabbedPane.addTab("About", null);


        JPanel loginPanel = new JPanel();
        loginPanel.setSize(300, 400);
        loginPanel.setLocation(25, 25);
        loginPanel.setLayout(null);

        loginPanel.add(inputPanel);
        loginPanel.add(submit);


        frame.getContentPane().add(tabbedPane);

        frame.setVisible(true);

    }
}
