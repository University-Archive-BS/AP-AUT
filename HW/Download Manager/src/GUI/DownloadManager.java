package GUI;

import Models.*;
import Files.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * This is the Main Frame of the download manager that will be shown...
 */
public class DownloadManager
{
    private static JFrame frame;

    TrayIcon icon;
    MenuItem openItem;
    MenuItem closeItem;

    private JMenuBar menuBar;
    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;
    private JMenu language;
    public static boolean isEnglish = true;
    private JMenuItem farsi;
    private JMenuItem english;
    private JMenu lookAndFeel;
    private JMenuItem metalLF;
    private JMenuItem nimbusLF;
    private JMenuItem CdeLF;
    private JMenuItem windowsLF;
    private JMenuItem windowsClassicLF;
    private JMenu downloadMenu;
    private JMenuItem newDownloadMenuItem;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem cancelMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem settingMenuItem;
    private JMenuItem exitMenuItem;


    private static JPanel rightSide;

    private JToolBar toolBar;
    private JButton newDownloadButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton cancelButton;
    private JButton sortButton;
    private JButton deleteButton;
    private JButton settingButton;
    private JTextField filterFilesTextField;

    private Setting setting;
    private AboutDialog aboutDialog;
    private NewDownloadFrame newDownloadFrame;


    private SettingFile settingFile;
    private static DownloadList downloadList;
    private Vector<Download> downloadVectorSave;  //I serialize this vector


    private JPanel leftSide;

    private JPanel downloadManagerLogo;

    private JToolBar leftToolBar;
    private JButton queueButton;
    private QueueFrame queueFrame;

    private JPanel myLogo;


    /**
     * This is the constructor of the main JDM frame
     */
    public DownloadManager()
    {
        loadSetting();
        try
        {
            UIManager.setLookAndFeel(settingFile.lookAndFeel);
        }
        catch (ClassNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (InstantiationException e1)
        {
            e1.printStackTrace();
        }
        catch (IllegalAccessException e1)
        {
            e1.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e1)
        {
            e1.printStackTrace();
        }

        frame = new JFrame("Java Download Manager");
        frame.setSize(settingFile.width, settingFile.height);
        //frame.setLocationRelativeTo(null);
        frame.setLocation(settingFile.X, settingFile.Y);
        frame.getContentPane().setLayout(new BorderLayout());

        setTray();
        setMenuBar();
        setLeftSide();
        setRightSide();
        showFrame();
    }

    /**
     * This method generates the popup menu
     * and also set the close button not to exit the program and bring it to the system tray.
     */
    public void setTray()
    {
        SystemTray systemTray = SystemTray.getSystemTray();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(getClass().getResource("/Icons/eagleIcon.png"));
        PopupMenu popupMenu = new PopupMenu();
        icon = new TrayIcon(image, "JDM", popupMenu);
        openItem = new MenuItem("Open JDM");
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(true);
            }
        });
        popupMenu.add(openItem);

        closeItem = new MenuItem("Close JDM");
        closeItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SystemTray.getSystemTray().remove(icon);
                System.exit(0);
            }
        });
        popupMenu.add(closeItem);
        icon.setImageAutoSize(true);
        try
        {
            systemTray.add(icon);
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method will create the MenuBar
     * create Download and Help Menu
     * create About MenuItem in Help Menu
     * create NewDownload, Resume, Pause, Cancel, Delete, Setting, Exit in Download Menu
     * and will set all of their configurations
     * This is the JMenuBar of the Frame.
     */
    public void setMenuBar()
    {
        //Here I created the menu bar
        menuBar = new JMenuBar();

        /*
        Here I Started to add the download menu
        then add the menu Items and their features
         */
        downloadMenu = new JMenu("Download");
        downloadMenu.setMnemonic(KeyEvent.VK_D);

        newDownloadMenuItem = new JMenuItem("New Download", KeyEvent.VK_N);
        newDownloadMenuItem.setToolTipText("New Download");
        KeyStroke newDownloadMenu = KeyStroke.getKeyStroke("control N");
        newDownloadMenuItem.setAccelerator(newDownloadMenu);
        newDownloadMenuItem.addActionListener(new JDMActionListener());
        downloadMenu.add(newDownloadMenuItem);

        downloadMenu.add(new JSeparator());

        resumeMenuItem = new JMenuItem("Resume", KeyEvent.VK_R);
        resumeMenuItem.setToolTipText("Resume");
        KeyStroke resumeMenu = KeyStroke.getKeyStroke("control R");
        resumeMenuItem.setAccelerator(resumeMenu);
        resumeMenuItem.addActionListener(new JDMActionListener());
        downloadMenu.add(resumeMenuItem);

        pauseMenuItem = new JMenuItem("Pause", KeyEvent.VK_P);
        pauseMenuItem.setToolTipText("Pause");
        KeyStroke pauseMenu = KeyStroke.getKeyStroke("control P");
        pauseMenuItem.setAccelerator(pauseMenu);
        pauseMenuItem.addActionListener(new JDMActionListener());
        downloadMenu.add(pauseMenuItem);

        cancelMenuItem = new JMenuItem("Cancel", KeyEvent.VK_C);
        cancelMenuItem.setToolTipText("Cancel");
        KeyStroke cancelMenu = KeyStroke.getKeyStroke("alt C");
        cancelMenuItem.setAccelerator(cancelMenu);
        cancelMenuItem.addActionListener(new JDMActionListener());
        downloadMenu.add(cancelMenuItem);

        downloadMenu.add(new JSeparator());

        deleteMenuItem = new JMenuItem("Delete", KeyEvent.VK_D);
        deleteMenuItem.setToolTipText("Delete");
        KeyStroke deleteMenu = KeyStroke.getKeyStroke("control D");
        deleteMenuItem.setAccelerator(deleteMenu);
        deleteMenuItem.addActionListener(new JDMActionListener());
        downloadMenu.add(deleteMenuItem);

        downloadMenu.add(new JSeparator());

        settingMenuItem = new JMenuItem("Setting", KeyEvent.VK_S);
        settingMenuItem.setToolTipText("Settings");
        KeyStroke settingMenu = KeyStroke.getKeyStroke("alt S");
        settingMenuItem.setAccelerator(settingMenu);
        settingMenuItem.addActionListener(new JDMActionListener());
        downloadMenu.add(settingMenuItem);

        downloadMenu.add(new JSeparator());

        exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit");
        KeyStroke exitMenu = KeyStroke.getKeyStroke("alt E");
        exitMenuItem.setAccelerator(exitMenu);
        exitMenuItem.addActionListener(new JDMActionListener());
        downloadMenu.add(exitMenuItem);

        //here I add the download menu to the menu bar
        menuBar.add(downloadMenu);


        /*
        here I started to make the help menu
        and the About menu Item and their features
         */
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        aboutDialog = new AboutDialog(frame);
        aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutMenuItem.setToolTipText("About");
        KeyStroke aboutMenu = KeyStroke.getKeyStroke("alt A");
        aboutMenuItem.setAccelerator(aboutMenu);
        aboutMenuItem.addActionListener(new JDMActionListener());
        helpMenu.add(aboutMenuItem);

        lookAndFeel = new JMenu("Look And Feel");
        lookAndFeel.setMnemonic(KeyEvent.VK_L);

        metalLF = new JMenuItem("Metal", KeyEvent.VK_M);
        metalLF.setToolTipText("Metal");
        metalLF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.SHIFT_MASK));
        metalLF.addActionListener(new JDMActionListener());
        lookAndFeel.add(metalLF);

        nimbusLF = new JMenuItem("Nimbus", KeyEvent.VK_N);
        nimbusLF.setToolTipText("Nimbus");
        nimbusLF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.SHIFT_MASK));
        nimbusLF.addActionListener(new JDMActionListener());
        lookAndFeel.add(nimbusLF);

        CdeLF = new JMenuItem("CDE/Motif", KeyEvent.VK_D);
        CdeLF.setToolTipText("CDE/Motif");
        CdeLF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.SHIFT_MASK));
        CdeLF.addActionListener(new JDMActionListener());
        lookAndFeel.add(CdeLF);

        windowsLF = new JMenuItem("Windows", KeyEvent.VK_W);
        windowsLF.setToolTipText("Windows");
        windowsLF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.SHIFT_MASK));
        windowsLF.addActionListener(new JDMActionListener());
        lookAndFeel.add(windowsLF);

        windowsClassicLF = new JMenuItem("Windows Classic", KeyEvent.VK_L);
        windowsClassicLF.setToolTipText("Windows Classic");
        windowsClassicLF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.SHIFT_MASK));
        windowsClassicLF.addActionListener(new JDMActionListener());
        lookAndFeel.add(windowsClassicLF);

        helpMenu.add(lookAndFeel);


        language = new JMenu("Language");

        farsi = new JMenuItem("فارسی", KeyEvent.VK_F);
        farsi.setToolTipText("زبان فارسی");
        farsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.SHIFT_MASK));
        farsi.addActionListener(new JDMActionListener());
        language.add(farsi);

        english = new JMenuItem("English", KeyEvent.VK_E);
        english.setToolTipText("English Language");
        english.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK));
        english.addActionListener(new JDMActionListener());
        language.add(english);

        helpMenu.add(language);

        //and here add the help menu to menu bar
        menuBar.add(helpMenu);

        //and at last I added the menu bar to the main frame
        frame.setJMenuBar(menuBar);
    }

    /**
     * This method will make all of things that related to the left black bar
     * make a black panel and place the the EagleGet logo
     * create the Queue, downloaded items and other tabs...
     * Layout Manager = Border layout
     * This is the West of the Main Frame.
     */
    public void setLeftSide()
    {
        //here I created the panel and set it's color
        leftSide = new JPanel(new BorderLayout());
        leftSide.setBackground(Color.decode("#32363f"));


        //create the JDM Logo to add to the Frame
        downloadManagerLogo = new JPanel();
        ImageIcon thisLogo = new ImageIcon(getClass().getResource("/Icons/logoInFrame.png"));
        JLabel logo = new JLabel(thisLogo);
        downloadManagerLogo.add(logo);
        //use transparent of the picture not to show the white background under the eagle
        downloadManagerLogo.setOpaque(false);
        //at last, I added the logo panel to the left panel
        leftSide.add(downloadManagerLogo, BorderLayout.NORTH);


        //here I create the leftSide Toolbar for 3 buttons that contains downloadLists
        leftToolBar = new JToolBar(JToolBar.VERTICAL);
        leftToolBar.setFloatable(false);
        leftToolBar.setBackground(new Color(50, 54, 63));

        Icon queueIcon = new ImageIcon(getClass().getResource("/Icons/queueIcon.png"));
        queueButton = new JButton("Queue", queueIcon);
        queueButton.setForeground(new Color(255, 255, 255));
        queueButton.setOpaque(false);
        queueButton.setFocusable(false);
        queueButton.addActionListener(new JDMActionListener());

        leftToolBar.add(Box.createRigidArea(new Dimension(0, 20)));
        leftToolBar.add(new JSeparator(SwingConstants.HORIZONTAL));
        leftToolBar.add(queueButton);
        leftToolBar.add(new JSeparator(SwingConstants.HORIZONTAL));
        leftToolBar.add(Box.createRigidArea(new Dimension(0, 300)));

        leftSide.add(leftToolBar, BorderLayout.CENTER);


        //create the author's panel to add to the left panel
        myLogo = new JPanel();
        ImageIcon myIcon = new ImageIcon(getClass().getResource("/Icons/myIcon.png"));
        JLabel myLabel = new JLabel(myIcon);
        myLogo.add(myLabel);
        //use transparent of the picture not to show the white background under the eagle
        myLogo.setOpaque(false);
        myLogo.setFocusable(true);
        //here I added the authors panel to the left panel
        leftSide.add(myLogo, BorderLayout.SOUTH);

        //and at last...Adding the left panel to the main panel
        frame.add(leftSide, BorderLayout.WEST);
    }

    /**
     * this method will create a panel that placed in the right side of the frame.
     * contains buttons and also list of the downloads
     * Layout Manager = Border Layout
     * The buttons are in a JToolBar that placed at the NORTH of the panel.
     * and the list of the downloads placed at the CENTER of the panel.
     */
    public void setRightSide()
    {
        //create the right panel and set color
        rightSide = new JPanel(new BorderLayout());
        rightSide.setBackground(Color.decode("#e7effb"));

        setToolBar();

        downloadList = new DownloadList();
        loadDownloadList();

        //at last I add the right panel to the main frame
        frame.add(rightSide, BorderLayout.CENTER);
    }

    /**
     * This method do the ToolBar part of the setRightSide method
     */
    public void setToolBar()
    {
        //create the toolbar of the buttons and avoid being floating
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.decode("#d0dff8"));

        //create the newDownload button and set the icon and other features
        Icon newDownloadIcon = new ImageIcon(getClass().getResource("/Icons/newDownloadIcon.png"));
        newDownloadButton = new JButton(newDownloadIcon);
        newDownloadButton.setToolTipText("New Download");
        newDownloadButton.setOpaque(false);
        newDownloadButton.setFocusable(false);
        newDownloadButton.addActionListener(new JDMActionListener());

        //create the pause button and set the icon and other features
        Icon pauseIcon = new ImageIcon(getClass().getResource("/Icons/pauseIcon.png"));
        pauseButton = new JButton(pauseIcon);
        pauseButton.setToolTipText("Pause");
        pauseButton.setOpaque(false);
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(new JDMActionListener());

        //create the resume button and set the icon and other features
        Icon resumeIcon = new ImageIcon(getClass().getResource("/Icons/resumeIcon.png"));
        resumeButton = new JButton(resumeIcon);
        resumeButton.setToolTipText("Resume");
        resumeButton.setOpaque(false);
        resumeButton.setFocusable(false);
        resumeButton.addActionListener(new JDMActionListener());

        //create the cancel button and set the icon and other features
        Icon cancelIcon = new ImageIcon(getClass().getResource("/Icons/cancelIcon.png"));
        cancelButton = new JButton(cancelIcon);
        cancelButton.setToolTipText("Cancel");
        cancelButton.setOpaque(false);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(new JDMActionListener());

        //create the sort button and set the icon and other features
        Icon sortIcon = new ImageIcon(getClass().getResource("/Icons/sortIcon.png"));
        sortButton = new JButton(sortIcon);
        sortButton.setToolTipText("Sort");
        sortButton.setOpaque(false);
        sortButton.setFocusable(false);
        sortButton.addActionListener(new JDMActionListener());

        //create the delete button and set the icon and other features
        Icon deleteIcon = new ImageIcon(getClass().getResource("/Icons/deleteIcon.png"));
        deleteButton = new JButton(deleteIcon);
        deleteButton.setToolTipText("Delete Download");
        deleteButton.setOpaque(false);
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new JDMActionListener());

        //create the setting button and set the icon and other features
        Icon settingIcon = new ImageIcon(getClass().getResource("/Icons/settingIcon.png"));
        settingButton = new JButton(settingIcon);
        settingButton.setToolTipText("Setting");
        settingButton.setOpaque(false);
        settingButton.setFocusable(false);
        settingButton.addActionListener(new JDMActionListener());

        //textfield for filtering files by searching words and other features
        filterFilesTextField = new JTextField("                                       ");
        filterFilesTextField.setToolTipText("Search");
        filterFilesTextField.setForeground(new Color(166, 166, 166));
        filterFilesTextField.setOpaque(false);
        filterFilesTextField.addActionListener(new JDMActionListener());

        //add the buttons to their tool bar and set the spaces and separators
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(newDownloadButton);
        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));

        toolBar.add(new JSeparator(SwingConstants.VERTICAL));

        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolBar.add(resumeButton);

        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolBar.add(pauseButton);

        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolBar.add(cancelButton);
        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));

        toolBar.add(new JSeparator(SwingConstants.VERTICAL));

        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolBar.add(sortButton);

        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolBar.add(new JSeparator(SwingConstants.VERTICAL));

        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolBar.add(deleteButton);
        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));

        toolBar.add(new JSeparator(SwingConstants.VERTICAL));

        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));

        toolBar.add(settingButton);

        toolBar.add(Box.createRigidArea(new Dimension(30, 0)));
        toolBar.add(filterFilesTextField);
        toolBar.add(Box.createRigidArea(new Dimension(1, 0)));

        //add the toolBar to the NORTH of the right panel
        rightSide.add(toolBar, BorderLayout.NORTH);
    }

    /**
     * This method will make the Main frame visible to show on the screens.
     */
    public static void showFrame()
    {
        //pack();
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    /**
     * This method will create the Setting Frame
     * contains where to save files
     * and how many downloads can perform together
     */
    public void setSettingFrame()
    {
        setting = new Setting();
    }

    /**
     * This method will create the About Frame
     * contains author's name, id, date, ...
     */
    public void showAboutDialog()
    {
        aboutDialog.showDialog();
    }

    /**
     * This method will create the New Download Frame
     */
    public void setNewDownloadFrame()
    {
        newDownloadFrame = new NewDownloadFrame(this);
    }

    /**
     * This is the main part of changing the Look and Feel
     * this method is called by the MouseHandler.
     */
    public void setUI()
    {
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public DownloadList getDownloadList()
    {
        return downloadList;
    }

    public static JPanel getRightSide()
    {
        return rightSide;
    }

    public static JFrame getFrame()
    {
        return frame;
    }

    /**
     * I handle the actions here
     */
    public class JDMActionListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //toolbar buttons
            if (e.getSource().equals(newDownloadButton))
            {
                System.out.println("newDownloadButton");
                setNewDownloadFrame();
            }
            else if (e.getSource().equals(resumeButton))
            {
                System.out.println("resumeButton");
            }
            else if (e.getSource().equals(pauseButton))
            {
                System.out.println("pauseButton");
            }
            else if (e.getSource().equals(cancelButton))
            {
                System.out.println("cancelButton");
            }
            else if (e.getSource().equals(deleteButton))
            {
                System.out.println("deleteButton");
                downloadList.actionOnDownload(0);

            }
            else if (e.getSource().equals(settingButton))
            {
                System.out.println("settingButton");
                setSettingFrame();
            }
            else if (e.getSource().equals(filterFilesTextField))
            {
                System.out.println(filterFilesTextField.getText());
            }


            //left toolbar Queue
            else if (e.getSource().equals(queueButton))
            {
                System.out.println("queueButton");
                //callDownloadQueue();
            }


            //for the menuItems
            else if (e.getSource().equals(newDownloadMenuItem))
            {
                System.out.println("newDownloadMenuItem");
                setNewDownloadFrame();
            }
            else if (e.getSource().equals(resumeMenuItem))
            {
                System.out.println("resumeMenuItem");
            }
            else if (e.getSource().equals(pauseMenuItem))
            {
                System.out.println("pauseMenuItem");
            }
            else if (e.getSource().equals(cancelMenuItem))
            {
                System.out.println("cancelMenuItem");
            }
            else if (e.getSource().equals(deleteMenuItem))
            {
                System.out.println("deleteMenuItem");
                downloadList.actionOnDownload(0);
            }
            else if (e.getSource().equals(settingMenuItem))
            {
                System.out.println("settingMenuItem");
                setSettingFrame();
            }
            else if (e.getSource().equals(exitMenuItem))
            {
                System.out.println("exitMenuItem");
                save();
                System.exit(0);
            }
            else if (e.getSource().equals(aboutMenuItem))
            {
                System.out.println("aboutMenuItem");
                showAboutDialog();
            }


            //Listener of the Metal
            else if (e.getSource().equals(metalLF))
            {
                System.out.println("Metal Look&Feel");
                try
                {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    Setting.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    setUI();
                }
                catch (ClassNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (InstantiationException e1)
                {
                    e1.printStackTrace();
                }
                catch (IllegalAccessException e1)
                {
                    e1.printStackTrace();
                }
                catch (UnsupportedLookAndFeelException e1)
                {
                    e1.printStackTrace();
                }
                showFrame();
            }
            //Listener of the Nimbus
            else if (e.getSource().equals(nimbusLF))
            {
                System.out.println("Nimbus Motif");
                try
                {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    Setting.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    setUI();
                }
                catch (ClassNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (InstantiationException e1)
                {
                    e1.printStackTrace();
                }
                catch (IllegalAccessException e1)
                {
                    e1.printStackTrace();
                }
                catch (UnsupportedLookAndFeelException e1)
                {
                    e1.printStackTrace();
                }
                showFrame();
            }
            //Listener of the Motif
            else if (e.getSource().equals(CdeLF))
            {
                System.out.println("Motif Look&Feel");
                try
                {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    Setting.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    setUI();
                }
                catch (ClassNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (InstantiationException e1)
                {
                    e1.printStackTrace();
                }
                catch (IllegalAccessException e1)
                {
                    e1.printStackTrace();
                }
                catch (UnsupportedLookAndFeelException e1)
                {
                    e1.printStackTrace();
                }
                showFrame();
            }
            //Listener of the Windows
            else if (e.getSource().equals(windowsLF))
            {
                System.out.println("Windows Look&Feel");
                try
                {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    Setting.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    setUI();
                }
                catch (ClassNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (InstantiationException e1)
                {
                    e1.printStackTrace();
                }
                catch (IllegalAccessException e1)
                {
                    e1.printStackTrace();
                }
                catch (UnsupportedLookAndFeelException e1)
                {
                    e1.printStackTrace();
                }
                showFrame();
            }
            //Listener of the Windows Classic
            else if (e.getSource().equals(windowsClassicLF))
            {
                System.out.println("windows Classic Look&Feel");
                try
                {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    Setting.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    setUI();
                }
                catch (ClassNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (InstantiationException e1)
                {
                    e1.printStackTrace();
                }
                catch (IllegalAccessException e1)
                {
                    e1.printStackTrace();
                }
                catch (UnsupportedLookAndFeelException e1)
                {
                    e1.printStackTrace();
                }
                showFrame();
            }

            else if (e.getSource().equals(farsi))
            {
                System.out.println("فارسی");
                farsi();
                showFrame();
            }
            else if (e.getSource().equals(english))
            {
                System.out.println("English");
                english();
                showFrame();
            }

            else
            {
                System.out.println("Extra Buttons");
            }
        }
    }


    public void farsi()
    {
        frame.setName("دانلود منیجر جاوا");
        icon.setToolTip("دانلود منیجر جاوا");
        openItem.setLabel("باز کردن");
        closeItem.setLabel("بستن");
        downloadMenu.setText("دانلود");
        downloadMenu.setToolTipText("دانلود");
        newDownloadMenuItem.setText("دانلود جدید");
        newDownloadMenuItem.setToolTipText("دانلود جدید");
        resumeMenuItem.setText("ادامه");
        resumeMenuItem.setToolTipText("ادامه");
        pauseMenuItem.setText("توقف");
        pauseMenuItem.setToolTipText("توقف");
        cancelMenuItem.setText("انصراف");
        cancelMenuItem.setToolTipText("انصراف");
        deleteMenuItem.setText("حذف");
        deleteMenuItem.setToolTipText("حذف");
        settingMenuItem.setText("تنظیمات");
        settingMenuItem.setToolTipText("تنظیمات");
        exitMenuItem.setText("بستن");
        exitMenuItem.setToolTipText("بستن");
        helpMenu.setText("راهنما");
        helpMenu.setToolTipText("راهنما");
        aboutMenuItem.setText("درباره ما");
        aboutMenuItem.setToolTipText("درباره ما");
        lookAndFeel.setText("رابط کاربری");
        lookAndFeel.setToolTipText("رابط کاربری");
        queueButton.setText("صف");
        newDownloadButton.setToolTipText("دانلود جدید");
        pauseButton.setToolTipText("توقف");
        resumeButton.setToolTipText("ادامه");
        cancelButton.setToolTipText("انصراف");
        sortButton.setToolTipText("مرتب سازی");
        deleteButton.setToolTipText("پاک کردن دانلود");
        settingButton.setToolTipText("تنظیمات");
        filterFilesTextField.setToolTipText("جستجو");
        aboutDialog.setName("درباره ما");
        language.setText("زبان");
        aboutDialog.farsi();
        isEnglish = false;
    }

    public void english()
    {
        frame.setName("Java Download Manager");
        icon.setToolTip("Java Download Manager");
        openItem.setLabel("Open");
        closeItem.setLabel("Close");
        downloadMenu.setText("Download");
        downloadMenu.setToolTipText("Download menu");
        newDownloadMenuItem.setText("New Download");
        newDownloadMenuItem.setToolTipText("add a new Download");
        resumeMenuItem.setText("Resume");
        resumeMenuItem.setToolTipText("resume download");
        pauseMenuItem.setText("Pause");
        pauseMenuItem.setToolTipText("pause download");
        cancelMenuItem.setText("Cancel");
        cancelMenuItem.setToolTipText("cancel download");
        deleteMenuItem.setText("Delete");
        deleteMenuItem.setToolTipText("remove a download");
        settingMenuItem.setText("Setting");
        settingMenuItem.setToolTipText("program settings");
        exitMenuItem.setText("Exit");
        exitMenuItem.setToolTipText("exit the program");
        helpMenu.setText("Help");
        helpMenu.setToolTipText("help menu");
        aboutMenuItem.setText("About Us");
        aboutMenuItem.setToolTipText("about us");
        lookAndFeel.setText("Look And Feel");
        lookAndFeel.setToolTipText("change look and feel");
        queueButton.setText("Queue");
        newDownloadButton.setToolTipText("add a new Download");
        pauseButton.setToolTipText("pause download");
        resumeButton.setToolTipText("resume download");
        cancelButton.setToolTipText("cancel download");
        sortButton.setToolTipText("Sort downloads");
        deleteButton.setToolTipText("remove download");
        settingButton.setToolTipText("settings");
        filterFilesTextField.setToolTipText("Search");
        aboutDialog.setName("About Us");
        language.setText("Language");
        aboutDialog.english();
        isEnglish = true;
    }


    //These are save methods

    public void save()
    {
        saveSetting();
        saveDownloadList();
        saveDeletedDownloads();
        generateZip();
    }

    public void generateZip()
    {
        String[] files = {"Files/List.jdm", "Files/Settings.jdm", "Files/DeletedURLs.txt"};
        String zipFile = "Export.zip";
        try
        {
            byte[] buffer = new byte[2048];
            FileOutputStream out = new FileOutputStream(zipFile);
            ZipOutputStream zip = new ZipOutputStream(out);
            for (int i = 0; i < files.length; i++)
            {
                File save = new File(files[i]);
                FileInputStream in = new FileInputStream(save);
                zip.putNextEntry(new ZipEntry(save.getName()));

                int length;
                while ((length = in.read(buffer)) > 0)
                {
                    zip.write(buffer, 0, length);
                }

                zip.closeEntry();
                in.close();
            }
            zip.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveDeletedDownloads()
    {
        try (FileWriter writer = new FileWriter("Files/DeletedURLs.txt", true); BufferedWriter buffer = new BufferedWriter(writer))
        {
            for (Download x : downloadList.getDeletedDownloadVector())
            {
                buffer.newLine();
                buffer.write(x.getURL());
                buffer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void saveDownloadList()
    {
        downloadVectorSave = downloadList.getDownloadVector();
        try (FileOutputStream fileOutputStream = new FileOutputStream("Files/List.jdm"))
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(downloadVectorSave);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadDownloadList()
    {
        try (FileInputStream fileInputStream = new FileInputStream("Files/List.jdm"))
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            downloadVectorSave = (Vector<Download>) objectInputStream.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        downloadList.setDownloadListVector(downloadVectorSave);
        downloadList.vectorPanelGenerator(downloadList.getDownloadVector());
        showFrame();
    }

    public void saveSetting()
    {
        settingFile = new SettingFile();
        settingFile.lookAndFeel = setting.getLookAndFeel();
        settingFile.maxSimultaneouslyDownload = setting.getMaxSimultaneouslyDownload();
        settingFile.path = setting.getPath();
        settingFile.X = frame.getX();
        settingFile.Y = frame.getY();
        settingFile.width = frame.getWidth();
        settingFile.height = frame.getHeight();

        try (FileOutputStream fileOutputStream = new FileOutputStream("Files/Settings.jdm"))
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(settingFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadSetting()
    {
        settingFile = null;
        try (FileInputStream fileInputStream = new FileInputStream("Files/Settings.jdm"))
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            settingFile = (SettingFile) objectInputStream.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        setting.maxSimultaneouslyDownload = settingFile.maxSimultaneouslyDownload;
        setting.setPath(settingFile.path);
        setting.setLookAndFeel(settingFile.lookAndFeel);
    }
}
