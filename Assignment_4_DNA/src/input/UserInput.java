package input;

import java.util.Scanner;

/* ************************************************************************
 *                 Assignment 4 -- String Manipulation                    *
 *                                                                        *
 * PROGRAMMER: Kevin Ritter                                               *
 * CLASS:    CS200                                                        *
 * INSTRUCTOR: Dean Zeller                                                *
 * SUBMISSION DATE: 11/15/2019                                            *
 *                                                                        *
 * DESCRIPTION:                                                           *
 * The project using this user input class is a bioinformatics project    *
 * in Java.  It is intended to teach the programmer string manipulation   *
 * methods and by extension something about genetic research              *
 * in the computer age.                                                   *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/
public class UserInput {
    /**
     *   getData method returns a new string of user entered data,
     *   not restricted to clean DNA
     */
    public String getData() {
        String rawData = "";
        Boolean satisfaction = false;
        Scanner strIn = new Scanner(System.in);
        do {
            do {
                try {
                    System.out.println();
                    System.out.println("Please enter a DNA sequence string below.");
                    rawData = strIn.nextLine();

                } catch (Exception e) {
                    System.out.println("String input expected, an error occurred. " +
                            "\nPlease try again.");
                }
            } while (rawData.equals(""));
            System.out.println();
            System.out.println("You entered: \n" + rawData);
            System.out.println("\nWould you like to re-enter your data?");
            if(strIn.nextLine().toUpperCase().charAt(0)=='N'){satisfaction = true;}
            else{System.out.println("Additional attempt requested...");}
        }while(!satisfaction);
        return rawData;
    }
}