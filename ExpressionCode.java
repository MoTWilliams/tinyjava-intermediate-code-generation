/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 29, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * ExpressionCode.java: Used to encapsulate type, result location, and        *
 * generated code for expressions during intermediate code generation         *
 ******************************************************************************/

public class ExpressionCode {
    private String code;
    private String type;
    private String place;

    public ExpressionCode(String code, String type, String place) {
        this.code = code;
        this.type = type;
        this.place = place;
    }

    public String Code() { return code; }
    public String Type() { return type; }
    public String Place() { return place; }
}
