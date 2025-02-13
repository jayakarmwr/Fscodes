/*
 * Balbir Singh is working with Binary Trees.
The elements of the tree are given in level-order format.

Balbir is observing the tree from the right side, meaning he 
can only see the rightmost nodes (one node per level).

You are given the root of a binary tree. Your task is to determine 
the nodes visible from the right side and return them in top-to-bottom order.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the node values visible from the right side


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

Sample Output-1:
----------------
[1, 3, 5]



Sample Input-2:
---------------
3 2 4 3 2

Sample Output-2:
----------------
[3, 4, 2]

 */
package Day5_trees;

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
        String s1=sc.nextLine();
        String[] str=s1.split(" ");
        int[] arr=new int[str.length];
        for(int i=0;i<str.length;i++)
        {
            arr[i]=Integer.parseInt(str[i]);
        }
        Tree1 root=buildtree(arr);
        List<Integer> l=new ArrayList<>();
        rightview(l,root,0);
        System.out.println(l);
    }
    public static Tree1 buildtree(int[] arr)
    {
        if(arr.length==0){
            return null;
        }
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
    public static void rightview(List<Integer> l,Tree1 root,int level)
    {
        if(root==null)
        {
            return;
        }
       if(level==l.size())
       {
            l.add(root.data);
       }
        rightview(l,root.right,level+1);
        rightview(l,root.left,level+1);
    }
}