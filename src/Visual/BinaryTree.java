/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

/**
 *
 * @author anil
 */
public class BinaryTree {
   
    Node root;
    int size=0;
    class Node{
        int val;
        Node left,right;
        Node(int n)
        {
            val=n;
        }            
    }
    BinaryTree()
    {
        size=0;
        root=null;
    }
    public int size()
    {
        return size;
    }
    public void add(int n)
    {
        if(root==null)
        {
            root=new Node(n);
            size++;
        }else
         add(root,n);
    }
    public int getIndex(int n)
    {
        String s=getPath(n);
        int ans=1;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)=='R')
                ans=2*ans+1;
           else
                ans=2*ans;
        return ans;
    }
    public String getPath(int n)
    {
        StringBuilder sb=new StringBuilder();
        Node start=root;
        while(start!=null)
        {
            if(start.val<n){
                sb.append('R');
                start=start.right;
            }else if(start.val>n){
                sb.append('L');
                start=start.left;
            }else
             break;
        }
        return sb.toString();
    }
    private void add(Node present,int n)
    {
       if(present.val>n)
       {
           if(present.left==null)
           {
               present.left=new Node(n);
               size++;
           }else
            add(present.left,n);
       }else if(present.val<n)
       {
            if(present.right==null)
           {
               present.right=new Node(n);
               size++;
           }else
            add(present.right,n);
       }
    }
    public void print()
    {
      print(root);
    }
    private void print(Node present)
    {
        if(present!=null)
        {
            System.out.println(present.val);
            print(present.left);
            print(present.right);
        }
    }
}
