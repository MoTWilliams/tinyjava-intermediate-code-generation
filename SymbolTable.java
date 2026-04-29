/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 29, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * SymbolTable.java: Stores variables, functions, and classes for each scope  *
 * and facilitates symbol insertion, lookup, and nested environment traversal *
 ******************************************************************************/

import java.util.*;

public class SymbolTable {
    private String id;
    private SymbolTable parentEnv;
    
    private TreeMap<String, SymbolTableEntry> table;
    private HashMap<String, String> temps; // name, type

    private static int maxlen = 2;

    public SymbolTable() {
        this.id = "global";
        this.parentEnv = null;
        table = new TreeMap<>();
        temps = new HashMap<>();
    }

    public SymbolTable(String id, SymbolTable parentEnv) {
        this.id = id;
        this.parentEnv = parentEnv;
        table = new TreeMap<>();
        temps = new HashMap<>();
    }


    public SymbolTableEntry Entry(String id) { return table.get(id); }

    public String ID() { return id; }

    public SymbolTableEntry ThisEntry() {
        if (parentEnv == null) return null;
        if (!parentEnv.Contains(id)) return null;
        return parentEnv.Entry(id);
    }

    public String ParentID() {
        if (parentEnv == null) return null;
        return parentEnv.ID();
    }

    public SymbolTable ParentEnv() { return parentEnv; }
    
    public SymbolTableEntry ParentClassEntry() {
        SymbolTable nextParent = ParentEnv();
        if (nextParent == null) return null;

        while (nextParent != null)
        {
            SymbolTableEntry entry = nextParent.ThisEntry();
            if (entry != null && entry.IsClass()) return entry;
            nextParent = nextParent.ParentEnv();
        }
        
        // Parent class not found
        return null;
    }

    public boolean Contains(String id) { return table.containsKey(id); }

    private boolean AddEntry(SymbolTableEntry entry) {
        if (entry == null) return false;
        String entryID = entry.ID();
        table.put(entryID, entry);
        if (entryID.length() > maxlen) maxlen = entryID.length();
        return true;
    }

    // Function
    public boolean AddFunc(String id, String type, Boolean isStatic,
                                                            SymbolTable env) {
        if (this.Contains(id))
        {
            System.out.println("[ERROR]: Duplicate function entry"); 
            return false;
        }
        
        SymbolTableEntry funcEntry = new FunctionEntry(id, isStatic, type, env);
        AddEntry(funcEntry);
        return true;
    }

    // Class
    public boolean AddClass(String id, SymbolTable env) {
        if (this.Contains(id))
        {
            System.out.println("[ERROR]: Duplicate class entry"); 
            return false;
        }
        
        SymbolTableEntry classEntry = new ClassEntry(id, env);
        AddEntry(classEntry);
        return true;
    }

    // Variable--scalar
    public boolean AddVar(String id, String type) {
        if (this.Contains(id))
        {
            System.out.println("[ERROR]: Duplicate variable entry"); 
            return false;
        }

        SymbolTableEntry varEntry = new VariableEntry(id, type);
        AddEntry(varEntry);
        return true;
    }

    // Variable--array
    public boolean AddVar(String id, String type, 
                                Integer dimensions, List<Integer> sizes) {
        if (this.Contains(id))
        {
            System.out.println("[ERROR]: Duplicate variable entry"); 
            return false;
        }

        SymbolTableEntry varEntry = 
            new VariableEntry(id, type, dimensions, sizes);
        AddEntry(varEntry);
        return true;
    }

    // Variable--array argument
    public boolean AddVar(String id, String type, Integer dimensions) {
        if (this.Contains(id))
        {
            System.out.println("[ERROR]: Duplicate variable entry"); 
            return false;
        }

        SymbolTableEntry varEntry = 
            new VariableEntry(id, type, dimensions);
        AddEntry(varEntry);
        return true;
    }

    public void PrintEnv(String blockName) {
        // Table title
        System.out.println("\nIdentifier Table for " + blockName);
        System.out.print("---------------------");
        for (int i = 0; i < blockName.length(); i++) System.out.print("-");
        System.out.println("\n");

        // Column headings
        System.out.print("Id");
        for (int i = 2; i < maxlen; i++) System.out.print(" ");
        System.out.println(" Category Static Type");

        // Heading rule lines
        System.out.print("--");
        for (int i = 2; i < maxlen; i++) System.out.print(" ");
        System.out.println(" -------- ------ ----");

        Iterator<Map.Entry<String, SymbolTableEntry>> envIterator = 
            table.entrySet().iterator();
        TreeMap<String, SymbolTableEntry> nestedScopeList = 
            new TreeMap<String, SymbolTableEntry>();
        
        while (envIterator.hasNext()) {
            // Grab the next entry's key and value
            Map.Entry<String, SymbolTableEntry> pair = envIterator.next();
            String id = pair.getKey();
            SymbolTableEntry entry = pair.getValue();
            System.out.print(id);

            for (int i = id.length(); i < maxlen+1; i++) System.out.print(" ");
            System.out.printf("%-8s", entry.Category());
            System.out.printf(" %-7s", entry.IsStaticString());
            System.out.print(entry.TypeString() + "\n");

            if (entry.IsNestedScope()) nestedScopeList.put(id, entry);
        }

        Iterator<Map.Entry<String, SymbolTableEntry>> nestedScopeIterator =
            nestedScopeList.entrySet().iterator();

        while (nestedScopeIterator.hasNext()) {
            Map.Entry<String, SymbolTableEntry> pair = 
                nestedScopeIterator.next();
            String nestedScopeName = pair.getKey();
            SymbolTableEntry entry = pair.getValue();
            entry.Env().PrintEnv(nestedScopeName);
        }
    }

    public void AddTemp(String name, String type) { temps.put(name, type); }
    
    public List<String> Temps() {
        // Convert pairs to strings
        List<String> t = new ArrayList<>();

        for (var temp : temps.entrySet())
        {
            String name = temp.getKey();
            String type = temp.getValue();
            t.add(type + " " + name + ";\n");
        }

        return t;
    }
}
