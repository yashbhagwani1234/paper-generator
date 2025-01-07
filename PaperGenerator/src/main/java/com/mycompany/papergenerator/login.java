
package com.mycompany.papergenerator;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.application.Platform;
import javafx.stage.*;
import java.sql.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class login extends Application{
    private String myid;
    
    @Override
    public void start(Stage stage){
       stage.initStyle(StageStyle.UNDECORATED);
       Label title = new Label("Login Your Account");
       title.setFont(new Font("Arial",32));
       title.setTextFill(Color.BLACK);
       
       Label name = new Label("Username:-");
       name.setFont(new Font("Arial",32));
       name.setTextFill(Color.BLACK);
       
       
       TextField tf1 = new TextField();
       tf1.setPrefHeight(50);
       tf1.setPrefWidth(200);
       
       Label pass = new Label("Password:-");
       pass.setFont(new Font("Arial",32));
       pass.setTextFill(Color.BLACK);
       
       
       TextField tf2 = new TextField();
       tf2.setPrefHeight(50);
       tf2.setPrefWidth(200);
       
       Button cb1 = new Button("Back");
       cb1.setStyle("-fx-font-size: 18px; -fx-background-color: white; -fx-text-fill: black");
       cb1.setOnAction(e->Platform.exit());
       cb1.setOnAction(e->{
           try{
               Signup sp = new Signup();
               sp.start(new Stage());
           }
           catch(Exception es)
           {
               es.printStackTrace();
           }
       });
       
       Button lb = new Button("Login");
       lb.setStyle("-fx-font-size: 18px; -fx-background-color: skyblue; -fx-text-fill: white");
       lb.setOnAction(e->{
           
           try{       
               String usern = tf1.getText();
               String passw = tf2.getText();
               Conn cn = new Conn();
               String query1 = "select username from users where username = '"+usern+"'";
               ResultSet rs = cn.s.executeQuery(query1);
               String getuser;
               if(rs.next())
               {
                   getuser = rs.getString("username");
                   String query2 = "select pass from users where username = '"+getuser+"'";
                   ResultSet rs2 = cn.s.executeQuery(query2);
                   
                   if(rs2.next())
                   {
                       String password = rs2.getString("pass");
                       if(passw.equals(password))
                       {
                           String query3 = "select id from users where pass = '"+passw+"'";
                           ResultSet rs3 = cn.s.executeQuery(query3);
                           if(rs3.next())
                           {
                               String useid = rs3.getString("id");
                               String query = "INSERT INTO login (id,l_usern,l_pass,type) VALUES ('"+useid+"', '"+usern+"', '"+passw+"', 'enable')";
                               cn.s.executeUpdate(query);
                               setId(useid);
                               Dashboard db = new Dashboard(useid);
                               db.start(new Stage());
                           }
                           
                       }
                       else{
                           showAlert(AlertType.ERROR,"Wrong password","Enter correct password");
                       }
                   }
                   else
                   {
                       showAlert(AlertType.ERROR,"Wrong password","Enter correct password");
                   }
               }
               else{
                   showAlert(AlertType.ERROR,"Invalid Username","Username doesn't exits");
               }
           }
           catch(Exception ev)
           {
               ev.printStackTrace();
           }
       });
       
       HBox hb1 = new HBox(30,name,tf1);
       HBox hb2 = new HBox(30,pass,tf2);
       HBox hb3 = new HBox(30,cb1,lb);
       hb3.setAlignment(Pos.CENTER);
       
       VBox ml1 = new VBox(70,title,hb1,hb2,hb3);
       ml1.setAlignment(Pos.TOP_CENTER);
       ml1.setPadding(new Insets(20));
       
       Image backg = new Image("https://t3.ftcdn.net/jpg/04/41/24/22/360_F_441242293_SyF6LMbnSenWabN91c7jbezbNRvw6k5h.jpg");
       BackgroundImage bc = new BackgroundImage(
        backg,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true)
       );
       BorderPane bp = new BorderPane(ml1);
       bp.setBackground(new Background(bc));
       
       Scene scene = new Scene(bp,600,700);
       stage.setScene(scene);
       stage.setTitle("Login page");
       stage.show();
    }
    public void setId(String id)
    {
        this.myid = id;
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String args[])
    {
        launch();
    }
}
