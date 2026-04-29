/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 29, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * ComparisonCode.java: Helper class for intermediate code generation used to *
 * represent a comparison operation and its right-hand expression             *
 ******************************************************************************/

public class ComparisonCode {
    private String op;
    private ExpressionCode rhs;

    public ComparisonCode(String op, ExpressionCode rhs) {
        this.op = op;
        this.rhs = rhs;
    }

    public String Op() { return op; }
    public ExpressionCode RHS() { return rhs; }
}
