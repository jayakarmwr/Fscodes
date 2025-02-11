 /*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
-----------------
25

Sample Output-1:
------------------
255.255.255.128


Sample Input-2:
-----------------
22

Sample Output-2:
------------------
255.255.252.0
*/


package Day4_slidingwindow;

import java.util.Scanner;

public class subnet1 {
    public static void main(String[] arg)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(subnetmask(n));
    }
    public static String subnetmask(int n)
    {
        int mask=0xFFFFFFFF << (32-n);
        return String.format("%d.%d.%d.%d",
        (mask>>24) & 0xFF,
        (mask>>16) & 0xFF,
        (mask>>8) & 0xFF,
        (mask & 0xFF));
        
    }
    
}
