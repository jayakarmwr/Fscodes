/*
 * Construct the binary tree from the given In-order and Pre-order. 
Find Nodes Between Two Levels in Spiral Order.
The spiral order is as follows:
-> Odd levels → Left to Right.
-> Even levels → Right to Left.

Input Format:
--------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the pre-order traversal.
Two integers:
Lower Level (L)
Upper Level (U)

Output Format:
Print all nodes within the specified levels, but in spiral order.

Example:
Input:
7
4 2 5 1 6 3 7
1 2 4 5 3 6 7
2 3

Output:
3 2 4 5 6 7

Explanation:
Binary tree structure:
        1
       / \
      2   3
     / \  / \
    4   5 6  7

Levels 2 to 3 in Regular Order:
Level 2 → 2 3
Level 3 → 4 5 6 7

Spiral Order:
Level 2 (Even) → 3 2 (Right to Left)
Level 3 (Odd) → 4 5 6 7 (Left to Right)

 */
package Day4_trees;
import java.util.*;

public class Tree1 {
    int data;
    Tree1 left;
    Tree1 right;
    Tree1(int data)
    {
        this.data=data;
        this.left=null;
        this.right=null;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] inorder=new int[n];
        int[] preorder=new int[n];
        Map<Integer,Integer> map=new Hashtable<>();
        for(int i=0;i<n;i++)
        {
            inorder[i]=sc.nextInt();
            map.put(inorder[i],i);
        }
        for(int i=0;i<n;i++)
        {
            preorder[i]=sc.nextInt();
        }
        int l=sc.nextInt();
        int r=sc.nextInt();
        Tree1 root=buildtree(inorder,preorder,0,n-1,new int[]{0},map);
        Map<Integer,List<Integer>> levelmap=new Hashtable<>();
        levelorder(levelmap,root);
        List<Integer> res=new ArrayList<>();
        for(int i=l;i<=r;i++)
        {
            if(levelmap.containsKey(i))
            {
                if(i%2==0)
                {

                    List<Integer> reverse = new ArrayList<>(levelmap.get(i)); 
                    Collections.reverse(reverse);
                    res.addAll(reverse);

                }
                else{
                    res.addAll(levelmap.get(i));
                }
            }
        }
        System.out.println(res);

    }
    public static Tree1 buildtree(int[] inorder,int[] preorder,int l,int r,int[] preorderindex,Map<Integer,Integer> map)
    {
        if(l>r)
        {
            return null;
        }
        Tree1 root=new Tree1(preorder[preorderindex[0]++]);
        int index=map.get(root.data);
        root.left=buildtree(inorder, preorder, l, index-1, preorderindex, map);
        root.right=buildtree(inorder, preorder, index+1, r, preorderindex, map);
        return root;
    }
    public static void levelorder(Map<Integer,List<Integer>> map,Tree1 root)
    {
        if(root==null)
        {
            return ;
        }
        Queue<Tree1> q=new LinkedList<>();
        int level=1;
        q.offer(root);
        while(!q.isEmpty())
        {
            int size=q.size();
            List<Integer> l=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                Tree1 node=q.poll();
                l.add(node.data);
                if(node.left!=null)
                {
                    q.offer(node.left);
                }
                if(node.right!=null)
                {
                    q.offer(node.right);
                }
            }
            map.put(level++,l);
        }
    }
    
}
