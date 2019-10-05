package Sorters;

import java.util.ArrayList;

public class Bubble
{
    private ArrayList<Integer> sortedList;
    private int lengthOf;
    private int comparisonNum;
    private int swapNum;

    public Bubble()
{
    this.comparisonNum = 1;
    this.swapNum = 1;
}
    public void arrayInput(ArrayList<Integer> listIn)
    {
        this.sortedList = listIn;
        this.lengthOf = this.sortedList.size();
        this.runSorter();
    }
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        for(int elem: this.sortedList)
        {
            if(elem < 10)
            {
                output.append("   " + elem);
            }
            else
            {
                output.append("  " + elem);
            }
        }
        return output.toString();
    }
    public int getLengthOf()
    {
        return this.lengthOf;
    }
    public int getComparisonNum()
    {
        return this.comparisonNum;
    }
    public int getSwapNum()
    {
        return this.swapNum;
    }
    private boolean makeComparison(int index1, int index2)
    {
        // If the first index value is larger than the second return True
        return (this.sortedList.get(index1) > this.sortedList.get(index2));
    }
    private void makeSwap(int index1, int index2)
    {
        // swap two index values
        int temp = this.sortedList.get(index1);
        this.sortedList.set(index1, this.sortedList.get(index2));
        this.sortedList.set(index2, temp);
        this.swapNum++;
    }
    public int countInversions()
    {
        int count = 0;
        for(int i = 0; i< this.lengthOf-1; i++)
        {
            for(int j = i + 1; j< this.lengthOf-1; j++) {
                if (this.makeComparison(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    public void runSorter()
    {
        int loopNum = 1;
        do {
            System.out.println("\nLoop #" + loopNum + ":\tArray =\t" + this.toString());
            for(int j = 0; j< this.lengthOf - 1; j++)
            {
                String spacer = "";
                for(int k = 0; k < j; k++)
                {
                    spacer += "    ";
                }
                System.out.format("\tComparison #%3d %s%4d%4d\n" , this.comparisonNum, spacer, this.sortedList.get(j), this.sortedList.get(j+1));
                if(this.makeComparison(j, j+1))
                {
                    System.out.format("\tSwap #%3d\t    %s%4d%4d\n" , this.swapNum, spacer, this.sortedList.get(j+1), this.sortedList.get(j));
                    this.makeSwap(j, j+1);

                }
                this.comparisonNum++;
            }
            loopNum++;
        }while(countInversions()>0);

    }

}