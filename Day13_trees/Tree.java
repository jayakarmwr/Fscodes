/*
 * Imagine you are navigating a maze where each path you take has a section with a 
code. The maze is structured as a series of interconnected rooms, 
represented as a tree structure. Each room in the maze has a code (integral value)
associated with it, and you are trying to check if a given sequence of code 
matches a valid path from the entrance to an exit. 

You are provide with the maze structure, where you have to build the maze and then,
you are provided with a series of space seperated digits, representing a journey 
starting from the entrance and passing through the rooms along the way. 
The task is to verify whether the path corresponding to this array of codes 
exists in the maze.

Example 1:
Input:
0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
0 1 0 1                         //path to verify

Output: true

Explanation:
               0
             /   \
            1     0
           / \    /
          0   1  0
           \  / \
            1 0  0

The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


Example 2:
Input:
1 2 3
1 2 3

output: false

Explanation:
The proposed path 1 → 2 → 3 does not exist in the maze, 
so it cannot be a valid path.

 */
package Day13_trees;
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
        String p=sc.nextLine();
        String[] p1=p.split(" ");
        int[] path=new int[p1.length];
        for(int i=0;i<p1.length;i++)
        {
            path[i]=Integer.parseInt(p1[i]);
        }
        Tree root=buildtree(arr);
        System.out.println(validpath(root,0,path));
    }
    public static boolean validpath(Tree root,int index,int[] path)
    {
        if(root==null)
        {
            return false;
        }
        if(root.data==path[index])
        {
            if(index+1==path.length)
            {
                return true;
            }
            return validpath(root.left,index+1,path) || validpath(root.right,index+1,path);
        }
        return false;
        
        
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
}