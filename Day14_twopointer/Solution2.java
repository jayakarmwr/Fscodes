/*
 * Two brothers want to play a game, 
The rules of the game are: one player gives two sorted lists of 
numerical elements and a number (sum). 
The opponent has to find the closest pair of elements 
to the given sum.
-> pair consists of elements from each list

Please help those brothers to develop a program, that takes 
two sorted lists as input and return a pair as output.

Input Format:
-------------
size of list_1
list_1 values
size of list_2
list_2 values
closest number

Output Format:
--------------
comma-separated pair

Sample Input-1:
---------------
4
1 4 5 7
4
10 20 30 40
32
Sample Output-1
---------------
1, 30

Sample Input-2
---------------
3
2 4 6
4
5 7 11 13
15
sample output-2
---------------
2, 13

 */
package Day14_twopointer;

import java.util.*;
class Solution2
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr1=new int[n];
        for(int i=0;i<n;i++)
        {
            arr1[i]=sc.nextInt();
        }
        int m=sc.nextInt();
        int[] arr2=new int[m];
        for(int i=0;i<m;i++)
        {
            arr2[i]=sc.nextInt();
        }
        int s=sc.nextInt();
        int l=0;
        int r=m-1;
        int minabs=Integer.MAX_VALUE;;
        int[] ans=new int[2];
        while(l<n && r>0)
        {
            int abs=Math.abs(arr1[l]+arr2[r]-s);
            if(abs<minabs)
            {
                minabs=abs;
                ans[0]=arr1[l];
                ans[1]=arr2[r];
            }
            if(arr1[l]+arr2[r]>s)
            {
                r--;
            }
            else
            {
                l++;
            }
        }
        System.out.println(ans[0]+", "+ans[1]);
        
    }
}