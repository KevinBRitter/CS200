import bioinformatics.ToolKit;
import input.FileInput;
import input.MenuInput;
import input.RandomInput;
import input.UserInput;
import output.FileOutput;

import java.io.IOException;

/* ************************************************************************
 *                 Assignment 4 -- String Manipulation                    *
 *                                                                        *
 * PROGRAMMER: Kevin Ritter                                               *
 * CLASS:    CS200                                                        *
 * INSTRUCTOR: Dean Zeller                                                *
 * SUBMISSION DATE: 11/15/2019                                            *
 *                                                                        *
 * DESCRIPTION:                                                           *
 * The project called by this tester main class is a bioinformatics       *
 * toolkit in Java.  It is intended to teach the programmer string        *
 * manipulation methods and by extension something about genetic          *
 * research in the computer age.                                          *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/

public class MainTester {
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to this tool kit.");
        runMain();
        System.out.println("\nThank you for using this tool kit.");
    }
    /**
     *  The runMain method loops the main menu for the program.
     */
    private static void runMain(){
        boolean exit = false;
        int userSelection = -1;
        String inputString = "";
        MenuInput intReturn = new MenuInput();
        do{
            System.out.println(printMenu());
            userSelection = intReturn.getInputNum(1, 4);
            System.out.println(userSelection + " selected.");
            if(userSelection == 1)
            {
                // Ask for file input:
                inputString = readDNAfromFile();
                System.out.println("Analysis to begin on the following string:\n" + samplePrint(inputString));
            }
            else if (userSelection == 2)
            {
                // Ask for user input:
                inputString = userDNAentry();
                System.out.println("Analysis to begin on the following string:\n" + samplePrint(inputString));
            }
            else if (userSelection == 3)
            {
                // Ask for randomly generated sequence of user defined length:
                System.out.println("Choose an number between 1 and 100 thousand for the new random sequence length");
                inputString = generateRandomDNA(intReturn.getInputNum(1, 100000));
                System.out.println("Analysis to begin on the following string:\n" + samplePrint(inputString));
            }
            else{
                // Selection from menu # 4 triggers exit condition
                exit = true;
            }
            try {
                saveDNAtoFile("Assignment_4/src/raw_data.txt", inputString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!exit){ToolKit program = new ToolKit(inputString);}
        }while(!exit);


    }
    /**
     *  The print Menu method returns a menu string to be printed.
     */
    private static String printMenu(){
        String mainMenu;
        mainMenu = "\nMain menu:\n" + "# 1. File Input - read in a sequence from a file.\n"
                + "# 2. User Input - paste or write in sequence.\n"
                + "# 3. Random Generation - create a sequence on demand.\n"
                + "# 4. Exit program."
        ;
        return mainMenu;
    }
    /**
     *  The saveDNAtoFile method writes the print report to an output file.
     */
    private static void saveDNAtoFile(String file, String data) throws IOException {
        FileOutput writtenWord = new FileOutput();
        writtenWord.writeOutput(file, data);
    }
    /**
     *  The userDNAentry method returns a string of user input DNA for analysis.
     */
    private static String userDNAentry()
    {
        UserInput userIn = new UserInput();
        return userIn.getData();
    }
    /**
     *  The generateRandomDNA method returns a string of randomly generated DNA for analysis.
     */
    private static String generateRandomDNA(int valIn)
    {
        RandomInput randIn = new RandomInput();
        randIn.setSequenceLength(valIn);
        return randIn.getData();
    }
    /**
     *  The readDNAfromFile method returns a string of DNA pulled from a file for analysis.
     */
    private static String readDNAfromFile()
    {
        FileInput fileIn = new FileInput();
        return fileIn.getData();
    }
    /**
     * The samplePrint method prints out the DNA string up to 150 characters
     */
    private static String samplePrint(String stringIn)
    {
        if(stringIn.length() > 150){
            int excess = stringIn.length()- 150;
            return stringIn.substring(0, 150) + " ==> characters omitted: " + excess;}
        else{return stringIn;}
    }
}