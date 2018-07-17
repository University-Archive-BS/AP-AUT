package GUI;

import Models.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class NewDownloadFrame extends JFrame
{
    private JPanel frame;

    private JPanel centerPanel;

    private JPanel URLPanel;
    private JLabel URLLabel;
    private JTextField URLField;

    private JPanel pathPanel;
    private JLabel pathLabel;
    private JTextField pathField;

    private JButton fileChooserButton;
    private JFileChooser fileChooser;

    private JPanel queuePanel;
    private JLabel queueOrNotLabel;
    private JCheckBox checkBox;

    private JPanel downPanel;
    private JButton confirmButton;
    private JButton cancelButton;

    private DownloadManager downloadManager;

    public NewDownloadFrame(DownloadManager downloadManager)
    {

        super("New Download");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(NewDownloadFrame.HIDE_ON_CLOSE);

        this.downloadManager = downloadManager;

        frame = new JPanel(new BorderLayout(10, 10));
        frame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setBackground(Color.decode("#dff8d0"));
        getContentPane().add(frame);

        centerPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        centerPanel.setOpaque(false);
        setURL();
        setPath();
        setQueueOrNot();
        frame.add(centerPanel, BorderLayout.CENTER);

        setDownPanel();
        frame.add(downPanel, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(confirmButton);
        if (DownloadManager.isEnglish)
        {
            english();
        }
        else
        {
            farsi();
        }
        showFrame();
    }

    public void setURL()
    {
        URLPanel = new JPanel(new BorderLayout(23, 5));
        URLPanel.setOpaque(false);

        URLLabel = new JLabel("URL: ");
        URLPanel.add(URLLabel, BorderLayout.WEST);
        URLField = new JTextField();
        URLPanel.add(URLField, BorderLayout.CENTER);

        centerPanel.add(URLPanel);
    }

    public void setPath()
    {
        pathPanel = new JPanel(new BorderLayout(5, 5));
        pathPanel.setOpaque(false);

        pathLabel = new JLabel("Save to: ");
        pathPanel.add(pathLabel, BorderLayout.WEST);
        pathField = new JTextField("C:\\Users\\Ali\\Desktop");
        pathField.setForeground(new Color(89, 89, 89));
        pathPanel.add(pathField, BorderLayout.CENTER);

        Icon fileChooserIcon = new ImageIcon(getClass().getResource("/Icons/fileChooserIcon.png"));
        fileChooserButton = new JButton("Browse", fileChooserIcon);
        fileChooserButton.setForeground(Color.decode("#32363f"));
        fileChooserButton.setFont(new Font("Titillium Web", 4, 20));
        fileChooserButton.addActionListener(new NewDownloadActionListener());
        pathPanel.add(fileChooserButton, BorderLayout.EAST);

        centerPanel.add(pathPanel);
    }

    public void setFileChooserHttp()
    {
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Browse For Folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getName());
            pathField.setText(selectedFile.getAbsolutePath());
        }
    }

    public void setQueueOrNot()
    {
        queuePanel = new JPanel(new BorderLayout(5, 5));
        queuePanel.setOpaque(false);

        queueOrNotLabel = new JLabel("add to Queue: ");
        queueOrNotLabel.setOpaque(false);
        queuePanel.add(queueOrNotLabel, BorderLayout.WEST);

        checkBox = new JCheckBox("Yes");
        checkBox.setOpaque(false);
        checkBox.addActionListener(new NewDownloadActionListener());
        queuePanel.add(checkBox, BorderLayout.CENTER);
        centerPanel.add(queuePanel);
    }

    public void setDownPanel()
    {
        downPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        downPanel.setOpaque(false);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new NewDownloadActionListener());
        cancelButton.setForeground(Color.decode("#32363f"));
        cancelButton.setFont(new Font("Titillium Web", 4, 20));
        downPanel.add(cancelButton);
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new NewDownloadActionListener());
        confirmButton.setForeground(Color.decode("#32363f"));
        confirmButton.setFont(new Font("Titillium Web", 4, 20));
        downPanel.add(confirmButton);
    }

    /**
     * I handle the actions here
     */
    public void showFrame()
    {
        //pack();
        repaint();
        setVisible(true);
    }

    public void farsi()
    {
        queueOrNotLabel.setText(":  اضافه کردن به صف");
        checkBox.setText("بله");
        setName("دانلود جدید");
        cancelButton.setText("انصراف");
        confirmButton.setText("تایید");
        URLLabel.setText(": آدرس");
        fileChooserButton.setText("انتخاب");
        pathLabel.setText(":  ذخیره در");
    }

    public void english()
    {
        queueOrNotLabel.setText("add to Queue: ");
        checkBox.setText("Yes");
        setName("New Download");
        cancelButton.setText("Cancel");
        confirmButton.setText("Confirm");
        URLLabel.setText("URL: ");
        fileChooserButton.setText("Browse");
        pathLabel.setText("Save to: ");
    }

    private class NewDownloadActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource().equals(fileChooserButton))
            {
                System.out.println("fileChooserButton");
                setFileChooserHttp();
            }
            else if (e.getSource().equals(confirmButton))
            {
                if (URLField.getText().contains(Setting.bannedSite))
                {
                    System.out.println("ban ban");
                    JOptionPane.showMessageDialog(frame, "This URL is banned!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (downloadManager.getDownloadList().getDownloadVector().size() == Setting.maxSimultaneouslyDownload)
                {
                    System.out.println("maxSimultaneouslyDownload");
                    JOptionPane.showMessageDialog(frame, "Maximum number of downloads is " + Setting.maxSimultaneouslyDownload +" !", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("confirmButton");
                Download temp = new Download(URLField.getText(), pathField.getText());
                if (!checkBox.isSelected())
                {
                    downloadManager.getDownloadList().addDownload(temp);
                }
                else
                {
                    System.out.println("No Queue");
                }
                //setVisible(false); //you can't see me!
                //DownloadManager.showFrame();
                dispose(); //Destroy the JFrame object
            }
            else if (e.getSource().equals(cancelButton))
            {
                System.out.println("cancelButton");
                //setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
            }
            else if (checkBox.isSelected())
            {
                System.out.println("checkBox is Selected");
            }
            else
            {
                System.out.println("Extra Buttons");
            }
        }

    }
}
