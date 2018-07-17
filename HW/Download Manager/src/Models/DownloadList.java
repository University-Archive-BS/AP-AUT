package Models;

import GUI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class DownloadList extends JPanel
{
    private static Vector<Download> downloadListVector;
    private Vector<JPanel> downloadListPanel;
    private static Vector<Download> deletedDownloadVector;
    private JScrollPane scrollPane;
    public static Download selectedDownload;
    public static JPanel selectedPanel;

    public DownloadList()
    {
        super(new GridLayout(20, 1));
        this.downloadListVector = new Vector<>();
        this.deletedDownloadVector = new Vector<>();
        downloadListPanel = new Vector<>();
        scrollPane = new JScrollPane(this);
        DownloadManager.getRightSide().add(scrollPane, BorderLayout.CENTER);
    }

    public Vector<Download> getDownloadVector()
    {
        return downloadListVector;
    }
    public Vector<Download> getDeletedDownloadVector()
    {
        return deletedDownloadVector;
    }
    public Vector<JPanel> getDownloadListPanel()
    {
        return downloadListPanel;
    }
    public JPanel getSelectedPanel()
    {
        return selectedPanel;
    }
    public Download getSelectedDownload()
    {
        return selectedDownload;
    }
    public void setSelectedDownload(Download input)
    {
        selectedDownload = input;
    }
    public void setSelectedPanel(JPanel input)
    {
        selectedPanel = input;
    }

    public void addDownload(Download input)
    {
        downloadListVector.add(input);
        new DownloadPanel(input, this);
    }

    public static void setDownloadListVector(Vector<Download> downloadListVector)
    {
        DownloadList.downloadListVector = downloadListVector;
    }

    public void vectorPanelGenerator(Vector<Download> downloadVector)
    {
        for (Download x : downloadVector)
        {
            new DownloadPanel(x, this);
        }
        DownloadManager.showFrame();
    }

    public void actionOnDownload(int input)
    {
        switch (input)
        {
            case 0:
            {
                deletedDownloadVector.add(selectedDownload);
                downloadListVector.remove(selectedDownload);
                downloadListPanel.remove(selectedPanel);
                System.out.println("download deleted");
                updatePanel();
            }
            case 1:
            {
                System.out.println("haah");
            }
        }
    }

    public void updatePanel()
    {
        this.removeAll();
        for (JPanel x : downloadListPanel)
        {
            this.add(x);
        }
        DownloadManager.showFrame();
    }


}