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
import Assignment3.Animals.Dog;
import Assignment3.Animals.Hare;

import java.util.ArrayList;

public class RaceTester {
    /* RaceTester is the main argument file for tortoise v. hare program.  This file builds the race
    and runs it to print the results of three different initial race conditions.  The winner is
    displayed at the end. */
    public static void main(String[] args)
    {
        // race1
        ArrayList<Animal> race1Animals = new ArrayList<>();
        ArrayList<Animal> race2Animals = new ArrayList<>();
        ArrayList<Animal> race3Animals = new ArrayList<>();

        // new tortoise
        Animal tommy = new Animal("Tommy", "Tortoise", 2.0, 0.2);
        race1Animals.add(tommy);
        // new hare
        Hare harry = new Hare("Harry", 12.0, 2.0);
        harry.setNapStartTime(5);
        harry.setNapDuration(20);
        race1Animals.add(harry);
        // run the race
        Race race1 = new Race(100, race1Animals);
        race1.runRace();

        System.out.println();
        System.out.println();

        // new tortoise
        Animal speedy = new Animal("Speedy", "Tortoise", 2.0, 0.5);
        race2Animals.add(speedy);
        // new hare
        Hare thumper = new Hare("Thumper", 10.0, 1.5);
        thumper.setNapStartTime(60);
        thumper.setNapDuration(80);
        race2Animals.add(thumper);
        // new dog
        Dog doug = new Dog("Doug", 6.5, 0.75, 200);
        race2Animals.add(doug);

        // run the race
        Race race2 = new Race(500, race2Animals);
        race2.runRace();

        System.out.println();
        System.out.println();

        // new tortoise
        Animal creepy = new Animal("Creepy", "Tortoise", 2.1, 1);
        race3Animals.add(creepy);
        // new hare
        Hare buggs = new Hare("Buggs", 11.0, 2.0);
        buggs.setNapStartTime(80);
        buggs.setNapDuration(250);
        race3Animals.add(buggs);
        // new dog
        Dog pluto = new Dog("Pluto", 9.0, 0.5, 600);
        race3Animals.add(pluto);
        // new snail
        Animal gary = new Animal("Gary", "Snail", 1.0, 0.25);
        race3Animals.add(gary);

        // run the race
        Race race3 = new Race(1000, race3Animals);
        race3.runRace();
    }
}
