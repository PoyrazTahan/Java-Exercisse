package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Student Name: Doga Poyraz TAHAN
 * Student ID: 041503044
 * date: 21/02/2018
 *
 * Explanation:
 * This code prompt a screen with determiened circles and randomly colored points.
 * points are colored differently depending if they are in orout of the circles.
 *
 * @author poyraztahan
 * @version 0.000001
 * @since 21/02/2018
 *
 *
 */
public class Doga_Poyraz_TAHAN extends Application{
    public void start(Stage primaryStage){

        //xCoords / yCoords / radius of placed circles
        int[][] circles = new int[][]{
                { 100, 100 ,50},
                { 300, 100 ,20},
                { 500,100,50},
                {100,300 ,20},
                { 300,300 ,150},
                { 500, 300 ,20},
                { 100, 500 ,50},
                { 300,500,20},
                { 500,500,50},
        };
        int N = 500000; // stores the number of shots

        double scene_h=600; // display screen height
        double scene_w=600;// display screen width
        double radius=0.1;  // displayed circle's raidus


        Pane pane=new Pane();// an object to manipulate the window
        Scene scene=new Scene(pane,scene_h,scene_w);//creates a window object

        // drawing the Circle
        for (int i = 0; i<circles.length; ++i){
            Circle myCircle = setGUI(circles[i][0],circles[i][1],circles[i][2]);
            pane.getChildren().add(myCircle); //**prints out the my circle object

        }
        // ends drwaing the Circle


        // shooting starts
        for(int i=0;i<N;i++){
            Circle myCircle2=new Circle(); // creates a circle for every shoot
            double x=Math.random()*scene_h; // randomly choses X coord
            double y=Math.random()*scene_w; // randomly choses y coord

            // starts filling new shoot circle
            myCircle2.setCenterX(x);
            myCircle2.setCenterY(y);
            myCircle2.setRadius(radius);
            //ends filling new shoot circle

            //double distance=Math.pow(Math.pow(x-scene_h/2,2)+Math.pow(y-scene_w/2,2),0.5);

            if(isInsideCircle(myCircle2,circles)){
                myCircle2.setStroke(Color.RED);
                myCircle2.setFill(Color.RED);
            }
            else{
                myCircle2.setStroke(Color.GRAY);
                myCircle2.setFill(Color.GRAY);
            }

            pane.getChildren().add(myCircle2); // prints the new children
        }
        //shooting ends

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * A boolean method to check if a point is inside any of the circles.
     *
     * @param shoot //randomlt determined point
     * @param circleList // list of the circles their X & Y Coord & radiuses.
     * @return true if inside false if outside
     */
    public static boolean isInsideCircle(Circle shoot, int[][] circleList){
        //double distance=Math.pow(Math.pow(x-scene_h/2,2)+Math.pow(y-scene_w/2,2),0.5);
        for(int i=0;i<circleList.length;++i){
            for( int j = 0; j<circleList[0].length;++j){
                double distance=Math.pow(Math.pow(shoot.getCenterX()-circleList[i][0],2)+Math.pow(shoot.getCenterY()-circleList[i][1],2),0.5);


                //if(distance<=circleList[i][2]+shoot.getRadius())
                if(distance<=circleList[i][2])
                    return true;

            }
        }

        return false;
    }

    /**
     *
     * @param x for the x coord of the circle being promt
     * @param y for the y coord of the circle being promt
     * @param r for the radius coord of the circle being promt
     * @return
     */
    public static Circle setGUI(double x, double y , double r){

        // drawing the Circle
        Circle myCircle=new Circle();
        //for (int i = 1; i<circleList.length;++i)
        myCircle.setCenterX(x);// sets X to the middle
        myCircle.setCenterY(y);// sets Y to int the middle
        myCircle.setRadius(r);
        myCircle.setStrokeWidth(1); // Çerçeve kalınlığı
        myCircle.setFill(Color.PINK); //sets bacground for the circle
        myCircle.setStroke(Color.BLACK); // circle çerçeve rengi
        // ends drwaing the Circle
        return myCircle;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
