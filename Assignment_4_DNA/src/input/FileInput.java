package input;

import java.io.File;
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
 * The project using this file input class is a bioinformatics project    *
 * in Java.  It is intended to teach the programmer string manipulation   *
 * methods and by extension something about genetic research              *
 * in the computer age.                                                   *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/
public class FileInput {
    public String getData() {
        StringBuilder rawData = new StringBuilder();
        int countFails = 0;
        Boolean satisfaction = false;
        Scanner scanIn;
        Scanner userInput = new Scanner(System.in);
        File fileReadIt;
        do{
            do {
                System.out.println("Please enter a filename.");
                String filename =  userInput.nextLine();
                fileReadIt = new File(filename);
                try {
                    scanIn = new Scanner(fileReadIt);
                    while(scanIn.hasNextLine()) {
                        rawData.append(scanIn.nextLine());
                    }

                } catch (Exception e) {
                    System.out.println("String input expected, an error occurred. " +
                            "\nPlease try again.");
                    countFails++;
                }
            } while (rawData.toString().equals("") && countFails < 4);

            System.out.println();
            System.out.println("Data from file: \n" + rawData.toString());
            System.out.println("\nAre you satisfied this is correct?");
            if(userInput.nextLine().toUpperCase().charAt(0)=='Y'){satisfaction = true;}
            else{System.out.println("Additional attempt requested...");}
        }while(!satisfaction);
        return rawData.toString();
    }
}
