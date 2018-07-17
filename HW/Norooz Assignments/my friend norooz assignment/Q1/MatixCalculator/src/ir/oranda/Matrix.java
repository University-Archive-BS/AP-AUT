package ir.oranda;

/*
By MohammadMahdi Khancherly | 9631808
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Matrix class - for storing matrices , adding , substracting and multiplying them .
 * @author MMKH
 * @version 1.0.0
 */
public class Matrix {

    // Calculation errors
    enum CalculationErrors{
        NOTSAMESIZED,
        CANNOTMULTIPLY
    }

    // Row and Column size of matrix
    private int row;
    private int col;

    // Matrix cells
    private ArrayList<ArrayList<Integer>> mat;

    // Matrix ErrorCode (from parsing)
    private ErrorCodes ecode;

    /**
     * Creates a new empty Matrix
     */
    public Matrix() {
        this.row = 0;
        this.col = 0;
        mat = new ArrayList<>();
        ecode=ErrorCodes.CORRECT;
    }

    /**
     * Inputs this matrix from System.in and checks for any illegal inputs .
     * If there was any illegal inputs , it will retry .
     */
    public void inputThisMatrix() {
        Scanner sc = new Scanner(System.in);
        String inputLine = sc.nextLine();
        while(inputLine.trim().length()!=0){
            if(col!=0 && inputLine.replace(" ","").split(",").length != col){
                // Column count must be same with first row
                System.out.println("Column count must be same with first row.");
            }
            else {
                try {
                    addMatrixRowData(inputLine.replace(" ", "").split(","));
                } catch (NumberFormatException e){
                    // There is something on matrix that cannot be parsed to Integer.
                    System.out.println(e.getMessage());
                }

            }
            inputLine = sc.nextLine();
        }
    }


    /**
     * Adds other matrix to this matrix and returns result matrix
     * @param other
     * @return Result matrix - on failure , error is printed and null is returned
     */
    public Matrix add(Matrix other){
        if(isSameSize(other)){
            Matrix result = new Matrix();
            for(int i = 0 ; i<row ; i++){
                ArrayList<String> newrow = new ArrayList<>();
                for(int j = 0 ; j<col ; j++){
                    newrow.add(String.valueOf(mat.get(i).get(j) + other.getCell(i,j)));
                }
                result.addMatrixRowData(newrow.toArray(new String[newrow.size()]));
            }

            return result;
        }

        // an error occurred !
        errorOccurred(CalculationErrors.NOTSAMESIZED);
        return null;
    }

    @Deprecated
    public Matrix negative(){
        return multiply(-1);
    }
    @Deprecated
    public Matrix substract(Matrix other){
        return add(other.negative());
    }

    /**
     * Multiplies other matrix to this matrix and returns result matrix
     * @param other
     * @return Result matrix - on failure , error is printed and null is returned
     */
    public Matrix multiply(Matrix other){
        if(canMultiplyTo(other)){
            Matrix result = new Matrix();
            for(int i = 0 ; i<row ; i++){
                ArrayList<String> newrow = new ArrayList<>();
                for(int j = 0 ; j<other.col ; j++){
                    int dotMultiple = 0;
                    for(int k = 0 ; k<col ; k++){
                        dotMultiple += mat.get(i).get(k) * other.getCell(k,j);
                    }
                    newrow.add(String.valueOf(dotMultiple));
                }
                result.addMatrixRowData(newrow.toArray(new String[newrow.size()]));
            }

            return result;
        }

        // an error occurred !
        errorOccurred(CalculationErrors.CANNOTMULTIPLY);
        return null;
    }

    /**
     * Multiplies a coefficient to this matrix and returns result matrix
     * @param coefficient
     * @return Result matrix
     */
    public Matrix multiply(int coefficient){
        Matrix result = new Matrix();
        for(int i = 0 ; i<row ; i++){
            ArrayList<String> newrow = new ArrayList<>();
            for(int j = 0 ; j<col ; j++){
                newrow.add(String.valueOf(mat.get(i).get(j) * (coefficient)));
            }
            result.addMatrixRowData(newrow.toArray(new String[newrow.size()]));
        }

        return result;
    }

    /**
     * Gets errorcode of this matrix
     * @return ErrorCodes
     */
    public ErrorCodes getEcode() {
        return ecode;
    }

    /**
     * Sets errorcode of this matrix
     * @param ecode
     */
    public void setEcode(ErrorCodes ecode) {
        this.ecode = ecode;
    }

    /**
     * Returns a human readable format of this matrix - if there is any errors , returns that errors.
     * @return
     */
    @Override
    public String toString() {
        if(ecode==ErrorCodes.CORRECT) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++)
                    sb.append((j == 0 ? "" : ",") + mat.get(i).get(j));
                sb.append(i == row - 1 ? "" : "\n");
            }
            return sb.toString();
        }
        else{
            return ecode.toString();
        }
    }


    /**
     * Adds an array of String as a row to this matrix .
     * @param rowdata Splitted matrix row data
     * @throws NumberFormatException if there is something on matrix that cannot be parsed to Integer
     */
    private void addMatrixRowData(String[] rowdata) throws NumberFormatException {
        if(col==0)
            col = rowdata.length;
        ArrayList<Integer> newrow = new ArrayList<>();
        for(int i = 0 ; i<rowdata.length ; i++){
            try{
                newrow.add(Integer.parseInt(rowdata[i]));
            } catch (NumberFormatException e){
                throw new NumberFormatException("Invalid matrix.");
            }


        }

        mat.add(newrow);
        row++;
    }

    /**
     * Gets a cell of this matrix
     * @param rowNumber i
     * @param columnNumber j
     * @return a number
     */
    private int getCell(int rowNumber, int columnNumber){
        return mat.get(rowNumber).get(columnNumber);
    }


    private boolean isSameSize(Matrix other){
        return (col==other.col) && (row==other.row);
    }

    private boolean canMultiplyTo(Matrix other){
        return (col==other.row);
    }

    /**
     * Prints calculation errors
     * @param errCode CalculationErrors
     */
    private void errorOccurred(CalculationErrors errCode){
        switch (errCode){
            case NOTSAMESIZED:
                System.out.println("Matrix x and y must be same sized.");
                break;
            case CANNOTMULTIPLY:
                System.out.println("Cannot multiply matrix x and y.");
                break;
            default:

        }
    }
}
