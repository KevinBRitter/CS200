package Sorters;

import java.util.ArrayList;

public class Quick {
    private ArrayList<Integer> sortedList;
    private int lengthOf;
    private int comparisonNum;
    private int swapNum;
    private int levelNum;

    public Quick()
    {
        this.comparisonNum = 1;
        this.swapNum = 0;
    }
    public void arrayInput(ArrayList<Integer> listIn)
    {
        this.sortedList = listIn;
        this.lengthOf = this.sortedList.size();
        this.runSorter();
        this.levelNum = 1;
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
    private void quickSort(int low, int high)
    {
        if (low < high)
        {
            System.out.println("Level " + this.levelNum + ": \t\t" + "Array =" + this.toString());
            this.levelNum++;
            int p = this.partition(low, high);
            this.quickSort(low, p - 1);
            this.quickSort(p + 1, high);
        }
    }
    private int partition(int low, int high)
    {
        int pivot = this.sortedList.get(high);
        int i = low;
        System.out.println("\tLow = " + low +"\n\tHigh = " + high + "\n\tPivot = " + pivot);
        for (int j = low; j <= high - 1; j++)
        {
            String leadSpacer = "";
            for(int k = 0; k < j + 1; k++)
            {
                leadSpacer += "    ";
            }
            System.out.format("\tComparison #%-3d%s%4d\n", this.comparisonNum, leadSpacer,
                    this.sortedList.get(j));
            this.comparisonNum++;
            if (this.sortedList.get(j) < pivot)
            {
                if (i != j) {
                    leadSpacer = "";
                    for(int m = 0; m < i + 1; m++)
                    {
                        leadSpacer += "    ";
                    }
                    String tailSpacer = "";
                    for(int n = 0; n < j - i - 1; n++)
                    {
                        tailSpacer += "    ";
                    }
                    this.makeSwap(i, j);
                    System.out.format("\tSwap #%-2d       %s%4d%s%4d\n", this.swapNum, leadSpacer, this.sortedList.get(i),
                            tailSpacer, this.sortedList.get(j));
                }
                i++;
            }
        }
        if (this.sortedList.get(i) != this.sortedList.get(high)) {
            String leadSpacer = "";
            for(int p = 0; p < i + 1; p++)
            {
                leadSpacer += "    ";
            }
            String tailSpacer = "";
            for(int q = 0; q < high - i - 1; q++)
            {
                tailSpacer += "    ";
            }
            this.makeSwap(i, high);
            System.out.format("\tSwap #%-2d       %s%4d%s%4d\n", this.swapNum, leadSpacer, this.sortedList.get(i),
                    tailSpacer, this.sortedList.get(high));

        }
        return i;
    }
    public void runSorter()
    {
        this.quickSort(0, this.getLengthOf() - 1);
    }
}
