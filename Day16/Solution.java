/*
 * There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1
 
Note: Look HINT for explanation.

 */
package Day16;

import java.util.*;
class Solution
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][] arr=new int[m][2];
        int parent[]=new int[n];
        for(int i=0;i<n;i++)
        {
            parent[i]=i;
        }
        for(int i=0;i<m;i++)
        {
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
            union(arr[i][0],arr[i][1],parent);
            
        }
        
        HashSet<Integer> s=new HashSet<>();
        for(int i=0;i<n;i++)
        {
            s.add(find(i,parent));
        }
        System.out.println(s.size());
        
        
        
        
    }
    public static int find(int x,int[] parent)
    {
        if(parent[x]!=x)
        {
            parent[x]=find(parent[x],parent);
        }
        
        return parent[x];
        
    }
    public static void union(int x,int y,int[] parent)
    {
        int a=find(x,parent);
        int b=find(y,parent);
        if(a==b)
        {
            return;
        }
        if(a<b)
        {
            parent[b]=a;
        }
        else{
            parent[a]=b;
            
        }
        
        return;
    }
    

}