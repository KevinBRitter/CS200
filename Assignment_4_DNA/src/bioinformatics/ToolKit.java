package bioinformatics;

/* ************************************************************************
 *                 Assignment 4 -- String Manipulation                    *
 *                                                                        *
 * PROGRAMMER: Kevin Ritter                                               *
 * CLASS:    CS200                                                        *
 * INSTRUCTOR: Dean Zeller                                                *
 * SUBMISSION DATE: 11/15/2019                                            *
 *                                                                        *
 * DESCRIPTION:                                                           *
 * The project using this tool kit class is a bioinformatics project      *
 * in Java.  It is intended to teach the programmer string manipulation   *
 * methods and by extension something about genetic research              *
 * in the computer age.                                                   *
 *                                                                        *
 * COPYRIGHT:                                                             *
 * This program is the sole work and toil of Kevin Ritter (c) 2019        *
 * While it does implement commonly used algorithms, the final            *
 * application of these algorithms is novel to this work.                 *
 * ***********************************************************************/

import input.MenuInput;
import input.UserInput;
import output.PrintReport;

import java.util.*;

public class ToolKit {
    private String rawData;
    private String data;
    private String dataRNA = " ";
    private StringBuilder programLogger = new StringBuilder();
    private HashMap<String, Integer> singleCounts;
    private HashMap<String, Integer> doubleCounts;
    private HashMap<String, Integer> tripleCounts;
    public ToolKit(String rawDataIn)
    {
        this.rawData = rawDataIn;
        runProgramMethods();
    }
    /**
     * The runProgramMethods method runs a loop menu to allow users to chose operations
     * to run on the data.  This is meant to facilitate testing and execution.
     */
    private void runProgramMethods()
    {
        this.programLogger.append("\nLogging program started.\n");
        this.data = stripErrors(this.rawData);
        boolean exit = false;
        MenuInput intSelection = new MenuInput();
        Scanner scanIn = new Scanner(System.in);
        String userSelection;
        int selectionNumber = -1;
        do{
            printMenu();
            selectionNumber = intSelection.getInputNum(0, 6);
            if(selectionNumber == 1){
                System.out.println("Generate RNA selected.");
                generateRNA(this.data);
                this.dataRNA = getRNA();
                userSelection = "RNA generation";
                this.programLogger.append("generateRNA method called.\n");
                System.out.println("DNA converted to RNA: \n"+ this.dataRNA);
                this.programLogger.append("getRNA method called.\n");
                this.programLogger.append("DNA converted to RNA: \n").append(this.dataRNA).append("\n");
            }
            else if(selectionNumber == 2){
                System.out.println("Search sequence selected.");
                UserInput userString = new UserInput();
                String subString = stripErrors(userString.getData());
                int indVal = searchSequence(this.data, subString);
                this.programLogger.append("searchSequence method called.\n");
                if(indVal == -1){System.out.println("Index: -1, substring not found.");}
                else{System.out.println("Substring at index: " + indVal);}
                this.programLogger.append("substring at index: ").append(indVal).append("\n");
                userSelection = "Sequence search";
            }
            else if(selectionNumber == 3){
                System.out.println("Simple sequence analysis selected.");
                this.programLogger.append("simpleSequenceAnalysis method called.\n");
                simpleSequenceAnalysis();
                System.out.println("Single strings found: ");
                printHash(getSingles());
                this.programLogger.append("Single strings found: \n").append(getSingles()).append("\n");

                System.out.println("\nDouble strings found: ");
                printHash(getDoubles());
                this.programLogger.append("Double strings found: \n").append(getDoubles()).append("\n");

                System.out.println("\nTriple strings found: ");
                printHash(getTriples());
                this.programLogger.append("Triple strings found: \n").append(getTriples()).append("\n");

                userSelection = "Simple sequence analysis";
            }
            else if(selectionNumber == 4){
                System.out.println("Multi search selected.");
                this.programLogger.append("multiSearchSequence method called.\n");
                UserInput userString = new UserInput();
                String subString = stripErrors(userString.getData());

                System.out.println("Searching for: " + subString);
                this.programLogger.append("Searching for: \n").append(subString).append("\n");

                ArrayList<Integer> indicesFound = multiSearchSequence(this.data, subString);
                System.out.println("Indices found: " + indicesFound.toString());
                this.programLogger.append("Indices found: \n").append(indicesFound.toString()).append("\n");
                userSelection = "Multi search sequence";
            }
            else if(selectionNumber == 5){
                System.out.println("Most common sequence selected.");
                this.programLogger.append("mostCommonSequence method called.\n");
                System.out.println("Range of options: 1 to " + this.data.length());
                int userSelectNum = intSelection.getInputNum(1,this.data.length());

                String maximumKey = getKeyOfMaxValue(getHashOfSize(userSelectNum));
                System.out.println("Max " + maximumKey);
                this.programLogger.append("Max: ").append(maximumKey).append("\n");
                userSelection = "Most common sequence";
            }
            else if(selectionNumber == 6) {
                System.out.println("Replace sequence selected.");
                this.programLogger.append("replaceSequence method called.\n");
                UserInput userString = new UserInput();
                System.out.println("Choose sequence to search for: ");
                String userInput1 = stripErrors(userString.getData());

                System.out.println("Choose sequence to replace selection with: ");
                String userInput2 = stripErrors(userString.getData());

                System.out.println("Replacement operation returned: " + replaceSequence(userInput1, userInput2));
                this.programLogger.append("Replacement operation called for: ").append(userInput1)
                        .append(" and ").append(userInput2).append("\n");
                userSelection = "Replace sequence";
            }
            else{userSelection = "Arbitrary selection";}

            System.out.println();
            System.out.println(userSelection + " completed. \n\nWould you like to chose another method?");
            if(scanIn.nextLine().toUpperCase().charAt(0)=='N'){
                exit = true;
                System.out.println("Returning to Main menu.");
            }
            else{System.out.println("Returning to menu.");}
        }while(!exit);
        this.programLogger.append("User selected to exit.\nExiting tool kit.\n");
        printReport();
//        System.out.println(this.programLogger.toString());
    }
    /**
    *  The stripErrors method cleans DNA data of errors, only 'ATCG' allowed
    */
    private String stripErrors(String dataIn)
    {
        this.programLogger.append("stripErrors method called.\n");
        String basicDNA = "ATCGatcg";
        StringBuilder cleanDNA = new StringBuilder();
        StringBuilder junkDNA = new StringBuilder();
        HashMap<String, Integer> junkHash = new HashMap<>();
        for(char c: dataIn.toCharArray()) {
            if (basicDNA.contains(Character.toString(c))) {
                cleanDNA.append(Character.toString(c).toUpperCase());
            } else{
                junkDNA.append(c);
                if(!junkHash.containsKey(Character.toString(c))){junkHash.put(Character.toString(c),1);}
                else{junkHash.put(Character.toString(c), (int)junkHash.get(Character.toString(c)) + 1);}
            }
        }
        System.out.println("Checking for errors.\nErrors counted: " + junkDNA.length()
                + "\nErrors found:\n" + junkDNA.toString() + "\nError Counts:\n");
        printHash(junkHash);
        String cleanDNAPrint;
        int excess;
        if (cleanDNA.length() > 150) {
            excess = cleanDNA.length() - 150;
            cleanDNAPrint = cleanDNA.toString().substring(0, 150) + " ==> characters omitted: " + excess;
        }
        else
            cleanDNAPrint = cleanDNA.toString();
        System.out.println("\n\nDNA sequence length: " + cleanDNA.length()
                + "\nDNA remaining: \n" + cleanDNAPrint + "\n");
        return cleanDNA.toString();
    }
    /**
     * The printMenu method displays all the user options on the main program.
     */
    private void printMenu()
    {
        this.programLogger.append("selection menu displayed.\n");
        String menu = "Choose from the following options:\n"
                + "# 1.  Generate RNA from raw data: Replace Thymine with Uracil.\n"
                + "# 2.  Search for a given sequence: Return index if found or -1.\n"
                + "# 3.  Run a simple sequence analysis on the sequence: Ex. count 'A's.\n"
                + "# 4.  Multi sequence analysis: Returns list of found indices.\n"
                + "# 5.  Most common sequence: returns the max sequence of choice size.\n"
                + "# 6.  Replace Sequence: swap substring A with string B.\n";
        System.out.println(menu);
    }
    /**
     * The getRNA method returns the RNA string when called.
     */
    private String getRNA(){
        return this.dataRNA;
    }
    /**
     * The generateRNA method converts DNA to RNA.
     */
    private void generateRNA(String dnaIn){
        this.dataRNA = dnaIn.replace('T', 'U');
    }
    /**
     * The searchSequence method returns the index of a substring within data.
     */
    private int searchSequence(String mainSequenceIn, String minorSequenceIn)
    {
        return mainSequenceIn.indexOf(minorSequenceIn);
    }
    /**
     * The multiSearchSequence method returns an ArrayList of indices for all occurrences
     * of a string found within a larger string, or an empty list if none are found.
     */
    private ArrayList<Integer> multiSearchSequence(String mainSequenceIn, String minorSequenceIn)
    {
        ArrayList<Integer> indicesFound = new ArrayList<>();
        String mainString = mainSequenceIn;
        int currentIndex, totalIndex;
        totalIndex = 0;
        do{
            currentIndex = searchSequence(mainString, minorSequenceIn);
            if(currentIndex!=-1){
                totalIndex += currentIndex;
                indicesFound.add(totalIndex);
                mainString = mainString.substring(currentIndex + 1);
            }
            totalIndex++;
        }while(currentIndex!=-1);
        return indicesFound;
    }
    /**
     * The simpleSequenceAnalysis method calls the counts of all substring sequences for one
     * two and three characters within data.
     */
    private void simpleSequenceAnalysis()
    {
        countSingles();
        countDoubles();
        countTriples();
    }
    /**
     * The countSingles method creates a HashMap of all individual elements discovered
     * in a string.
     */
    private void countSingles(){
        this.singleCounts = new HashMap<>();
        for(char c: this.data.toCharArray()) {
            if(!this.singleCounts.containsKey(Character.toString(c))){this.singleCounts.put(Character.toString(c),1);}
            else{this.singleCounts.put(Character.toString(c), this.singleCounts.get(Character.toString(c)) + 1);}
        }
    }
    private HashMap<String, Integer> getSingles(){return this.singleCounts;}
    /**
     * The countDoubles method creates a HashMap of all double elements discovered
     * in a string.
     */
    private void countDoubles(){
        this.doubleCounts = new HashMap<>();
        String tempString;
        for(int i = 2; i < this.data.length() + 1; i++)
        {
            tempString = data.subSequence(i - 2, i).toString();
            if(!this.doubleCounts.containsKey(tempString)){this.doubleCounts.put(tempString, 1);}
            else{this.doubleCounts.put(tempString, this.doubleCounts.get(tempString) + 1);}
        }
    }
    private HashMap<String, Integer> getDoubles(){return this.doubleCounts;}
    /**
     * The countTriples method creates a HashMap of all triple character elements discovered
     * in a string.
     */
    private void countTriples(){
        this.tripleCounts = new HashMap<>();
        String tempString;
        for(int i = 3; i < this.data.length() + 1; i++)
        {
            tempString = data.subSequence(i - 3, i).toString();
            if(!this.tripleCounts.containsKey(tempString)){this.tripleCounts.put(tempString, 1);}
            else{this.tripleCounts.put(tempString, this.tripleCounts.get(tempString) + 1);}
        }
    }
    private HashMap<String, Integer> getTriples(){return this.tripleCounts;}
    /**
     * The getHashOfSize method returns a custom hash map of keys that are
     * of given integer size
     */
    private HashMap<String, Integer> getHashOfSize(int numIn){
        HashMap<String, Integer> customHash = new HashMap<>();
        String tempString;
        for(int i = numIn; i < this.data.length() + 1; i++)
        {
            tempString = data.subSequence(i - numIn, i).toString();
            if(!customHash.containsKey(tempString)){customHash.put(tempString, 1);}
            else{customHash.put(tempString, customHash.get(tempString) + 1);}
        }
        return customHash;
    }
    /**
     * The getKeyOfMaxValue method returns the key corresponding to the largest
     * value of a particular HashMap
     */
    private String getKeyOfMaxValue(HashMap<String, Integer> hashIn)
    {
        return Collections.max(hashIn.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
    /**
     * The printHash method takes in a hash map and prints out the keys and values
     * in a table format.
     */
    private void printHash(HashMap<String, Integer> hashIn){
        int counter = 0;
        StringBuilder output = new StringBuilder();
        ArrayList<String> sortedKeys = new ArrayList<String>(hashIn.keySet());
        Collections.sort(sortedKeys);
        for(String key: sortedKeys) {
            counter++;
            output.append(key).append(" = ").append(hashIn.get(key)).append("  ");
            if (counter % 16 == 0)
                output.append("\n");
        }
        System.out.println(output);
    }
    /**
     * The replaceSequence method takes user input and swaps one sequence for another if it exist
     * it returns an integer value to declare success or failure
     */
    private int replaceSequence(String finderIn, String replacerIn){
        if(searchSequence(this.data, finderIn) == -1){return -1;}
        else{
            int indexOf = searchSequence(this.data, finderIn);
            this.data = this.data.substring(0, indexOf) + replacerIn
                    + this.data.substring(indexOf + replacerIn.length());
            System.out.println(this.data);
            return 1;
        }
    }
    /**
     * The printReport method calls the PrintReport class and passes into it the program log
     * that has been appending throughout the operation.
     */
    private void printReport(){
        this.programLogger.append("printReport method called: \nthis.data: \n");
        if(this.data.length() > 150){
            int excess = this.data.length()- 150;
            this.programLogger.append(this.data, 0, 150)
                    .append(" ==> characters omitted: ").append(excess).append("\n");}
        else{this.programLogger.append(this.data).append("\n");}
        PrintReport printout = new PrintReport();
        printout.writeToFile(this.programLogger.toString());
        System.out.println(this.programLogger.toString());
    }
}