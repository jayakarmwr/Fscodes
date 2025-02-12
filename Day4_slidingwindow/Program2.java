/*
 * You are a renowned Alchemist exploring a mystical forest teeming with magical plants. 
Each plant you encounter has a unique Essence Power represented by an integer in 
the array plants of length n.

By collecting essences from consecutive plants, you can brew powerful Elixirs. 
The potency of an elixir is determined by the sum of the essence powers of the plants used. 
Due to the complexity of brewing, you can only create elixirs using sequences of 
plants that are at least m in length.

Your objective is to find the kth smallest elixir potency that can be brewed 
from these sequences.

Example 1:
Input: n=3 plants = [2, 1, 3], k = 2, m = 2
Output: 4

Explanation:
Possible elixirs (sequences of length ≥ 2):
- [2, 1]: Potency = 2 + 1 = 3
- [1, 3]: Potency = 1 + 3 = 4
- [2, 1, 3]: Potency = 2 + 1 + 3 = 6
Ordered elixir potencies: 3, 4, 6
The 2nd smallest elixir potency is 4.

Input Format:
-------------
Line-1: 3 space separated integers, n, k, m
Line-2: n space separated integers, movieRatings[].

Output Format:
-------------
An integer, The kth smallest elixir potency


Sample Input:
4 3 2
3 -3 5 2

Sample output:
4

Explanation:
Possible elixirs (sequences of length ≥ 2):
- [3, -3]: Potency = 3 + -3 = 0
- [-3, 5]: Potency = -3 + 5 = 2
- [5, 2]: Potency = 5 + 2 = 7
- [3, -3, 5]: Potency = 3 + -3 + 5 = 5
- [-3, 5, 2]: Potency = -3 + 5 + 2 = 4
- [3, -3, 5, 2]: Potency = 3 + -3 + 5 + 2 =7

Ordered elixir potencies: 0, 2, 4, 5, 7, 7
The 3rd smallest elixir potency is 4.
 */
package Day4_slidingwindow;

import java.util.*;
class Program2
{
    public static PriorityQueue<Integer> q=new PriorityQueue<>();
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
         int k=sc.nextInt();
        int m=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
       
        
        
        for(int i=m;i<=n;i++)
        {
            slide(arr,i);
        }
        int res=0;
        while(!q.isEmpty() && k>0)
        {
            res=q.poll();
            k--;
        }
        System.out.println(res);
    }
    public static void slide(int[] arr,int n)
    {
        int sum=0;
        for(int i=0;i<n;i++)
        {
            sum+=arr[i];
        }
        q.add(sum);
        for(int i=n;i<arr.length;i++)
        {
            sum-=arr[i-n];
            sum+=arr[i];
            
            
            q.add(sum);
            
        }
        return;
    }
}