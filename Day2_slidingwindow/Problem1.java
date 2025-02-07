/*
 In a software development company, a team works on various projects over n weeks. 
The team completes a certain number of tasks tasks[i] each week and dedicates 
hours[i] hours of work. Given an integer k, for every consecutive sequence of 
k weeks (tasks[i], tasks[i+1], ..., tasks[i+k-1] and 
hours[i], hours[i+1], ..., hours[i+k-1] for all 0 <= i <= n-k), 
they evaluate T, the total number of tasks completed during that sequence 
of k weeks, and E, the total hours of work during that sequence of k weeks:

a) If T < lower and E >= work_goal, the team performed very poorly and loses 2 points
b) If T < lower and E < work_goal, the team performed poorly and loses 1 point.
c) If T >= upper and E >= work_goal, the team performed well and gains 1 point.
d) If 5, the team performed exceptionally well and gains 2 points.
e) Otherwise, the team's performance is normal and there is no change in points.

Initially, the team starts with zero points. Return the total number of points 
the team has after working for n weeks. Note that the total points can be negative.

Example 1
Input:
n = 5
tasks = [10, 20, 30, 40, 50]
hours = [30, 20, 10, 30, 40]
k = 2
lower = 35
upper = 70
work_goal = 45

Output: 1
Explanation:
For [10, 20] and [30, 20]:
T = 30 < lower and E = 50 >= work_goal, the team performed very poorly and loses 2 points.

For [20, 30] and [20, 10]:
T = 50 >= lower and T <= upper and E = 30 < work_goal, no change in points.

For [30, 40] and [10, 30]:
T = 70 = upper and E = 40 < work_goal, the team performed exceptionally well and 
gains 2 points.

For [40, 50] and [30, 40]:
T = 90 > upper and E = 70 >= work_goal, the team performed well and gains 1 point.

Therefore, the team gains 1 point (0 - 2 + 2 + 1 = 1).

Sample Input=
4	//n
5 8 10 15
25 30 20 25
3	//k
25	//lower
40	//upper
60	//work_goal
Sample Output=
-2
 */
package Day2_slidingwindow;

import java.util.*;
class Problem1
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] tasks=new int[n];
        int[] hours=new int[n];
        for(int i=0;i<n;i++)
        {
            tasks[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++)
        {
            hours[i]=sc.nextInt();
        }
        int k=sc.nextInt();
        int lower=sc.nextInt();
        int upper=sc.nextInt();
        int goal=sc.nextInt();
        int T=0;
        int E=0;
        int total=0;
        for(int i=0;i<k;i++)
        {
            T+=tasks[i];
            E+=hours[i];
            
        }
        if(T < lower  && E >= goal)
            {
                total-=2;
            }
            else if(T < lower && E < goal)
            {
                total-=1;
            }
            else if(T >= upper && E >= goal)
            {
                total+=1;
                
            }
            else if(T >= upper && E < goal)
            {
                total+=2;
            }
        for(int i=k;i<n;i++)
        {
            T-=tasks[i-k];
            T+=tasks[i];
            E-=hours[i-k];
            E+=hours[i];
            if(T < lower && E >= goal)
            {
                total-=2;
            }
            else if(T < lower && E < goal)
            {
                total-=1;
            }
            else if(T >= upper && E >= goal)
            {
                total+=1;
                
            }
            else if(T >= upper && E < goal)
            {
                total+=2;
            }
        }
        System.out.println(total);
    }
}
