/* ************************************************************************
 *                 Assignment 2 -- Sorting Algorithms                     *
 *                                                                        *
 * PROGRAMMER: Kevin Ritter                                               *
 * CLASS:    CS200                                                        *
 * INSTRUCTOR: Dean Zeller                                                *
 * SUBMISSION DATE: 10/04/2019                                            *
 *                                                                        *
 * DESCRIPTION:                                                           *
 * The following is a user chosen algorithm demonstrator.  It calls on    *
 * and uses multiple Classes stored in the 'Sorters' package.  A user     *
 * selects from a menu whether they wish to enter their own numbers or    *
 * use random number entries.                                             *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/

import Sorters.Bubble;
import Sorters.Insertion;
import Sorters.Selection;
import Sorters.Quick;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/* ************************************************************************
 * Main Class contains the main method and is the runnable argument.      *
 * ***********************************************************************/
public class Main {

    public static void main(String[] args) {
        // Declare global variables necessary for running
        Scanner scanIn = new Scanner(System.in); // user input scanner
        Random randIn = new Random();
        Boolean keepRunning = true;

        ArrayList<Integer> selectValues; // working array passes into sorters

        Bubble sorterBubble = new Bubble(); // new bubble sorter algorithm tool
        Insertion sorterInsertion = new Insertion();  // new insertion sorter algorithm tool
        Selection sorterSelection = new Selection();  // new selection sorter algorithm tool
        Quick sorterQuick = new Quick();  // new quick sorter algorithm tool

        // Welcome notes and first selection
        System.out.println("Thank you for choosing this sorting program.\n\nPlease select from the menu below: \n");
        System.out.println("Choices are made by entering an associated\nnumber then hitting the enter key.\n");
//        System.out.println("If you are unsure what each choice means,\nenter 'h' to see descriptions.");

        do {
            selectValues = new ArrayList<>();
            String menuSize = "Enter the number of integers you wish to see sorted.";
            System.out.println(menuSize);
            int sizeOfArray = scanIn.nextInt();
            System.out.println();

            String menuManual = "1. Enter positive integers manually.\n2. Use randomly generated integers.\n";
            System.out.println(menuManual);
            int manualChoice = scanIn.nextInt();
            if(manualChoice == 1)
            {
                System.out.println("You chose to enter manually.");
                for(int i = 0; i<sizeOfArray; i++)
                {
                    System.out.format("Enter value %d\n", i+1);
                    selectValues.add(scanIn.nextInt());
                }
            }
            else
            {
                System.out.println("Enter the lower bound for your numbers less than 99");
                int lowerIn = scanIn.nextInt();
                System.out.println("Enter the upper bound for your numbers less than 99");
                int upperIn = scanIn.nextInt();
                for(int i = 0; i<sizeOfArray; i++)
                {
                    selectValues.add(randIn.nextInt(upperIn - lowerIn) + lowerIn);
                }
            }

            String menuSorters = "Choose a sorting method:\n1. Bubble sort\n2. Insertion sort\n3. Selection sort\n" +
                    "4. Quick sort\n";
            System.out.print(menuSorters);
            int sorterChoice = scanIn.nextInt();
            int compares = 0;
            int swaps = 0;
            int work = 0;
            if(sorterChoice == 1)
            {
                sorterBubble.arrayInput(selectValues);
                compares = sorterBubble.getComparisonNum();
                swaps = sorterBubble.getSwapNum();
            }
            else if(sorterChoice == 2)
            {
                sorterInsertion.arrayInput(selectValues);
                compares = sorterInsertion.getComparisonNum();
                swaps = sorterInsertion.getSwapNum();
            }
            else if(sorterChoice == 3)
            {
                sorterSelection.arrayInput(selectValues);
                compares = sorterSelection.getComparisonNum();
                swaps = sorterSelection.getSwapNum();
            }
            else if(sorterChoice == 4)
            {
                sorterQuick.arrayInput(selectValues);
                compares = sorterQuick.getComparisonNum();
                swaps = sorterQuick.getSwapNum();
            }
            work = compares + swaps * 5;
            String analyze = "\nAnalysis:\n\tComparisons:   %d\n\tSwaps:         %d\n\tWork:          %d + %d*5 = %d\n";
            System.out.format(analyze, compares, swaps, compares, swaps, work);


            String checkRun = scanIn.nextLine();
            String runAgain = "Would you like to keep running algorithms? yes/no";
            System.out.println();
            System.out.println(runAgain);
            checkRun = scanIn.nextLine();
            if (checkRun.charAt(0)!='y')
            {
                keepRunning = false;
            }
        }while(keepRunning);
    }
}
