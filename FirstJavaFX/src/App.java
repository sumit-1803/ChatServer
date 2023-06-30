import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    
    private TextField num1Field;
    private TextField num2Field;
    private TextField expressionField;
    private Label resultLabel;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        num1Field = new TextField();
        GridPane.setConstraints(num1Field, 0, 0);
        grid.getChildren().add(num1Field);
        
        num2Field = new TextField();
        GridPane.setConstraints(num2Field, 0, 1);
        grid.getChildren().add(num2Field);
        
        expressionField = new TextField();
        GridPane.setConstraints(expressionField, 0, 2);
        grid.getChildren().add(expressionField);
        
        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = num1 + num2;
            resultLabel.setText(Double.toString(result));
        });
        GridPane.setConstraints(addButton, 1, 0);
        grid.getChildren().add(addButton);
        
        Button subtractButton = new Button("-");
        subtractButton.setOnAction(e -> {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = num1 - num2;
            resultLabel.setText(Double.toString(result));
        });
        GridPane.setConstraints(subtractButton, 2, 0);
        grid.getChildren().add(subtractButton);
        
        Button multiplyButton = new Button("*");
        multiplyButton.setOnAction(e -> {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = num1 * num2;
            resultLabel.setText(Double.toString(result));
        });
        GridPane.setConstraints(multiplyButton, 1, 1);
        grid.getChildren().add(multiplyButton);
        
        Button divideButton = new Button("/");
        divideButton.setOnAction(e -> {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = num1 / num2;
            resultLabel.setText(Double.toString(result));
        });
        GridPane.setConstraints(divideButton, 2, 1);
        grid.getChildren().add(divideButton);
        
        Button evaluateButton = new Button("Evaluate");
        evaluateButton.setOnAction(e -> {
            String expression = expressionField.getText();
            double result = evaluatePrefixExpression(expression);
            resultLabel.setText(Double.toString(result));
        });
        GridPane.setConstraints(evaluateButton, 1, 2);
        grid.getChildren().add(evaluateButton);
        
        resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 0, 3);
        GridPane.setColumnSpan(resultLabel, 3);
        grid.getChildren().add(resultLabel);
        
        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private double evaluatePrefixExpression(String expression) {
        String[] tokens = expression.split("\\s+");
        Stack<Double> stack = new Stack<>();
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (isNumber(token)) {
                double num = Double.parseDouble(token);
                stack.push(num);
            } else {
                double num1 = stack.pop();
                double num2 = stack.pop();
                double result = 0;
                switch (token) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }
    
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
