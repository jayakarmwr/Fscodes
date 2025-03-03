/*
 * Cliff Shaw is working on the singly linked list.
He is given a list of boxes arranged as singly linked list,
where each box is printed a positive number on it.

Your task is to help Mr Cliff to find the given list is equivalent to 
the reverse of it or not. If yes, print "true", otherwise print "false"

Input Format:
-------------
Line-1: space separated integers, boxes as list.

Output Format:
--------------
Print a boolean a value.


Sample Input-1:
---------------
3 6 2 6 3

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 6 2 3 6

Sample Output-2:
----------------
false
 */
package Day14_twopointer;
import java.util.*;
class LL
{
    int data;
    LL left;
    LL(int data)
    {
        this.data=data;
        this.left=null;
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String st[]=s.split(" ");
        int[] arr=new int[st.length];
        for(int i=0;i<st.length;i++)
        {
            arr[i]=Integer.parseInt(st[i]);
        }
        LL head=build(arr);
        LL middle=findmiddle(head);
        System.out.println(isvalid(head,middle));
    }
    public static LL findmiddle(LL head)
    {
        LL slow=head;
        LL fast=head;
        while(fast!=null && fast.left!=null)
        {
            slow=slow.left;
            fast=fast.left.left;
        }
        return slow;
    }
    public static boolean isvalid(LL head,LL middle)
    {
        LL temp=middle.left;
        middle.left=reverse(temp);
        
        LL head1=head;
        LL head2=middle.left;
    
        while(head2!=null)
        {
            if(head1.data!=head2.data)
            {
                return false;
            }
            head1=head1.left;
            head2=head2.left;
        }
        return true;
        
    }
    public static LL reverse(LL head)
    {
        LL curr=head;
        LL prev=null;
        LL next=null;
        while(curr!=null)
        {
            next=curr.left;
            curr.left=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
    
    public static LL build(int[] arr)
    {
        int i=1;
        LL head=new LL(arr[0]);
        LL temp=head;
        while(i<arr.length)
        {
            temp.left=new LL(arr[i]);
            i++;
            temp=temp.left;
        }
        return head;
        
    }
}