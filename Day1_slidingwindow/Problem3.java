/*
 You are an architect tasked with designing a series of connected rooms in a 
building. You are given a list of room sizes represented by an integer array 
roomSizes, an integer maxFrequency representing the maximum number of times 
a particular room size can appear, and an integer maxArea representing 
the maximum allowable total area of connected rooms. A set of connected 
rooms is called viable if the frequency of each room size in this set is 
less than or equal to maxFrequency, and the total area of the rooms in 
this set is less than or equal to maxArea. 

Return the length of the longest viable set of connected rooms from roomSizes.

Input Format:
-------------
Line-1: 3 space separated integers, n, maxFrequency, maxArea
Line-2: N space separated integers, roomSizes[].

Output Format:
-------------
An integer, the length of the longest viable set of connected rooms


Sample Input-1:
---------------
8 2 10
1 2 3 1 2 3 1 2

Sample Output-1:
----------------
5 

Explanation: 
------------
The longest possible viable set of connected rooms is [1, 2, 3, 1, 2] since 
the room sizes 1, 2, and 3 appear at most twice, and the total area is less than 10.

Sample Input-2:
---------------
6 1 3
1 2 1 2 1 2

Sample Output-2:
----------------
2

Explanation: The longest possible viable set of connected rooms is [1, 2] since 
the room sizes 1 and 2 appear at most once, and the total area is 3.

Constraints:
------------
1 <= roomSizes.length <= 10^5
1 <= roomSizes[i] <= 10^4 where roomSizes[i] is the size of the i-th room.
1 <= maxFrequency <= roomSizes.length
1 <= maxArea <= 10^9

 */
package Day1_slidingwindow;
import java.util.*;
public class Problem3 {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int f=sc.nextInt();
        int m=sc.nextInt();
        int sum=0;
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        HashMap<Integer,Integer> h=new HashMap<>();
        int r=0;
        int l=0;
        int max=0;
        while(r<n)
        {
            h.put(arr[r],h.getOrDefault(arr[r],0)+1);
            sum+=arr[r];
            while(sum>m || h.get(arr[r])>f && l<=r)
            {
                h.put(arr[l],h.get(arr[l])-1);
                if (h.get(arr[l]) == 0){
                    h.remove(arr[l]); 
                }
                sum-=arr[l++];
                
                
            }
            max=Math.max(r-l+1,max);
            r++;
        }
        System.out.println(max);
        
        
    }

    
}
