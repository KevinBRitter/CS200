package Sorters;

import java.util.ArrayList;

public class Insertion {
    private ArrayList<Integer> sortedList;
    private int lengthOf;
    private int comparisonNum;
    private int swapNum;

    public Insertion()
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
            for(int j = 1; j< this.lengthOf; j++)
            {
                System.out.println("Loop #" + loopNum + ":\tArray = \t" + this.toString());
                int k = j;
                do {
                    String spacer = "";
                    for(int l = 0; l < k; l++)
                    {
                        spacer += "    ";
                    }
                    System.out.format("\tComparison #%3d %s%4d%4d\n" , this.comparisonNum, spacer, this.sortedList.get(k - 1), this.sortedList.get(k));
                    if (this.makeComparison(k - 1, k)) {
                        System.out.format("\tSwap #%3d\t    %s%4d%4d\n" , this.swapNum, spacer, this.sortedList.get(k), this.sortedList.get(k - 1));
                        this.makeSwap(k - 1, k);
                    }
                    this.comparisonNum++;
                    k--;
                }while(k > 0 && this.sortedList.get(k-1) > this.sortedList.get(k));
                loopNum++;
            }
        }while(countInversions()>0);

    }
}
