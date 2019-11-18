package input;

import java.util.Random;

/* ************************************************************************
 *                 Assignment 4 -- String Manipulation                    *
 *                                                                        *
 * PROGRAMMER: Kevin Ritter                                               *
 * CLASS:    CS200                                                        *
 * INSTRUCTOR: Dean Zeller                                                *
 * SUBMISSION DATE: 11/15/2019                                            *
 *                                                                        *
 * DESCRIPTION:                                                           *
 * The project using this random input class is a bioinformatics project  *
 * in Java.  It is intended to teach the programmer string manipulation   *
 * methods and by extension something about genetic research              *
 * in the computer age.                                                   *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/

public class RandomInput {
    private int sequenceLength = 100;

    /**
     * The getData method creates and returns a new sequence of randomly generated DNA
     */
    public String getData() {
        Random newNum = new Random();
        StringBuilder sequence = new StringBuilder();
        String possibleChars = "AGCT";
        for(int i = 0; i < getSequenceLength(); i++)
        {
            sequence.append(possibleChars.charAt(newNum.nextInt(4)));
        }
        return sequence.toString();
    }
    /**
    * The get and set SequenceLength methods return and adjust the length respectively
    */
    private int getSequenceLength(){
        return this.sequenceLength;
    }
    public void setSequenceLength(int numIn){
        this.sequenceLength = numIn;
    }
}