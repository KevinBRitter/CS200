package input;

/* ************************************************************************
 *                 Assignment 4 -- String Manipulation                    *
 *                                                                        *
 * PROGRAMMER: Kevin Ritter                                               *
 * CLASS:    CS200                                                        *
 * INSTRUCTOR: Dean Zeller                                                *
 * SUBMISSION DATE: 11/15/2019                                            *
 *                                                                        *
 * DESCRIPTION:                                                           *
 * The project using this menu input class is a bioinformatics project    *
 * in Java.  It is intended to teach the programmer string manipulation   *
 * methods and by extension something about genetic research              *
 * in the computer age.                                                   *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/

import java.util.Scanner;

/**
 * The MenuInput class returns an integer selection for menu options
 * It is used to filter bad entries via a looped try catch.
 */
public class MenuInput {
    private Scanner intIn = new Scanner(System.in);
    public int getInputNum(int lower, int upper){
        int userChoice = -1;
        do{
//            System.out.println("Choose a number between: " + lower + " and " + upper);
            try{
                userChoice = Integer.parseInt(intIn.nextLine());
            }catch(Exception e){System.out.println("Invalid entry: integer expected between "
                    + lower + " and " + upper);}
        }while(!(userChoice >= lower && userChoice <= upper));
        return userChoice;
    }
}
