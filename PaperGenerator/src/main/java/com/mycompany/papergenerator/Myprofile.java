
package com.mycompany.papergenerator;

import javafx.application.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.sql.*;
import javafx.stage.Stage;

public class Myprofile extends Application{
    String Pass;
    private String id,username,email,nums,passw;
    Myprofile(String id)
    {
        this.id = id;
    }
    @Override
    public void start(Stage stage)
    {   String username,passw,email,nums;
        try{
        Conn c = new Conn();
        String query1 = "select * from users where id = '"+id+"'";
        ResultSet rs = c.s.executeQuery(query1);
        if(rs.next())
        {
            username = rs.getString("username");
            email = rs.getString("email");
            nums = rs.getString("number");
            passw = rs.getString("pass");
            setthis(username,email,passw,nums);
        }
        else{
            System.out.println("not found");
            
        }
       }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                
        Label prof = new Label("My Profile");
        Font pf = Font.font("Brush Script Mt",FontWeight.BLACK,FontPosture.ITALIC,50);
        prof.setFont(pf);
        prof.setTextFill(Color.BLACK);
        
        Label fnamee = new Label("Username:-  ");
        Font nf = Font.font("Brush Script Mt",FontWeight.BLACK,FontPosture.ITALIC,50);
        fnamee.setFont(nf);
        fnamee.setTextFill(Color.BLACK);
        
        TextField nft = new TextField(getname());
        nft.setPrefHeight(50);
        nft.setPrefWidth(200);
        
        Label lnamee = new Label("Email id:- ");
        Font lnf = Font.font("Brush Script Mt",FontWeight.BLACK,FontPosture.ITALIC,50);
        lnamee.setFont(lnf);
        lnamee.setTextFill(Color.BLACK);
        
        TextField lnft = new TextField(getemail());
        lnft.setPrefHeight(50);
        lnft.setPrefWidth(200);
        
        Label num = new Label("Number:-");
        Font nuf = Font.font("Brush Script Mt",FontWeight.BOLD,FontPosture.ITALIC,50);
        num.setFont(nuf);
        num.setTextFill(Color.BLACK);
        
        TextField ntf = new TextField(getnum());
        ntf.setPrefHeight(50);
        ntf.setPrefWidth(200);
        
        Label pas = new Label("Password:-");
        Font pasf = Font.font("Brush Script Mt",FontWeight.BOLD,FontPosture.ITALIC,50);
        pas.setFont(pasf);
        pas.setTextFill(Color.BLACK);
        
        try{
           Signup su = new Signup();
           Pass = su.generatedPassword;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        TextField past = new TextField(getpass());
        past.setPrefHeight(50);
        past.setPrefWidth(200);
        
        Button up = new Button("Update");
        up.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black");
        up.setPrefHeight(50);
        up.setPrefWidth(100);
        up.setOnAction(e->{
            String nam = nft.getText();
            String ema = lnft.getText();
            String nu = ntf.getText();
            String pasn = past.getText();
            try{
                Conn cn = new Conn();
                String que = "UPDATE users SET username = '" + nam + "', email = '" + ema + "', number = '" + nu + "', pass = '" + pasn + "' WHERE id = '"+id+"'";
                cn.s.executeUpdate(que); 
                showAlert(Alert.AlertType.INFORMATION,"U have updated","updated profile sucessfully");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        
        Button back = new Button("back");
        back.setStyle("-fx-background-color: black; -fx-text-fill: white");
        back.setPrefHeight(50);
        back.setPrefWidth(100);
        back.setOnAction(e->{
            try{
                Dashboard db1 = new Dashboard(id);
                db1.start(new Stage());
            }
            catch(Exception ev)
            {
                ev.printStackTrace();
            }
        });
        
        HBox hb1 = new HBox(30,fnamee,nft);
        HBox hb2 = new HBox(50,lnamee,lnft);
        HBox hb3 = new HBox(90,num,ntf);
        HBox hb4 = new HBox(50,pas,past);
        HBox hb5 = new HBox(50,back,up);
        hb5.setAlignment(Pos.CENTER);
        
        VBox vb = new VBox(20,prof,hb1,hb2,hb3,hb4,hb5);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.setPadding(new Insets(20));
        
        Image backgr = new Image("https://media.istockphoto.com/id/1305905498/vector/gray-and-white-diagonal-line-architecture-geometry-tech-abstract-subtle-background-vector.jpg?s=612x612&w=0&k=20&c=n_Dfoaoz9905EFEF91sW_FnKN1yOYD8AnL-AnXwIXoI=");
        BackgroundImage bg = new BackgroundImage(backgr,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true));
        
        BorderPane bp1 = new BorderPane(vb);
        bp1.setBackground(new Background(bg));
        
        Scene sc = new Scene(bp1,600,700);
        stage.setScene(sc);
      
        stage.show();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void setthis(String a,String b,String c,String d)
    {
            this.username = a;
            this.email = b;
            this.passw = c;
            this.nums = d;
    }
    private String getname()
    {
        return username;
    }
    private String getemail()
    {
        return email;
    }
    private String getnum()
    {
        return nums;
    }
    private String getpass()
    {
        return passw;
    }
    public static void main(String args[])
    {
        launch();
    }
}
