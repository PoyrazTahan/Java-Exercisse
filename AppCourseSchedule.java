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

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application {

    /**
     * Reads the text file written down
     */
    public void start(Stage primaryStage) throws FileNotFoundException {

        // create file object
        File file = new File("schedule1.txt"); // sets the file at hand as object

        // if file is not found, report and exit program
        if (!file.exists()) {
            // if couldn't finde the file Exit
            System.out.println("File can not be found! Exiting program...");
            System.exit(1);
        }

        // open a scanner object to read from file
        BufferedReader reader = new BufferedReader(new FileReader(file)); // line by line reading
        String str;
        //ArrayList<Coordinate> coordList = new ArrayList<>();
        ArrayList<Course> courseList = new ArrayList<Course>();
        String name = null;

        try {

            name = reader.readLine();
            while ((str = reader.readLine()) != null) {
                // while there is something to read

                //split the line by space as seperator
                String[] line = str.split(":");

                String courseN = line[0];
                String courseD = line[1];

                ArrayList<Integer> tmp = new ArrayList<Integer>(); // create an array with -2 the length of line array since one of them is name and day


                for (int i = 2; i < line.length; i++)
                    tmp.add(Integer.parseInt(line[i])); // parses int from string and enters it to temp section


                Course c = new Course(courseN, courseD, tmp); // constructs a student with its name and grades

                courseList.add(c);


            }
            //closing the reader
            reader.close();
        } catch (Exception ex) {
            //if it finds an error it will throw and error mesage
            System.err.println(ex.getMessage());
        }

        showInfo(primaryStage, courseList, name);
    }


    private void showInfo(Stage primaryStage, ArrayList<Course> cList, String n){
        int dayNumber = 5;
        int secNumber = 8;

        int sceneWidth = 1100;
        int sceneHeight = 600;

        int widthDayBar = 720;
        int heightDayBar = 100;

        int widthSecBar = 120;
        int heighSecBar = 100;

        Pane pane = new Pane();


        for(int i = 0; i<dayNumber; ++i){

            int y = 80 + i * heighSecBar;
            for(int j=0; j<secNumber; ++j){
                int x = 80 + j * widthSecBar;
                Rectangle tmpR = new Rectangle(x, y,widthSecBar,heighSecBar);


                tmpR.setFill(Color.DEEPSKYBLUE);
                tmpR.setStroke(Color.BLACK);
                pane.getChildren().add(tmpR);
            }
        }


        for (int i = 0 ; i<cList.size();++i){
            Course tmpCourse = cList.get(i);
            int x = 0;
            int y = 0;

            String tmpDay = tmpCourse.getCourseDay();
            if(tmpDay.equals("MONDAY"))
                y = 80 + heightDayBar*0;
            else if(tmpDay.equals("TUESDAY"))
                y = 80 + heightDayBar*1;
            else if(tmpDay.equals("WEDNESDAY"))
                y = 80 + heightDayBar*2;
            else if(tmpDay.equals("THURSDAY"))
                y = 80 + heightDayBar*3;
            else if(tmpDay.equals("FRIDAY"))
                y = 80 + heightDayBar*4;
            else
                System.out.println("The course is not on the wwe day");

            String tmpCode = tmpCourse.getCourseCode();

            for(int j =0; j<tmpCourse.getCourseSection().size();++j){
                ArrayList<Integer> tmpArray = tmpCourse.getCourseSection();

                x = 80 + widthSecBar * (tmpArray.get(j)-1);

                Rectangle tmpR = new Rectangle(x, y,widthSecBar,heighSecBar);

                if(tmpCode.substring(0,4).equals("COMP")){
                    Color rectangleColor = new Color(1, 0, 1, 0.55);
                    tmpR.setFill(rectangleColor);
                    //tmpR.setFill(Color.PURPLE);
                }
                else if (tmpCode.substring(0,2).equals("EE")){
                    Color rectangleColor = new Color(0, 1, 0, 0.55);
                    tmpR.setFill(rectangleColor);
                    //tmpR.setFill(Color.GREEN).9
                }
                else{
                    Color rectangleColor = new Color(1, 1, 0, 0.55);
                    tmpR.setFill(rectangleColor);
                    //tmpR.setFill(Color.ORANGE);
                }
                tmpR.setStroke(Color.BLACK);

                Text myTextCourse = new Text();
                myTextCourse.setX(x+5);
                myTextCourse.setY(y+12);
                myTextCourse.setText(tmpCode);
                pane.getChildren().add(myTextCourse);



                pane.getChildren().add(tmpR);
            }



        }

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday","Friday" };
        for (int i =0; i<5; ++i){
            Text myTextday = new Text();
            myTextday.setX(10);
            myTextday.setY(150 + i * heightDayBar);
            myTextday.setText(days[i]);
            pane.getChildren().add(myTextday);
        }


        Text myTextName = new Text();
        myTextName.setX(80);
        myTextName.setY(60);
        myTextName.setText("Sheducle of"+ n + "");
        pane.getChildren().add(myTextName);


        Scene scene = new Scene(pane, sceneWidth, sceneHeight);
        primaryStage.setTitle("Course Schedule");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();




    }

}