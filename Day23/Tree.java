/*
 * Imagine you are designing a grand castle where each room holds a specific amount 
of treasure. The castle is built in a binary layout, meaning every room may lead 
to two adjacent wings—a left wing and a right wing. 

An "organized section" of the castle follows this rule: for any given room, 
every room in its left wing contains a treasure value that is strictly less 
than the current room’s value, and every room in its right wing contains a 
value that is strictly greater. Additionally, each wing must itself be organized
according to the same rule.

Your challenge is to determine the maximum total treasure (i.e., the sum of 
treasure values) that can be gathered from any such organized section of the castle.

Example 1:
input=
1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
output=
20

Castle:
          1
        /   \
       4     3
      / \   / \
     2   4 2   5
              / \
             4   6

Explanation: The best organized section starts at the room with a treasure value 
of 3, yielding a total treasure of 20.

Example 2:
input=
4 3 -1 1 2
output=
2

Castle:
    4
   /
  3
 / \
1   2

Explanation: The optimal organized section is just the single room with a 
treasure value of 2.

Example 3:
input=
-4 -2 -5
output=
0

Castle:
   -4
  /  \
-2   -5
 
Explanation: Since all rooms contain negative treasure values, no beneficial 
organized section exists, so the maximum total treasure is 0.

Constraints:

- The number of rooms in the castle ranges from 1 to 40,000.
- Each room’s treasure value is an integer between -40,000 and 40,000.

 */
package Day23;

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
        int[] res=new int[]{0};
        for(int i=0;i<st.length;i++)
        {
            arr[i]=Integer.parseInt(st[i]);
        }
        Tree root=build(arr);
        int count=organised(root,res);
        System.out.println(res[0]);
        
    }
    public static int organised(Tree root,int[] res)
    {
        if(root==null)
        {
            return 0;
        }
        int l=organised(root.left,res);
        int r=organised(root.right,res);
        if(l>-1 && r>-1)
        {
            if(l!=0 && root.left.data>=root.data)
            {
                return -1;
            }
            if(r!=0 && root.right.data<=root.data)
            {
                return -1;
            }
        }
        else
        {
            return -1;
        }
        int sum=l+r+root.data;
        res[0]=Math.max(res[0],sum);
        return sum;
    }
    public static Tree build(int[] arr)
    {
        int i=1;
        Tree root=new Tree(arr[0]);
        Queue<Tree> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            Tree node=q.poll();
            if(i<arr.length && arr[i]!=-1)
            {
                node.left=new Tree(arr[i]);
                q.add(node.left);
            }
            i++;
            if(i<arr.length && arr[i]!=-1)
            {
                node.right=new Tree(arr[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
}