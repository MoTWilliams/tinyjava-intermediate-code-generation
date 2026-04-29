/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 29, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * TinyJavaCodeGen.java: Main driver; parses input file, constructs the       *
 * symbol table, and outputs generated C code to file                         *
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
            // Read the input file
            Reader r = new FileReader(programName + ".java");
            
            // Instantiate lexer and parser
            SymbolFactory sf = new ComplexSymbolFactory();
            TinyJavaLexer lexer = new TinyJavaLexer(r, sf);
            TinyJavaParser parser = new TinyJavaParser(lexer, sf);

            // Parse and generate intermediate code
            Code code = (Code) parser.parse().value;
            code.Env().PrintEnv("global");
            System.out.println("\nParsing successful.");

            // Write generated C code to the output file
            PrintStream outputStream = 
                new PrintStream(programName + ".c");
            outputStream.print(code.Generate("global"));
            outputStream.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
