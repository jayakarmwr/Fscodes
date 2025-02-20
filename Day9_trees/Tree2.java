/*
 * Imagine you are a librarian organizing books on vertical shelves in a grand 
library. The books are currently scattered across a tree-like structure, where 
each book (node) has a position determined by its shelf number (column) and row 
number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged 
from left to right, just as they appear in the original scattered arrangement.

Sample Input:
-------------
3 9 20 -1 -1 15 7

Sample Output:
--------------
[[9],[3,15],[20],[7]]

Explanation:
------------
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]


Sample Input-2:
---------------
3 9 8 4 0 1 7

Sample Output-2:
----------------
[[4],[9],[3,0,1],[8],[7]]

Explanation:
------------

          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]

 */
package Day9_trees;

import java.util.*;
class Tree2
{
    int data;
    Tree2 left;
    Tree2 right;
    Tree2(int data)
    {
        this.data=data;
        this.left=null;
        this.right=null;
    }
    static class Pair
    {
        Tree2 node;
        int row;
        int col;
        Pair(Tree2 node,int row,int col)
        {
            this.node=node;
            this.row=row;
            this.col=col;
        }
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
        Tree2 root=buildtree(arr);
        List<List<Integer>> l=verticaltraversal(root);
        System.out.println(l);
        
    }
    public static List<List<Integer>> verticaltraversal(Tree2 root)
    {
        TreeMap<Integer,TreeMap<Integer,List<Integer>>> map=new TreeMap<>();
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(root,0,0));
        while(!q.isEmpty()){
            Pair t=q.poll();
            Tree2 node=t.node;
            int x=t.row;
            int y=t.col;
            if(!map.containsKey(x)){
                map.put(x,new TreeMap<>());
            }
            if(!map.get(x).containsKey(y)){
                map.get(x).put(y,new ArrayList<>());
            }
            map.get(x).get(y).add(node.data);
            if(node.left!=null){
                q.add(new Pair(node.left,x-1,y+1));
            }
            if(node.right!=null){
                q.add(new Pair(node.right,x+1,y+1));
            }

        }
        List<List<Integer>> res=new ArrayList<>();
        for(TreeMap<Integer,List<Integer>> k:map.values()){
            List<Integer> vertical = new ArrayList<>();
            for (List<Integer> pq : k.values()) {
                    //Collections.sort(pq);
                    vertical.addAll(pq);
                
            }
            res.add(vertical);
        }
        return res;
    }
    
    
    public static Tree2 buildtree(int[] arr)
    {
        Queue<Tree2> q=new LinkedList<>();
        Tree2 root=new Tree2(arr[0]);
        q.offer(root);
        int i=1;
        while(!q.isEmpty())
        {
            Tree2 node=q.poll();
            if(i<arr.length && arr[i]!=-1)
            {
                node.left=new Tree2(arr[i]);
                q.offer(node.left);
            }
            i++;
            if(i<arr.length && arr[i]!=-1)
            {
                node.right=new Tree2(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }
}
