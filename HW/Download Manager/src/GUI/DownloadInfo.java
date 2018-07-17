package GUI;

import Models.*;
import javax.swing.*;
import java.awt.*;

public class DownloadInfo extends JDialog
{
    //private JPanel filePanel;
    private JLabel fileLabel;
    private JTextField fileField;

    //private JPanel linkPanel;
    private JLabel linkLabel;
    private JTextField linkField;

    //private JPanel pathPanel;
    private JLabel pathLabel;
    private JTextField pathField;

    //private JPanel sizePanel;
    private JLabel sizeLabel;
    private JTextField sizeField;

    //private JPanel startPanel;
    private JLabel startLabel;
    private JTextField startField;

    public DownloadInfo(JFrame downloadManagerFrame, String name, Download download)
    {
        super(downloadManagerFrame, name);
        setLayout(new GridLayout(5, 2));
        setSize(350, 200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(208, 223, 248));
        getContentPane().setForeground(new Color(50, 54, 63));

        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setResizable(false);

        //filePanel = new JPanel(new BorderLayout());
        fileLabel = new JLabel("File:", SwingConstants.CENTER);
        fileLabel.setFont(new Font("Titillium Web", 4, 14));
        add(fileLabel);
        //filePanel.add(fileLabel, BorderLayout.WEST);
        fileField = new JTextField(download.getName());
        fileField.setEditable(false);
        add(fileField);
        //filePanel.add(fileField, BorderLayout.CENTER);
        //filePanel.setOpaque(false);
        //add(filePanel);

        //sizePanel= new JPanel(new BorderLayout());
        sizeLabel = new JLabel("Size:", SwingConstants.CENTER);;
        sizeLabel.setFont(new Font("Titillium Web", 4, 14));
        add(sizeLabel);
        //sizePanel.add(sizeLabel, BorderLayout.WEST);
        sizeField = new JTextField("This is a Test");
        sizeField.setEditable(false);
        add(sizeField);
        //sizePanel.add(sizeField, BorderLayout.CENTER);
        //sizePanel.setOpaque(false);
        //add(sizePanel);

        //pathPanel= new JPanel(new BorderLayout());
        pathLabel = new JLabel("Save To:", SwingConstants.CENTER);
        pathLabel.setFont(new Font("Titillium Web", 4, 14));
        add(pathLabel);
        //pathPanel.add(pathLabel, BorderLayout.WEST);
        pathField = new JTextField(download.getPath());
        pathField.setEditable(false);
        add(pathField);
        //.add(pathField, BorderLayout.CENTER);
        //pathPanel.setOpaque(false);
        //add(pathPanel);

        //startPanel= new JPanel(new BorderLayout());
        startLabel = new JLabel("Started:", SwingConstants.CENTER);
        startLabel.setFont(new Font("Titillium Web", 4, 14));
        add(startLabel);
        //startPanel.add(startLabel, BorderLayout.WEST);
        startField = new JTextField("This is a Test");
        startField.setEditable(false);
        add(startField);
        //startPanel.add(startField, BorderLayout.CENTER);
        //startPanel.setOpaque(false);
        //add(startPanel);

        //linkPanel = new JPanel(new BorderLayout());
        linkLabel = new JLabel("URL:", SwingConstants.CENTER);
        linkLabel.setFont(new Font("Titillium Web", 4, 14));
        add(linkLabel);
        //linkPanel.add(linkLabel, BorderLayout.WEST);
        linkField = new JTextField(download.getURL());
        linkField.setEditable(false);
        add(linkField);
        //linkPanel.add(linkField, BorderLayout.CENTER);
        //linkPanel.setOpaque(false);
        //add(linkPanel);

        repaint();
        setVisible(true);
    }

}
