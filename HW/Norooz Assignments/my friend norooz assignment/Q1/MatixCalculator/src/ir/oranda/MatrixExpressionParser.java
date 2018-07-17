package ir.oranda;

/*
By MohammadMahdi Khancherly | 9631808
 */

/**
 * MatrixExpressionParser class - a class for parsing a matrix expression
 * @author MMKH
 * @version 1.0.0
 */
public class MatrixExpressionParser {


    /**
     * Prechecks given expression for any illegal or invalid operators or operands
     * NOTE : This method only prechecks expression , and it doesn't parse it . use {@code public MatrixExpression parse(String expression)} instead .
     * @param expression Matrix expression ( has X or Y or both )
     * @return an ErrorCode - On success , it returns {@code ErrorCodes.CORRECT}
     */
    public ErrorCodes tryToParse(String expression){
        // 1. "#(" or ")#" is illegal
        try {
            if(expression.contains("(")) {
                if (isNumeric(String.valueOf(expression.toCharArray()[expression.replace(" ", "").indexOf("(")-1])))
                    return ErrorCodes.INVALIDPARENTHESES;
            }
        } catch (Exception e) {}
        try {
            if(expression.contains(")")) {
                if (isNumeric(String.valueOf(expression.toCharArray()[expression.replace(" ", "").indexOf("(")-1])))
                    return ErrorCodes.INVALIDPARENTHESES;
            }
        } catch (Exception e) {}



        // 2. Duplicate variable
        try {
            if (expression.toUpperCase().replaceFirst("X", "").contains("X"))
                return ErrorCodes.DUPLICATEVAR;
        } catch (Exception e) {}
        try {
            if (expression.toUpperCase().replaceFirst("Y", "").contains("Y"))
                return ErrorCodes.DUPLICATEVAR;
        } catch (Exception e) {}


        // 3. Illegal OP
        try {
            if(expression.toUpperCase().replaceAll("[- XY*+()]","").length()>0)
                if(!isNumeric(expression.toUpperCase().replaceAll("[- XY*+()]","")))
                    return ErrorCodes.ILLEGALOP;
        } catch (Exception e) {}


        // 4. No power
        try {
            if(expression.toUpperCase().contains("X"))
                if(isNumeric(String.valueOf(expression.toUpperCase().replace(" ","").toCharArray()[expression.toUpperCase().replace(" ","").indexOf("X")+1])))
                    return ErrorCodes.NOPOWER;
        } catch (Exception e) {}
        try {
            if(expression.toUpperCase().contains("Y"))
                if(isNumeric(String.valueOf(expression.toUpperCase().replace(" ","").toCharArray()[expression.toUpperCase().replace(" ","").indexOf("Y")+1])))
                    return ErrorCodes.NOPOWER;
        } catch (Exception e) {}


        // 5. X or Y is last char
        try {
            if(expression.toUpperCase().contains("X") && !expression.toUpperCase().contains("Y"))
                if(expression.toUpperCase().replaceAll("[ ()]","").toCharArray()[expression.toUpperCase().replaceAll("[ ()]","").length()-1] != 'X')
                    return ErrorCodes.ISNOTLAST;
        } catch (Exception e) {}
        try {
            if(expression.toUpperCase().contains("Y") && !expression.toUpperCase().contains("X"))
                if(expression.toUpperCase().replaceAll("[ ()]","").toCharArray()[expression.toUpperCase().replaceAll("[ ()]","").length()-1] != 'Y')
                    return ErrorCodes.ISNOTLAST;
        } catch (Exception e) {}


        // 6. Star is illegal on first char (*X or *3X or *-2X or *(-4)X)
        try {
            if(expression.toUpperCase().replaceAll("[ ()]","").toCharArray()[0] == '*')
                    return ErrorCodes.ILLEGALSTAR;
        } catch (Exception e) {}


        // 7. Alone numbers !
        try {
            String[] testingParts = expression.replaceAll("[ ()]","").split("[-+]");
            for (int i=0 ; i<testingParts.length ; i++)
                if(isNumeric(testingParts[i]))
                    return ErrorCodes.ALONENUMBER;
        } catch (Exception e) {}


        // 8. Alone XY !
        try {
            if(expression.toUpperCase().replaceAll("[ ()]","").contains("XY"))
                return ErrorCodes.ALONEXY;
        } catch (Exception e) {}



        // Nothing is wrong upon here! So it seems like to be CORRECT!
        return ErrorCodes.CORRECT;
    }

    /**
     * Parses a matrix expression and searches for any exceptions .
     * @param expression Matrix expression
     * @return a MatrixExpression object , Which it's {@code ecode} must be checked for any illegal or invalid operators or operands .
     */
    public MatrixExpression parse(String expression){
        // Creates a new MatrixExpression for returning
        MatrixExpression matexp = new MatrixExpression();

        // Precheck expression for any illegal or invalid operators or operands .
        matexp.setEcode(tryToParse(expression));
        // If there is any illegal or invalid operators or operands , break up process and return an empty matrix with ecode .
        if(matexp.getEcode()!=ErrorCodes.CORRECT)
            return matexp;

        // Cleans up given expression for processing .
        expression = doCleanup(expression);

        // Checks for X and Y presence
        matexp.setHasX(expression.contains("X"));
        matexp.setHasY(expression.contains("Y"));

        if(matexp.isHasX() && matexp.isHasY()) {
            // Expression has both X and Y
            matexp.setXIsBeforeY(expression.indexOf("X") < expression.indexOf("Y"));
            if (!matexp.isXBeforeY()){
                // If Y is before X , swap them .
                int yindex = expression.indexOf("Y");
                expression = expression.replace("X","Y").substring(0,yindex) + "X" + expression.substring(yindex+1,expression.length());
            }

                // Finding expression for X
                matexp.setxExpression(expression.split("X")[0]);

                // Finding expression for Y
                if(expression.split("X")[1].toCharArray()[0] == '*'){
                    matexp.setyExpression(expression.split("X")[1].substring(1).replace("Y",""));
                }
                else{
                    matexp.setyExpression(expression.split("X")[1].replace("Y",""));
                }

                // Finding operator ( * or [+-] )
                switch (expression.split("X")[1].toCharArray()[0]){
                    case '*':
                        matexp.setMatop(MatrixExpression.MatrixOperation.multiply);
                        break;
                    default:
                        matexp.setMatop(MatrixExpression.MatrixOperation.add);
                        break;
                }


            if (!matexp.isXBeforeY()){
                // If Y was before X , swap them again .
                String xExp = matexp.getxExpression();
                matexp.setxExpression(matexp.getyExpression());
                matexp.setyExpression(xExp);
            }
        }
        else {
            if(matexp.isHasY()){
                // Expression has only Y
                matexp.setyExpression(expression.replace("Y",""));
            }
            else {
                // Expression has only X
                matexp.setxExpression(expression.replace("X",""));
            }
        }



        if(matexp.isHasY()) {
            // Some cleanups and corrections for Y
            while(matexp.getyExpression().contains("--") || matexp.getyExpression().contains("+-") || matexp.getyExpression().contains("-+") || matexp.getyExpression().contains("++")) {
                matexp.setyExpression(matexp.getyExpression().replace("--",""));
                matexp.setyExpression(matexp.getyExpression().replace("+-","-"));
                matexp.setyExpression(matexp.getyExpression().replace("-+","-"));
                matexp.setyExpression(matexp.getyExpression().replace("++",""));
            }


            if (matexp.getyExpression().replace("-", "").trim().length() == 0) {
                matexp.setyExpression(matexp.getyExpression() + "1");
            }
            if (matexp.getyExpression().replace("+", "").trim().length() == 0) {
                matexp.setyExpression(matexp.getyExpression() + "1");
            }

            matexp.setyExpression(matexp.getyExpression().replace("+",""));

            if (matexp.getyExpression().toCharArray()[matexp.getyExpression().length()-1] == '*') {
                matexp.setyExpression(matexp.getyExpression().substring(0,matexp.getyExpression().length()-1));
            }

            // Last legal check for Y
            if(!isNumeric(matexp.getyExpression())){
                matexp.setEcode(ErrorCodes.INVALIDEXP);
            }

        }
        if(matexp.isHasX()) {
            // Some cleanups and corrections for X
            while(matexp.getxExpression().contains("--") || matexp.getxExpression().contains("+-") || matexp.getxExpression().contains("-+") || matexp.getxExpression().contains("++")) {
                matexp.setxExpression(matexp.getxExpression().replace("--", ""));
                matexp.setxExpression(matexp.getxExpression().replace("+-", "-"));
                matexp.setxExpression(matexp.getxExpression().replace("-+", "-"));
                matexp.setxExpression(matexp.getxExpression().replace("++", ""));
            }
            if (matexp.getxExpression().replace("-", "").trim().length() == 0) {
                matexp.setxExpression(matexp.getxExpression() + "1");
            }
            if (matexp.getxExpression().replace("+", "").trim().length() == 0) {
                matexp.setxExpression(matexp.getxExpression() + "1");
            }
            matexp.setxExpression(matexp.getxExpression().replace("+",""));

            if (matexp.getxExpression().toCharArray()[matexp.getxExpression().length()-1] == '*') {
                matexp.setxExpression(matexp.getxExpression().substring(0,matexp.getxExpression().length()-1));
            }

            // Last legal check for X
            if(!isNumeric(matexp.getxExpression())){
                matexp.setEcode(ErrorCodes.INVALIDEXP);
            }

        }


        // Returns MatrixExpression
        return matexp;
    }

    /**
     * Is given string a number ?
     * Works for all numbers
     * @param s
     * @return true or false
     */
    private boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Does expression cleanup (removing spaces , parentheses , ...)
     * @param expression
     * @return Cleaned expression
     */
    private String doCleanup(String expression){
        return expression.replace("\n","").replace(" ","").replace("(","").replace(")","").toUpperCase().trim();
    }

}
