/*
 * Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

The puzzle board has some patterns formed with boxes in it, 
the patterns may be repeated. The patterns are formed with boxes (1's) only, 
that are connected horizontally and vertically but not diagonally.

Pranav wants to find out the number of unique patterns in the board.

You are given the board in the form of a grid M*N, filled wth 0's and 1's.
Your task is to help Pranav to find the number of unique patterns in 
the puzzle board.

Input Format:
-------------
Line-1: Two integers M and N, the number of rows and columns in the grid-land.
Next M lines: contains N space-separated integers [0, 1].

Output Format:
--------------
Print an integer, the number of unique patterns in the puzzle board.


Sample Input-1:
---------------
5 5
0 1 0 1 1
1 1 1 0 1
0 1 0 1 0
1 0 1 1 1
1 1 0 1 0

Sample Output-1:
----------------
3

Explanation-1:
------------
The unique patterns are as follows:
  1			1 1	    1 
1 1 1		  1 ,	1 1
  1	   ,	
   
   
Sample Input-2:
---------------
6 6
1 1 0 0 1 1
1 0 1 1 0 1
0 1 0 1 0 0
1 1 0 0 0 1
0 0 1 0 1 1
1 1 0 1 0 0

Sample Output-2:
----------------
5

Explanation-2:
------------
The unique patterns are as follows:
1 1		1 1		    1		1 1	,	1
1   ,     1 ,	    1 1 ,		


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
        int[][] arr=new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                arr[i][j]=sc.nextInt();
            }
        }
        System.out.println(find(arr));
    }
    public static int find(int[][] arr)
    {
        Set<String> set=new HashSet<>();
        boolean[][] vist=new boolean[arr.length][arr[0].length];
        for(int r=0;r<arr.length;r++)
        {
            for(int c=0;c<arr[0].length;c++)
            {
                if(arr[r][c]==1 && !vist[r][c])
                {
                    StringBuilder s=new StringBuilder();
                    dfs(arr,r,c,r,c,s,vist);
                    set.add(s.toString());
                }
            }
        }
        return set.size();
    }
    public static void dfs(int[][] arr,int r,int c,int baser,int basec,StringBuilder s,boolean[][] vist)
    {
        if(r<0 || c<0 || r>=arr.length || c>=arr[0].length || vist[r][c] || arr[r][c]==0)
        {
            return;
        }
        vist[r][c]=true;
        s.append((r-baser)+""+(c-basec));
        dfs(arr,r,c+1,baser,basec,s,vist);
        dfs(arr,r,c-1,baser,basec,s,vist);
        dfs(arr,r+1,c,baser,basec,s,vist);
        dfs(arr,r-1,c,baser,basec,s,vist);
        return;
        
    }
}