public class Main
{
    public static void main(String[] args)
    {
        Pelak mine = new Pelak("9631075", "Ali", "Nazari");

        mine.addPenalty("عبور از چراغ قرمز");
        mine.addPenalty("سبقت غیر مجاز");
        mine.addPenalty("صحبت با تلفن همراه حین رانندگی");
        mine.addPenalty("حرکت مارپیچ");
        mine.addPenalty("سرعت غیر مجاز");

        mine.showvalidation();
        System.out.println();
        mine.printPenalties();

        mine.subPenalty("سبقت غیر مجاز");

        mine.showvalidation();
        System.out.println();
        mine.printPenalties();
    }
}

