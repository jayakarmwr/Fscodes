/*
 * Imagine you're the curator of an ancient manuscript archive. Each manuscript is
assigned a unique significance score, and the archive is arranged so that every 
manuscript on the left has a lower score and every manuscript on the right has a
higher score, forming a special ordered display. Now, for an upcoming exhibition,
scholars have decided that only manuscripts with significance scores between low 
and high (inclusive) are relevant. Your task is to update the archive by removing
any manuscripts whose scores fall outside the range [low, high]. Importantly, 
while you remove some manuscripts, you must preserve the relative order of the 
remaining ones—if a manuscript was originally displayed as a descendant of another, 
that relationship should remain intact. It can be proven that there is a unique 
way to update the archive.

Display the manuscript of the updated archive. Note that the main manuscript 
(the root) may change depending on the given score boundaries.

Input format:
Line 1: space separated scores to build the manuscript archive
Line 2: two space seperated integers, low and high.

Output format:
space separated scores of the updated archive

Example 1:
input=
1 0 2
1 2
output=
1 2

Explanation:
Initial archieve:
      1
     / \
    0   2


Updated archieve:
    1
     \
      2
After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
and its right manuscript 2 remain.

Example 2:
input=
3 0 4 2 1
1 3
output=
3 2 1

Explanation:
Initial archieve:
          3
         / \
        0   4
         \
          2
         /
        1

Updated archieve:
      3
     /
    2
   /
  1

 */
package Day22;

import java.util.*;
public class Tree{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int low=sc.nextInt();
        int high=sc.nextInt();
        int[] arr = new int[s.length];
        for(int i = 0; i<s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        root = cut(root, low,high);
        levelorder(root);
    }
    
    static void levelorder(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.poll();
            System.out.print(node.val+" ");
            if(node.left!=null){
                q.add(node.left);
            }
            if(node.right!=null){
                q.add(node.right);
            }
        }
    }
    
    static Node cut(Node root, int low,int high){
        if(root==null){
            return null;
        }
        // levelorder(root);
        // System.out.println();
        Node x=root.right;
        if(root.val>high){
            root=cut(root.left,low,high);
            if(root==null){
                return null;
            }
            insert(root,x);
            
        }
        else{
            root.left=cut(root.left,low,high);
        }
        // levelorder(root);
        // System.out.println();
        x=root.left;
        if(root.val<low){
            root=cut(root.right,low,high);
            if(root==null){
                return null;
            }
            insert(root,x);
        }
        else{
            root.right=cut(root.right,low,high);
        }
        // levelorder(root);
        // System.out.println();
        return root;
    }
    
    static void insert(Node root,Node a){
        if(a==null){
            return;
        }
        if(a.val>root.val){
            if(root.right==null){
                root.right=a;
            }
            else{
                insert(root.right,a);
                return;
            }
        }
        if(a.val<root.val){
            if(root.left==null){
                root.left=a;
            }
            else{
                insert(root.left,a);
                return;
            }
        }
    }
    
    static Node insert(int[] arr){
        Node root = new Node(arr[0]);
        int i = 1;
        while(i<arr.length){
            insert(root,new Node(arr[i]));
            i++;
        }
        return root;
    }
}

class Node{
    int val;
    Node left;
    Node right;
    public Node(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}