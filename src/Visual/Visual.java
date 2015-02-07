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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Visual {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Binary Tree");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    } 
}

class MyPanel extends JPanel {

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 30;
    private int squareH = 30;
    ArrayList<Point> al=new ArrayList<>();
    ArrayList<Integer> all=new ArrayList<>();
    BinaryTree bt=new BinaryTree();
    public MyPanel() {
        JButton jb=new JButton("Hit Me");
        JTextField jtf=new JTextField("write here");
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
       {
           String ans=jtf.getText();
           int val=Integer.parseInt(ans);
           bt.add(val);
           al.add(getPoint(bt.getPath(val)));
           all.add(val);
     //      al.add(getPoint(al.size()+1));
           repaint();
        // display/center the jdialog when the button is pressed
        
      }
        });
        this.add(jb);
        this.add(jtf);
        setBorder(BorderFactory.createLineBorder(Color.black));

     /*   addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });*/
        
    }
    
    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX!=x) || (squareY!=y)) {
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        } 
    }
    
    private Point getPoint(int n)
    {
        int x=600,y=50;
        int addX=320;
        int addY=100;
        String s=getPath(n);
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='R')
            x+=addX;
            else
            x-=addX;
            y+=addY;
            addX=addX/2;
        }
        return new Point(x,y);
    }
    private Point getPoint(String s)
    {
        int x=600,y=50;
        int addX=320;
        int addY=100;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='R')
            x+=addX;
            else
            x-=addX;
            y+=addY;
            addX=addX/2;
        }
        return new Point(x,y);
        
    }
    private String getPath(int n)
    {
        StringBuilder sb=new StringBuilder();
        while(n>1)
        {
            if(n%2==1)
                sb.append('R');
            else
                sb.append('L');
            n=n/2;
        }
        return sb.reverse().toString();
    }
    public Dimension getPreferredSize() {
        return new Dimension(1200,600);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
     //   g.setColor(Color.RED);
    //    g.fillOval(squareX, squareY, squareW, squareH);
    //    g.fillRect(squareX,squareY,squareW,squareH);
        g.setColor(Color.BLACK);
        int i=0;
        Point prev=new Point(600,50);
        for(Point x:al)
        {
            
               g.drawOval(x.x, x.y, squareW, squareH);
               g.drawString(all.get(i)+"", x.x+5, x.y+20);
               prev=getPoint(bt.getIndex(all.get(i))/2);
               g.drawLine(prev.x+15, prev.y+15, x.x+15, x.y+15);
               i++;
        }
    //    g.drawRect(squareX,squareY,squareW,squareH);
    }  
    
}
