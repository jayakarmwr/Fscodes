/*
 * Budget Padmanabham planned to visit the tourist places. There are N tourist 
places in the city. The tourist places are numbered from 1 to N.

You are given the routes[] to travel between the tourist places in the city.
where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
Your task is to help Budget Padmanabham to find the cheapest route plan, to 
visit all the tourist places in the city. If you are not able to find such plan, 
print -1.
 
Input Format:
-------------
Line-1: Two space separated integers N and R,number of places and routes.
Next R lines: Three space separated integers, start, end and price.
  
Output Format:
--------------
Print an integer, minimum cost to visit all the tourist places.
 
 
Sample Input-1:
---------------
4 5
1 2 3
1 3 5
2 3 4
3 4 1
2 4 5
 
Sample Output-1:
----------------
8
 
Explanation:
------------
The cheapest route plan is as follows:
1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
Sample Input-2:
---------------
4 3
1 2 3
1 3 5
2 3 4
 
Sample Output-2:
----------------
-1

 */
package Day18;

import java.util.*;
class Solution
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][] arr=new int[m][3];
        int[] parent=new int[n];
        for(int i=0;i<n;i++)
        {
            parent[i]=i;
        }
        for(int i=0;i<m;i++)
        {
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
            arr[i][2]=sc.nextInt();
            
        }
        Arrays.sort(arr,(a,b)->
        {
            return a[2]-b[2];
        });
        int sum=0;
        for(int i=0;i<m;i++){
            if(union(arr[i][0]-1,arr[i][1]-1,parent))
            {
                sum+=arr[i][2];
            }
        }
        print(sum,parent);
        
        
    }
    public static void print(int sum,int[] parent)
    {
        for(int i=0;i<parent.length;i++)
        {
            if(find(i,parent)!=0)
            {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sum);
        return;
    }
    public static boolean union(int x,int y,int[] parent)
    {
        int a=find(x,parent);
        int b=find(y,parent);
        if(a==b)
        {
            return false;
        }
        if(a<b)
        {
            parent[b]=a;
        }
        else
        {
            parent[a]=b;
        }
        return true;
    }
    public static int find(int x,int[] parent)
    {
        if(parent[x]!=x)
        {
            parent[x]=find(parent[x],parent);
        }
        return parent[x];
    }
}
