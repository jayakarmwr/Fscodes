/*
 * A game rewards players with points when they complete levels. However, 
to maintain fairness and difficulty progression, the game designer wants 
to adjust the points assigned to each level so that:
    - All levels must have distinct rewards.
    - The total reward points should be minimized, ensuring that the reward 
    for each level is at least as much as it was originally assigned.

Given N levels and an array of N integers representing the initial rewards for 
each level, determine the minimum total reward points the game can assign after 
making the necessary adjustments.

Constraints
-----------
1 ≤ N ≤ 100 (total levels)
1 ≤ reward[i] ≤ 1000 (minimum reward per level)
The given rewards array may contain duplicate values

Input Format
------------
An integer N - representing the number of levels.
An array of N integers - representing the initial rewards.

Output Format
-------------
A single integer, representing the minimum total reward points after ensuring 
that all levels have unique rewards.


Sample Input:
-------------
5
10 20 30 40 50

Sample Output:
--------------
150

Explanation:
-----------
Since all reward points are already unique, the total remains:
10 + 20 + 30 + 40 + 50 = 150.

Sample Input:
-------------
5
10 30 20 30 20

Sample Output:
--------------
112

 */


import java.util.*;
class Solution2
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int sum=0;
        Set<Integer> s=new HashSet<>();
        for(int i=0;i<n;i++)
        {
            int k=sc.nextInt();
            while(s.contains(k))
            {
                k++;
            }
            sum+=k;
            s.add(k);
        }
        
        System.out.println(sum);
    }
}