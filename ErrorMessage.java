/******************************************************************************
 * Morgan Williams - mtw0067                                                  *
 * CSCE 4650.001                                                              *
 * Assignment 5: Intermediate Code Generation                                 *
 * Apr 30, 2026                                                               *
 ******************************************************************************/

/******************************************************************************
 * ErrorMessage.java: Prints error messages                                   *
 ******************************************************************************/

public class ErrorMessage {
    public static void LaunchError() {
        System.out.println("***** ERROR: File name expected *****");
        System.out.println("Usage: make run input_program_name");
        System.exit(0);
    }

    public static void print (String message) {
        System.out.println("***** Error: " + message + " *****");
        System.exit(0);
    }

    public static void print (int position, String message) {
        // Point at the syntax error
        System.out.println("");
        for (int i = 0; i < position; i++) System.out.print (" ");
        System . out . println ("^");
        
        // Print error message and terminate
        print (message);
    }
}
