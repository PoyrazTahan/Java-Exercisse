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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 *  @author Doga Poyraz TAHAN
 *  @since 12/03/2018
 *  With a given text file that contains the name and the grades.
 *  This class will display a bar chart of the students in a sorted way
 *
 */
public class AppStudentGrades extends Application{


    /**
     * Reads the text file written down
     */
    public void start(Stage primaryStage) throws FileNotFoundException {

        // create file object
        File file = new File("student_info4.txt"); // sets the file at hand as object

        // if file is not found, report and exit program
        if (!file.exists()) {
            // if couldn't finde the file Exit
            System.out.println("File can not be found! Exiting program...");
            System.exit(1);
        }

        // open a scanner object to read from file
        BufferedReader reader = new BufferedReader(new FileReader(file)); // line by line reading
        String str;
        ArrayList<StudentRegister> stdList = new ArrayList<StudentRegister>();


        try {

            str = reader.readLine();
            // writes to the console but the program doesn't use this line
            System.out.println(str);

            while((str = reader.readLine()) != null) {
                // while there is something to read
                System.out.println(str); // prints the line at hand // for debugging
                //split the line by space as seperator
                String[] line = str.split(" ");


                int[] tmpGrades = new int[line.length - 1]; // create an array with -1 the length of line array since one of them is name

                for (int i = 1; i < line.length; i++)
                    tmpGrades[i-1] = Integer.parseInt(line[i]); // parses int from string and enters it tp temp Grades


                StudentRegister student = new StudentRegister(line[0], tmpGrades); // constructs a student with its name and grades

                stdList.add(student);


            }
            //closing the reader
            reader.close();
        }
        catch(Exception ex) {
            //if it finds an error it will throw and error mesage
            System.err.println(ex.getMessage());
        }

        // sort the list with the method
        sortStudents(stdList);
        // create a GUI upon set info.
        showInfo(primaryStage, stdList);
    }


    /**
     * This function is used for creating a Bar Screen for the Students Grade data
     *
     * @param primaryStage
     * @param students
     * @since 11.03.2018
     *
     */
    private void showInfo(Stage primaryStage, ArrayList<StudentRegister> students){

        int nStudent = students.size();

        int widthStdBar = 800;
        int heightStdBar = 500/nStudent;


        int sceneWidth = widthStdBar;
        int sceneHeight = heightStdBar * students.size();
        Pane pane = new Pane();

        for (int j = 0; j < students.size(); ++j ) {
            // plot students grades
            int scaleFactor = 6;
            int w;
            int h = 400/nStudent;
            int x = 100;
            int y = 50 + j * h;

            // create a fence rechtangle
            Rectangle r0 = new Rectangle(x, y, 100 * scaleFactor, h);
            r0.setFill(null);
            r0.setStroke(Color.BLACK);
            pane.getChildren().add(r0);

            //create a text for the name
            Text myTextName = new Text();
            myTextName.setX(x - 80);
            myTextName.setY(y + h/2);
            myTextName.setText(students.get(j).getName() + "");
            pane.getChildren().add(myTextName);

            // create a text for the total grade
            Text myTextTotal = new Text();
            myTextTotal.setX( x +  100 * scaleFactor + 3);
            myTextTotal.setY(y + h/2);
            myTextTotal.setText(students.get(j).getTotal() + "");
            pane.getChildren().add(myTextTotal);

            for (int i = 0; i < students.get(0).getGrade().length; ++i) {



                Rectangle rec = new Rectangle(x, y, students.get(j).getGrade(i) * scaleFactor, h);

                //random color
                Random generator = new Random();
                double r = generator.nextDouble();
                double g = generator.nextDouble();
                double b = generator.nextDouble();
                Color rectangleColor = new Color(r, g, b, 0.5);
                // end of random color

                rec.setFill(rectangleColor);
                rec.setStroke(Color.BLUE);
                pane.getChildren().add(rec);

                //text for the each grade in to the rectangle
                Text myTextGrades = new Text();
                myTextGrades.setFont(Font.font("verdana", FontWeight.THIN, FontPosture.REGULAR, 10));
                myTextGrades.setX(x + 4);
                myTextGrades.setY(y + 12);
                myTextGrades.setText(students.get(j).getGrade(i) + "");
                pane.getChildren().add(myTextGrades);


                // set your new x after the set panel of one grade
                x = x + students.get(j).getGrade(i) * scaleFactor;
            }
        }
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, sceneWidth, sceneHeight);
        primaryStage.setTitle("JavaFX Student Application"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false);
        primaryStage.show(); // Display the stage
    }

    /**
     * This function sorts the given arrayList of StudentRegister class
     *
     * @param students is the list of students to print
     */
    private void sortStudents(ArrayList<StudentRegister> students){

        for(int i = 0; i < students.size(); ++i){
            int min = i;
            for (int j= i; j < students.size(); ++j){
                if(students.get(min).getTotal() > students.get(j).getTotal())
                    min = j;
            }
            if (i != min) {
                StudentRegister tmpStd = students.get(min);
                students.set(min, students.get(i));
                students.set(i, tmpStd);
            }
        }
    }



    /**
     * This Program reads the text file with the student name and any number of grades.
     * This Program needs in its text files One Stirng of name and then any number of grades for the student.
     * But Each student needs to have a consistent number of Grades.
     * You can put as many student as you like in a text file.
     *
     */
    public static void main(String[] args) {
        launch(args);
    }
}


