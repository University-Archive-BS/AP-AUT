package ir.oranda;

import java.util.Scanner;

/*
By MohammadMahdi Khancherly | 9631808
 */

/**
 * Main class
 * @author MMKH
 * @version 1.0.0
 */
public class Main {

    /**
     * Start point
     * @param args Not used
     */
    public static void main(String[] args) {

        // Declare first matrix (X)
        Matrix m1 = new Matrix();
        System.out.println("Define the first matrix (X):");
        // Inputs first matrix ( and prints error if need , until a valid matrix is entered. )
        m1.inputThisMatrix();

        // Declare second matrix (Y)
        Matrix m2 = new Matrix();
        System.out.println("Define the second matrix (Y):");
        // Inputs second matrix ( and prints error if need , until a valid matrix is entered. )
        m2.inputThisMatrix();



        System.out.println("Enter your polynomial expression:");
        Scanner sc = new Scanner(System.in);

        // Inputs polynomial expression until q is entered .
        String inputexp = sc.nextLine();
        while (!inputexp.trim().toLowerCase().equals("q")) {
            // Creates a Parser object , then parses inputted expression and finally , calculates the parsed MatrixExpression with X=m1 and Y=m2 .
            Matrix result = (new MatrixExpressionParser()).parse(inputexp).calculate(m1, m2);
            if(result!=null) {
                // Result isn't null , so if calculate method returned null for some reason , expression is ignored .
                // ---->> Note that if there is a handled error or exception , result.toString() will return that error instead of result matrix .
                System.out.println("Result :\n" + result.toString());
            }
            // Proceed to next input .
            System.out.println("\nEnter your polynomial expression:");
            inputexp = sc.nextLine();
        }

        System.out.println("Bye !");


    }
}
