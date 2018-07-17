package model;

public class Note
{
    // TODO: Phase2: uncomment this code
    private String title;
    private String content;
    private String date;

    public Note(String title, String content, String date)
    {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }



}
