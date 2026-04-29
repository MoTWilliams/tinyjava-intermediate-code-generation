/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 29, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * SymbolTableEntry.java: Base class for all symbol table entries. Defines    *
 * common fields and behaviors among class, function, and variable symbol     *
 * table entries                                                              *
 ******************************************************************************/

public class SymbolTableEntry {
    private String id;
    private SymbolTable env;

    public SymbolTableEntry(String id, SymbolTable env) {
        this.id = id;
        this.env = env;
    }

    public String ID() { return id; }
    public SymbolTable Env() { return env; }
    public String Code() { return "code not found"; }

    public String IsStaticString() { return "n/a"; }
    public boolean IsClass() { return false; }
    public String Type() { return "unknown type"; }
    public String TypeString() { return "unknown type"; }
    public String Category() { return "unknown category"; }
    public boolean IsNestedScope() { return false; }
}
