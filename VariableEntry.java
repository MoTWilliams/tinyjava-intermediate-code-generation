import java.util.*;

public class VariableEntry extends SymbolTableEntry {
    Boolean isStatic;
    String type;
    Integer arrayDimensions;
    List<Integer> arraySizes;

    // Scalar
    public VariableEntry(String id, String type) {
        super(id, null);
        this.isStatic = false;
        this.type = type;
        arrayDimensions = 0;
        arraySizes = new ArrayList<>();
    }

    // Array
    public VariableEntry(String id, String type,
                                    Integer dimensions, List<Integer> sizes) {
        super(id, null);
        this.isStatic = false;
        this.type = type;
        arrayDimensions = dimensions;
        arraySizes = sizes;
    }

    // Array argument
    public VariableEntry(String id, String type, Integer dimensions) {
        super(id, null);
        this.isStatic = false;
        this.type = type;
        arrayDimensions = dimensions;

        arraySizes = new ArrayList<>();
        for (int i = 0; i < dimensions; i++) arraySizes.add(0);
    }

    @Override
    public String Code() { 
        String code = type + " " + ID();

        for (Integer size : arraySizes) code += "[" + size + "]";
        return code + ";";
    }

    @Override
    public String IsStaticString() { return isStatic ? "yes" : "no"; }

    @Override
    public String Type() { return type; }

    @Override
    public String TypeString() {
        if (arrayDimensions == 0) return type;

        String str = "array([";
        for (int i = 0; i < arrayDimensions; i++)
        {
            str += arraySizes.get(i);
            if (i < arrayDimensions - 1) str += ",";
        }
        str += "], " + type + ")";

        return str;
    }

    @Override
    public String Category() { return "variable"; }

    public void MarkStatic() { isStatic = true; }

    public static boolean IsValidArray(String typeL, String typeR,
                                    Integer dimensions, List<Integer> sizes) {
        if (typeR != null && !typeR.equals(typeL))
        { 
            System.out.println("[ERROR]: Array type mismatch"); 
            return false;
        }
        if (typeR != null && sizes != null && sizes.size() != dimensions)
        { 
            System.out.println("[ERROR]: Array dimensions mismatch"); 
            return false;
        }

        return true;
    }
}
