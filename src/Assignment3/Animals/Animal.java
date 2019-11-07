package Assignment3.Animals;

/* ************************************************************************
 *                 Assignment 3 -- Tortoise v. Hare                       *
 *                                                                        *
 * PROGRAMMER: Kevin Ritter                                               *
 * CLASS:    CS200                                                        *
 * INSTRUCTOR: Dean Zeller                                                *
 * SUBMISSION DATE: 11/01/2019                                            *
 *                                                                        *
 * DESCRIPTION:                                                           *
 * The following is a Dean chosen animal race demonstrator.  It calls on  *
 * and uses multiple Classes stored in the 'Animals' package.  A race     *
 * adds animal instances to a list and iterates over them until one wins. *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/
import java.util.Random;

public class Animal {
    // Class attributes
    private String name, species;
    String comment = "Position Speed";
    private double runningSpeed, variationOfSpeed, currentPosition;
    double roundSpeed;
    int currentTime, printGap;
    Random randNum;

    Animal(String nameIn, String speciesIn, double runningSpeedIn, double variationOfSpeedIn,
           double currentPositionIn)
    {
        this.name = nameIn;
        this.species = speciesIn;
        this.runningSpeed = runningSpeedIn;
        this.variationOfSpeed = variationOfSpeedIn;
        this.currentPosition = currentPositionIn;
        this.currentTime = 0;
        this.printGap = 16;
        randNum = new Random();
    }
    public Animal(String nameIn, String speciesIn, double runningSpeedIn, double variationOfSpeedIn)
    {
        this(nameIn, speciesIn, runningSpeedIn, variationOfSpeedIn, 0);
    }
    public Animal(String nameIn, String speciesIn, double runningSpeedIn)
    {
        this(nameIn, speciesIn, runningSpeedIn, runningSpeedIn*0.1, 0);
    }
    public Animal()
    {
        this("Generic Name", "Unknown", 2, 0.1);
    }

    // Get methods
    public String getName(){return this.name;}
    public String getSpecies(){return this.species;}
    double getRunningSpeed(){return this.runningSpeed;}
    double getVariationOfSpeed(){return this.variationOfSpeed;}
    public double getCurrentPosition(){return this.currentPosition;}
    double getRoundSpeed(){return this.roundSpeed;}
    public int getCurrentTime(){return this.currentTime;}
    public int getPrintGap(){return this.printGap;}
    public String getComment(){return this.comment;}
    public String getIntro()
    {
        return String.format("\tRunning speed: %10.1f%n\tVariation of speed: %5.1f",
                getRunningSpeed(), getVariationOfSpeed());
    }

    // Set methods
    public void setRoundSpeed(){this.roundSpeed = getRunningSpeed() - getVariationOfSpeed()
            + randNum.nextDouble()*2*getVariationOfSpeed();}
    void setCurrentPosition(double currentPositionIn){this.currentPosition = currentPositionIn;}

    // methods not used
    public void setName(String nameIn){this.name = nameIn;}
    public void setSpecies(String speciesIn){this.species = speciesIn;}
    public void setPrintGap(int printGapIn){this.printGap = printGapIn;}
    public void setRunningSpeed(double runningSpeedIn){this.runningSpeed = runningSpeedIn;}
    public void setVariationOfSpeed(double variationOfSpeedIn){this.variationOfSpeed = variationOfSpeedIn;}

    public void updatePosition()
    {
        // Set the new current position as the old position + the current speed +/- a random value in
        // the range of two times the variation of speed
        setRoundSpeed();
        setCurrentPosition(getCurrentPosition()+ getRoundSpeed());
        // Increment the time by 1 unit
        this.currentTime++;
    }

    public String toString()
    {
        return String.format(" %8.1f %6.1f", getCurrentPosition(), getRoundSpeed());
    }

}
