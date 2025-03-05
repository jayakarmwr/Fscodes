/*
 * There are N computers in a network, all the computers are connected as tree 
structure. And one new connection is added in the Network. The computers in 
the network are identified with their IDs, the IDs are numbered between 1 to N.

The connections in the network is given as coonection[i] = [comp-A, comp-B], 
there is a connection between comp-A and comp-B.

Your task is to remove a connection in the network and print it, so that 
all the computers are connected as tree structure. If there are multiple 
options to remove, remove the connection that occurs last in the input.


Input Format:
-------------
Line-1: Two space separated integers N, number of computers.
Next N lines: Two space separated integers, comp-A & comp-B.

Output Format:
--------------
Print the connection which is removed.


Sample Input-1:
---------------
6
1 2
3 4
3 6
4 5
5 6
2 3

Sample Output-1:
---------------
5 6


Sample Input-2:
---------------
4
1 2
2 3
3 4
2 4

Sample Output-2:
---------------
2 4

 */
package Day17;

import java.util.*;
class Solution
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int max=-1;
        int[][] arr=new int[n][2];
        for(int i=0;i<n;i++)
        {
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
            max=Math.max(max,Math.max(arr[i][0],arr[i][1]));
        }
        int[] parent=new int[max];
        for(int i=0;i<max;i++)
        {
            parent[i]=i;
        }
        int a=-1;
        int b=-1;
        for(int i=0;i<n;i++)
        {
            if(union(arr[i][0]-1,arr[i][1]-1,parent))
            {
                a=arr[i][0];
                b=arr[i][1];
            }
        }
        System.out.println(a+" "+b);
        
        
    }
    public static boolean union(int x,int y,int[] parent)
    {
        int a=find(x,parent);
        int b=find(y,parent);
        if(a==b)
        {
            return true;
        }
        if(a<b)
        {
            parent[b]=a;
        }
        else
        {
            parent[a]=b;
        }
        return false;
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
