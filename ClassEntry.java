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
