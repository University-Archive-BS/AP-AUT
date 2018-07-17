package ir.oranda;

/*
By MohammadMahdi Khancherly | 9631808
 */

/**
 * MatrixExpression class - holds a matrix expression and its instructions
 * @author MMKH
 * @version 1.0.0
 */
public class MatrixExpression {

    // Matrix operations (substract is defined as add)
    enum MatrixOperation{
        add,multiply
    }

    // Does expression have X variable ?
    private boolean hasX;

    // Does expression have Y variable ?
    private boolean hasY;

    // Is X variable before Y ? (If there is any)
    private boolean xIsBeforeY;

    // Expression for X (If there is any)
    private String xExpression;

    // Expression for Y (If there is any)
    private String yExpression;

    // Expression operation (multiply or add)
    private MatrixOperation matop;

    // Expression error code (from tryToParse method)
    private ErrorCodes ecode;


    /**
     * Calculates this MatrixExpression and returns result matrix .
     * @param x Matrix X
     * @param y Matrix Y
     * @return Result matrix or null if there is a calculation error (Like matrix multiplication error)
     */
    public Matrix calculate(Matrix x, Matrix y){
        if(ecode==ErrorCodes.CORRECT) {
            Matrix resultX = null;
            Matrix resultY = null;
            if (hasX)
                resultX = calculateOnX(x);
            if (hasY)
                resultY = calculateOnY(y);
            if (resultX == null)
                return resultY;
            if (resultY == null)
                return resultX;

            switch (matop) {
                case add:
                    return resultX.add(resultY);
                case multiply:
                    if (isXBeforeY())
                        return resultX.multiply(resultY);
                    else
                        return resultY.multiply(resultX);
            }

            return null;
        }
        else{
            Matrix result = new Matrix();
            result.setEcode(ecode);
            return result;
        }
    }

    /**
     * Calculates X expression of this MatrixExpression and returns result matrix .
     * @param x
     * @return Result matrix or null if there is a calculation error (Like matrix multiplication error)
     */
    private Matrix calculateOnX(Matrix x){
        return x.multiply(Integer.parseInt(xExpression));
    }

    /**
     * Calculates Y expression of this MatrixExpression and returns result matrix .
     * @param y
     * @return Result matrix or null if there is a calculation error (Like matrix multiplication error)
     */
    private Matrix calculateOnY(Matrix y){
        return y.multiply(Integer.parseInt(yExpression));
    }

    /**
     * Gets expression operator
     * @return MatrixOperation
     */
    public MatrixOperation getMatop() {
        return matop;
    }

    /**
     * Sets expression operator
     * @param matop MatrixOperation
     */
    public void setMatop(MatrixOperation matop) {
        this.matop = matop;
    }

    /**
     * Gets X expression
     * @return
     */
    public String getxExpression() {
        return xExpression;
    }

    /**
     * Sets X expression
     * @param xExpression
     */
    public void setxExpression(String xExpression) {
        this.xExpression = xExpression;
    }

    /**
     * Gets Y expression
     * @return
     */
    public String getyExpression() {
        return yExpression;
    }

    /**
     * Sets Y expression
     * @param yExpression
     */
    public void setyExpression(String yExpression) {
        this.yExpression = yExpression;
    }

    public boolean isXBeforeY() {
        return xIsBeforeY;
    }

    public void setXIsBeforeY(boolean xIsBeforeY) {
        this.xIsBeforeY = xIsBeforeY;
    }

    public boolean isHasX() {
        return hasX;
    }

    public void setHasX(boolean hasX) {
        this.hasX = hasX;
    }

    public boolean isHasY() {
        return hasY;
    }

    public void setHasY(boolean hasY) {
        this.hasY = hasY;
    }

    /**
     * Gets ErrorCode of this expression
     * @return ErrorCodes
     */
    public ErrorCodes getEcode() {
        return ecode;
    }

    /**
     * Sets ErrorCode of this expression
     * @param ecode ErrorCodes
     */
    public void setEcode(ErrorCodes ecode) {
        this.ecode = ecode;
    }
}
