/*
 * You are given an integer array nums and two integers l and r. Your task is to 
analyze the volatility of a sequence of values. The volatility of a sequence is 
defined as the difference between the maximum and minimum values in that sequence.

You need to determine the sequence with the highest volatility among all 
sequences of length between l and r (inclusive).

Return the highest volatility. If no such sequence exists, return -1.

Input Format:
-------------
Line-1: 3 space separated integers, n, l, r
Line-2: n space separated integers, nums[].

Output Format:
-------------
An integer, the highest volatility.


Sample Input-1:
---------------
5 2 3
8 3 1 6 2

Sample Output-1:
----------------
7

Explanation:
------------
The possible sequences of length between l = 2 and r = 3 are:

[8, 3] with a volatility of 8−3=5
[3, 1] with a volatility of 3−1=2
[1, 6] with a volatility of 6−1=5
[8, 3, 1] with a volatility of 8−1=7
The sequence [8, 3, 1] has the highest volatility of 7.

Sample Input-2:
---------------
4 2 4
5 5 5 5

Sample Output-2:
----------------
0

Explanation:
------------
All possible sequences have no volatility as the maximum and minimum values 
are the same, resulting in a difference of 0.
 

 */
package Day3_slidingwindow;

import java.util.*;
class Program3
{
    public static void main(String[] args)
    {
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int l=sc.nextInt();
        int r=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        int max=-1;
        max=Math.max(max,slide(arr,r));
        System.out.println(max);
        
    }
    public static int slide(int[] arr,int l)
    {
        int max_value=-1;
        PriorityQueue<Integer> min=new PriorityQueue<>();
        PriorityQueue<Integer> max=new PriorityQueue<>((a,b)->b-a);
        for(int i=0;i<l;i++)
        {
            min.add(arr[i]);
            max.add(arr[i]);
            
        }
        max_value=Math.max(max_value,max.peek()-min.peek());
        for(int i=l;i<arr.length;i++)
        {
            min.remove(arr[i-l]);
            max.remove(arr[i-l]);
            min.add(arr[i]);
            max.add(arr[i]);
            max_value=Math.max(max_value,max.peek()-min.peek());
            
        }
        return max_value;
        
    }
}
