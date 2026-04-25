/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 30, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * TinyJavaCodeGen.java: Main Driver; Reads from the input file, constructs   * 
 * the symbol table, and writes intermediate code to a .c file                *
 ******************************************************************************/

import java.io.*;
import java_cup.runtime.*;

public class TinyJavaCodeGen {
    
    public static void main(String args[]) throws java.io.IOException {
        // Error if missing input file
        if (args.length != 1) ErrorMessage.LaunchError();
        
        String programName = args[0];

        try
        {
            Reader r = new FileReader("../../tests/" + programName + ".java");
            SymbolFactory sf = new ComplexSymbolFactory();

            TinyJavaLexer lexer = new TinyJavaLexer(r, sf);
            TinyJavaParser parser = new TinyJavaParser(lexer, sf);

            Code code = (Code) parser.parse().value;
            code.Env().PrintEnv("global");
            System.out.println("\nParsing successful.");

            PrintStream outputStream = 
                new PrintStream("../../output/" + programName + ".c");
            outputStream.print(code.Generate("global"));
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
