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

    public void SetPlaceType(SymbolTable env, String type) {
        this.type = type;

        env.AddTemp(place, type);
    }
}
