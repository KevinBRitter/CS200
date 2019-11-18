package output;

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
 * The project using this print report class is a bioinformatics project  *
 * in Java.  It is intended to teach the programmer string manipulation   *
 * methods and by extension something about genetic research              *
 * in the computer age.                                                   *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/

public class PrintReport {
    private FileOutput ouputWriter = new FileOutput();
    public void writeToFile(String reportIn){
        try{
            ouputWriter.writeOutput("Assignment_4/src/report.txt", reportIn);
        }catch(IOException e){System.out.println("Report file not written due to file error.");}
    }
}
