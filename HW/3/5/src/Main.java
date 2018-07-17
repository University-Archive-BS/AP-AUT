import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int lines = 0;
        int words = 0;
        int chars = 0;

        String line;
        String word;

        while (!(line = input.nextLine()).isEmpty())
        {
            lines++; 
            Scanner reader = new Scanner(line); //reading lines
            while (reader.hasNext())
            {
                word = reader.next();
                words++;
                chars += word.length();
            }
        }
        if(args.length == 0)
        {
            System.out.print(lines + " " + words + " " + chars);
        }
        else if(args[0].equals("-l") || args[0].equals("--lines"))
        {
            System.out.println(lines);
        }
        else if(args[0].equals("-w") || args[0].equals("--words"))
        {
            System.out.println(words);
        }
        else if(args[0].equals("-c") || args[0].equals("--chars"))
        {
            System.out.println(chars);
        }
    }
}