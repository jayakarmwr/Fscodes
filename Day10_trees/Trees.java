/*
 * You are a gardener designing a beautiful floral pathway in a vast botanical 
garden. The garden is currently overgrown with plants, trees, and bushes 
arranged in a complex branching structure, much like a binary tree. Your task 
is to carefully prune and rearrange the plants to form a single-file walking 
path that visitors can follow effortlessly.

To accomplish this, you must flatten the garden’s layout into a linear sequence 
while following these rules:
    1. The garden path should maintain the same PlantNode structure, 
       where the right branch connects to the next plant in the sequence, 
       and the left branch is always trimmed (set to null).
    2. The plants in the final garden path should follow the same arrangement 
       as a pre-order traversal of the original garden layout. 

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print the list.


Sample Input:
-------------
1 2 5 3 4 -1 6

Sample Output:
--------------
1 2 3 4 5 6


Explanation:
------------
input structure:
       1
      / \
     2   5
    / \    \
   3   4    6
   
output structure:
	1
	 \
	  2
	   \
		3
		 \
		  4
		   \
			5
			 \
			  6

 */
package Day10_trees;
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
    public static Tree prev=null;
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
        Tree root=buildtree(arr);
        flatterntree(root);
        preorder(root);
        
    }
    public static void preorder(Tree root)
    {
        if(root==null)
        {
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void flatterntree(Tree root)
    {
        if(root==null)
        {
            return;
        }
        flatterntree(root.right);
        flatterntree(root.left);
        root.right=prev;
        root.left=null;
        
        prev=root;
        
    }
    public static Tree buildtree(int[] arr)
    {
        Queue<Tree> q=new LinkedList<>();
        Tree root=new Tree(arr[0]);
        q.add(root);
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