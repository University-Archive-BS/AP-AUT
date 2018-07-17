package GUI;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    private JLabel author;
    private JLabel id;
    private JLabel startDate;
    private JLabel finalDate;

    public AboutDialog(JFrame downloadManagerFrame) {
        super(downloadManagerFrame, "About");
        setLayout(new GridLayout(4, 1));
        setSize(350, 200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(208, 223, 248));
        getContentPane().setForeground(new Color(50, 54, 63));

        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setResizable(false);

        author = new JLabel("By: Ali Nazari", SwingConstants.CENTER);
        author.setFont(new Font("Titillium Web", 4, 20));
        add(author);
        id = new JLabel("9631075", SwingConstants.CENTER);
        id.setFont(new Font("Titillium Web", 4, 17));
        add(id);
        startDate = new JLabel("Started project on 12 May 2018", SwingConstants.CENTER);
        startDate.setFont(new Font("Titillium Web", 4, 14));
        add(startDate);
        finalDate = new JLabel("Finished on 3 June 2018", SwingConstants.CENTER);
        finalDate.setFont(new Font("Titillium Web", 4, 14));
        add(finalDate);
    }

    public void showDialog()
    {
        setVisible(true);
    }

    public void farsi()
    {
        setName("About");
        author.setText("کاری از علی نظری");
        startDate.setText("شروع پروژه از 22 اردیبهشت 97");
        finalDate.setText("اتمام پروژه در 14 خرداد 97");
    }

    public void english()
    {
        setName("About");
        author.setText("By: Ali Nazari");
        startDate.setText("Started project on 12 May 2018");
        finalDate.setText("Finished on 3 June 2018");
    }
}