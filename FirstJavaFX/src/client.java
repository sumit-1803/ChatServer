
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class client extends Application {
   @Override//from ww w  .  ja v a2s  . co m
   public void start(Stage primaryStage) {
      // Create the top section of the UI
      Text tNumber1 = new Text("Number 1:");
      Text tNumber2 = new Text("Number 2:");
      Text tResult = new Text("Result:");
      TextField tfNumber1 = new TextField();
      TextField tfNumber2 = new TextField();
      TextField tfResult = new TextField();
      tfResult.setEditable(false);

      // Create the bottom section of the UI
      Button btAdd = new Button("Add");
      Button btSubtract = new Button("Subtract");
      Button btMultiply = new Button("Multiply");
      Button btDivide = new Button("Divide");

      // Add top and bottom UI to HBox containers
      HBox calcTop = new HBox(5);
      calcTop.setAlignment(Pos.CENTER);
      calcTop.setPadding(new Insets(5));
      calcTop.getChildren().addAll(tNumber1, tfNumber1, tNumber2, tfNumber2, tResult, tfResult);
      HBox calcBottom = new HBox(5);
      calcBottom.setAlignment(Pos.CENTER);
      calcBottom.setPadding(new Insets(5));
      calcBottom.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);

      // Add HBox containers to a BorderPane
      BorderPane pane = new BorderPane();
      pane.setCenter(calcTop);
      pane.setBottom(calcBottom);

      // Register event handlers for buttons
      btAdd.setOnAction(e -> {
         double a = getDoubleFromTextField(tfNumber1);
         double b = getDoubleFromTextField(tfNumber2);
         tfResult.setText(String.valueOf(a + b));
      });

      btSubtract.setOnAction(e -> {
         double a = getDoubleFromTextField(tfNumber1);
         double b = getDoubleFromTextField(tfNumber2);
         tfResult.setText(String.valueOf(a - b));
      });

      btMultiply.setOnAction(e -> {
         double a = getDoubleFromTextField(tfNumber1);
         double b = getDoubleFromTextField(tfNumber2);
         tfResult.setText(String.valueOf(a * b));
      });

      btDivide.setOnAction(e -> {
         double a = getDoubleFromTextField(tfNumber1);
         double b = getDoubleFromTextField(tfNumber2);
         tfResult.setText(b == 0 ? "NaN" : String.valueOf(a / b));
      });

      Scene scene = new Scene(pane);
      primaryStage.setTitle("java2s.com");
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.show();
   }

   private static double getDoubleFromTextField(TextField t) {
      return Double.parseDouble(t.getText());
   }

   public static void main(String[] args) {
      launch(args);
   }
}
