/*
 * Construct Tree from the given Level-order and In-order.
Compute the Depth and Sum of the Deepest nodes in the Binary tree

Input Format:
-------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the level-order traversal.

Output Format:
--------------
Print two values:
->The maximum number of levels.
->The sum of all node values at the deepest level.

Example:
-------------
Input:
11
7 8 4 2 5 9 11 10 1 6 3
1 2 3 4 5 6 7 9 8 10 11

Output:
6 11

Explanation:
The binary tree structure:
           1
         /   \
       2       3
      / \     /
     4   5   6
    /     \   
   7       9
    \       \
     8      10
            /
          11
Maximum Depth: 6
nodes at the Deepest Level (6): 11
Sum of nodes at Level 6: 11

 */
package Day4_trees;
import java.util.*;

public class Tree2 {
    int data;
    Tree2 left;
    Tree2 right;

    Tree2(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] levelorder = new int[n];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            inorder[i] = sc.nextInt();
            map.put(inorder[i], i);
        }
        for (int i = 0; i < n; i++) {
            levelorder[i] = sc.nextInt();
        }
        sc.close();

        Map<Integer, List<Integer>> levelmap = new HashMap<>();
        Tree2 root = buildtree(levelorder, inorder, map);
        int level = levelordertraversal(root, levelmap);
        int sum = 0;

        for (int i : levelmap.get(level)) {
            sum += i;
        }

        System.out.println(level + " " + sum);
    }

    public static Tree2 buildtree(int[] levelorder, int[] inorder, Map<Integer, Integer> map) {
        Queue<Tree2> q = new LinkedList<>();
        Tree2 root = new Tree2(levelorder[0]);
        q.offer(root);
        Set<Integer> used = new HashSet<>();
        used.add(root.data);

        for (int i = 1; i < levelorder.length; i++) {
            int nodeval = levelorder[i];
            if (used.contains(nodeval)) continue;

            Tree2 node = new Tree2(nodeval);
            Tree2 parent = findparent(root, nodeval, map);

            if (parent != null) {
                if (parent.left == null && map.get(nodeval) < map.get(parent.data)) {
                    parent.left = node;
                } else if (parent.right == null) {
                    parent.right = node;
                }
                q.offer(node);
                used.add(nodeval);
            }
        }
        return root;
    }

    public static Tree2 findparent(Tree2 root, int val, Map<Integer, Integer> map) {
        Queue<Tree2> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Tree2 node = q.poll();
            int index = map.get(node.data);

            if (map.get(val) < index) {
                if (node.left == null) return node;
                q.offer(node.left);
            } else {
                if (node.right == null) return node;
                q.offer(node.right);
            }
        }
        return null;
    }

    public static int levelordertraversal(Tree2 root, Map<Integer, List<Integer>> map) {
        Queue<Tree2> q = new LinkedList<>();
        int level = 1;
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Tree2 node = q.poll();
                l.add(node.data);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            map.put(level++, l);
        }
        return level - 1;
    }
}
