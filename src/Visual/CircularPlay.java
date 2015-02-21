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
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.*;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.*;

public class CircularPlay extends Application {

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
    BinaryTree btree = new BinaryTree();
    int count = 1;

    @Override
    public void init() {
        bt.setLayoutX(sw - 200);
        bt.setLayoutY(50);
        label.setLayoutX(sw - 200);
        label.setLayoutY(100);
        tf.setLayoutX(sw - 200);
        tf.setLayoutY(150);
        circ.setFill(Color.GREEN);

    }

    private void draw(int n) {
        gc.strokeOval(cw / 2, tmargin, r, r);
        gc.strokeText(n + "", cw / 2 + 0.2 * r, tmargin + 0.6 * r);
        gc.stroke();

    }

    private void drawAfter(int n) {
        btree.add(n);
        Point p = getPoint(n);
        Point p1 = getPointByIndex(btree.getIndex(n) / 2);
        sline(p.x + r / 2, p.y + r / 2, p1.x + r / 2, p1.y + r / 2);
         gc.setStroke(Color.BLACK);
        gc.strokeOval(p.x, p.y, r, r);
        gc.strokeText(n + "", p.x + 0.2 * r, p.y + 0.6 * r);
    }

    private void sline(double x1, double y1, double x2, double y2) {
        Line l = new Line(x1, y1, x2, y2);
        l = shrink(l);
        gc.setStroke(Color.RED);
        gc.strokeLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
    }

    private Path calcPath(int n) {
        Path path = new Path();
        int i = 1;
        Point p1 = getPointByIndex(i);
        String s = btree.getPath(n);
        count = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == 'R') {
                i = 2 * i + 1;
            } else {
                i = 2 * i;
            }
            Point p2 = getPointByIndex(i);
            Line l = shrink(new Line(p1.x + r / 2, p1.y + r / 2, p2.x + r / 2, p2.y + r / 2));
            path.getElements().add(new MoveTo(p1.x + r, p1.y + r / 2));

            path.getElements().add(new ArcTo(r / 2, r / 2, 0, p1.x + r, p1.y + r / 2 + 1, true, false));

            path.getElements().add(new MoveTo(l.getStartX(), l.getStartY()));
            path.getElements().add(new LineTo(l.getEndX(), l.getEndY()));
            p1 = p2;
            count++;
        }

        return path;
    }

    private void play(int n) {
        /*  Path path = new Path();

         path.getElements().add(new MoveTo(1.5*r, r/2));
         path.getElements().add(new ArcTo(r/2, r/2, 90, (1.5*r), (r/2)+1, true, false));
         */
        PathTransition pathTransition = new PathTransition();

        pathTransition.setNode(circ);
        pathTransition.getNode().setVisible(true);
        pathTransition.setPath(calcPath(n));
        pathTransition.setRate(0.2);
        //  pathTransition.setDuration(Duration.millis(count*2000));
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pathTransition.getNode().setVisible(false);
                drawAfter(n);
            }
        });
        pathTransition.play();
        //    sleep(2000);

    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(scene);

        root.getChildren().add(canvas);
        root.getChildren().add(circ);
        root.getChildren().add(bt);
        root.getChildren().add(label);
        root.getChildren().add(tf);

        bt.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                int num = Integer.parseInt(tf.getText());
                if (btree.size() == 0) {
                    btree.add(num);
                    draw(num);
                } else {
                    play(num);
                }
                // drawAfter();
            }
        });

        primaryStage.show();
    }

  

    private Point getPoint(int n) {
        int x = cw / 2, y = tmargin;
        int addX = legX;
        int addY = legY;
        String s = btree.getPath(n);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                x += addX;
            } else {
                x -= addX;
            }
            y += addY;
            addX = addX / 2;
        }
        return new Point(x, y);
    }

    private Point getPointByIndex(int n) {
        int x = cw / 2, y = tmargin;
        int addX = legX;
        int addY = legY;
        String s = getPath(n);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                x += addX;
            } else {
                x -= addX;
            }
            y += addY;
            addX = addX / 2;
        }
        return new Point(x, y);
    }

    private String getPath(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 1) {
            if (n % 2 == 1) {
                sb.append('R');
            } else {
                sb.append('L');
            }
            n = n / 2;
        }
        return sb.reverse().toString();
    }

    private Line shrink(Line l) {
        double x1 = l.getStartX();
        double y1 = l.getStartY();
        double x2 = l.getEndX();
        double y2 = l.getEndY();
        double dx = (r * (x2 - x1)) / Math.sqrt(sqr(x2 - x1) + sqr(y2 - y1));
        double dy = (r * (y2 - y1)) / Math.sqrt(sqr(x2 - x1) + sqr(y2 - y1));
        return new Line(x1 + dx / 2, y1 + dy / 2, x2 - dx / 2, y2 - dy / 2);
    }

    private double sqr(double x) {
        return x * x;
    }

}
