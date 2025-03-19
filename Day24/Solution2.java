/*
 * Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.

Below is the mapping of digits to letters (as found on a traditional telephone keypad):

| Digit | Letters       |
|-------|---------------|
| 2     | a, b, c       |
| 3     | d, e, f       |
| 4     | g, h, i       |
| 5     | j, k, l       |
| 6     | m, n, o       |
| 7     | p, q, r, s    |
| 8     | t, u, v       |
| 9     | w, x, y, z    |

Note: The digit 1 does not correspond to any letters.

Example 1:
Input: 23  
Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

Example 2:
Input: 2 
Output: [a, b, c]


Constraints:

- 0 <= digits.length <= 4  
- Each digit in the input is between '2' and '9'.

 */
package Day24;

import java.util.*;
class Solution2
{
    public static Map<Integer,List<String>> map=new HashMap<>();
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        List<String> l=new ArrayList<>();
        
        map.put(2,Arrays.asList("a","b","c"));
        map.put(3,Arrays.asList("d","e","f"));
        map.put(4,Arrays.asList("g","h","i"));
        map.put(5,Arrays.asList("j","k","l"));
        map.put(6,Arrays.asList("m","n","o"));
        map.put(7,Arrays.asList("p","q","r","s"));
        map.put(8,Arrays.asList("t","u","v"));
        map.put(9,Arrays.asList("w","x","y","z"));
        backtrack(l,new StringBuilder(),0,s);
        System.out.println(l);
        
    }
    public static void backtrack(List<String> l,StringBuilder st,int index,String s)
    {
        if(index==s.length())
        {
            l.add(st.toString());
            return;
        }
        int k=s.charAt(index)-'0';
        List<String> res=map.get(k);
        for(String i: res)
        {
            st.append(i);
            backtrack(l,st,index+1,s);
            st.deleteCharAt(st.length()-1);
        }
        
    }
}
