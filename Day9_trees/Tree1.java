/*
 * Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6

Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6

Sample Output-2:
----------------
100

 */
package Day9_trees;

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
    public static HashSet<Long> set=new HashSet<>();
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
        
        Tree1 root=buildtree(arr);
        int total=sum(root);
        long res=Integer.MIN_VALUE;
        //System.out.println(set);
        //System.out.println(total);
        for(long i:set)
        {
            res=Math.max(res,Math.abs((i-total)*i));
        }
        System.out.println(res);
        
    }
    public static int sum(Tree1 root)
    {
        if(root==null)
        {
            return 0;
        }
        int s=root.data+sum(root.left)+sum(root.right);
        set.add((long)s);
        return s;
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
