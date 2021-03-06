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
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author anil
 */
public class CanvasAVL extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    int sw = 1200, sh = 800;
    int cw = 1000, ch = 800;
    int ar = 5;
    int r = 30;

    int tmargin = 50;
    int legX = 200, legY = 50;
    Group root = new Group();
    Scene scene = new Scene(root, sw, sh, Color.WHITE);
    Button bt = new Button("Start");
    TextField tf = new TextField();
    Circle circ = new Circle(ar);
    Canvas canvas = new Canvas(cw, ch);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    AvlTree<Integer> st = new AvlTree<>();

    @Override
    public void init() {
        bt.setLayoutX(sw - 200);
        bt.setLayoutY(50);
        tf.setLayoutX(sw - 200);
        tf.setLayoutY(150);
        circ.setFill(Color.GREEN);

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);

        root.getChildren().add(canvas);
        root.getChildren().add(circ);
        root.getChildren().add(bt);
        root.getChildren().add(tf);

        bt.setOnAction((ActionEvent e) -> {
            int num = Integer.parseInt(tf.getText());
            st.add(num);
            gc.clearRect(0, 0, cw, ch);
            
            
                for (Integer x : st.keys()) {
                    Point p = getPoint(x);
                    Point p1=getParent(x);
                    
                    gc.strokeLine(p1.x+r/2, p1.y+r/2, p.x+r/2, p.y+r/2);
                    
                    gc.setStroke(Color.BLACK);
                    gc.strokeOval(p.x, p.y, r, r);
                    gc.strokeText(x + "", p.x + 0.2 * r, p.y + 0.6 * r);
                    
                }
            
        });

        primaryStage.show();

    }

    private Point getPoint(int n) {
        int x = cw / 2, y = tmargin;
        int addX = legX;
        int addY = legY;
        String s = st.getPath(n);
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
    private Point getParent(int n) {
        int x = cw / 2, y = tmargin;
        int addX = legX;
        int addY = legY;
        String s = st.getPath(n);
        for (int i = 0; i < s.length()-1; i++) {
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
}
