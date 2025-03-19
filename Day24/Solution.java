/*
 * Imagine you are an explorer who stumbles upon two ancient treasure chests. 
However, these aren’t ordinary chests—the coins inside are arranged in a mysterious, 
branching order. In each chest, the coins are hidden in secret compartments where 
lower valued coins are tucked away on one side and higher valued coins are placed 
on the opposite side. To unlock the legendary vault of riches, you must select one 
coin from the first chest and one from the second chest such that their magical 
values add up to a secret key number. 
Your challange is to return true if you can unlock the legendary vault of riches, 
otherwise false.

Example 1
----------
Input=
2 1 4
1 0 3
5

Output=
true

Chest A:
    2
   / \
  1   4

Chest B:
    1
   / \
  0   3

Explanation:Choosing the coin with value 2 from Chest A and the coin with value 3 
from Chest B adds up to 5, unlocking the vault

Example 2:
----------
Input=
0 -10 10
5 1 7 0 2
18

Output=
false

Chest A:
    0
   / \
-10   10

Chest B:
      5
     / \
    1   7
   / \
  0   2

Explanation: No combination of one coin from Chest A and one coin from Chest B 
sums to 18, so the vault remains sealed.

 */
package Day24;

/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
*/
import java.util.*;
class Solution {
    public static boolean result=false;
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        check(root1,root2,target);
        return result;
    
    
    
    }
    public static void check(TreeNode root1,TreeNode root2,int target)
    {
        if(root1==null || root2==null || result==true)
        {
            return;
        }
        int curr=root1.val+root2.val;
        if(curr==target)
        {
            result=true;
            return;
        }
        if(curr<target)
        {
            check(root1,root2.right,target);
            check(root1.right,root2,target);
        }
        else
        {
            check(root1,root2.left,target);
            check(root1.left,root2,target);
        }
    }
}
