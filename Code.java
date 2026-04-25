public class Code {
    private static int labelIdx;
    private static int tempIdx;

    private SymbolTable env;
    private String code;

    public Code (SymbolTable env, String code) {
        this.env = env;
        this.code = code;
    }

    public SymbolTable Env() { return env; }
    public String code() { return code; }

    public String Generate(String blockName) {
        return code + "for " + blockName + "\n";
    }

    public static void ResetLabels() { labelIdx = 0; }
    public static void ResetTemps() { tempIdx = 0; }

    public static String NextLabel() { return "_L" + labelIdx++; }
    public static String NextTemp() { return "_T" + tempIdx++; }    
}
