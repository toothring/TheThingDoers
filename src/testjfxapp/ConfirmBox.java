package testjfxapp;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    static boolean answer;

    public static Boolean display(String title, String message) {

        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        Button yes = new Button("Quit");
        yes.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button no = new Button("No, wait!");
        no.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yes, no);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(ConfirmBox.class.getResource("TetsawStylesheet.css").toString());
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

}