/*
 * Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8

Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edegs between 6 and 6.

 */
package Day6_trees;

import java.util.*;
class Tree
{
    int data;
    Tree left;
    Tree right;
    Tree(int data)
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
        int start=sc.nextInt();
        int end=sc.nextInt();
        Tree root=buildtree(arr);
        Tree lca=findlca(root,start,end);
        int left=finddepth(lca,start,0);
        int right=finddepth(lca,end,0);
        System.out.println(left+right);
        
    }
    public static int finddepth(Tree root ,int a,int level)
    {
        if(root==null)
        {
            return -1;
        }
        if(root.data==a)
        {
            return level;
        }
        int l=finddepth(root.left,a,level+1);
        int r=finddepth(root.right,a,level+1);
        return l!=-1?l:r;
        
    }
    public static Tree findlca(Tree root,int start,int end)
    {
        if(root==null || root.data==start || root.data==end)
        {
            return root;
        }
        Tree left=findlca(root.left,start,end);
        Tree right=findlca(root.right,start,end);
        if(left!=null && right!=null)
        {
            return root;
        }
        return left!=null?left:right;
    }
    public static Tree buildtree(int[] arr)
    {
        Queue<Tree> q=new LinkedList<>();
        Tree root=new Tree(arr[0]);
        q.offer(root);
        int i=1;
        while(!q.isEmpty())
        {
            Tree node=q.poll();
            if(i<arr.length && arr[i]!=-1)
            {
                node.left=new Tree(arr[i]);
                q.offer(node.left);
                
            }
            i++;
            if(i<arr.length && arr[i]!=-1)
            {
                node.right=new Tree(arr[i]);
                q.offer(node.right);
                
            }
            i++;
        }
        return root;
        
    }
}
