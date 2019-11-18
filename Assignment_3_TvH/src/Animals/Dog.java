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
public class Dog extends Animal {
    // Declare Dog specific attributes
    private boolean squirrel = false;
    private int squirrelEndurance = randNum.nextInt(5) + 1;
    private int squirrelStart;

    public Dog(String nameIn, double runningSpeedIn, double variationOfSpeedIn, int squirrelStartIn)
    {
        super(nameIn, "Dog", runningSpeedIn, variationOfSpeedIn, 0);
        this.printGap = 24;
        this.squirrelStart = squirrelStartIn;
        this.comment = "Position Speed Comments";
    }
    private void newSquirrel() {
        this.squirrel = randNum.nextDouble() * 2 > 1.5;
        this.squirrelEndurance = randNum.nextInt(5) + 1;
    }
    // get methods
    private String getSquirrel()
    {
        if(this.squirrel)
        {
            return "squirrel";
        }
        else{return " ";}
    }
    private int getSquirrelStart(){return this.squirrelStart;}

    @Override
    public String getIntro()
    {
        String temp = String.format("\tRunning speed: %10.1f%n\tVariation of speed: %5.1f",
                getRunningSpeed(), getVariationOfSpeed());
        String temp2 = String.format("%n\tSquirrel location: %6d feet",
                getSquirrelStart());
        return (temp + temp2);
    }
    @Override
    public void setRoundSpeed(){this.roundSpeed = getRunningSpeed() - getVariationOfSpeed()
            + randNum.nextDouble()*2*getVariationOfSpeed();}

    private void setAltRoundSpeed(){this.roundSpeed = getRunningSpeed() + getVariationOfSpeed()
            - randNum.nextDouble()*2*getVariationOfSpeed();}

    // Override default updatePosition with custom method for Dog
    @Override
    public void updatePosition() {
        if(this.squirrel) {
            // Set the new current position as the old position - the current speed +/- a random value in
            // the range of two times the variation of speed (effectively back tracking)
            setAltRoundSpeed();
            setCurrentPosition(getCurrentPosition()- getRoundSpeed());
        }
        else{
            // Set the new current position as the current speed +/- a random value in the range of
            // two times the variation of speed
            setRoundSpeed();
            setCurrentPosition(getCurrentPosition()+ getRoundSpeed());
        }
        // Increment the time by 1 unit
        this.currentTime++;
        // Determine if a squirrel appears or escapes
        if(this.squirrelEndurance > 0){ this.squirrelEndurance --;}
        else{newSquirrel();}
    }

    @Override
    public String toString()
    {
        String tmpSign = " ";
        if(this.squirrel)
        {
            tmpSign = "-";
        }
        return String.format(" %6.1f  %1s%-4.1f %8s ", getCurrentPosition(), tmpSign, getRoundSpeed(), getSquirrel());
    }
}
