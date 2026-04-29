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
