package com.company;

import java.util.Arrays;

public class StudentRegister{
    private String name; // stores name of the each student
    private String surname;
    private int[] grade; // stores gradesof the each student
    private long id;
    private String department;

    public String getName() {return this.name;} // returns the name
    public String getSurname() {return this.surname;}
    public int[] getGrade() {return this.grade;}
    public int getGrade(int index) {return this.grade[index];}
    public long getId() {return this.id;}
    public String getDepartment() {return this.department;}

    public int totalGrade() {
        int result = 0;
        for (int i : grade)
            result += i;
        return result;
    };
    public int getTotal() {return this.totalGrade();}

    public void setName(String temp) {name = temp;} // puts a name for the student
    public void setSurname(String temp) {surname = temp;}
    public void setId(long temp){id = temp;}
    public void setDepartment(String temp){department = temp;}

    public void setGrade(int g[]){
        this.grade = g;
    }    // puts grade of the student
    public void setGrade(int g){
        // this function has an assumption of a student can't take a negative grade
        if (grade[2]!=-1)
            System.out.println("Son girilen not islenmedi");

        for (int i=0;i<3;++i)
            if(grade[i]==-1){
                grade[i]=g;
                break;
            }
    }
    public void setGrade(int g, int index){
        grade[index - 1] = g;
    }
    public void printInfo(){
        //System.out.printf("Student\nname: %s\nID: %l\nDeaprtment: %s\nGrades: " , name,id,department);
        System.out.println("Student: " + name + " " + surname);
        System.out.println("Id: " + id);
        System.out.println("Department: " + department);
        System.out.print("Grades: ");
        System.out.println(Arrays.toString(grade));
    }


    public StudentRegister(){
        /*
        name = "Unknown";
        for (int i = 1;i<3;++i)
            grade[i]=-1;
        */
    }
    public StudentRegister(String n){
        name = n;
        for (int i = 1;i<3;++i)
            grade[i]=-1;
    }
    public StudentRegister(String n,int[] g){
        name = n;
        grade = g;
    }
    public StudentRegister(int[] g){
        grade = g;
    }
}

