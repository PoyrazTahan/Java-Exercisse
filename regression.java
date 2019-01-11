package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.shape.Circle;


/**
 *  @author Doga Poyraz TAHAN
 *  @since 05/04/2018
 *
 *  Fits a regression Line to the given dataset
 *
 */
public class Main extends Application{

    /**
     * Reads the text file written down
     */
    public void start(Stage primaryStage) throws FileNotFoundException {

        // create file object
        File file = new File("coordinates1.txt"); // sets the file at hand as object

        // if file is not found, report and exit program
        if (!file.exists()) {
            // if couldn't finde the file Exit
            System.out.println("File can not be found! Exiting program...");
            System.exit(1);
        }

        // open a scanner object to read from file
        BufferedReader reader = new BufferedReader(new FileReader(file)); // line by line reading
        String str;
        ArrayList<Coordinate> coordList = new ArrayList<>();


        try {

            while((str = reader.readLine()) != null) {
                // while there is something to read

                //split the line by space as seperator
                String[] line = str.split(" ");


                int[] tmpCoord = new int[line.length]; // create an array with -1 the length of line array since one of them is name

                for (int i = 0; i < line.length; i++)
                    tmpCoord[i] = Integer.parseInt(line[i]); // parses int from string and enters it tp temp Grades


                Coordinate c = new Coordinate(tmpCoord[0], tmpCoord[1]); // constructs a student with its name and grades

                coordList.add(c);


            }
            //closing the reader
            reader.close();
        }
        catch(Exception ex) {
            //if it finds an error it will throw and error mesage
            System.err.println(ex.getMessage());
        }


        Collection coords = new Collection(coordList);

        // scene parameters
        int scene_w = coords.maxX - coords.minX;
        int scene_h = coords.maxY - coords.minY;
        // circle parameters
        double radius = 1.2;
        // JavaFX drawing commands
        Pane pane = new Pane(); // create a pane


        for(int i =0 ; i<coords.cList.size();++i) {
            Circle circle = getCircle(coords.symetricList.get(i).x, coords.symetricList.get(i).y, radius);

            pane.getChildren().add(circle); // add circle to pane
        }

        double startX = coords.minX;

        double startY = coords.midPoint-(coords.coeff+coords.slope*coords.minX- coords.midPoint);
        // This calculation is because plot counts from upper right but human eye looks from lower right
        double endX = coords.maxX;
        double endY = coords.midPoint-(coords.coeff+coords.slope*coords.maxX- coords.midPoint);

        Line line1 = new Line(startX,startY,endX,endY);

        pane.getChildren().add(line1);

        Scene scene = new Scene(pane, scene_w, scene_h);
        primaryStage.setTitle("Scatter Plot"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false);
        primaryStage.show(); // Display the stage



    }


    public static Circle getCircle(int xCoord, int yCoord, double radius){
        // Circle drawing code

        Circle circle = new Circle();
        circle.setCenterX(xCoord);
        circle.setCenterY(yCoord);
        circle.setRadius(radius);

        circle.setStroke(Color.color(Math.random(), Math.random(), Math.random(),0.7)); //random color
        circle.setStrokeWidth(1);
        circle.setFill(null);

        return circle;
    }

    /**
     * This program reads the given x y values store them in ab object called Coordinates
     * Scatter plots them and fits a regression line
     *
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}


