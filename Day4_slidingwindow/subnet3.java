/*
 * 
Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
whether IP-1 and IP-2 belongs to same host range or not.

Input Format:
---------------
Two space separated strings, IP1 and IP2.
An integer, CIDR (subnet mask).

Output Format:
---------------
A boolean result.


Sample Input-1:
-----------------
192.168.1.10 192.168.1.20
24

Sample Output-1:
------------------
true


Sample Input-2:
-----------------
192.0.2.1 192.0.3.253
24

Sample Output-2:
------------------
false


 */
package Day4_slidingwindow;

import java.util.Scanner;

public class subnet3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String ip1 = input[0];
        String ip2 = input[1];
        int cidr = sc.nextInt();
        sc.close();

        int subnetMask = (-1 << (32 - cidr)); 
        int ip1Int = ipToInt(ip1);
        int ip2Int = ipToInt(ip2);

        boolean sameNetwork = (ip1Int & subnetMask) == (ip2Int & subnetMask);

        System.out.println(sameNetwork);
    }

    private static int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int result = 0;
        for (String part : parts) {
            result = (result << 8) + Integer.parseInt(part);
        }
        return result;
    }
}

