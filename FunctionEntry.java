import java.util.*;

public class FunctionEntry extends SymbolTableEntry {
    Boolean isStatic;
    String type;
    List<String> argTypes;

    public FunctionEntry(String id, Boolean isStatic, String type,
                                SymbolTable env) {
        super(id, env);
        this.isStatic = isStatic;
        this.type = type;
        argTypes = new ArrayList<>();
    }

    @Override
    public String IsStaticString() { return isStatic ? "yes" : "no"; }

    @Override
    public String TypeString() {
        String str = "[";
        for (int i = 0; i < argTypes.size(); i++)
        {
            str += argTypes.get(i);
            if (i < argTypes.size() - 1) str += ", ";
        }
        str += "]->" + type;
        return str;
    }

    public void AddArgType(String type) { argTypes.add(type); }

    @Override
    public String Category() { return "function"; }
    
    @Override
    public boolean IsNestedScope() { return true; }
}
