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
public class Hare extends Animal {
    // Declare Hare specific attributes
    private int napStartTime, napDuration, napCount;
    private boolean napping;


    public Hare(String nameIn, double runningSpeedIn, double variationOfSpeedIn)
    {
        super(nameIn, "Hare", runningSpeedIn, variationOfSpeedIn, 0);
        this.napStartTime = 5;
        this.napDuration = 15;
        this.napping = false;
        this.napCount = 0;
        this.printGap = 24;
        this.comment = "Position Speed Comments";
    }
    // Hare get methods
    private int getNapCount(){return this.napCount;}
    private int getNapStartTime(){return this.napStartTime;}
    private int getNapDuration(){return this.napDuration;}

    @Override
    public String getIntro()
    {
        String temp = String.format("\tRunning speed: %10.1f%n\tVariation of speed: %5.1f",
                getRunningSpeed(), getVariationOfSpeed());
        String temp2 = String.format("%n\tStart nap: %14d seconds%n\tNap duration: %11d seconds",
                getNapStartTime(), getNapDuration());
        return (temp + temp2);
    }
    // Hare set methods
    public void setNapStartTime(int napStartTime_){this.napStartTime = napStartTime_;}
    public void setNapDuration(int napDuration_){this.napDuration = napDuration_;}
    // method not used
    public void setNapping(boolean napping_){this.napping = napping_;}

    // Override default updatePosition with custom method for Dog
    @Override
    public void updatePosition()
    {
        // Napping conditional determines if the rabbit should be napping
        this.napping = this.currentTime >= this.napStartTime && this.currentTime < this.napStartTime + this.napDuration;
//        System.out.println("Hare napping = " + this.napping);
        // Set the new current position as the current speed +/- a random value in the range of
        // two times the variation of speed
        if (!this.napping) {
            setRoundSpeed();
            setCurrentPosition(getCurrentPosition() + getRoundSpeed());
            this.napCount = 0;
        }
        else
        {
            // Increment napCount by 1;
            this.roundSpeed = 0;
            this.napCount ++;
        }
        // Increment the time by 1 unit
        this.currentTime++;
    }

    @Override
    public String toString()
    {
        String napStat = " ";
        if(this.napping){napStat = String.format("nap-%-3s", getNapCount() - 1);}
        return String.format("%8.1f %5.1f %8s", getCurrentPosition(), getRoundSpeed(), napStat);
    }
}
