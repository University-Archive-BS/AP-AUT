package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Setting
{
    private JFrame frame;
    private JPanel superPanel;

    private JPanel eastSuperPanel;
    private JLabel filteredSites;
    private JTextArea filter;
    public static String bannedSite;


    private JPanel centerSuperPanel;

    private JPanel centerPanel;

    private JPanel maxPanel;
    private JLabel maxSimultaneouslyDownloadLabel;
    private JSpinner spinnerDownloads;
    public static int maxSimultaneouslyDownload = 100;

    private JPanel pathPanel;
    private JLabel pathLabel;
    private JTextField pathField;
    private JFileChooser fileChooser;
    private JButton fileChooserButton;
    private static String path = "C:\\Users\\Ali\\Desktop";

    private JPanel downPanel;
    private JButton cancelButton;
    private JButton confirmButton;

    private JPanel lookAndFeelPanel;
    private JLabel lookAndFeelLabel;
    private JSpinner lookAndFeelSpinner;
    private static String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";


    public Setting()
    {
        frame = new JFrame("Setting");
        frame.setSize(750, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);

        superPanel = new JPanel(new BorderLayout(10, 10));
        superPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        superPanel.setBackground(Color.decode("#dff8d0"));

        eastSuperPanel = new JPanel(new BorderLayout(10, 10));
        eastSuperPanel.setBackground(Color.decode("#dff8d0"));
        eastSuperPanel.setOpaque(false);

        filteredSites = new JLabel("Enter the URL", SwingConstants.CENTER);
        filteredSites.setFont(new Font("Titillium Web", 4, 20));
        filteredSites.setOpaque(false);
        eastSuperPanel.add(filteredSites, BorderLayout.NORTH);

        filter = new JTextArea(" ");
        filter.setPreferredSize(new Dimension(100, 200));
        eastSuperPanel.add(filter, BorderLayout.CENTER);


        centerSuperPanel = new JPanel(new BorderLayout(10, 10));
        centerSuperPanel.setBackground(Color.decode("#dff8d0"));
        centerSuperPanel.setOpaque(false);

        centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setOpaque(false);
        setMaxSimultaneouslyDownload();
        setPathPanel();

        lookAndFeelPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        lookAndFeelPanel.setOpaque(false);
        lookAndFeel();
        centerPanel.add(lookAndFeelPanel);

        centerSuperPanel.add(centerPanel, BorderLayout.CENTER);

        downPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        downPanel.setOpaque(false);
        buttons();
        centerSuperPanel.add(downPanel, BorderLayout.SOUTH);

        superPanel.add(centerSuperPanel, BorderLayout.CENTER);
        superPanel.add(eastSuperPanel, BorderLayout.EAST);

        frame.getContentPane().add(superPanel);
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

    public void setMaxSimultaneouslyDownload()
    {
        maxPanel = new JPanel(new BorderLayout());
        maxPanel.setOpaque(false);
        maxSimultaneouslyDownloadLabel = new JLabel("Maximum of Simultaneously Download: ");
        maxPanel.add(maxSimultaneouslyDownloadLabel, BorderLayout.CENTER);

        SpinnerModel numberModel = new SpinnerNumberModel(5, 1, 100, 1);
        spinnerDownloads = new JSpinner(numberModel);
        spinnerDownloads.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                maxSimultaneouslyDownload = (int) spinnerDownloads.getValue();
                System.out.println(maxSimultaneouslyDownload);
            }
        });
        maxPanel.add(spinnerDownloads, BorderLayout.EAST);

        centerPanel.add(maxPanel);
    }

    public void setPathPanel()
    {
        pathPanel = new JPanel(new BorderLayout(10, 10));
        pathPanel.setOpaque(false);

        pathLabel = new JLabel("Save to: ");
        pathPanel.add(pathLabel, BorderLayout.WEST);
        pathField = new JTextField(path);
        pathField.setForeground(new Color(89, 89, 89));
        pathPanel.add(pathField, BorderLayout.CENTER);

        Icon fileChooserIcon = new ImageIcon(getClass().getResource("/Icons/fileChooserIcon.png"));
        fileChooserButton = new JButton("Browse", fileChooserIcon);
        fileChooserButton.setForeground(Color.decode("#32363f"));
        fileChooserButton.setFont(new Font("Titillium Web", 4, 20));
        fileChooserButton.addActionListener(new SettingActionListener());
        pathPanel.add(fileChooserButton, BorderLayout.EAST);

        centerPanel.add(pathPanel);
    }

    public void lookAndFeel()
    {
        lookAndFeelLabel = new JLabel("Look and Feel: ");
        lookAndFeelPanel.add(lookAndFeelLabel, BorderLayout.CENTER);

        String[] LFs = {"Choose Look & Feel", "Windows", "Nimbus", "CDE/Motif", "Metal", "Windows Classic"};
        SpinnerModel nameModel = new SpinnerListModel(LFs);
        lookAndFeelSpinner = new JSpinner(nameModel);
        lookAndFeelSpinner.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                if (lookAndFeelSpinner.getValue().equals(LFs[1]))
                {
                    System.out.println("Windows Look&Feel");
                    lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                }
                else if (lookAndFeelSpinner.getValue().equals(LFs[2]))
                {
                    System.out.println("Nimbus Look&Feel");
                    lookAndFeel = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
                }
                else if (lookAndFeelSpinner.getValue().equals(LFs[3]))
                {
                    System.out.println("CDE/Motif Look&Feel");
                    lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                }
                else if (lookAndFeelSpinner.getValue().equals(LFs[4]))
                {
                    System.out.println("Metal Look&Feel");
                    lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
                }
                else if (lookAndFeelSpinner.getValue().equals(LFs[5]))
                {
                    System.out.println("Windows Classic Look&Feel");
                    lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
                }
            }
        });
        lookAndFeelPanel.add(lookAndFeelSpinner, BorderLayout.EAST);
    }

    public void buttons()
    {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new SettingActionListener());
        cancelButton.setForeground(Color.decode("#32363f"));
        cancelButton.setFont(new Font("Titillium Web", 4, 20));
        downPanel.add(cancelButton);
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new SettingActionListener());
        confirmButton.setForeground(Color.decode("#32363f"));
        confirmButton.setFont(new Font("Titillium Web", 4, 20));
        downPanel.add(confirmButton);
    }

    public void setFileChooser()
    {
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Browse For Folder ");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getName());
            pathField.setText(selectedFile.getAbsolutePath());
            path = selectedFile.getAbsolutePath();
        }
    }

    public static String getLookAndFeel()
    {
        return lookAndFeel;
    }
    public static void setLookAndFeel(String input)
    {
        lookAndFeel = input;
    }

    public static String getPath()
    {
        return path;
    }

    public static int getMaxSimultaneouslyDownload()
    {
        return maxSimultaneouslyDownload;
    }
    public static void setPath(String input)
    {
        path = input;
    }

    public class SettingActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource().equals(fileChooserButton))
            {
                System.out.println("fileChooserButton");
                setFileChooser();
            }
            else if (e.getSource().equals(confirmButton))
            {
                System.out.println("confirmButton");
                bannedSite = filter.getText();
                System.out.println(bannedSite);
                try {
                    UIManager.setLookAndFeel(lookAndFeel);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(DownloadManager.getFrame());
                frame.setVisible(false); //you can't see me!
                DownloadManager.showFrame();
            }
            else if (e.getSource().equals(cancelButton))
            {
                System.out.println("cancelButton");
                frame.setVisible(false); //you can't see me!
                frame.dispose(); //Destroy the JFrame object
            }
            else
            {
                System.out.println("DD");
            }
        }
    }

    public void showFrame()
    {
        //pack();
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void farsi()
    {
        filteredSites.setText("آدرس را وارد کنید");
        frame.setName("تنظیمات");
        maxSimultaneouslyDownloadLabel.setText(":  حداکثر تعداد دانلود همزمان");
        lookAndFeelLabel.setText(":  رابط کاربری");
        cancelButton.setText("انصراف");
        confirmButton.setText("تایید");
        pathLabel.setText(":  ذخیره در");
    }

    public void english()
    {
        filteredSites.setText("Enter the URL");
        frame.setName("Setting");
        maxSimultaneouslyDownloadLabel.setText("Maximum of Simultaneously Download: ");
        lookAndFeelLabel.setText("Look and Feel: ");
        cancelButton.setText("Cancel");
        confirmButton.setText("Confirm");
        pathLabel.setText("Save to: ");
    }

}
