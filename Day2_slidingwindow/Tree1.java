/*
 Write a program to construct a binary tree from level-order input, while treating -1 
as a placeholder for missing nodes. The program reads input, constructs the tree, 
and provides an in-order traversal to verify correctness.

Input Format:
---------------
Space separated integers, level order data (where -1 indiactes null node).

Output Format:
-----------------
Print the in-order data of the tree.


Sample Input:
----------------
1 2 3 -1 -1 4 5

Sample Output:
----------------
2 1 4 3 5

Explanation:
--------------
    1
   / \
  2   3
     / \
    4   5


Sample Input:
----------------
1 2 3 4 5 6 7

Sample Output:
----------------
4 2 5 1 6 3 7

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4  5 6  7

 */
package Day2_slidingwindow;

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
        String str[]=s.split(" ");
        int[] arr=new int[str.length];
        for(int i=0;i<str.length;i++)
        {
          arr[i]=Integer.parseInt(str[i]);
        }
        Tree root=build(arr);
        inorder(root);
        
    }
    public static Tree build(int[] arr)
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
                i++;
            }
            else{
                i++;
            }       
            
            if(i<arr.length && arr[i]!=-1)
            {
                node.right=new Tree(arr[i]);
                q.offer(node.right);
                i++;
            }
            else{
                i++;
            }
             
    
        }
        return root;
    }
    
    public static void inorder(Tree root)
    {
        if(root==null)
        {
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

}
