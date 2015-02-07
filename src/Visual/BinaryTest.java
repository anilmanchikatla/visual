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
import java.util.*;
public class BinaryTest {
    public static void main(String[] args)
    {
	BinaryTree bt=new BinaryTree();
	Scanner in=new Scanner(System.in);
	for(int i=0;i<10;i++)
	{
		int val=in.nextInt();
		bt.add(val);
		System.out.println(bt.getPath(val));
		
	}
        System.out.println();
    }
}
