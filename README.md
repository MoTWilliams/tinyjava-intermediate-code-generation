# TinyJava Compiler: Intermediate Code Generation

Morgan Williams  
CSCE 4650.001

### Description:

This program implements a compiler for TinyJava, a subset of the Java programming language. The compiler reads the TinyJava code from a file, builds a symbol table, performs basic type checking, and builds intermediate three-address code in the form of a C file that can be compiled and executed.

### Files:

- ***TinyJavaCodeGen.java:*** Main driver. Parses input file, constructs the symbol table, and outputs generated C code
- ***TinyJava.jflex:*** Defines lexical rules and tokens for the lexer
- ***TinyJava.cup:*** CUP grammar with semantic actions. Used for parsing TinyJava and generating intermediate three-address code
- ***SymbolTable.java:*** Stores variables, functions, and classes for each scope and facilitates symbol insertion, lookup, and nested traversal
- ***SymbolTableEntry.java:*** Base class for all symbol table entries. Defines common fields and behaviors
- ***ClassEntry.java:*** SymbolTableEntry subclass used to store class declarations and their associated scopes
- ***FunctionEntry.java:*** SymbolTableEntry subclass used to store metadata for function declarations, including return type and scope
- ***VariableEntry.java:*** SymbolTableEntry subclass used to store metadata for scalar and array variables, including type, and array dimensions, if they exist
- ***Code.java:*** Maintains generated code and creates unique temporaries and labels
- ***ExpressionCode.java:*** Encapsulates type, result location, and generated code for expressions during intermediate code generation
- ***ComparisonCode.java:*** Helper class for intermediate code generation used to represent a comparison operation and its right-hand expression
- ***ErrorMessage.java:*** Prints error messages
- ***java-cup-11b-runtime.jar:*** CUP runtime library; provides classes necessary for the parser to run
- ***Makefile:*** Automates lexer/parser generation, compilation, and execution of the TinyJava compiler

### Usage:

***Compile:*** `make`  
***Run:*** Ensure the test file is in the project directory, then use `make generate_code FILE=fileName`, where `fileName` is the name of the test file, without any extension or file path.  
***Clean:*** `make clean`

### Notes:

- ***Supported:***
    - Programs consisting of a single class with multiple member variables and function definitions
    - Control flow statements (if/while)
    - Boolean and arithmetic expressions
    - Array element reads are fully supported
-  ***Not supported:***
    - Recursive functions 
    - Programs with multiple classes
    - Proper three-address array-element assignment is not implemented. LHS offset calculation is performed in the assignment statement (i.e. `my_list_tl[_T4] = my_list[i];`)
    - Array-element offsets are not calculated before passing into function calls. I.e., array elements are passed directly into the function, rather than first being assigned to a temporary variable.

### Known Issues:

- Order of temporary variable declaration may vary due to implementation using HashMap