import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Second second = new Second();
        Minute minute = new Minute();
        Hour hour = new Hour();
        Day day = new Day();
        Month month = new Month();
        Year year = new Year();


        while (true)
        {
            if (second.changeSecond(minute) == 1) {
                if (minute.changeMinute(hour) == 1) {
                    if (hour.changeHour(day) == 1) {
                        if (day.changeDay(month) == 1) {
                            if (month.changeMonth(year) == 1) {
                            }
                        }
                    }
                }
            }
            try {
            Thread.sleep(1 );
            }
            catch (Exception e)
            {
            System.out.println("Got an exception!");
            }
            System.out.println(""
                + year.left.getNumber() + year.secondLeft.getNumber() + year.secondRight.getNumber() + year.right.getNumber()
                + " YEAR : "
                + month.left.getNumber() + month.right.getNumber()
                + " Month : "
                + day.left.getNumber() + day.right.getNumber()
                + " DAY : "
                + hour.left.getNumber() + hour.right.getNumber()
                + " h : "
                + minute.left.getNumber() + minute.right.getNumber()
                + " m : "
                + second.left.getNumber() + second.right.getNumber() + " s");
        }
    }
}


