package com.mycompany.papergenerator;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;

public class Createpaper extends Application {
    Label lb,sy,sem,subj,unit,level,mark;
    TextField marks,tf,tf2;
    private String tfs,tfs2,id;
    public String getTfs()
    {
        return tfs;
    }
    public String getTfs2()
    {
        return tfs2;
    }
    Createpaper(String id)
    {
        this.id = id;
        System.out.println(tfs);
    }
    @Override
    public void start(Stage stage) {
        // Title label
        lb = new Label("Create Paper");
        lb.setFont(new Font("Arial", 35));
        lb.setTextFill(Color.BLACK);

        // Year selection label
        sy = new Label("      Select year :-");
        sy.setFont(new Font("Arial", 25));
        sy.setTextFill(Color.BLACK);

        // Semester selection label
        sem = new Label("      Select semester :-");
        sem.setFont(new Font("Arial", 25));
        sem.setTextFill(Color.BLACK);
        
        subj = new Label("      Select subject:-");
        subj.setFont(new Font("Arial", 25));
        subj.setTextFill(Color.BLACK);
        
        unit = new Label("      Select unit:-");
        unit.setFont(new Font("Arial",25));
        unit.setTextFill(Color.BLACK);
        
        Label dash = new Label("                        ");
        dash.setFont(new Font("Arial",25));
        
        level = new Label("     Select level:-");
        level.setFont(new Font("Arial",25));
        level.setTextFill(Color.BLACK);
        
        mark = new Label("     Enter Marks:-");
        mark.setFont(new Font("Arial",25));
        mark.setTextFill(Color.BLACK);
                
        marks = new TextField();
        marks.setPromptText("Enter marks (30 or 70)");
        marks.setPrefHeight(30);
        marks.setPrefWidth(200);
        
        Label titel = new Label("    Ente paper name:-");
        titel.setFont(new Font("Arial",25));
        titel.setTextFill(Color.BLACK);
        
        Label dep = new Label("   Enter College name:-");
        dep.setFont(new Font("Arial",25));
        dep.setTextFill(Color.BLACK);
        
        tf2 = new TextField();
        tf2.setPrefHeight(30);
        tf2.setPrefWidth(200);
        
        tf = new TextField();
        tf.setPrefHeight(30);
        tf.setPrefWidth(200);
        Button gp = new Button("Generate paper");
        Button bp = new Button("Back");
        
        bp.setOnAction(e->{
            try
            {
            Dashboard db = new Dashboard(id);
            db.start(new Stage());
            }
            catch(Exception ep)
            {
                ep.printStackTrace();
            }
        });
        StringBuilder us = new StringBuilder();
        StringBuilder sl = new StringBuilder();
        CheckBox unit1 = new CheckBox("Unit-1");
        CheckBox unit2 = new CheckBox("Unit-2");
        CheckBox unit3 = new CheckBox("Unit-3");
        CheckBox unit4 = new CheckBox("Unit-4");
        CheckBox unit5 = new CheckBox("Unit-5");
        CheckBox unit6 = new CheckBox("Unit-6");
        CheckBox unit7 = new CheckBox("Unit-7");
        CheckBox unit8 = new CheckBox("Unit-8");
        CheckBox easy = new CheckBox("Easy");
        CheckBox medium = new CheckBox("Medium");
        CheckBox hard = new CheckBox("Hard");
        CheckBox[] checkBoxes = {easy,hard,medium};
        
        for (CheckBox cb : checkBoxes) {
            cb.setOnAction(event -> {
                if (cb.isSelected()) {
                    for (CheckBox otherCb : checkBoxes) {
                        if (otherCb != cb) {
                            otherCb.setSelected(false); // Deselect others
                        }
                    }
                }
            });
        }
        // Semester ComboBox
        ComboBox<String> sub = new ComboBox<>();
        sub.setPromptText("Select subject");
        sub.setPrefHeight(30);
        sub.setPrefWidth(200);
       
        ComboBox<String> semes = new ComboBox<>();
        semes.setPromptText("Select semester");
        semes.setPrefHeight(30);
        semes.setPrefWidth(200);

        // Year ComboBox
        ComboBox<String> years = new ComboBox<>();
        years.getItems().addAll("1 year", "2 year", "3 year", "4 year");
        years.setPromptText("Select year");
        years.setPrefHeight(30);
        years.setPrefWidth(200);
      
        Button add = new Button("add");
        add.setTextFill(Color.BLACK);
        add.setStyle("-fx-background-color: black; -fx-text-fill: white");
        add.setOnAction(e->{
            if(unit1.isSelected())
            {
                us.append("1,");
            }
            if(unit2.isSelected())
            {
                us.append("2,");
            }
            if(unit3.isSelected())
            {
                us.append("3,");
            }
            if(unit4.isSelected())
            {
                us.append("4,");
            }
            if(unit5.isSelected())
            {
                us.append("5,");
            }
            if(unit6.isSelected())
            {
                us.append("6,");
            }
            if(unit7.isSelected())
            {
                us.append("7,");
            }
            if(unit8.isSelected())
            {
                us.append("8,");
            }
        });
        // Add action for year selection to update semester options
        years.setOnAction(e -> {
            String selectedYear = years.getValue();
            semes.getItems().clear(); // Clear existing semester options
            if ("1 year".equals(selectedYear)) {
                semes.getItems().addAll("1 semester", "2 semester");
            } else if ("2 year".equals(selectedYear)) {
                semes.getItems().addAll("3 semester", "4 semester");
            } else if ("3 year".equals(selectedYear)) {
                semes.getItems().addAll("5 semester", "6 semester");
            } else if ("4 year".equals(selectedYear)) {
                semes.getItems().addAll("7 semester", "8 semester");
            } 
        });
        // Layout for year selection
        semes.setOnAction(e -> {
           String selectedSemester = semes.getValue();
           sub.getItems().clear(); // Clear existing subject options
    
            if ("1 semester".equals(selectedSemester)) {
                    sub.getItems().addAll("ENGINEERING MATHEMATICS-1","ENGINEERING PHYSICS", "ENGINEERING CHEMISTRY", "COMMUNICATION SKILLS", "MANAGERIAL ECONOMICS AND ACCOUNTING", "INTRODUCTION TO BUILT ENVIRONMENT", "BASIC ELECTRICAL ENGINEERING", "COMPUTER FUNDAMENTALS AND PROGRAMMING", "ELEMENTS OF MECHANICAL ENGINEERING" );
               } else if ("2 semester".equals(selectedSemester)) {
                    sub.getItems().addAll("ENGINEERING MATHEMATICS-1","ENGINEERING PHYSICS-1","ENGINEERING CHEMISTRY-1","COMMUNICATION SKILLS", "MANAGERIAL ECONOMICS AND ACCOUNTING", "INTRODUCTION TO BUILT ENVIRONMENT", "BASIC ELECTRICAL ENGINEERING", "COMPUTER FUNDAMENTALS AND PROGRAMMING", "ELEMENTS OF MECHANICAL ENGINEERING" );
               } else if ("3 semester".equals(selectedSemester)) {
                    sub.getItems().addAll("ADVANCE ENGINEERING MATHEMATICS","DIGITAL ELECTRONICS","DATA STRUCTURE AND ALGORITHMS","OBJECT-ORIENTED PROGRAMMING USING C++","SOFTWARE ENGINEERING","LINUX AND SHELL PROGRAMMING");
               } else if ("4 semester".equals(selectedSemester)) {
                    sub.getItems().addAll("DISCRETE MATHEMATICS","MICROPROCESSOR AND INTERFACING","THEORY OF COMPUTATION","DATABASE MANAGEMENT SYSTEM","INTRODUCTION TO PYTHON PROGRAMMING","INTRODUCTION TO JAVA PROGRAMMING");
               }else if ("5 semester".equals(selectedSemester)) {
                    sub.getItems().addAll("OPERATING SYSTEM","COMPUTER ORGANIZATION AND ARCHITECTURE","COMPUTER NETWORK","CLOUD COMPTING AND DEVOPS","MACHINE LEARNING","HUMAN COMPUTER INTERACTION","DATA SCIENCE","DISTRIBUTED SYSTEM","AUGEMENTED REALITY AND VIRTUAL REALITY","INTRODUCTION TO BLOCKCHAIN","DATA MINING AND WAREHOUSE","COMPILER DESIGN","DESIGN AND ANALYSIS OF ALGO.","INFORMATION SECURITY SYSTEM","DIGITAL IMAGE PROCESSING","INTRODUCTION TO INTERNET OF THING","ARTIFICIAL INTELLIGENCE","BIG DATA ANALYSIS","NATURAL LANGUAGE PROCESSING");
               }else if ("6 semester".equals(selectedSemester)) {
                   sub.getItems().addAll("OPERATING SYSTEM","COMPUTER ORGANIZATION AND ARCHITECTURE","COMPUTER NETWORK","CLOUD COMPTING AND DEVOPS","MACHINE LEARNING","HUMAN COMPUTER INTERACTION","DATA SCIENCE","DISTRIBUTED SYSTEM","AUGEMENTED REALITY AND VIRTUAL REALITY","INTRODUCTION TO BLOCKCHAIN","DATA MINING AND WAREHOUSE","COMPILER DESIGN","DESIGN AND ANALYSIS OF ALGO.","INFORMATION SECURITY SYSTEM","DIGITAL IMAGE PROCESSING","INTRODUCTION TO INTERNET OF THING","ARTIFICIAL INTELLIGENCE","BIG DATA ANALYSIS","NATURAL LANGUAGE PROCESSING");
               }else if ("7 semester".equals(selectedSemester)) {
                    sub.getItems().addAll("","MICROPROCESSOR AND INTERFACING","THEORY OF COMPUTATION","DATABASE MANAGEMENT SYSTEM","INTRODUCTION TO PYTHON PROGRAMMING","INTRODUCTION TO JAVA PROGRAMMING");
               }else if ("8 semester".equals(selectedSemester)) {
                    sub.getItems().addAll("","MICROPROCESSOR AND INTERFACING","THEORY OF COMPUTATION","DATABASE MANAGEMENT SYSTEM","INTRODUCTION TO PYTHON PROGRAMMING","INTRODUCTION TO JAVA PROGRAMMING");
               }
               else {
                   sub.setDisable(true); // Disable subject selection if no subjects available
               }// Enable the subject ComboBox if it has items
               sub.setDisable(sub.getItems().isEmpty());
        });
       /* List<String> ls = new ArrayList<>();
        units.setOnAction(e->{     });*/
       // Add submit button 
     
        gp.setOnAction(event -> {
            String subb = sub.getValue();
            String yea = years.getValue();
            String se = semes.getValue();
            String medi; 
            System.out.println(us);
            // Validate the entered marks
            setString(tf);
            setString2(tf2);
            if(easy.isSelected())
            {
                medi = "easy";
            }
            else if(medium.isSelected())
            {
                medi = "medium";
            }
            else if(hard.isSelected())
            {
                medi = "hard";
            }
            else{
                medi = "null";
            }
            String input = marks.getText().trim();
            if (input.equals("30") || input.equals("70")) {
                // If valid, show a success message
                 CreatedPaper crp = new CreatedPaper(getTfs(),subb,getTfs2(),yea,se,us,medi);
                 crp.start(new Stage());
            } else {// If not valid, show an error message
                showAlert(AlertType.ERROR, "Invalid Input", "Marks must be either 30 or 70.");
            }
            
        });
        
        HBox butn = new HBox(100,bp,gp);
        butn.setAlignment(Pos.CENTER);
        
        HBox tb2 = new HBox(50,dep,tf2);
        tb2.setAlignment(Pos.CENTER_LEFT);
        
        HBox tb = new HBox(50,titel,tf);
        tb.setAlignment(Pos.CENTER_LEFT);
        
        HBox markbox = new HBox(100,mark,marks);
        HBox uniBox = new HBox(30,unit,unit1,unit2,unit3,unit4);
        uniBox.setAlignment(Pos.CENTER_LEFT);
        
        HBox unibox2 = new HBox(30,dash,unit5,unit6,unit7,unit8,add);
        unibox2.setAlignment(Pos.CENTER_LEFT);
        
        HBox yearBox = new HBox(105, sy, years);
        yearBox.setAlignment(Pos.CENTER_LEFT);

        
        HBox levbox = new HBox(50,level,easy,medium,hard);
        levbox.setAlignment(Pos.CENTER_LEFT);
        // Layout for semester selection
        HBox semBox = new HBox(50, sem, semes);
        semBox.setAlignment(Pos.CENTER_LEFT);

        // Layout for subject selection
        HBox subbox = new HBox(78,subj,sub);
        subbox.setAlignment(Pos.CENTER_LEFT);
        // Main layout
        VBox bv = new VBox(30, lb, yearBox, semBox,subbox,uniBox,unibox2,levbox,markbox,tb,tb2,butn);
        bv.setAlignment(Pos.TOP_CENTER);
        bv.setStyle("-fx-background-color: white");
        // Scene setup
        Scene sc = new Scene(bv, 700, 700);
        stage.setScene(sc);
        stage.setTitle("Paper Generator");
        stage.show();
    }
    public void setString(TextField tf)
    {
        tfs = tf.getText();
    }
    public void setString2(TextField tf)
    {
        tfs2 = tf.getText();
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
