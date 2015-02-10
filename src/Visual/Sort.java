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
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import java.util.Random;

public class Sort extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    int sw = 1200, sh = 800;
    int cw = 1000, ch = 800;
    int ar = 5;
    int r = 40;
    int tmargin = 50;
    int legX = 200, legY = 50;
    TextField tf = new TextField("Write a number");
    Group root = new Group();
    Scene scene = new Scene(root, sw, sh, Color.WHITE);
    Button bt = new Button("Start");
    Label label = new Label("change this");
    Circle circ = new Circle(ar);
    Canvas canvas = new Canvas(cw, ch);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    PathTransition pathTransition = new PathTransition();
    PathTransition pathTransition1 = new PathTransition();
    Label[] labels;
    Circle[] circles;
    Group[] groups;
    int[] ints;
    int[] gprs;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        labels = new Label[10];
        circles = new Circle[10];
        groups = new Group[10];
        ints = new int[10];
        gprs=new int[10];
         for (int i = 0; i < 10; i++)
         {
             ints[i] = i;
             gprs[i]=i;
         }
        shuffleArray(ints);
        for (int i = 0; i < 10; i++) {
           
            labels[i] = new Label(ints[i] + "");
            circles[i] = new Circle(20);
            groups[i] = new Group();
            circles[i].setFill(Color.WHITE);
            circles[i].setStroke(Color.BLACK);
            //    circles[i].setLayoutY(100);
            //    circles[i].setLayoutX(100+i*50);
            //   labels[i].setLayoutY(90);
            //   labels[i].setLayoutX(100+i*50);
            groups[i].getChildren().add(circles[i]);
            groups[i].getChildren().add(labels[i]);
          //  groups[i].setLayoutY(100);
       //     groups[i].setLayoutX(100 + i * 50);
            groups[i].relocate(100+i*50,100);
        }
        for (int i = 0; i < 10; i++) {
            //     root.getChildren().add(circles[i]);
            //   root.getChildren().add(labels[i]);
            root.getChildren().add(groups[i]);

        }
        
        bt.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                int m = 0, n = 0;
                boolean found=false;
                for (int i = 0; i < 10&&!found; i++) {
                    for (int j = i+1; j < 10 &&!found; j++) {
                        if (ints[i] > ints[j]) {
                            m = i;
                            n = j;
                            found=true;
                            break;
                        }
                    }
                }
                System.out.println(m+" "+n);
                play1(m, n);
                play2(m, n);
             
                swapInts(m,n);
          //      reAssign();
        //        swapGroups(m,n);

            }
        });
        root.getChildren().add(bt);

        primaryStage.show();
    }

    private void play1(int m, int n) {
        
        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));
        path.getElements().add(new LineTo((n-m)* 50, 0));
        groups[gprs[m]].relocate(100+m*50, 100);
        pathTransition.setNode(groups[gprs[m]]);
        pathTransition.setPath(path);
        pathTransition.setRate(0.2);
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
             //  groups[gprs[m]].relocate(100+n*50, 100);
            }
        });
    //    System.out.println(groups[m].getLayoutX()+" "+groups[m].getLayoutY());
    //    System.out.println(groups[n].getLayoutX()+" "+groups[n].getLayoutY());
        pathTransition.play();
    }
     private void play2(int m, int n) {
        
        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));
        path.getElements().add(new LineTo((m-n)* 50, 0));
        groups[gprs[n]].relocate(100+n*50, 100);
        pathTransition1.setNode(groups[gprs[n]]);
        pathTransition1.setPath(path);
        pathTransition1.setRate(0.2);
        pathTransition1.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
           //    groups[gprs[n]].relocate(100+m*50, 100);
            }
        });
    //    System.out.println(groups[m].getLayoutX()+" "+groups[m].getLayoutY());
    //    System.out.println(groups[n].getLayoutX()+" "+groups[n].getLayoutY());
        pathTransition1.play();
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    private void swapInts(int i,int j)
    {
        int tmp=ints[i];
        ints[i]=ints[j];
        ints[j]=tmp;
        tmp=gprs[i];
        gprs[i]=gprs[j];
        gprs[j]=tmp;
    }
    static void shuffleArray(int[] ar)
  {
    Random rnd = new Random();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }
   
   

}
