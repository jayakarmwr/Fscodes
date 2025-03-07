/*
 * AlphaCipher is a string formed from another string by rearranging its letters

You are given a string S.
Your task is to check, can any one of the AlphaCipher is a palindrome or not.

Input Format:
-------------
A string S

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
carrace

Sample Output-1:
----------------
true


Sample Input-2:
---------------
code

Sample Output-2:
----------------
false

 */
package Day20;

import java.util.*;
class Solution
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println(ispalindrome(s));
    }
    public static boolean ispalindrome(String s)
    {
        int bitmask=0;
        for(int i=0;i<s.length();i++)
        {
            int c=s.charAt(i)-'a';
            bitmask^=1<<c;
            
        }
        return (bitmask==0 || (bitmask &(bitmask-1))==0);
    }
}
