import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Fact extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Create UI elements
        Label inputLabel = new Label("Enter a number:");
        TextField inputField = new TextField();
        Label outputLabel = new Label("Factorial:");
        Label resultLabel = new Label();
        Button calculateButton = new Button("Calculate");
        
        // Create UI layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(inputLabel, 0, 0);
        gridPane.add(inputField, 1, 0);
        gridPane.add(outputLabel, 0, 1);
        gridPane.add(resultLabel, 1, 1);
        gridPane.add(calculateButton, 1, 2);
        
        // Add event handler to calculate button
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int input = Integer.parseInt(inputField.getText());
                    long factorial = 1;
                    for (int i = 1; i <= input; i++) {
                        factorial *= i;
                    }
                    resultLabel.setText(String.valueOf(factorial));
                } catch (NumberFormatException e) {
                    resultLabel.setText("Invalid input");
                }
            }
        });
        
        // Create and show scene
        Scene scene = new Scene(gridPane, 300, 150);
        primaryStage.setTitle("Factorial Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
