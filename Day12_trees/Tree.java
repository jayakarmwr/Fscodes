/*
 * Imagine a company where each employee has a performance score, and 
the organizational chart is structured as a binary tree with the CEO at the top. 
An employee is considered "outstanding" if, along the chain of command from the 
CEO down to that employee, no one has a performance score higher than that 
employee's score. Your task is to determine the total number of outstanding 
employees in the company.

Example 1:
Input: 3 1 4 3 -1 1 5
Output: 4

Chart formed:
         3
        / \
       1   4
      /   / \
     3   1   5

Explanation:
- The CEO (score 3) is automatically outstanding.
- The employee with score 4, whose chain is [3,4], is outstanding because 4 
  is higher than 3.
- The employee with score 5, following the path [3,4,5], is outstanding as 5 
  is the highest so far.
- The subordinate with score 3, along the path [3,1,3], is outstanding because 
  none of the managers in that chain have a score exceeding 3.

Example 2:
Input: 3 3 -1 4 2
Output: 3

Chart formed:
       3
      / 
     3
    / \
   4   2

Explanation:
- The CEO (score 3) is outstanding.
- The subordinate with score 3 (chain: [3,3]) is outstanding.
- The employee with score 2 (chain: [3,3,2]) is not outstanding because the 
  managers have higher scores.

 */
package Day12_trees;
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
    public static int x=0;
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
        int max=Integer.MIN_VALUE;
        Tree root=buildtree(arr);
        get(root,max);
        System.out.println(x);
    }
    public static Tree buildtree(int[] arr)
    {
        Queue<Tree> q=new LinkedList<>();
        int i=1;
        Tree root=new Tree(arr[0]);
        q.offer(root);
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
    public static void get(Tree root,int max)
    {
        if(root==null)
        {
            return ;
        }
        if(root.data>=max)
        {
            x++;
            max=root.data;
        }
        
            get(root.left,max);
            get(root.right,max);
        
        
    }
}