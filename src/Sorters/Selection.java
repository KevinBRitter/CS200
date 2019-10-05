package Sorters;

import java.util.ArrayList;

public class Selection {
    private ArrayList<Integer> sortedList;
    private int lengthOf;
    private int comparisonNum;
    private int swapNum;

    public Selection()
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
        int maxIndex;
        int loopNum = 1;
        int tempLength = this.lengthOf - 1;
        do{
            int tempMax = 0;
            System.out.println("Loop #" + loopNum + ":\tArray = \t" + this.toString());
            for(int j = 0; j < tempLength; j++)
            {
                String leadSpacer = "";
                for(int k = 0; k < j + 1; k++)
                {
                    leadSpacer += "    ";
                }
                String tailSpacer = "";
                for(int m = 0; m < this.lengthOf - j; m++)
                {
                    tailSpacer += "    ";
                }
                System.out.format("\tComparison #%-3d %s%4d%s max=%2d   array [%d]\n" , this.comparisonNum, leadSpacer,
                        this.sortedList.get(j), tailSpacer, this.sortedList.get(tempMax), tempMax);
                if (this.makeComparison(j + 1, tempMax))
                {
                    tempMax = j + 1;
                }
                this.comparisonNum++;
            }
            maxIndex = tempMax;
            if(this.sortedList.get(maxIndex) > this.sortedList.get(tempLength))
            {
                String leadSpacer = "";
                for(int n = 0; n < maxIndex + 1; n++)
                {
                    leadSpacer += "    ";
                }
                String tailSpacer = "";
                for(int p = 0; p < tempLength - maxIndex - 1; p++)
                {
                    tailSpacer += "    ";
                }
                System.out.format("\tSwap #%3d\t    %s%4d%s%4d\n" , this.swapNum, leadSpacer,
                        this.sortedList.get(tempLength), tailSpacer, this.sortedList.get(tempMax));
                this.makeSwap(tempMax, tempLength);
            }
            tempLength--;
            loopNum++;
        }while(countInversions()>0);
    }
}
