/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author anil
 */
public class Minimal extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    int sw = 1200, sh = 800;
    int cw = 1000, ch = 800;
    int ar = 5;
    int r = 30;
    
    Group root = new Group();
    Scene scene = new Scene(root, sw, sh, Color.WHITE);
    Button bt = new Button("Start");
    Label label = new Label("change this");
    Circle circ = new Circle(ar);
    Canvas canvas = new Canvas(cw, ch);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    PathTransition pt1 = new PathTransition();
    PathTransition pt2 = new PathTransition();
    PathTransition pt3 = new PathTransition();
    PathTransition pt4 = new PathTransition();
     PathTransition pt5 = new PathTransition();
    RotateTransition pt6 = new RotateTransition();
    Path p1=new Path();
    Path p2=new Path();
    Path p3=new Path();
    Path p4=new Path();
    Path p5=new Path();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        Circle circ1=new Circle(500,50,r,Color.WHITE);
        Circle circ2=new Circle(400,150,r,Color.WHITE);
        Circle circ3=new Circle(300,250,r,Color.WHITE);
        Line line1=new Line(500,50,400,150);
        Line line2=new Line(300,250,400,150);
        
         Line line3=new Line(100,100,300,300);
         Rotate rotate=new Rotate(0,500,50);
         line1.getTransforms().add(rotate);
         
        KeyValue start = new KeyValue(rotate.angleProperty(), 0);
        KeyValue end = new KeyValue(rotate.angleProperty(), -90);
        KeyFrame kf1=new KeyFrame(Duration.ZERO, start);
        KeyFrame kf2=new KeyFrame(Duration.seconds(2), end);
        Animation rotateAnimation = new Timeline(kf1, kf2);
         rotateAnimation.play();
      //  line1.getTransforms().add(new Rotate(-90,500,50));
        circ1.setStroke(Color.BLACK);
        circ2.setStroke(Color.BLACK);
        circ3.setStroke(Color.BLACK);
        
        root.getChildren().add(circ1);
        root.getChildren().add(circ2);
        root.getChildren().add(circ3);
        root.getChildren().add(line1);
        root.getChildren().add(line2);
    //    root.getChildren().add(line3);
    //    root.getChildren().add(new Circle(100,100,5,Color.GREEN));
        pt1.setNode(circ1);
        pt2.setNode(circ2);
        pt3.setNode(circ3);
        pt4.setNode(line1);
        pt5.setNode(line2);
        pt6.setNode(line3);
        
        p1.getElements().add(new MoveTo(500,50));
        p2.getElements().add(new MoveTo(400,150));
        p3.getElements().add(new MoveTo(300,250));
        p4.getElements().add(new MoveTo(500,50));
        p5.getElements().add(new MoveTo(250,300));
        
        
        p1.getElements().add(new LineTo(600,150));
        p2.getElements().add(new LineTo(500,50));
        p3.getElements().add(new LineTo(400,150));
        p4.getElements().add(new ArcTo(150,150,0,600,150,false,false));
        p5.getElements().add(new LineTo(450,100));
        
        pt1.setPath(p1);
        pt2.setPath(p2);
        pt3.setPath(p3);
        pt4.setPath(p4);
        pt5.setPath(p5);
   //    pt6.setByAngle(90);
        
        pt1.setRate(0.2);
        pt2.setRate(0.2);
        pt3.setRate(0.2);
        pt4.setRate(0.2);
        pt5.setRate(0.2);
        pt6.setRate(0.2);
        pt1.play();
        pt2.play();
        pt3.play();
    //    pt4.play();
        pt5.play();
      //  pt6.play();
        
        
        primaryStage.show();
        
    }
}
