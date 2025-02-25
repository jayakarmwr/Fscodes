/*
 * Imagine you’re decoding a secret message that outlines the hierarchical structure 
of a covert spy network. The message is a string composed of numbers and parentheses. 
Here’s how the code works:

- The string always starts with an agent’s identification number, this is the 
  leader of the network.
- After the leader’s ID, there can be zero, one, or two segments enclosed in 
  parentheses. Each segment represents the complete structure of one subordinate 
  network.
- If two subordinate networks are present, the one enclosed in the first (leftmost) 
  pair of parentheses represents the left branch, and the second (rightmost) 
  represents the right branch.

Your mission is to reconstruct the entire spy network hierarchy based on this 
coded message.

Example 1:
Input: 4(2(3)(1))(6(5))
Output: [4, 2, 6, 3, 1, 5]

Spy network:
        4
       / \
      2   6
     / \  /
    3   1 5

Explanation:
Agent 4 is the leader.
Agent 2 (with its own subordinates 3 and 1) is the left branch.
Agent 6 (with subordinate 5) is the right branch.

Example 2:
Input: 4(2(3)(1))(6(5)(7))
Output: [4, 2, 6, 3, 1, 5, 7]

Spy network:
         4
       /   \
      2     6
     / \   / \
    3   1 5   7

Explanation:
Agent 4 leads the network.
Agent 2 with subordinates 3 and 1 forms the left branch.
Agent 6 with subordinates 5 and 7 forms the right branch.

 */
package Day12_trees;

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
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        Tree1 root=buildtree(s);
        List<Integer> l=new ArrayList<>();
        levelorder(root,l);
        System.out.println(l);
        
    }
    public static void levelorder(Tree1 root,List<Integer> l)
    {
        Queue<Tree1> q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty())
        {
            int size=q.size();
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
        }
    }
    public static Tree1 buildtree(String s)
    {
        if(s.length()==0)
        {
          return null;  
        }  
      int index=s.length();
      for(int i=0;i<s.length();i++)
      {
        if(s.charAt(i)=='(')
        {
          index=i;
          break;
          
        }
      }  
      Tree1 root=new Tree1(Integer.parseInt(s.substring(0,index)));
      Stack<Character> st=new Stack<>();
      
      if(index<s.length() && s.charAt(index)=='(')
      {
          int r=s.length();
      for(int i=index;i<s.length();i++)
      {
        if(s.charAt(i)==')' && st.size()==1)
        {
          r=i;
          break;
        }  
        
          
          if(s.charAt(i)==')')
          {
            
              st.pop();
          } 
          
          if(s.charAt(i)=='(')
          {
              st.push('(');
          }
          
          
          
          
        
      }
      root.left=buildtree(s.substring(index+1,r));
          if(r+1<s.length() && s.charAt(r+1)=='(')
          {
               root.right=buildtree(s.substring(r+2,s.length()-1));
          }
          else
          {
              root.right=null;
          }
      }
    return root;
        
    }
    

}