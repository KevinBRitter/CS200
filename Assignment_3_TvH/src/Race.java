package Assignment3;

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
import Assignment3.Animals.Animal;
import java.util.ArrayList;

public class Race {
    // Class attributes
    private String event, winnerName, winningSpecies;
    private String timeGap = "| %4s |";
    private StringBuilder dashes;
    private int length, winningTime, animalCount;
    private ArrayList<Animal> contestants;

    // Race constructors
    Race(int lengthIn, ArrayList<Animal> contestantsIn)
    {
        this.length = lengthIn;
        this.contestants = contestantsIn;
        this.animalCount = 1;
        introRace();
    }
    public Race(ArrayList<Animal> contestantsIn)
    {
        this(500, contestantsIn);
    }
    // set methods
    private void setEvent(String eventIn){this.event = eventIn;}
    public void setLength(int lengthIn){this.length = lengthIn;}

    // get methods
    private String getEvent(){return this.event;}
    private int getLength(){return this.length;}
    private String getCustomSpacer(Animal animalIn)
    {
        return " %" + animalIn.getPrintGap() + "s |";
    }

    /*
    introRace method creates all the dialog that appears before the race starts and adds custom line breaks
    */
    private void introRace()
    {
        // StringBuilder is a data type that facilitates string concatenation in a loop
        this.dashes = new StringBuilder("+------+");
        StringBuilder tempEvent = new StringBuilder(String.format("Event:  The %s ", this.contestants.get(0).getSpecies()));
        for(int i = 1; i < this.contestants.size(); i++)
        {
            tempEvent.append(String.format("v. the %s ", this.contestants.get(i).getSpecies()));
        }
        setEvent(tempEvent.toString());
        System.out.println(getEvent());
        System.out.println();
        System.out.println("Race distance (feet): " + getLength());
        System.out.println();
        for(Animal animal: this.contestants)
        {
            System.out.println("Contestant " + animalCount + ": " + animal.getName() + " the " + animal.getSpecies());
            System.out.println(animal.getIntro());
            System.out.println();
            this.animalCount++;
            // appends to the string builder by repeating gap size worth of dashes and finishes with a '+' sign
            this.dashes.append("-".repeat(Math.max(0, animal.getPrintGap() + 2))).append("+");
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("Aaaaaaaand... we're OFF!!!");
        System.out.println(this.dashes);

        System.out.printf(this.timeGap, " ");
        for(Animal animal: this.contestants)
        {
            System.out.printf(getCustomSpacer(animal), animal.getName());
        }
        System.out.println();

        System.out.printf(this.timeGap, " ");
        for(Animal animal: this.contestants)
        {
            System.out.printf(getCustomSpacer(animal), "(" + animal.getSpecies() + ")");
        }
        System.out.println();

        System.out.printf(this.timeGap, " ");
        for(Animal animal: this.contestants)
        {
            System.out.printf(getCustomSpacer(animal), animal.getComment());
        }
        System.out.println();
    }

    /*
    runRace method iterates through the race until an animal representing the max distance goes beyond
    the race length
    */
    void runRace()
    {
        // create a max distance value that carries the race leader footage traveled
        double max = 0;
        do {
            System.out.printf(this.timeGap, (this.contestants.get(0).getCurrentTime()));
            for(Animal animal: this.contestants){
                /*polymorphism is here where animal includes special
                species such as Dog and Hare.*/

                if(animal.getCurrentPosition()>=max){
                    max = animal.getCurrentPosition();
                    this.winnerName = animal.getName();
                    this.winningTime = animal.getCurrentTime();
                    this.winningSpecies = animal.getSpecies();
                }
                System.out.printf(getCustomSpacer(animal), animal);
                animal.updatePosition();
            }
            if(max>this.length)
            {
                break;
            }
            System.out.println();
        }while(max<this.length);
        System.out.println();
        System.out.println(this.dashes);
        // print race winner
        System.out.println("\nRace over in " + this.winningTime + " secs\n"
                + this.winnerName + " the " + this.winningSpecies+ " wins!!!");
    }
}
