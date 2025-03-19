/*
 * Imagine you are the curator of a historic library, where books are arranged in a 
unique catalog system based on their publication years. The library’s archive is 
structured like a hierarchical tree, with each book’s publication year stored at 
a node. You are given the nodes of this catalog tree starting with main node
and a list of query years.

For each query year, you need to find two publication years:
- The first is the latest year in the archive that is less than or equal to the 
  query year. If no such book exists, use -1.
- The second is the earliest year in the archive that is greater than or equal 
  to the query year. If no such book exists, use -1.

Display the results as an list of pairs, where each pair corresponds to a query.

Example 1:
----------
Input: 
2006 2002 2013 2001 2004 2009 2015 2014
2002 2005 2016

Output:
[[2002, 2002], [2004, 2006], [2015, -1]] 


Archive Structure:
          2006
         /    \
     2002     2013
     /   \     /   \
  2001  2004  2009  2015
                     /
                  2014
                  
Explanation:  
- For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
  the earliest publication year that is ≥ 2002 is also 2002.  
- For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
  the earliest publication year that is ≥ 2005 is 2006.  
- For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
  there is no publication year ≥ 2016, so we output -1 for the second value.

Example 2:
----------
Input:  
2004 2009
2003

Output:
[[-1, 2004]]

Explanation:  
- For the query 2003, there is no publication year ≤ 2003, while the earliest 
  publication year that is ≥ 2003 is 2004.

Constraints:
- The total number of books in the archive is in the range [2, 10^5].
- Each publication year is between 1 and 10^6.
- The number of queries n is in the range [1, 10^5].
- Each query year is between 1 and 10^6.

 */

import java.util.*;
public class Tree{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String input=sc.nextLine();
        String[] k=input.split(" ");
        int[] a=new int[k.length];
        for(int i=0;i<k.length;i++){
            a[i]=Integer.parseInt(k[i]);
        }
        Node root = build(a);
        String input1=sc.nextLine();
        String[] k1=input1.split(" ");
        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
        for(int i=0;i<k1.length;i++){
            arr.add(search(root,Integer.parseInt(k1[i])));
        }
        System.out.println(arr);
    }
    static ArrayList<Integer> search(Node root,int i){
        ArrayList<Integer> arr = new ArrayList<>();
        int left=minsearch(root,i);
        if(left==i){
            arr.add(i);
            arr.add(i);
            return arr;
        }
        arr.add(left);
        int right=maxsearch(root,i);
        arr.add(right);
        return arr;
    }
    static int minsearch(Node root,int i){
        if (root==null){
            return -1;
        }
        if(root.val==i){
            return i;
        }
        else if(root.val<i){
            int x=minsearch(root.right,i);
            if(x!=-1){
                return Math.max(root.val,x);
            }
            return root.val;
        }
        else{
            int x=minsearch(root.left,i);
            return x;
        }
    }
    static int maxsearch(Node root,int i){
        if (root==null){
            return -1;
        }
        if(root.val==i){
            return i;
        }
        else if(root.val>i){
            int x=maxsearch(root.left,i);
            if(x!=-1){
                return Math.min(root.val,x);
            }
            return root.val;
        }
        else{
            int x=maxsearch(root.right,i);
            return x;
        }
    }
    static void insert(Node root,int a){
        if(a>root.val){
            if(root.right==null){
                root.right=new Node(a);
            }
            else{
                insert(root.right,a);
                return;
            }
        }
        if(a<root.val){
            if(root.left==null){
                root.left=new Node(a);
            }
            else{
                insert(root.left,a);
                return;
            }
        }
    }
    static Node build(int[] arr){
        if(arr[0]==-1){
            return null;
        }
        Node root=new Node(arr[0]);
            for(int i=1;i<arr.length;i++){
                insert(root,arr[i]);   
            }
        return root;
    }
}


class Node{
    int val;
    Node left;
    Node right;
    Node(int v){
        val=v;
        left=right=null;
    }
}