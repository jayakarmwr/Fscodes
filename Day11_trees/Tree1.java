/*
 * Imagine you are designing a network of secret corridors in an ancient castle. 
Each chamber in the castle leads to at most two other chambers through 
hidden passageways, forming a branching layout. 
The castle’s "longest secret route" is defined as the maximum number of corridors 
you must traverse to get from one chamber to another (without repeating the corridor). 
This route may or may not pass through the main entry chamber.

Your task is to compute the length of longest secret route between 
two chambers which is represented by the number of corridors between them.

Example 1
input=
1 2 3 4 5 
output=
3

Structure:
       1
      / \
     2   3
    / \
   4   5

Explanation:
The longest secret route from chamber 4 to chamber 3 
(alternatively, from chamber 5 to chamber 3) along the route:
4 → 2 → 1 → 3
This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.

Example 2:
input=
1 -1 2 3 4
output=
2

Structure:
   1
    \
     2
    / \
   3   4

Explanation:
The longest secret route from chamber 3 to chamber 4 
(alternatively, from chamber 1 to chamber 4) along the route:
3 → 2 → 4
This path has 2 corridors (3–2, 2–4), so the length is 2.



 */
package Day11_trees;

import java.util.*;
class Tree1
{
    int data;
    Tree1 left;
    Tree1 right;
    Tree1(int data)
    {
        this.data=data;
        this.left=null;
        this.right=null;
        
    }
    public static int res=0;
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] st=s.split(" ");
        int arr[]=new int[st.length];
        for(int i=0;i<st.length;i++)
        {
            arr[i]=Integer.parseInt(st[i]);
        }
        Tree1 root=buildtree(arr);
        
        helper(root);
        System.out.println(res);
        
        
        
    }
    public static int helper(Tree1 root)
    {
        if(root==null)
        {
            return 0;
        }
        int l=helper(root.left);
        int r=helper(root.right);
        res=Math.max(l+r,res);
        return Math.max(l,r)+1;
    }
    public static Tree1 buildtree(int[] arr)
    {
        Queue<Tree1> q=new LinkedList<>();
        int i=1;
        Tree1 root=new Tree1(arr[0]);
        q.offer(root);
        while(!q.isEmpty())
        {
            Tree1 node=q.poll();
            if(i<arr.length && arr[i]!=-1)
            {
                node.left=new Tree1(arr[i]);
                q.offer(node.left);
                
            }
            i++;
            if(i<arr.length && arr[i]!=-1)
            {
                node.right=new Tree1(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }
}