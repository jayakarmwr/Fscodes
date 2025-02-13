/*
 * Given the preorder and postorder traversals of a binary tree, construct 
the original binary tree and print its level order traversal.

Input Format:
---------------
Space separated integers, pre order data
Space separated integers, post order data

Output Format:
-----------------
Print the level-order data of the tree.


Sample Input:
----------------
1 2 4 5 3 6 7
4 5 2 6 7 3 1

Sample Output:
----------------
[[1], [2, 3], [4, 5, 6, 7]]

Explanation:*
--------------
        1
       / \
      2   3
     / \  / \
    4   5 6  7


Sample Input:
----------------
1 2 3
2 3 1

Sample Output:
----------------
[[1], [2, 3]]

Explanation:
--------------
    1
   / \
  2  3

 */
package Day5_trees;

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
        String s1=sc.nextLine();
        String s2=sc.nextLine();
        String[] pre=s1.split(" ");
        String[] post=s2.split(" ");
        int[] postorder=new int[post.length];
        int[] preorder=new int[pre.length];
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<pre.length;i++)
        {
            preorder[i]=Integer.parseInt(pre[i]);
            postorder[i]=Integer.parseInt(post[i]);
            map.put(postorder[i],i);
        }
        Tree root=buildtree(preorder,postorder,0,pre.length-1,new int[]{0},map);
        List<List<Integer>> l=levelorder(root);
        System.out.println(l);
        
        
        
        
    }
    public static List<List<Integer>> levelorder(Tree root)
    {
        List<List<Integer>> l=new ArrayList<>();
        Queue<Tree> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            List<Integer> r=new ArrayList<>();
            int size=q.size();
            
            for(int i=0;i<size;i++)
            {
                Tree node=q.poll();
                r.add(node.data);
                if(node.left!=null)
                {
                    q.offer(node.left);
                }
                if(node.right!=null)
                {
                    q.offer(node.right);
                }
            }
            l.add(r);
        }
        return l;
    }
    public static Tree buildtree(int[] preorder,int[] postorder,int l,int r,int[] preindx,Map<Integer,Integer> map)
    {
        if(l>r)
        {
            return null;
        }
        Tree root=new Tree(preorder[preindx[0]++]);
        if(l==r )
        {
            return root;
        }
        int index=map.get(preorder[preindx[0]]);
        root.left=buildtree(preorder,postorder,l,index,preindx,map);
        root.right=buildtree(preorder,postorder,index+1,r-1,preindx,map);
        
        return root;
    }
}