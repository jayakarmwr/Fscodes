/*
 * The Indian Army has established multiple military camps at strategic locations 
along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
a hierarchical structure, with a main base camp acting as the root of the network.

To fortify national security, the Government of India has planned to deploy 
a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
boundary of the network.

Structure of Military Camps:
    - Each military camp is uniquely identified by an integer ID.
    - A camp can have at most two direct connections:
        - Left connection → Represents a subordinate camp on the left.
        - Right connection → Represents a subordinate camp on the right.
    - If a military camp does not exist at a specific position, it is 
      represented by -1
	
Mission: Deploying the S.H.I.E.L.D.
Your task is to determine the list of military camp IDs forming the outer 
boundary of the military network. This boundary must be traversed in 
anti-clockwise order, starting from the main base camp (root).

The boundary consists of:
1. The main base camp (root).
2. The left boundary:
    - Starts from the root’s left child and follows the leftmost path downwards.
    - If a camp has a left connection, follow it.
    - If no left connection exists but a right connection does, follow the right connection.
    - The leftmost leaf camp is NOT included in this boundary.
3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
4. The right boundary (in reverse order):
    - Starts from the root’s right child and follows the rightmost path downwards.
    - If a camp has a right connection, follow it.
    - If no right connection exists but a left connection does, follow the left connection.
    - The rightmost leaf camp is NOT included in this boundary.


Input Format:
-------------
space separated integers, military-camp IDs.

Output Format:
--------------
Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


Sample Input-1:
---------------
5 2 4 7 9 8 1

Sample Output-1:
----------------
[5, 2, 7, 9, 8, 1, 4]


Sample Input-2:
---------------
11 2 13 4 25 6 -1 -1 -1 7 18 9 10

Sample Output-2:
----------------
[11, 2, 4, 7, 18, 9, 10, 6, 13]


Sample Input-3:
---------------
1 2 3 -1 -1 -1 5 -1 6 7

Sample Output-3:
----------------
[1, 2, 7, 6, 5, 3]

 */
package Day5_trees;

import java.util.*;
class Tree3
{
    int data;
    Tree3 left;
    Tree3 right;
    Tree3(int data)
    {
        this.data=data;
        this.left=null;
        this.right=null;
        
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] st=s.split(" ");
        int[] arr=new int[st.length];
        for(int i=0;i<st.length;i++)
        {
            arr[i]=Integer.parseInt(st[i]);
        }
        Tree3 root=buildtree(arr);
        List<Integer> l=new ArrayList<>();
        boundary(l,root);
        System.out.println(l);
        
    }
    public static Tree3 buildtree(int[] arr)
    {
        if(arr.length==0)
        {
            return null;
        }
        Queue<Tree3> q=new LinkedList<>();
        Tree3 root=new Tree3(arr[0]);
        q.add(root);
        int i=1;
        while(!q.isEmpty())
        {
            Tree3 node=q.poll();
            if(i<arr.length && arr[i]!=-1)
            {
                node.left=new Tree3(arr[i]);
                q.offer(node.left);
                
            }
            i++;
            if(i<arr.length && arr[i]!=-1)
            {
                node.right=new Tree3(arr[i]);
                q.offer(node.right);
                
            }
            i++;
        }
        return root;
    }
    public static void boundary(List<Integer> l,Tree3 root)
    {
        if(root==null)
        {
            return;
        }
        l.add(root.data);
        List<Integer> r=new ArrayList<>();
        leftview(root.left,l);
        leaf(root,l);
        rightview(root.right,r);
        Collections.reverse(r);
        l.addAll(r);
    }
    public static void leaf(Tree3 root,List<Integer> l)
    {
        if(root==null )
        {
            return;
        }
        if(root.left==null && root.right==null)
        {
            l.add(root.data);
            return;
        }
        leaf(root.left,l);
        leaf(root.right,l);
    }
    public static void leftview(Tree3 root,List<Integer> l)
    {
        if(root==null || (root.left == null && root.right == null))
        {
            return;
        }
        l.add(root.data);
        if(root.left!=null)
        {
            leftview(root.left,l);
        }
        else
        {
            leftview(root.right,l);
        }
        
    }
    public static void rightview(Tree3 root,List<Integer> l)
    {
        if(root==null || (root.left == null && root.right == null))
        {
            return;
        }
        
        if(root.right!=null)
        {
            rightview(root.right,l);
        }
        else
        {
            rightview(root.left,l);
        }
        l.add(root.data);
        
    }
}