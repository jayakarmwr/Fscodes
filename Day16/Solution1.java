/*
 * You are a database integrity engineer working for a global cloud company. 
Your team maintains a distributed database network, where each server either:
    - Stores equivalent data to another server (serverX == serverY).
    - Stores different data from another server (serverX != serverY).

The transitive consistency rule must be followed:
    - If A == B and B == C, then A == C must be true.
    - If A == B and B != C, then A != C must be true.

Your task is to analyze the given constraints and determine whether they 
follow transitive consistency. If all relations are consistent, return true; 
otherwise, return falseGG

Input Format:
-------------
Space separated strnigs, list of relations

Output Format:
--------------
Print a boolean value, whether transitive law is obeyed or not.


Sample Input-1:
---------------
a==b c==d c!=e e==f

Sample Output-1:
----------------
true


Sample Input-2:
---------------
a==b b!=c c==a

Sample Output-2:
----------------
false

Explanation:
------------
{a, b} form one equivalence group.
{c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
However, b != c contradicts b == a and c == a.

Sample Input-3:
---------------
a==b b==c c!=d d!=e f==g g!=d

Sample Output-3:
----------------
true

 */
package Day16;

import java.util.*;
class Solution1
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] str=s.split(" ");
        int[] parent=new int[26];
        for(int i=0;i<26;i++)
        {
            parent[i]=i;
        }
        boolean flag=true;;
        for(int i=0;i<str.length;i++)
        {
            if(str[i].charAt(1)=='=')
            {
                union(str[i].charAt(0),str[i].charAt(3),parent);
            }
           
        }
        for(int i=0;i<str.length;i++)
        {
            if(str[i].charAt(1)=='!')
            {
               
                flag=isvalid(str[i].charAt(0),str[i].charAt(3),parent);
                if(!flag)
                {
                    break;
                }
            }
            
        }
        System.out.println(flag);
        
    }
    public static boolean isvalid(char x,char y,int[] parent)
    {
        int a=find(x-'a',parent);
        int b=find(y-'a',parent);
        if(a==b)
        {
            return false;
        }
        return true;
        
    }
    public static void union(char x,char y,int[] parent)
    {
        int a=find(x-'a',parent);
        int b=find(y-'a',parent);
        if(a==b)
        {
            return;
        }
        if(a<b)
        {
            parent[b]=a;
        }
        else
        {
            parent[a]=b;
        }
        return;
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
