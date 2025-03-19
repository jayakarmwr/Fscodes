/*
 * Imagine you're a digital security analyst reviewing a suspicious email. The email’s 
content is a continuous string of characters, and you have a list of keywords 
commonly used in phishing scams. Your mission is to scan the email text and flag 
every segment that exactly matches one of these keywords. In other words, identify 
all index pairs [i, j] such that the substring from position i to j in the email 
text is one of the suspicious keywords. Return these pairs sorted by their starting 
index, and if two pairs share the same starting index, sort them by their ending index.

Input Format
------------
Line-1: string STR(without any space)
Line-2: space separated strings, suspicious keywords[]

Output Format
-------------
Print the pairs[i, j] in sorted order.


Example 1:
----------
Input:  
cybersecuritybreachalert
breach alert cyber

Output: 
0 4
13 18
19 23

Example 2:
----------
Input:  
phishphishingphish
phish phishing

Output:
0 4
5 9
5 12
13 17


Explanation: Notice that keywords can overlap—for instance, the word "phish" appears 
as part of the substring [5,9] in addition to the complete "phishing" substring [5,12].

Constraints:

- 1 <= emailText.length <= 100  
- 1 <= suspiciousWords.length <= 20  
- 1 <= suspiciousWords[i].length <= 50  
- emailText and each suspicious word consist of lowercase English letters.  
- All suspicious words are unique.

 */
package Day26;

import java.util.*;
public class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s=sc.nextLine();
        String s1=sc.nextLine();
        Trie root=new Trie();
        String[] a=s1.split(" ");
        for(String i:a){
            root.insert(i);
        }
        for(int i=0;i<s.length();i++){
            root.search(s.substring(i),i);
        }
    }
}


class TrieNode {
	TrieNode[] children;
	boolean isEndOfWord;
	public TrieNode() {
		children = new TrieNode[26];
		isEndOfWord = false;
 	}
 }
 class Trie {
	private TrieNode root;
 	public Trie() {
 		root = new TrieNode();
 	}
 	public void insert(String word) {
 		TrieNode node = root;
 		for (char c : word.toCharArray()) {
 			int index = c - 'a';
 			if (node.children[index] == null) {
				node.children[index] = new TrieNode();
 			}
			node = node.children[index];
		}
		node.isEndOfWord = true;
 	}
 	public void search(String word,int i) {
 	    int end=i-1;
 		TrieNode node = root;
 		for (char c : word.toCharArray()) {
			int index = c - 'a';
 			if (node.children[index] == null) {
 				return;
 			}
 			end++;
 			node = node.children[index];
     		if(node.isEndOfWord){
    		    System.out.println(i+" "+end);
    		}
 		}
 	}
 }