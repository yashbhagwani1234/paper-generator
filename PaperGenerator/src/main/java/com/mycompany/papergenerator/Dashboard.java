package com.mycompany.papergenerator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dashboard extends Application {
    private String id;
    Image setting,menu,imgcreate,papers,que,syl,pro,subs,refer,report;
    ImageView sv,mv,icv,pv,quev,sylv,prov,subsv,refv,repv;
    Button sb,mb,tutButton,buyButton,cbt,pbt,syb,queb,prob,sub,refb,erb;
    Dashboard()
    {
        
    }
    Dashboard(String id){
        this.id = id;
    }
    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        // Create Label with custom font
        Label lb = new Label("Exam Paper Generator");
        lb.setFont(new Font("Arial",25));
        lb.setTextFill(Color.SKYBLUE);

        // Image for the settings icon
        setting = new Image("https://cdn-icons-png.freepik.com/256/1103/1103621.png?semt=ais_hybrid");
        sv = new ImageView(setting);
        navImage(sv);

        menu = new Image("https://cdn-icons-png.flaticon.com/512/4408/4408847.png");
        mv = new ImageView(menu);
        navImage(mv);

        imgcreate = new Image("https://cdn0.iconfinder.com/data/icons/outline-ui-2/120/Untitled-21-512.png");
        icv = new ImageView(imgcreate);
        creatImage(icv);

         papers = new Image("https://cdn-icons-png.freepik.com/256/6776/6776662.png?semt=ais_hybrid");
         pv = new ImageView(papers);
        creatImage(pv);
        
        que = new Image("https://cdn-icons-png.flaticon.com/512/4542/4542505.png");
        quev = new ImageView(que);
        creatImage(quev);
        
        syl = new Image("https://cdn-icons-png.flaticon.com/512/4231/4231825.png");
        sylv = new ImageView(syl);
        creatImage(sylv);
        
        pro = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6yz5e_CGu9k0RBMqBsMvMeC-CSwkJ3O5Q3w&s");
        prov = new ImageView(pro);
        creatImage(prov);
        
        subs = new Image("https://cdn0.iconfinder.com/data/icons/shopping-55/64/untitled_folder_Subscription-512.png");
        subsv = new ImageView(subs);
        creatImage(subsv);
        
        refer = new Image("https://static.vecteezy.com/system/resources/previews/023/346/461/non_2x/marketing-affiliate-icon-vector.jpg");
        refv = new ImageView(refer);
        creatImage(refv);

        report = new Image("https://icons.veryicon.com/png/o/miscellaneous/patrol-system-icon/data-query-2.png");
        repv = new ImageView(report);
        creatImage(repv);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        sb = new Button("", sv);
        sb.setOnAction(e -> System.out.println("setting button clicked"));

        mb = new Button("", mv);
        mb.setOnAction(e -> System.out.println("menu button clicked"));

        HBox nav = new HBox(10, lb, spacer, sb, mb);
        nav.setStyle("-fx-background-color: #00008B; -fx-padding: 10;");

        Text title = new Text("Generate New Question Paper");
        title.setFont(Font.font("Arial", 24));
        title.setFill(Color.BLACK);

        Text title2 = new Text("Generate Question Papers");
        title2.setFont(Font.font("Arial", 24));
        title2.setFill(Color.BLACK);
        
        Text title3 = new Text("My Account");
        title3.setFont(Font.font("Arial",24));
        title3.setFill(Color.BLACK);

       
        tutButton = new Button("Tutorials");
        buyButton = new Button("Buy");

        
        styleButton(tutButton);
        styleButton(buyButton);

        cbt = new Button("CREATE PAPER");
        cbt.setPrefWidth(100);
        cbt.setPrefHeight(50);
        cbt.setStyle("-fx-background-color: skyblue; -fx-text-fill: black; -fx-background-radius: 50");
        cbt.setOnAction(e->{
            try{
                Createpaper cp = new Createpaper(id);
                cp.start(new Stage());
            }
            catch(Exception ep)
            {
                ep.printStackTrace();
            }
        });

        pbt = new Button("PAPERS");
        pbt.setPrefWidth(100);
        pbt.setPrefHeight(50);
        pbt.setStyle("-fx-background-color: skyblue; -fx-text-fill: black; -fx-background-radius: 50");
        
        syb = new Button("SYLLABUS");
        syb.setPrefHeight(50);
        syb.setPrefWidth(100);
        syb.setStyle("-fx-background-color: skyblue; -fx-text-fill: black; -fx-background-radius: 50");
        
        queb = new Button("QUESTIONS");
        queb.setPrefHeight(50);
        queb.setPrefWidth(100);
        queb.setStyle("-fx-background-color: skyblue; -fx-text-fill: black; -fx-background-radius: 50");
        
        prob = new Button("MY Profile");
        prob.setPrefHeight(50);
        prob.setPrefWidth(100);
        prob.setStyle("-fx-background-color: skyblue; -fx-text-fill: black; -fx-background-radius: 50");
 
        sub = new Button("SUBSCRIPTION");
        sub.setPrefHeight(50);
        sub.setPrefWidth(100);
        prob.setStyle("-fx-background-color: skyblue; -fx-text-fill: black; -fx-background-radius: 50");
        
        refb = new Button("Refer and Earn");
        refb.setPrefHeight(50);
        refb.setPrefWidth(100);
        refb.setStyle("-fx-background-color: skyblue; -fx-text-fill: black; -fx-background-radius: 50");
        
        erb = new Button("Error Report");
        erb.setPrefHeight(50);
        erb.setPrefWidth(100);
        erb.setStyle("-fx-background-color: skyblue; -fx-text-fill: black");
        
        prob.setOnAction(e-> {
            try{
                Myprofile mp = new Myprofile(id);
                mp.start(new Stage());
            }
            catch(Exception ev)
            {
                ev.printStackTrace();
            }
        });
        HBox hb3 = new HBox(80, cbt, pbt);
        HBox hb4 = new HBox(70,sylv,quev);
        HBox hb5 = new HBox(80, syb,queb);
        HBox hb6 = new HBox(70,prov,subsv);
        HBox hb7 = new HBox(80,prob,sub);
        HBox hb8 = new HBox(70,refv,repv);
        HBox hb9 = new HBox(80,refb,erb);

        HBox buttonBox = new HBox(50, tutButton, buyButton);
        buttonBox.setAlignment(Pos.CENTER);

        HBox hb2 = new HBox(70, icv, pv);

        VBox mainLayout = new VBox(20, title, buttonBox);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: AB420A");

        VBox mainLayout2 = new VBox(20, title2, hb2, hb3,hb4,hb5);
        mainLayout2.setPadding(new Insets(20));
        mainLayout2.setStyle("-fx-background-color: white");

        VBox mainLayout3 = new VBox(20, title3,hb6,hb7,hb8,hb9);
        mainLayout3.setPadding(new Insets(20));
        mainLayout3.setStyle("-fx-background-color: white");
        
        VBox topSection = new VBox(nav, mainLayout, mainLayout2,mainLayout3);
        topSection.setSpacing(0);

        // Wrap VBox in a ScrollPane and force the vertical scroll bar to always show
        ScrollPane scrollPane = new ScrollPane(topSection);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Force vertical scrollbar always visible

       
        BorderPane bp = new BorderPane();
        bp.setCenter(scrollPane);
    

        /*Image backgroundImage = new Image("https://t4.ftcdn.net/jpg/03/41/16/09/360_F_341160981_CFBrQpnDWWuMZqTpDPvOkHcdZeEikeXm.jpg",true);
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        bp.setBackground(new Background(background));*/
        
        
        Scene scene = new Scene(bp, 600, 700);
        stage.setScene(scene);
        stage.setTitle("Exam Paper Generator Dashboard");
        stage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-border-color: #4CAF50; -fx-border-width: 2; -fx-text-fill: #4CAF50; -fx-padding: 10 20; -fx-font-size: 16; -fx-background-color: white; -fx-background-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 16; -fx-background-color: #4CAF50; -fx-background-radius: 5;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-border-color: #4CAF50; -fx-border-width: 2; -fx-text-fill: #4CAF50; -fx-padding: 10 20; -fx-font-size: 16; -fx-background-color: white; -fx-background-radius: 5;"));
    }

    private void navImage(ImageView iv) {
        iv.setFitWidth(20);
        iv.setFitHeight(60);
        iv.setPreserveRatio(true);
    }
    private void creatImage(ImageView cv)
    {
        cv.setFitWidth(100);
        cv.setFitHeight(100);
        cv.setPreserveRatio(true);
    }
    public static void main(String[] args) {
        launch();
    }
}
