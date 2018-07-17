package ir.oranda;

import ir.oranda.animals.Animal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * UIManager class - Handles all functions for user interface
 */
public class UIManager {

    // Number of displayed cards in a row
    private static final int cardsCountInRow = 7;

    // Width of title bar
    private static final int windowWidth = 16*cardsCountInRow;

    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[90;41m";
    public static final String ANSI_ERROR = "\u001B[31m";
    public static final String ANSI_WARNING = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE =  "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_FOREPURPLE = "\u001B[35m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CARD_UNCOLORED = "\u001B[107m";
    public static final String ANSI_CARD_COLORED = "\u001B[100m";
    public static final String ANSI_CARD_ATTACKTYPE_COLORED = "\u001B[100;34m";
    public static final String ANSI_CARD_ATTACKTYPE_UNCOLORED = "\u001B[107;34m";

    /**
     * Builds and returns current expression
     * @param player This player
     * @param animalIndexes Selected attackers indexes
     * @param completed {@code true} If waiting for next card , {@code false} If waiting for attack type.
     * @return Card expression
     */
    private String getExpression(Player player,ArrayList<Integer> animalIndexes,boolean completed){
        StringBuilder sb = new StringBuilder();
        for (Integer index:animalIndexes) {
            sb.append(UIManager.ANSI_GREEN);
            sb.append(index);
            sb.append(UIManager.ANSI_RESET);
            sb.append("[");
            if(!animalIndexes.get(animalIndexes.size()-1).equals(index) || completed) {
                sb.append(UIManager.ANSI_BLUE + (player.getPlayerAnimals().get(index).isAttackType2Selected() ? player.getPlayerAnimals().get(index).getAttackType2().getAttackName() : player.getPlayerAnimals().get(index).getAttackType1().getAttackName()) + UIManager.ANSI_RESET + "]");
                sb.append(" + ");
            }
        }
        return sb.toString();
    }

    /**
     * Prints cards expression and waiting for next card
     * @param player This player
     * @param opponent Opponent player
     * @param animalIndexes Selected attackers indexes
     * @param opponentAnimalIndexes Selected defender index (Must be empty on this method ; otherwise , use {@code printFullCardExpression})
     */
    private void printCardExpression(Player player,Player opponent,ArrayList<Integer> animalIndexes,ArrayList<Integer> opponentAnimalIndexes){
        clearConsole();
        createWindow("Opponent's Cards", 0);
        showCards(opponent.getPlayerAnimals(), new HashSet<>(opponentAnimalIndexes),ANSI_ERROR);
        createWindow("Your Cards", 0);
        showCards(player.getPlayerAnimals(), new HashSet<>(animalIndexes),ANSI_GREEN);
        System.out.println(" Total energy : " + ANSI_WARNING + player.calculateTotalEnergy(animalIndexes) + ANSI_RESET);
        System.out.println(" Remaining repairs : " + player.getRemainingRepairs());
        System.out.println(" (r for repair , c for clear)");
        System.out.print("Expression : " + getExpression(player,animalIndexes,true));
    }

    /**
     * Prints cards expression and waiting for defender card
     * @param player This player
     * @param opponent Opponent player
     * @param animalIndexes Selected attackers indexes
     * @param opponentAnimalIndexes Selected defender index
     * @param expression Cards expression
     */
    private void printFullCardExpression(Player player,Player opponent,ArrayList<Integer> animalIndexes,ArrayList<Integer> opponentAnimalIndexes,String expression){
        clearConsole();
        createWindow("Opponent's Cards", 0);
        showCards(opponent.getPlayerAnimals(), new HashSet<>(opponentAnimalIndexes),ANSI_ERROR);
        createWindow("Your Cards", 0);
        showCards(player.getPlayerAnimals(), new HashSet<>(animalIndexes),ANSI_GREEN);
        System.out.println(" Total energy : " + ANSI_WARNING + player.calculateTotalEnergy(animalIndexes) + ANSI_RESET);
        System.out.println();
        System.out.println(" (c for clear)");
        System.out.print("Expression : " + expression);
    }

    /**
     * Prints cards expression and waiting for attack type
     * @param player This player
     * @param opponent Opponent player
     * @param animalIndexes Selected attackers indexes
     * @param opponentAnimalIndexes Selected defender index (Must be empty on this method ; otherwise , use {@code printFullCardExpression})
     * @param selectedIndex Card index to ask for it's attack type
     */
    private void printCardExpression(Player player,Player opponent,ArrayList<Integer> animalIndexes,ArrayList<Integer> opponentAnimalIndexes,int selectedIndex){
        clearConsole();
        createWindow("Opponent's Cards", 0);
        showCards(opponent.getPlayerAnimals(), new HashSet<>(opponentAnimalIndexes),ANSI_ERROR);
        createWindow("Your Cards", 0);
        showCards(player.getPlayerAnimals(), new HashSet<>(animalIndexes),ANSI_GREEN);
        System.out.println();
        System.out.println();
        System.out.print("             ");
        repetitivePrint(" ", getExpression(player,animalIndexes,false).length() - UIManager.ANSI_BLUE.length()*((animalIndexes.size() - 1)*2 +1) - UIManager.ANSI_RESET.length()*((animalIndexes.size() - 1)*2 +1) - 1);
        System.out.println(player.getPlayerAnimals().get(selectedIndex).getAttackTypesString());
        System.out.print("Expression : " + getExpression(player,animalIndexes,false));
    }

    /**
     * Displays card selector , gets a full card expression for an attack and returns an ArrayList of animals , which it's first element is defender card and others are attacker cards
     * @param player This player
     * @param opponent Opponent player
     * @return An ArrayList of animals , which it's first element is defender card and others are attacker cards
     * @throws IOException
     */
    public ArrayList<Animal> cardExpression(Player player, Player opponent) throws IOException{
        ArrayList<Integer> animalIndexes = new ArrayList<>();
        ArrayList<Integer> opponentAnimalIndexes = new ArrayList<>();
        char inputtedChar=0;
        int currentAnimalIndex;
        boolean currentAnimalType2Selected;
        char selectedAttackTypeCode = '0';
        boolean errorOccured = false;
        while(inputtedChar!=10 || errorOccured) {
            errorOccured=false;
            printCardExpression(player,opponent,animalIndexes,opponentAnimalIndexes);
            inputtedChar = (char) System.in.read();
            clearInput();
            while ((inputtedChar!=10 && inputtedChar!=99 && inputtedChar!=67  && inputtedChar!=114 && inputtedChar!=82) && (inputtedChar < 48 || inputtedChar > 48 + player.getPlayerAnimals().size() - 1 || animalIndexes.contains(inputtedChar - 48))) {
                printCardExpression(player,opponent,animalIndexes,opponentAnimalIndexes);
                inputtedChar = (char) System.in.read();
                clearInput();
            }
            if(inputtedChar==99 || inputtedChar==67){
                // Clear
                if(animalIndexes.size()>0)
                    animalIndexes.remove(animalIndexes.size()-1);
            }
            else if(inputtedChar==114 || inputtedChar==82){
                // Repair
                if (animalIndexes.size()!=1){
                    System.out.println();
                    System.out.println(ANSI_ERROR + " ERROR : Select exactly one card , then try again." + ANSI_RESET);
                    System.out.print(" ");
                    System.out.println("   Press enter to continue...");
                    new Scanner(System.in).nextLine();
                    clearInput();
                }
                else{
                    if(player.getPlayerAnimals().get(animalIndexes.get(0)).getFullEnergy() == player.getPlayerAnimals().get(animalIndexes.get(0)).getEnergy()){
                        System.out.println();
                        System.out.println(ANSI_ERROR + " ERROR : This card has full energy." + ANSI_RESET);
                        System.out.print(" ");
                        System.out.println("   Press enter to continue...");
                        new Scanner(System.in).nextLine();
                        clearInput();
                    }
                    else {
                        // Try to repair...
                        if (!player.repair(animalIndexes.get(0))) {
                            System.out.println();
                            System.out.println(ANSI_ERROR + " ERROR : No remaining repairs." + ANSI_RESET);
                            System.out.print(" ");
                            System.out.println("   Press enter to continue...");
                            new Scanner(System.in).nextLine();
                            clearInput();
                        } else {
                            //No attack !
                            return null;
                        }
                    }
                }
            }
            else if(inputtedChar!=10) {
                currentAnimalIndex = inputtedChar - 48;
                animalIndexes.add(currentAnimalIndex);
                if (player.getPlayerAnimals().get(currentAnimalIndex).getAttackType2() != null) {
                    printCardExpression(player,opponent,animalIndexes,opponentAnimalIndexes,currentAnimalIndex);
                    inputtedChar = (char) System.in.read();
                    clearInput();
                    while (!(player.getPlayerAnimals().get(currentAnimalIndex).getAttackType2().getAttackCode() == ("" + inputtedChar).toUpperCase().charAt(0)) && !(player.getPlayerAnimals().get(currentAnimalIndex).getAttackType1().getAttackCode() == ("" + inputtedChar).toUpperCase().charAt(0))) {
                        printCardExpression(player,opponent,animalIndexes,opponentAnimalIndexes,currentAnimalIndex);
                        inputtedChar = (char) System.in.read();
                        clearInput();
                    }
                    currentAnimalType2Selected = (player.getPlayerAnimals().get(currentAnimalIndex).getAttackType2().getAttackCode() == ("" + inputtedChar).toUpperCase().charAt(0));
                    player.getPlayerAnimals().get(currentAnimalIndex).setAttackType2Selected(currentAnimalType2Selected);
                }
                else{
                    currentAnimalType2Selected=false;
                }

                if(animalIndexes.size()==1){
                    // First animal !
                    selectedAttackTypeCode = (currentAnimalType2Selected ? player.getPlayerAnimals().get(currentAnimalIndex).getAttackType2().getAttackCode() : player.getPlayerAnimals().get(currentAnimalIndex).getAttackType1().getAttackCode() );
                }
                else {
                    if(selectedAttackTypeCode != (currentAnimalType2Selected ? player.getPlayerAnimals().get(currentAnimalIndex).getAttackType2().getAttackCode() : player.getPlayerAnimals().get(currentAnimalIndex).getAttackType1().getAttackCode())){
                        printCardExpression(player,opponent,animalIndexes,opponentAnimalIndexes);
                        System.out.println();
                        System.out.print("             ");
                        repetitivePrint(" ", getExpression(player,animalIndexes,false).length() - UIManager.ANSI_BLUE.length()*((animalIndexes.size() - 1)*2 +1) - UIManager.ANSI_RESET.length()*((animalIndexes.size() - 1)*2 +1) - 1);
                        System.out.println(ANSI_ERROR + " ^ ERROR : Animals attack type must be same." + ANSI_RESET);
                        System.out.print("             ");
                        repetitivePrint(" ", getExpression(player,animalIndexes,false).length() - UIManager.ANSI_BLUE.length()*((animalIndexes.size() - 1)*2 +1) - UIManager.ANSI_RESET.length()*((animalIndexes.size() - 1)*2 +1) - 1);
                        System.out.println("   Press enter to continue...");
                        animalIndexes.remove(animalIndexes.size()-1);
                        errorOccured=true;
                        new Scanner(System.in).nextLine();
                        clearInput();

                    }
                }

                ArrayList<Integer> erroredAnimalIndexes = player.checkEnergyAvailablity(animalIndexes);
                if(erroredAnimalIndexes.size()>0 && !errorOccured){
                    // There are some errors !
                    printCardExpression(player,opponent,animalIndexes,opponentAnimalIndexes);
                    System.out.println();
                    System.out.print("             " + ANSI_WARNING);
                    String expression = getExpression(player,animalIndexes,true).replace(ANSI_RESET,"").replace(ANSI_GREEN,"").replace(ANSI_BLUE,"");
                    int lastBracketIndex=0;
                    int totalCount = 0;
                    int lastPrintedArrow =0;
                    int i=0;
                    do{

                        if(erroredAnimalIndexes.contains(animalIndexes.get(i))){
                            repetitivePrint(" ",expression.indexOf('[',lastBracketIndex+1) - lastPrintedArrow - 1);
                            System.out.print("^");
                            totalCount += expression.indexOf('[',lastBracketIndex+1) - lastPrintedArrow + 1;
                            lastPrintedArrow = expression.indexOf('[',lastBracketIndex+1) ;
                        }
                        lastBracketIndex = expression.indexOf('[',lastBracketIndex+1);
                        i++;
                    }while (expression.indexOf('[',lastBracketIndex+1)!=-1);
                    System.out.println(" WARNING : Insufficient energy." + ANSI_RESET);
                    System.out.print("             ");
                    repetitivePrint(" ", totalCount);
                    System.out.println("   Press enter to continue...");
                    new Scanner(System.in).nextLine();
                    clearInput();
                }

            }
            else{
                // Enter is pressed . So , checking selected cards...
                if(animalIndexes.size()==0){
                    System.out.print("             ");
                    System.out.println(ANSI_ERROR + "^ ERROR : Enter at least one card." + ANSI_RESET);
                    System.out.print("             ");
                    System.out.println("  Press enter to continue...");
                    errorOccured=true;
                    new Scanner(System.in).nextLine();
                    clearInput();
                }
                else{
                    ArrayList<Integer> erroredAnimalIndexes = player.checkEnergyAvailablity(animalIndexes);
                    if(erroredAnimalIndexes.size()>0){
                        // There are some errors !
                        System.out.print("             " + ANSI_ERROR);
                        String expression = getExpression(player,animalIndexes,true).replace(ANSI_RESET,"").replace(ANSI_GREEN,"").replace(ANSI_BLUE,"");
                        int lastBracketIndex=0;
                        int totalCount = 0;
                        int lastPrintedArrow =0;
                        int i=0;
                        do{

                            if(erroredAnimalIndexes.contains(animalIndexes.get(i))){
                                repetitivePrint(" ",expression.indexOf('[',lastBracketIndex+1) - lastPrintedArrow - 1);
                                System.out.print("^");
                                totalCount += expression.indexOf('[',lastBracketIndex+1) - lastPrintedArrow ;
                                lastPrintedArrow = expression.indexOf('[',lastBracketIndex+1) ;
                            }
                            lastBracketIndex = expression.indexOf('[',lastBracketIndex+1);
                            i++;
                        }while (expression.indexOf('[',lastBracketIndex+1)!=-1);
                        System.out.println(" ERROR : Insufficient energy." + ANSI_RESET);
                        System.out.print("             ");
                        repetitivePrint(" ", totalCount);
                        System.out.println("   Press enter to continue...");
                        errorOccured=true;
                        new Scanner(System.in).nextLine();
                        clearInput();
                    }
                    else{
                        // Everything is OK ! Proceed to target selection ...

                        // Target selection
                        String fullExpression = getExpression(player,animalIndexes,true);
                        fullExpression = fullExpression.substring(0,fullExpression.lastIndexOf("+")) + ANSI_FOREPURPLE + "--->" + ANSI_RESET + " ";
                        do {

                            printFullCardExpression(player, opponent, animalIndexes, opponentAnimalIndexes, fullExpression);
                            inputtedChar = (char) System.in.read();
                            clearInput();
                            while ((inputtedChar != 99 && inputtedChar != 67) && (inputtedChar < 48 || inputtedChar > 48 + opponent.getPlayerAnimals().size() - 1)) {
                                printFullCardExpression(player, opponent, animalIndexes, opponentAnimalIndexes, fullExpression);
                                inputtedChar = (char) System.in.read();
                                clearInput();
                            }
                            if (inputtedChar == 99 || inputtedChar == 67) {
                                errorOccured=false;
                            } else {
                                opponentAnimalIndexes.add(inputtedChar - 48);
                                printFullCardExpression(player, opponent, animalIndexes, opponentAnimalIndexes, fullExpression + ANSI_ERROR + ("" + (inputtedChar - 48)) + ANSI_RESET);
                                System.out.println();
                                System.out.println(" Are you sure ? [Y]es");
                                inputtedChar = (char) System.in.read();
                                clearInput();
                                if (("" + inputtedChar).toUpperCase().charAt(0) == 'Y') {
                                    animalIndexes.add(0, opponentAnimalIndexes.get(0));
                                    return player.cardExpressionIndexToAnimal( opponent, animalIndexes);
                                }
                                opponentAnimalIndexes.clear();
                                errorOccured=true;
                            }
                        } while(errorOccured);
                    }
                }

            }

        }


        return null;


    }


    /**
     * Is given string numeric ?
     * @param s Given string
     * @return
     */
    private boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }


    /**
     * Allow user to select 10 cards from the given cards
     * @param playerIndex Player index who is selecting
     * @param animals ArrayList of given animal cards
     * @return ArrayList of selected animal cards
     */
    public ArrayList<Animal> cardSelector(int playerIndex , ArrayList<Animal> animals){
        clearConsole();
        HashSet<Integer> selectedAnimalsIndexes = new HashSet<>();
        showCards(animals,selectedAnimalsIndexes,ANSI_GREEN);
        Scanner sc = new Scanner(System.in);
        String inputtedId = "";
        while(selectedAnimalsIndexes.size()<10){
            System.out.print("----> Player " + (playerIndex==0 ? ANSI_BLUE : ANSI_ERROR) +  "#" + (playerIndex+1) + ANSI_RESET + " , Input a card to add (R for reset) : ");
            inputtedId = sc.nextLine();
            if(inputtedId.toUpperCase().trim().equals("R"))
                selectedAnimalsIndexes.clear();
            else if(isNumeric(inputtedId) && Integer.parseInt(inputtedId) > -1 && Integer.parseInt(inputtedId) < animals.size())
                selectedAnimalsIndexes.add(Integer.parseInt(inputtedId));
            clearConsole();
            showCards(animals, selectedAnimalsIndexes,ANSI_GREEN);

        }

        ArrayList<Animal> selectedAnimals = new ArrayList<>();

        for (int index:selectedAnimalsIndexes) {
            selectedAnimals.add(animals.get(index));
        }

        return selectedAnimals;
    }


    /**
     * Shows animal cards in rows
     * @param animals ArrayList of animals to show
     * @param coloredAnimals Set of animals that are selected
     * @param idColor Color of card's id
     */
    public void showCards(ArrayList<Animal> animals , HashSet<Integer> coloredAnimals,String idColor) {
        showCards(animals, coloredAnimals,idColor,ANSI_CARD_COLORED,ANSI_CARD_UNCOLORED);
    }

    /**
     * Shows animal cards in rows
     * @param animals ArrayList of animals to show
     * @param coloredAnimals Set of animals that are selected
     * @param idColor Color of card's id
     * @param color Color of selected cards
     * @param nocolor Color of unselected cards
     */
    private void showCards(ArrayList<Animal> animals , HashSet<Integer> coloredAnimals,String idColor, String color , String nocolor){

        for(int i=0 ; i<(animals.size()-1)/cardsCountInRow + 1 ; i++){

            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print("  _____________ ");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                boolean isOdd = (animals.get(i*cardsCountInRow + j).getName().length() % 2 == 1);
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);

                System.out.print(idColor +  (i*cardsCountInRow + j) + ANSI_RESET);

                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);

                if(("" + (i*cardsCountInRow + j)).length() == 1){
                    if(isOdd)
                        repetitivePrint(" ",6 - animals.get(i*cardsCountInRow + j).getName().length()/2 - 1);
                    else
                        repetitivePrint(" ",6 - animals.get(i*cardsCountInRow + j).getName().length()/2 );
                }
                else{
                    if(isOdd)
                        repetitivePrint(" ",6 - animals.get(i*cardsCountInRow + j).getName().length()/2 -2);
                    else
                        repetitivePrint(" ",6 - animals.get(i*cardsCountInRow + j).getName().length()/2 -1);
                }
                System.out.print(animals.get(i*cardsCountInRow + j).getName());
                repetitivePrint(" ",6 - animals.get(i*cardsCountInRow + j).getName().length()/2 );

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);
                System.out.print("-+-+-+-+-+-+-");

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);
                System.out.print(ANSI_WARNING + " Energy: " + animals.get(i*cardsCountInRow + j).getEnergy());

                repetitivePrint(" " , 4 - ("" + animals.get(i*cardsCountInRow + j).getEnergy()).length());

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);
                System.out.print(ANSI_WARNING + "  -->Of: " + animals.get(i*cardsCountInRow + j).getFullEnergy());

                repetitivePrint(" " , 4 - ("" + animals.get(i*cardsCountInRow + j).getFullEnergy()).length());

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);
                System.out.print(ANSI_FOREPURPLE + " Health: " + animals.get(i*cardsCountInRow + j).getHealth());
                repetitivePrint(" " , 4 - ("" + animals.get(i*cardsCountInRow + j).getHealth()).length());

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);
                System.out.print("-=-=-=-=-=-=-");

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(ANSI_CARD_ATTACKTYPE_COLORED);
                else
                    System.out.print(ANSI_CARD_ATTACKTYPE_UNCOLORED);
                System.out.print(" ");
                repetitivePrint(" ",6-animals.get(i*cardsCountInRow + j).getAttackType1().getAttackName().length());
                System.out.print(animals.get(i*cardsCountInRow + j).getAttackType1().getAttackName() + ": " + animals.get(i*cardsCountInRow + j).getAttackType1().getAttackEnergy() + ( ("" + animals.get(i*cardsCountInRow + j).getAttackType1().getAttackEnergy()).length() == 3 ? "" : " ") + " ");

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(ANSI_CARD_ATTACKTYPE_COLORED);
                else
                    System.out.print(ANSI_CARD_ATTACKTYPE_UNCOLORED);
                System.out.print(" ");
                if(animals.get(i*cardsCountInRow + j).getAttackType2() != null) {
                    repetitivePrint(" ", 6 - animals.get(i * cardsCountInRow + j).getAttackType2().getAttackName().length());
                    System.out.print(animals.get(i * cardsCountInRow + j).getAttackType2().getAttackName() + ": " + animals.get(i * cardsCountInRow + j).getAttackType2().getAttackEnergy() + (("" + animals.get(i * cardsCountInRow + j).getAttackType2().getAttackEnergy()).length() == 3 ? "" : " ") + " ");
                }
                else{
                    System.out.print("            ");
                }

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();
            for(int j=0;(i==((animals.size()-1)/cardsCountInRow) ? j < (animals.size() - (((animals.size()-1)/cardsCountInRow)*cardsCountInRow)) : j<cardsCountInRow ) ; j++){
                System.out.print(" |");
                if(coloredAnimals.contains(i*cardsCountInRow + j))
                    System.out.print(color);
                else
                    System.out.print(nocolor);
                System.out.print("_____________");

                System.out.print(ANSI_RESET);
                System.out.print("|");
            }
            System.out.println();



        }
        System.out.println();
    }

    /**
     * Clears console by printing plenty of lines
     */
    public void clearConsole(){
        for(int i = 0 ; i<100 ; i++){
            System.out.println();
        }
        System.out.flush();
    }

    /**
     * Creates a title bar with a title with some tabs
     * @param title Title string
     * @param tabindex Count of tabs
     * @throws StringIndexOutOfBoundsException If length of titles exceed window's width.
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
     * Passes the game to a player (By showing a message)
     * @param playerIndex Index of player who is it's turn
     */
    public void passToPlayer(int playerIndex){
        clearConsole();
        createWindow("Player " + (playerIndex+1),0);
        System.out.println("----> Pass to player " + (playerIndex==0 ? ANSI_BLUE : ANSI_ERROR) +  "#" + (playerIndex+1) + ANSI_RESET);
        System.out.println("\t\tThen press enter..." );
        new Scanner(System.in).reset().nextLine();

    }

    /**
     * Shows a message and waits for enter key to be pressed
     * @param text Message
     */
    public void breakAndContinue(String text){
        breakAndContinue(text,0);
    }

    /**
     * Shows a message with some tabs and waits for enter key to be pressed
     * @param text Message
     * @param tabIndex Count of tabs
     */
    private void breakAndContinue(String text,int tabIndex){
        repetitivePrint("\t",tabIndex);
        System.out.println("--> " + text);
        repetitivePrint("\t",tabIndex);
        System.out.println("\t\tPress enter..." );
        Scanner sc = new Scanner(System.in);

        sc.reset().nextLine();

    }

    @Deprecated
    public int getCardIndexInput(int max ) throws Exception{
        if(max < 1 || max > 9) throw new Exception("Options count must be between 1 to 9.");
        char inputtedChar = (char)System.in.read();
        clearInput();
        while (inputtedChar < 48 || inputtedChar > 48+max-1){
            inputtedChar = (char)System.in.read();
            clearInput();
        }


        return inputtedChar-48;

    }

    /**
     * Prints a question that has options to select and gets selected option .
     * @param question
     * @param options
     * @param title
     * @return Index of selected option ( from {@code options} ArrayList )
     * @throws StringIndexOutOfBoundsException If length of titles exceed window's width.
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

    /**
     * Clears input buffer (System.in)
     */
    private void clearInput() {
        try{
            System.in.read(new byte[System.in.available()]);
        } catch (Exception e) { }

    }

}
