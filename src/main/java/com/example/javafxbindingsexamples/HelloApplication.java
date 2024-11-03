package com.example.javafxbindingsexamples;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

        @Override
        public void start(Stage stage) throws IOException {
            // Main window buttons
            Button rotateTextButton = new Button("Rotar texto");
            Button bindTextFieldsButton = new Button("Bind campos de texto");
            Button functionalBindingButton = new Button("Funcional Binding");

            // Main window layout
            VBox root = new VBox(20);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(30));
            root.getChildren().addAll(rotateTextButton, bindTextFieldsButton, functionalBindingButton);

            Scene mainScene = new Scene(root, 300, 250);
            stage.setTitle("Ventana principal");
            stage.setScene(mainScene);
            stage.show();

            // Stage 1: Rotate Text
            rotateTextButton.setOnAction(e -> {
                Stage rotateStage = new Stage();
                Label text = new Label("Texto rotatorio");
                Slider slider = new Slider(0, 360, 0);
                text.rotateProperty().bind(slider.valueProperty());

                VBox rotateRoot = new VBox(20);
                rotateRoot.setAlignment(Pos.CENTER);
                rotateRoot.setPadding(new Insets(20));
                rotateRoot.getChildren().addAll(text, slider);

                Scene rotateScene = new Scene(rotateRoot, 300, 200);
                rotateStage.setTitle("Rotación de texto");
                rotateStage.setScene(rotateScene);
                rotateStage.show();
            });

            // Stage 2: Bind Text Fields -
            bindTextFieldsButton.setOnAction(e -> {
                Stage bindStage = new Stage();
                TextField textField1 = new TextField();
                TextField textField2 = new TextField();
                TextField textField3 = new TextField();
                TextField textField4 = new TextField();

                // Unidirectional binding
                textField2.textProperty().bind(textField1.textProperty());

                // Bidirectional binding
                StringProperty sharedProperty = new SimpleStringProperty();
                textField3.textProperty().bindBidirectional(sharedProperty);
                textField4.textProperty().bindBidirectional(sharedProperty);

                VBox bindRoot = new VBox(10);
                bindRoot.setPadding(new Insets(20));
                bindRoot.getChildren().addAll(
                        new Label("Unidireccional:"),
                        textField1, textField2,
                        new Label("Bidireccional:"),
                        textField3, textField4
                );

                Scene bindScene = new Scene(bindRoot, 350, 250);
                bindStage.setTitle("Enlazar campos de texto");
                bindStage.setScene(bindScene);
                bindStage.show();
            });

            // Stage 3: Functional Binding
            functionalBindingButton.setOnAction(e -> {
                Stage functionalStage = new Stage();
                TextField field1 = new TextField();
                TextField field2 = new TextField();
                Label resultLabel = new Label();

                // Functional binding with Bindings class
                resultLabel.textProperty().bind(
                        Bindings.createStringBinding(
                                () -> "Suma: " + (toInt(field1.getText()) + toInt(field2.getText())),
                                field1.textProperty(), field2.textProperty()
                        )
                );

                VBox functionalRoot = new VBox(10);
                functionalRoot.setPadding(new Insets(20));
                functionalRoot.getChildren().addAll(field1, field2, resultLabel);

                Scene functionalScene = new Scene(functionalRoot, 300, 200);
                functionalStage.setTitle("Funcional Binding");
                functionalStage.setScene(functionalScene);
                functionalStage.show();
            });
        }

        private int toInt(String text) {
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        public static void main(String[] args) {
            launch(args);
        }

    }