/*
 * Mr. Rakesh is interested in working with Data Structures.

He has constructed a Binary Tree (BT) and asked his friend 
Anil to check whether the BT is a self-mirror tree or not.

Can you help Rakesh determine whether the given BT is a self-mirror tree?
Return true if it is a self-mirror tree; otherwise, return false.

Note:
------
In the tree, '-1' indicates an empty (null) node.

Input Format:
-------------
A single line of space separated integers, values at the treenode

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
2 1 1 2 3 3 2

Sample Output-1:
----------------
true


Sample Input-2:
---------------
2 1 1 -1 3 -1 3

Sample Output-2:
----------------
false

 */
package Day6_trees;

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
        if(arr[0]==-1)
        {
            System.out.println(false);
        }
        else
        {
            Tree1 root=buildtree(arr);
            System.out.println(issymmetric(root));
        }
    }
    public static boolean issymmetric(Tree1 root)
    {
    
        if(root==null)
        {
            return true;
        }
        return find(root.left,root.right);
    }
    public static boolean find(Tree1 left,Tree1 right)
    {
        if(left==null && right==null)
        {
            return true;
        }
        if(left==null || right==null)
        {
            return false;
        }
        if(left.data!=right.data)
        {
            return false;
        }
        return find(left.left,right.right) && find(left.right,right.left);
    }
    public static Tree1 buildtree(int[] arr)
    {
        Queue<Tree1> q=new LinkedList<>();
        Tree1 root=new Tree1(arr[0]);
        q.offer(root);
        int i=1;
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