package com.mycompany.papergenerator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreatedPaper extends Application {
    private String pn, subj, cname, year, sem, medi;
    private StringBuilder us;

    public CreatedPaper(String pn, String subj, String cname, String year, String sem, StringBuilder us, String medi) {
        this.pn = pn;
        this.subj = subj;
        this.cname = cname;
        this.sem = sem;
        this.year = year;
        this.us = us;
        this.medi = medi;
    }

    @Override
    public void start(Stage stage) {
        Label coname = createLabel(cname);
        Label dis = createLabel("BTech " + year + " (" + sem + ") 2024");
        Label pan = createLabel(pn);
        Label subjs = createLabel("Subject: " + subj);

        Label part1 = createSectionLabel("Part - 1: Word limit 20 words (Attempt all) 2 marks questions");
        Label part2 = createSectionLabel("Part - 2: Word limit 60 words (Attempt any two) 4 marks questions");
        Label part3 = createSectionLabel("Part - 3: Word limit 120 words (Attempt any two) 6 marks questions");

        // Fetching questions dynamically
        List<Label> part1Questions = fetchQuestions(2, 5);
        List<Label> part2Questions = fetchQuestions(4, 4);
        List<Label> part3Questions = fetchQuestions(6, 3);

        Button exportButton = new Button("Export to PDF");
        exportButton.setOnAction(e -> exportToPdf(coname.getText(), dis.getText(), pan.getText(), subjs.getText(),
                part1.getText(), part2.getText(), part3.getText(),
                part1Questions, part2Questions, part3Questions));

        VBox vb = new VBox(10);
        vb.getChildren().addAll(coname, dis, subjs, pan, part1);
        vb.getChildren().addAll(part1Questions);
        vb.getChildren().add(part2);
        vb.getChildren().addAll(part2Questions);
        vb.getChildren().add(part3);
        vb.getChildren().addAll(part3Questions);
        vb.getChildren().add(exportButton);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.setPadding(new Insets(10));

        Scene sc = new Scene(vb, 900, 800);
        stage.setScene(sc);
        stage.show();
    }

    private void exportToPdf(String coname, String dis, String pan, String subjs,
                             String part1, String part2, String part3,
                             List<Label> part1Questions, List<Label> part2Questions, List<Label> part3Questions) {
        try {
            String dest = "GeneratedPaper.pdf";
            PdfWriter writer = new PdfWriter(new FileOutputStream(dest));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(coname).setFontSize(18).setBold());
            document.add(new Paragraph(dis).setFontSize(14));
            document.add(new Paragraph(pan).setFontSize(14));
            document.add(new Paragraph(subjs).setFontSize(14));
            document.add(new Paragraph(part1).setFontSize(16).setBold());

            for (Label label : part1Questions) {
                document.add(new Paragraph(label.getText()).setFontSize(12));
            }

            document.add(new Paragraph(part2).setFontSize(16).setBold());
            for (Label label : part2Questions) {
                document.add(new Paragraph(label.getText()).setFontSize(12));
            }

            document.add(new Paragraph(part3).setFontSize(16).setBold());
            for (Label label : part3Questions) {
                document.add(new Paragraph(label.getText()).setFontSize(12));
            }

            document.close();
            System.out.println("PDF created: " + new File(dest).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Label> fetchQuestions(int marks, int limit) {
        List<Label> questions = new ArrayList<>();
        try {
            Conn c = new Conn();
            if (us.length() > 0 && us.charAt(us.length() - 1) == ',') {
                us.deleteCharAt(us.length() - 1);
            }

            if (us.length() == 0) {
                System.err.println("Error: No units specified.");
                throw new IllegalArgumentException("Units cannot be empty.");
            }

            String formattedUnits = us.toString().trim();
            String query = "SELECT question_text FROM questions WHERE unit IN (" + formattedUnits + ") " +
                           "AND marks = " + marks + " " +
                           "AND type_of_question = '" + medi + "' " +
                           "AND type = 'descriptive' " +
                           "ORDER BY RAND() LIMIT " + limit;

            ResultSet rs = c.s.executeQuery(query);

            int count = 1;
            while (rs.next()) {
                String questionText = rs.getString("question_text");
                Label questionLabel = new Label("   " + count + ". " + questionText);
                questionLabel.setFont(new Font("Arial", 15));
                questionLabel.setPadding(new Insets(5, 0, 5, 10));
                questionLabel.setStyle("-fx-alignment: top-left;");
                questions.add(questionLabel);
                count++;
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (questions.size() < limit) {
            Label placeholder = new Label("   " + (questions.size() + 1) + ". No question available");
            placeholder.setFont(new Font("Arial", 15));
            placeholder.setPadding(new Insets(5, 0, 5, 20));
            placeholder.setStyle("-fx-alignment: top-left;");
            questions.add(placeholder);
        }

        return questions;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 15));
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.TOP_CENTER);
        return label;
    }

    private Label createSectionLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.TOP_LEFT);
        return label;
    }

    public static void main(String args[]) {
        launch(args);
    }
}
