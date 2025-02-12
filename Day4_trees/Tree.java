/*
 * Given the in-order and post-order traversals of a binary tree, construct 
the original binary tree. For the given Q number of queries, 
each query consists of a lower level and an upper level. 
The output should list the nodes in the order they appear in a level-wise.

Input Format:
-------------
An integer N representing the number nodes.
A space-separated list of N integers representing the similar to in-order traversal.
A space-separated list of N integers representing the similar to post-order traversal.
An integer Q representing the number of queries.
Q pairs of integers, each representing a query in the form:
Lower level (L)
Upper level (U)

Output Format:
For each query, print the nodes in order within the given depth range

Example
Input:
7
4 2 5 1 6 3 7
4 5 2 6 7 3 1
2
1 2
2 3
Output:
[1, 2, 3]
[2, 3, 4, 5, 6, 7]

Explanation:
        1
       / \
      2   3
     / \  / \
    4   5 6  7
Query 1 (Levels 1 to 2): 1 2 3
Query 2 (Levels 2 to 3): 2 3 4 5 6 7

 */
package Day4_trees;
import java.util.*;
public class Tree {
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
        int n=sc.nextInt();
        int[] inorder=new int[n];
        int[] postorder=new int[n];
        Map<Integer,Integer> map=new Hashtable<>();
        for(int i=0;i<n;i++)
        {
            inorder[i]=sc.nextInt();
            map.put(inorder[i],i);

        }
        for(int i=0;i<n;i++)
        {
            postorder[i]=sc.nextInt();
        }
        int m=sc.nextInt();
        List<int[]> arr=new ArrayList<>();
        for(int i=0;i<m;i++)
        {
            int l=sc.nextInt();
            int r=sc.nextInt();
            arr.add(new int[]{l,r});
        }
        Tree root=buildtree(postorder,inorder,0,n-1,new int[]{n-1},map);
        
        Map<Integer,List<Integer>> levelmap=new Hashtable<>();
        levelorder(levelmap,root);
        for(int[] a:arr)
        {
            int start=a[0];
            int end=a[1];
            List<Integer> res=new ArrayList<>();
            for(int i=start;i<=end;i++)
            {
                if(levelmap.containsKey(i))
                {
                    res.addAll(levelmap.get(i));
                }
            }
            System.out.println(res);
        }
        
        
    }
    public static Tree buildtree(int[] postorder,int[] inorder,int l,int r,int[] postindex,Map<Integer,Integer> map)
    {
        if(l>r)
        {
            return null;
        }
        Tree root=new Tree(postorder[postindex[0]--]);
        int index=map.get(root.data);
        root.right=buildtree(postorder,inorder,index+1,r,postindex,map);
        root.left=buildtree(postorder,inorder,l,index-1,postindex,map);
        return root;

    }
    public static void levelorder(Map<Integer,List<Integer>> map,Tree root)
    {
        if(root==null)
        {
            return;
        }
        Queue<Tree> q=new LinkedList<>();
        q.offer(root);
        int level=1;
        while(!q.isEmpty())
        {
            
            List<Integer> l=new ArrayList<>();
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                Tree node=q.poll();
                l.add(node.data);
                if(node.left!=null){
                    q.offer(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                }
            }
            map.put(level++,l);
            

            
        }
    } 
    
}
