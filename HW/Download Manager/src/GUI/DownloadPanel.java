package GUI;

import Models.Download;
import Models.DownloadList;
import Models.Downloader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class DownloadPanel
{
    private DownloadList downloadList;
    private JPanel panel;
    private JLabel nameOfFile;
    private JProgressBar progressBar;
    private JLabel sizeOfFile;
    private Downloader downloader;

    public DownloadPanel(Download x, DownloadList list)
    {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 10));
        panel.setBackground(Color.decode("#e7effb"));

        downloadList = list;

        JPanel eagleGetLogo = new JPanel();
        ImageIcon eagleIcon = new ImageIcon(getClass().getResource("/Icons/eagleIcon.png"));
        JLabel eagle = new JLabel(eagleIcon);
        eagleGetLogo.add(eagle);
        eagleGetLogo.setOpaque(false);
        panel.add(eagleGetLogo, BorderLayout.WEST);


        JPanel centerOfPanel = new JPanel(new BorderLayout());
        centerOfPanel.setOpaque(false);

        nameOfFile = new JLabel(x.getName());
        nameOfFile.setFont(new Font("Titillium Web", 4, 12));
        nameOfFile.setOpaque(false);
        centerOfPanel.add(nameOfFile, BorderLayout.NORTH);

        progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        progressBar.setValue(Integer.parseInt(x.getProgress()));
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.decode("#018f99"));
        centerOfPanel.add(progressBar, BorderLayout.CENTER);

        sizeOfFile = new JLabel(x.getSize());
        sizeOfFile.setFont(new Font("Titillium Web", 4, 12));
        sizeOfFile.setOpaque(false);
        centerOfPanel.add(sizeOfFile, BorderLayout.SOUTH);

        panel.add(centerOfPanel, BorderLayout.CENTER);

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                System.out.println("download panel clicked");
                for (JPanel y : downloadList.getDownloadListPanel())
                {
                    y.setBackground(Color.decode("#e7effb"));
                }
                panel.setBackground(Color.decode("#C0C0C0"));
                downloadList.setSelectedDownload(x);
                downloadList.setSelectedPanel(panel);
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    System.out.println("open download info by right click");
                    new DownloadInfo(DownloadManager.getFrame(), "Download", x);
                }
                else if (e.getClickCount() == 2)
                {
                    try
                    {
                        Desktop.getDesktop().open(new File(x.getPath() + "/" + x.getName()));
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    System.out.println("Open file by double click");
                }
            }
        });

        if (progressBar.getValue() != 100)
        {
            downloader = new Downloader(x, this);
            downloader.addPropertyChangeListener(new PropertyChangeListener()
            {
                @Override
                public void propertyChange(PropertyChangeEvent evt)
                {
                    if (evt.getPropertyName().equals("progress"))
                    {
                        int newValue = (Integer) evt.getNewValue();
                        if (progressBar.getValue() < newValue)
                        {
                            progressBar.setValue(newValue);
                        }
                        x.setProgress(progressBar.getValue());
                    }
                }
            });
            downloader.execute();
        }

        downloadList.getDownloadListPanel().add(panel);
        downloadList.updatePanel();
        DownloadManager.showFrame();
    }

    public JLabel getNameOfFile()
    {
        return nameOfFile;
    }
    public JLabel getSizeOfFile()
    {
        return sizeOfFile;
    }

}
