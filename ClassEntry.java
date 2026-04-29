/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 29, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * ClassEntry.java: SymbolTableEntry subclass used to store class             *
 * declarations and their associated scope                                    *
 ******************************************************************************/

public class ClassEntry extends SymbolTableEntry {
 
    public ClassEntry(String id, SymbolTable env) {
        super(id, env);
    }

    @Override
    public boolean IsClass() { return true; }

    @Override
    public String TypeString() { return "n/a"; }

    @Override
    public String Category() { return "class"; }

    @Override
    public boolean IsNestedScope() { return true; }
}
