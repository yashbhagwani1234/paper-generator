package com.mycompany.papergenerator;

import javafx.scene.layout.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import java.security.SecureRandom;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.Desktop;
import java.net.URI;
import javafx.application.Platform;
import javafx.stage.StageStyle;

public class Signup extends Application {
    String generatedPassword;
    public Signup()
    {
        generatedPassword = generateRandomPassword(6); 
    }
    //TextField ut,ut2,ut3,ut4;
    
    @Override
    public void start(Stage stage) {
        // Title label
        stage.initStyle(StageStyle.UNDECORATED);
        Label text = new Label("Signup your account");
        text.setFont(new Font("Arial",32));
        text.setTextFill(Color.BLACK);

        Label un = new Label("   Username:-");
        un.setFont(new Font("Arial",32));
        un.setTextFill(Color.BLACK);
        
        
        Label un2 = new Label("   Email- id:-   ");
        un2.setFont(new Font("Arial",32));
        un2.setTextFill(Color.BLACK);
        
        TextField ut = new TextField("");
        ut.setPrefHeight(50);
        ut.setPrefWidth(200);
        
       TextField ut2 = new TextField("");
        ut2.setPrefHeight(50);
        ut2.setPrefWidth(200);
        
        Label un3 = new Label("   Number:-");
        un3.setFont(new Font("Arial",32));
        un3.setTextFill(Color.BLACK);
        
        TextField ut3 = new TextField();
        ut3.setPrefHeight(50);
        ut3.setPrefWidth(200);
        
        Label un4 = new Label("   Password:- ");
        un4.setFont(new Font("Arial",32));
        un4.setTextFill(Color.BLACK);
        
        PasswordField passwordField = new PasswordField();
        TextField ut4 = new TextField();
        ut4.setVisible(false);  // Initially hidden
        ut4.setManaged(false);
        
        passwordField.setPrefHeight(50);
        passwordField.setPrefWidth(200);
        ut4.setPrefHeight(50);
        ut4.setPrefWidth(200);
        
        //generatedPassword = generateRandomPassword(6); // Generate a password
        passwordField.setText(generatedPassword);
        ut4.setText(generatedPassword);
        
        Button showButton = new Button("Show");
        showButton.setStyle("-fx-font-size: 18px; -fx-background-color: gold; -fx-text-fill: black;");
        showButton.setOnAction(event -> {
        if (passwordField.isVisible()) {
        // Show password
        passwordField.setVisible(false);
        passwordField.setManaged(false);
        ut4.setVisible(true);
        ut4.setManaged(true);
        showButton.setText("Hide");
       } else {
        // Hide password
        passwordField.setVisible(true);
        passwordField.setManaged(true);
        ut4.setVisible(false);
        ut4.setManaged(false);
        showButton.setText("Show");
      }
});
       
        HBox hbt = new  HBox(30,un,ut);
        HBox hbt2 = new HBox(30,un2,ut2);
        HBox hbt3 = new HBox(70,un3,ut3);
        // "Sign in with Google" button
        HBox hbt4 = new HBox(30,un4,passwordField,ut4,showButton);
        Button sb = new Button("Sign In");
        sb.setStyle("-fx-font-size: 18px; -fx-background-color: white; -fx-text-fill: black");
        System.out.println("t3 text = "+ut3.getText());
        System.out.println(ut2.getText());
        sb.setOnAction(e->{
                 String username = ut.getText();
                 boolean checkuser = checkUsername(username);
                 String email = ut2.getText();
                 String number = ut3.getText();
                 boolean checknum = checkNum(number);
                 String pass = generatedPassword;
                if(email.endsWith("@gmail.com")&& (number.length() == 10) && (!number.isEmpty()) && username.length()<40 && email.length()<25 && checkuser == true && checknum == true){
                try{
                 
                 Dashboard db = new Dashboard();
                 db.start(new Stage());
                 Conn n = new Conn();
                 
                 String query = "INSERT INTO Users (username, email, number, pass) VALUES ('"+username+"', '"+email+"', '"+number+"', '"+pass+"')";
                 n.s.executeUpdate(query);
                 StackPane sp = new StackPane();
                 sp.setVisible(false);
                }
                catch(Exception ep)
                {
                    ep.printStackTrace();
                }
            }
            else if(ut2.getText().length()>25 || ut.getText().length()>20)
            {
                showAlert(AlertType.ERROR,"Invalid input","input is to long");
            }
            else if(ut3.getText().isEmpty())
            {
                showAlert(AlertType.ERROR,"Invalid Input ","Can't put username blank");
            }
            else if(checkuser == false )
            {
                showAlert(AlertType.ERROR,"Invalid  username","please enter username in alphabatic form");
            }
            else if(checknum == false)
            {
                showAlert(AlertType.ERROR,"Invalid number","please enter number in number format");
            }
            else{
                showAlert(AlertType.ERROR,"Invalid Input of number or Email","Enter Correct email and number");
            }
        });
        System.out.println(ut.getText());
        Button cb = new Button("Cancel");
        cb.setStyle("-fx-font-size: 18px; -fx-background-color: white; -fx-text-fill: black");
        cb.setOnAction(e->Platform.exit());
        
        Button lb = new Button("Login");
        lb.setStyle("-fx-font-size: 18px; -fx-background-color: skyblue; -fx-text-fill: white");
        lb.setOnAction(e->{
          try{
              login lg = new login();
              lg.start(new Stage());
          }
          catch(Exception ev)
          {
              ev.printStackTrace();
          } 
        });
        
        HBox hbt5 = new HBox(100,cb,lb,sb);
        hbt5.setAlignment(Pos.CENTER);
        
        Button googleSignInButton = new Button("Sign in with Google");
        googleSignInButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4285F4; -fx-text-fill: white;");
        
        googleSignInButton.setOnAction(event -> {
            try {
                // Open the Google OAuth URL in the default browser
                String authUrl = "https://accounts.google.com/o/oauth2/v2/auth?" +
                                 "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile%20email&" +
                                 "redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcallback&" +
                                 "response_type=code&" +
                                 "client_id=YOUR_CLIENT_ID";
                Desktop.getDesktop().browse(new URI(authUrl));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox ml = new VBox(40, text,hbt,hbt2,hbt3,hbt4,hbt5, googleSignInButton);
        ml.setAlignment(Pos.TOP_CENTER);
        ml.setPadding(new Insets(20));

        // Background image
        Image backgroundImage = new Image("https://images.unsplash.com/photo-1579547621706-1a9c79d5c9f1?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Z3JhZGllbnR8ZW58MHx8MHx8fDA%3D");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        BorderPane bp = new BorderPane(ml);
        bp.setBackground(new Background(background));

        // Scene setup
        Scene sc = new Scene(bp, 600, 700);
        stage.setScene(sc);
        stage.setTitle("Signup Page");
        stage.show();
    }
    
    public static String generateRandomPassword(int length) {
         String chars  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
         SecureRandom random = new SecureRandom();
         StringBuilder Password = new StringBuilder();
         for(int i =0; i<length; i++)
         {
             Password.append(chars.charAt(random.nextInt(chars.length())));
         }
         return Password.toString();
    }
    private boolean checkUsername(String user)
    {
        for(int i=0; i<user.length(); i++)
        {
            if(user.charAt(i)=='1' ||user.charAt(i)=='2' ||user.charAt(i)=='3' ||user.charAt(i)=='4' ||user.charAt(i)=='5' ||user.charAt(i)=='6' ||user.charAt(i)=='7' ||user.charAt(i)=='8' ||user.charAt(i)=='9')
            {
                return false;
            }  
        }
        return true;
    }
    private boolean checkNum(String num)
    {
        for(int i=0; i<num.length(); i++)
        {
            if(num.charAt(i) >='A' && num.charAt(i)<='z')
            {
                return false;
            }
        }
        return true;
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String args[]) {
        launch();
    }
}
