package ir.oranda;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UIManager - Manages UI of the game.
 *
 * @author MMKH
 * @version 1.0.0
 */
public class UIManager {

    // Window width (for title bar)
    private static final int windowWidth = 51;

    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[90;41m";
    public static final String ANSI_GREEN = "\u001B[90;42m";
    public static final String ANSI_BLUE = "\u001B[90;44m";
    public static final String ANSI_PURPLE = "\u001B[90;45m";

    /**
     * Shows a single board
     * @param board
     * @param c1color Color of cells with property 1.
     * @param c2color Color of cells with property 2.
     * @param propertyHolder Placeholder characters for cells ( for example , {'@','#'} )
     */
    public void showBoard(Board board,String c1color,String c2color,char... propertyHolder){
        for(int i=-1 ; i<10 ; i++){
            for(int j=-1 ; j<10 ; j++){
                if(i<0){
                    if(j<0){
                        System.out.print("  |");
                    }
                    else{
                        System.out.print(" " + j + " |");
                    }
                }
                else{
                    if(j<0){
                        System.out.print(i + " |");
                    }
                    else{
                        int property = board.getCellProperty(j,i);
                        String propertyChar = (property==0 ? "   " : " " + propertyHolder[property-1] + " ");
                        System.out.print((property==1 ? c1color : (property==2 ? c2color : "")) + propertyChar + ANSI_RESET + "|");
                    }
                }
            }
            System.out.println();
            for(int j=-1 ; j<10 ; j++){
                if(j<0){
                    System.out.print("--|");
                }
                else{
                    System.out.print("---+");
                }
            }

            System.out.println();

        }
    }

    /**
     * Shows a single board
     * @param board
     * @param c1color Color of cells with property 1.
     * @param c2color Color of cells with property 2.
     * @param propertyHolder Placeholder characters for cells ( for example , {'@','#'} )
     */
    /**
     * Shows two boards next to each other
     * @param title Title of board 1
     * @param title2 Title of board 2
     * @param board Board 1
     * @param board2 Board 2
     * @param c1color Color of cells with property 1 in board 1.
     * @param c2color Color of cells with property 2 in board 1.
     * @param c3color Color of cells with property 1 in board 2.
     * @param c4color Color of cells with property 2 in board 2.
     * @param b11 Placeholder character of cells with property 1 in board 1.
     * @param b12 Placeholder character of cells with property 2 in board 1.
     * @param b21 Placeholder character of cells with property 1 in board 2.
     * @param b22 Placeholder character of cells with property 2 in board 2.
     * @throws StringIndexOutOfBoundsException if length of titles exceed window's width.
     */
    public void showTwoBoards(String title,String title2,Board board,Board board2,String c1color,String c2color,String c3color,String c4color,char b11,char b12,char b21,char b22) throws StringIndexOutOfBoundsException{
        if(title.length() > windowWidth-12) throw new StringIndexOutOfBoundsException("Exceeded max " + (windowWidth-12) + " chars.");
        if(title2.length() > windowWidth-12) throw new StringIndexOutOfBoundsException("Exceeded max " + (windowWidth-12) + " chars.");

        boolean titleLengthIsOdd = (title.length()%2==1);
        int widthOfTitleBorder = (titleLengthIsOdd ? windowWidth-7 : windowWidth-8);
        boolean titleLengthIsOdd2 = (title2.length()%2==1);
        int widthOfTitleBorder2 = (titleLengthIsOdd2 ? windowWidth-7 : windowWidth-8);

        repetitivePrint("#",widthOfTitleBorder,0,false);
        System.out.print("\t");
        repetitivePrint("#",widthOfTitleBorder2,0,true);

        repetitivePrint("#",widthOfTitleBorder/2-((2+title.length())/2),0);
        repetitivePrint(" ",(2+title.length()),0);
        repetitivePrint("#",widthOfTitleBorder - (title.length() + (widthOfTitleBorder/2-((2+title.length())/2)) + 2 ),0,false);
        System.out.print("\t");
        repetitivePrint("#",widthOfTitleBorder2/2-((2+title2.length())/2),0);
        repetitivePrint(" ",(2+title2.length()),0);
        repetitivePrint("#",widthOfTitleBorder2 - (title2.length() + (widthOfTitleBorder2/2-((2+title2.length())/2)) + 2 ),0,true);

        repetitivePrint("#",widthOfTitleBorder/2-((2+title.length())/2),0);
        System.out.print(" " + title + " ");
        repetitivePrint("#",widthOfTitleBorder - (title.length() + (widthOfTitleBorder/2-((2+title.length())/2)) + 2 ),0,false);
        System.out.print("\t");
        repetitivePrint("#",widthOfTitleBorder2/2-((2+title2.length())/2),0);
        System.out.print(" " + title2 + " ");
        repetitivePrint("#",widthOfTitleBorder2 - (title2.length() + (widthOfTitleBorder2/2-((2+title2.length())/2)) + 2 ),0,true);

        repetitivePrint("#",widthOfTitleBorder/2-((2+title.length())/2),0);
        repetitivePrint(" ",(2+title.length()),0);
        repetitivePrint("#",widthOfTitleBorder - (title.length() + (widthOfTitleBorder/2-((2+title.length())/2)) + 2 ),0,false);
        System.out.print("\t");
        repetitivePrint("#",widthOfTitleBorder2/2-((2+title2.length())/2),0);
        repetitivePrint(" ",(2+title2.length()),0);
        repetitivePrint("#",widthOfTitleBorder2 - (title2.length() + (widthOfTitleBorder2/2-((2+title2.length())/2)) + 2 ),0,true);



        repetitivePrint("#",widthOfTitleBorder,0,false);
        System.out.print("\t");
        repetitivePrint("#",widthOfTitleBorder2,0,true);

        System.out.println();


        for(int i=-1 ; i<10 ; i++){
            for(int j=-1 ; j<10 ; j++){
                if(i<0){
                    if(j<0){
                        System.out.print("  |");
                    }
                    else{
                        System.out.print(" " + j + " |");
                    }
                }
                else{
                    if(j<0){
                        System.out.print(i + " |");
                    }
                    else{
                        int property = board.getCellProperty(j,i);
                        String propertyChar = (property==0 ? "   " : " " + (property==1 ? b11 : b12) + " ");
                        System.out.print((property==1 ? c1color : (property==2 ? c2color : "")) + propertyChar + ANSI_RESET + "|");
                    }
                }
            }

            System.out.print("\t\t");
            for(int j=-1 ; j<10 ; j++){
                if(i<0){
                    if(j<0){
                        System.out.print("  |");
                    }
                    else{
                        System.out.print(" " + j + " |");
                    }
                }
                else{
                    if(j<0){
                        System.out.print(i + " |");
                    }
                    else{
                        int property = board2.getCellProperty(j,i);
                        String propertyChar = (property==0 ? "   " : " " + (property==1 ? b21 : b22) + " ");
                        System.out.print((property==1 ? c3color : (property==2 ? c4color : "")) + propertyChar + ANSI_RESET + "|");
                    }
                }
            }

            System.out.println();
            for(int j=-1 ; j<10 ; j++){
                if(j<0){
                    System.out.print("--|");
                }
                else{
                    System.out.print("---+");
                }
            }
            System.out.print("\t\t");
            for(int j=-1 ; j<10 ; j++){
                if(j<0){
                    System.out.print("--|");
                }
                else{
                    System.out.print("---+");
                }
            }


            System.out.println();

        }
    }


    /**
     * Clears the console by printing a lot of empty lines.
     */
    public void clearConsole(){
        System.out.flush();
        for(int i = 0 ; i<3000 ; i++){
            System.out.println("\n");
        }
        System.out.flush();
    }

    /**
     * Creates and prints a title bar
     * @param title Title string
     * @param tabindex Number of tabs before title bar
     * @throws StringIndexOutOfBoundsException if length of title exceed window's width.
     */
    public void createWindow(String title,int tabindex) throws StringIndexOutOfBoundsException{
        if(title.length() > windowWidth-12) throw new StringIndexOutOfBoundsException("Exceeded max " + (windowWidth-12) + " chars.");

        boolean titleLengthIsOdd = (title.length()%2==1);
        int widthOfTitleBorder = (titleLengthIsOdd ? windowWidth-7 : windowWidth-8);

        repetitivePrint("#",widthOfTitleBorder,tabindex,true);
        repetitivePrint("#",widthOfTitleBorder/2-((2+title.length())/2),tabindex);
        repetitivePrint(" ",(2+title.length()),0);
        repetitivePrint("#",widthOfTitleBorder - (title.length() + (widthOfTitleBorder/2-((2+title.length())/2)) + 2 ),0,true);
        repetitivePrint("#",widthOfTitleBorder/2-((2+title.length())/2),tabindex);
        System.out.print(" " + title + " ");
        repetitivePrint("#",widthOfTitleBorder - (title.length() + (widthOfTitleBorder/2-((2+title.length())/2)) + 2 ),0,true);
        repetitivePrint("#",widthOfTitleBorder/2-((2+title.length())/2),tabindex);
        repetitivePrint(" ",(2+title.length()),0);
        repetitivePrint("#",widthOfTitleBorder - (title.length() + (widthOfTitleBorder/2-((2+title.length())/2)) + 2 ),0,true);

        repetitivePrint("#",widthOfTitleBorder,tabindex,true);
        System.out.println();

    }

    /**
     * Prints a question and gets input .
     * @param question
     * @param hint
     * @return Answer string
     */
    public String stringQuestion(String question,String hint){
        System.out.println("\t" + question);
        System.out.print("\t\t" + hint + " : \n\t\t");
        Scanner sc = new Scanner(System.in);
        return sc.next();

    }

    /**
     * Prints a notification to players to indicate whose turn it is.
     * @param playerIndex
     */
    public void passToPlayer(int playerIndex){
        clearConsole();
        createWindow("Player " + (playerIndex+1),0);
        System.out.println("----> Pass to player " + (playerIndex==0 ? ANSI_BLUE : ANSI_RED) +  "#" + (playerIndex+1) + ANSI_RESET);
        System.out.println("\t\tThen press enter..." );
        new Scanner(System.in).reset().nextLine();

    }

    /**
     * Prints a text and waits for enter to be pressed.
     * @param text
     */
    public void breakAndContinue(String text){
        breakAndContinue(text,0);
    }

    /**
     * Prints a text with a number of tabs and waits for enter to be pressed.
     * @param text
     * @param tabIndex number of tabs
     */
    public void breakAndContinue(String text,int tabIndex){
        repetitivePrint("\t",tabIndex);
        System.out.println("--> " + text);
        repetitivePrint("\t",tabIndex);
        System.out.println("\t\tPress enter..." );
        Scanner sc = new Scanner(System.in);

        sc.reset().nextLine();

    }


    /**
     * Prints a question that has options to select and gets selected option .
     * @param question
     * @param options
     * @param title
     * @return Index of selected option ( from {@code options} ArrayList )
     * @throws StringIndexOutOfBoundsException if length of titles exceed window's width.
     */
    public int modeSelectMessage(String question, ArrayList<String> options,String title) throws StringIndexOutOfBoundsException {
        if(question.length() > windowWidth-12) throw new StringIndexOutOfBoundsException("Exceeded max " + (windowWidth-12) + " chars.");
        clearConsole();
        createWindow(title,0);
        System.out.println("\t" + question);
        System.out.println();
        for (int i=0;i<options.size();i++){
            if(options.get(i).length() > windowWidth-12) throw new StringIndexOutOfBoundsException("Exceeded max " + (windowWidth-12) + " chars.");
            System.out.println("\t" + (i+1) + ". " + options.get(i));
        }

        Scanner sc = new Scanner(System.in);
        boolean inputIsValid = false;
        int choice = 0;
        System.out.println();
        while(!inputIsValid) {
            try {

                System.out.print("\tEnter your choice : ");
                choice = sc.nextInt();
                while (choice < 1 || choice > options.size()) {
                    System.out.println("\tInvalid input, retry .");
                    System.out.print("\tEnter your choice : ");
                    choice = sc.nextInt();
                }
                inputIsValid=true;
            }
            catch (Exception e){
                inputIsValid=false;
                sc.next();
                System.out.println("\tInvalid input, retry .");
            }



        }
        return choice;
    }

    /**
     * Prints something repetitively with a number of tabs (without newline character)
     * @param str
     * @param count
     * @param tabIndex
     */
    private void repetitivePrint(String str,int count,int tabIndex){
        repetitivePrint(str,count,tabIndex,false);
    }

    /**
     * Prints something repetitively with a number of tabs
     * @param str
     * @param count
     * @param tabIndex
     * @param withNL print newline character or not
     */
    private void repetitivePrint(String str,int count,int tabIndex,boolean withNL){
        if(withNL) {
            repetitivePrint("\t",tabIndex);
            repetitivePrint(str, count - 1);
            System.out.println(str);
        }
        else {
            repetitivePrint("\t",tabIndex);
            repetitivePrint(str,count);
        }
    }

    /**
     * Prints something repetitively (without newline character)
     * @param str
     * @param count
     */
    private void repetitivePrint(String str,int count){
        for(int i=0;i<count;i++){
            System.out.print(str);
        }
        System.out.flush();

    }

}
