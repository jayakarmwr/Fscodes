/*
 * Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
ancient runes, where each cell holds a single character. Legend has a 
powerful incantation—represented as a string—is hidden within these runes. 
To unlock the treasure, you must verify if the incantation exists on the map.

The incantation is formed by linking runes that are directly next to each other 
either horizontally or vertically. Each rune on the map can only be used once in
the incantation.

Your Task:  
Given an m x n grid representing the treasure map and a string representing the 
incantation, return true if the incantation can be traced on the map; 
otherwise, return false.


Example 1:
----------
Input:  
3 4
ABCD
SFCS
ADEE
ABCCED

Output:
ABCCED can be traced

Explanation (check hint)
Treasure Map Grid:  
[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCCED" exists in map


Example 2:
----------
Input:
3 4
ABCE
SFCS
ADEE
ABCB

Output: 
ABCB cannot be traced

Explanation:
Treasure Map Grid:  

[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCB" does not exist in map


Constraints:

- m == the number of rows in the grid  
- n == the number of columns in the grid  
- 1 <= m, n <= 6  
- 1 <= incantation length <= 15  
- The grid and incantation consist only of uppercase and lowercase English letters.

 */
package Day26;
import java.util.*;
public class WordSearch{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        char[][] arr= new char[m][n];
        for(int i=0;i<m;i++){
            String f=sc.next();
            for(int j=0;j<n;j++){
                arr[i][j]=f.charAt(j);
            }
        }
        boolean flag=false;
        String s=sc.next();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==s.charAt(0)){
                    char x=arr[i][j];
                    arr[i][j]='!';
                    if(search(arr,s,1,i,j)){
                        flag=true;
                        break;
                    }
                    arr[i][j]=x;
                }
            }
        }
        if(flag){
            System.out.println(s+" can be traced");
        }
        else{
            System.out.println(s+" cannot be traced");
        }
    }
    static public boolean search(char[][] arr,String s,int i,int m,int n) {
 	    if(s.length()==i){
 	        return true;
 	    }
 	    if(m+1<arr.length && arr[m+1][n]==s.charAt(i)){
 	        char x=arr[m+1][n];
            arr[m+1][n]='!';
 	        if(search(arr,s,i+1,m+1,n)){
 	            return true;   
 	        }
            arr[m+1][n]=x;
 	    }
 	    if(m-1>=0 && arr[m-1][n]==s.charAt(i)){
 	        char x=arr[m-1][n];
            arr[m-1][n]='!';
 	        if(search(arr,s,i+1,m-1,n)){
 	            return true;   
 	        }
            arr[m-1][n]=x;
 	    }
 	    if(n+1<arr[0].length && arr[m][n+1]==s.charAt(i)){
 	        char x=arr[m][n+1];
            arr[m][n+1]='!';
 	        if(search(arr,s,i+1,m,n+1)){
 	            return true;   
 	        }
            arr[m][n+1]=x;
 	    }
 	    if(n-1>=0 && arr[m][n-1]==s.charAt(i)){
 	        char x=arr[m][n-1];
            arr[m][n-1]='!';
 	        if(search(arr,s,i+1,m,n-1)){
 	            return true;   
 	        }
            arr[m][n-1]=x;
 	    }
 	    return false;
 	}
}