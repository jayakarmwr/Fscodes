/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, network IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

*/

package Day4_slidingwindow;
import java.util.*;
public class subnet2 {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int n=sc.nextInt();
        int subnetmask=(-1<<(32-n));
        int ip=iptoint(s);
        int network=ip & subnetmask;
        int host=network | ~subnetmask;
        System.out.println(inttoip(network)+" "+inttoip(host));

    }
    public static int iptoint(String s)
    {
        String[] parts=s.split("\\.");
        int ip=0;
        for(String part:parts)
        {
            ip=(ip<<8)|Integer.parseInt(part);
        }
        return ip;
    }
    public static String inttoip(int ip)
    {
        return ((ip >> 24) & 0xFF) + "." +
               ((ip >> 16) & 0xFF) + "." +
               ((ip >> 8) & 0xFF) + "." +
               (ip & 0xFF);
    
    }
}
