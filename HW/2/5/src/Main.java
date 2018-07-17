import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);

        Item item1;
        System.out.print("Enter the name of the First Item:");
        String itemName1 = inputs.nextLine();
        System.out.print("Enter the name of the Producer of the First Item:");
        String proName1 = inputs.nextLine();
        System.out.print("Enter the amount of the First Item:");
        int itemAmount1 = inputs.nextInt();
        item1 = new Item(itemName1, proName1);
        item1.incrementOrDecrement(itemAmount1);

        Item item2;
        String enter = inputs.nextLine();
        System.out.print("Enter the name of the Second Item:");
        String itemName2 = inputs.nextLine();
        System.out.print("Enter the name of the Producer of the Second Item:");
        String proName2 = inputs.nextLine();
        System.out.print("Enter the amount of the Second Item:");
        int itemAmount2 = inputs.nextInt();
        item2 = new Item(itemName2, proName2);
        item2.incrementOrDecrement(itemAmount2);

        System.out.println();
        item1.print();
        System.out.println();
        item2.print();
    }
}
