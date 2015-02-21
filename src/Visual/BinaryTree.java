/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import java.util.Scanner;

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
    public void remove(int n)
    {
        if(root==null)
            return;
        if(root.val==n)
        {
            if(root.right==null&&root.left==null)
                root=null;
            else if(root.left==null)
                root=root.right;
            else if(root.right==null)
                root=root.left;
            else{
                int minimum=getMin(root.right);
                root.val=minimum;
                deleteMin(root,root.right,true);
            }
                
        }else if(root.val>n)
            remove(root,root.left,false,n);
        else
            remove(root,root.right,true,n);
    
    }
    private void remove(Node parent,Node child,boolean which,int n)
    {
        if(child==null)
            return;
        if(child.val==n)
        {
           
            if(child.right==null)
            {
                if(which)
                 parent.right=child.left;
                else
                 parent.left=child.left;
            }
            else if(child.left==null)
            {
                  if(which)
                       parent.right=child.right;
                  else
                      parent.right=child.right;
               
            }
            else{
                int minimum=getMin(child.right);
                child.val=minimum;
                deleteMin(child,child.right,true);
            }
        }else if(child.val<n)
            remove(child,child.right,true,n);
        else
            remove(child,child.left,false,n);
    }
    private void deleteMin(Node parent,Node child,boolean which)
    {
        if(child==null)
            return;
        if(child.left==null)
        {
            if(which)
            parent.right=child.right;
            else
            parent.left=child.right;
        }else
            deleteMin(child,child.left,false);
        
    }
    
   
    private int getMin(Node exp)
    {
        if(exp==null)
            return 0;
        int ans=exp.val;
        if(exp.left==null&&exp.right==null)
            return ans;
        else if(exp.left==null)
            return min(ans,getMin(exp.right));
        else if(exp.right==null)
            return min(ans,getMin(exp.left));
        else
            return min(ans,min(getMin(exp.left),getMin(exp.right)));
    }
    private int min(int a,int b)
    {
        return a>b?b:a;
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
    
    @Override
    public String toString()
    {
        return getString(root);
    }
    private String getString(Node parent)
    {
        if(parent==null)
            return "";
        StringBuilder sb=new StringBuilder();
          sb.append(getString(parent.left));
          sb.append(" ");
          sb.append(parent.val);
          sb.append(" ");
         sb.append(getString(parent.right));
         return sb.toString();
        
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
    public static void main(String[] args)
    {
        BinaryTree bt=new BinaryTree();
        Scanner in=new Scanner(System.in);
        while(true)
        {
            String s=in.next();
            switch (s) {
                case "i":
                    bt.add(in.nextInt());
                    break;
                case "d":
                    bt.remove(in.nextInt());
                    break;
                case "p":
                    System.out.println(bt);
                    break;
            }
            if(s.equals("q"))
                break;
           
            
        }
           
    }
}
