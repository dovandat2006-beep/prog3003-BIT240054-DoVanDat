import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Bai4SumCalculatorApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label firstNumberLabel = new Label("Số thứ nhất:");
        Label secondNumberLabel = new Label("Số thứ hai:");
        Label resultLabel = new Label("Kết quả");

        TextField firstNumberField = new TextField();
        TextField secondNumberField = new TextField();
        Button sumButton = new Button("Tính tổng");

        sumButton.setOnAction(event -> {
            try {
                double firstNumber = Double.parseDouble(firstNumberField.getText());
                double secondNumber = Double.parseDouble(secondNumberField.getText());
                resultLabel.setText("Kết quả: " + (firstNumber + secondNumber));
            } catch (NumberFormatException e) {
                resultLabel.setText("Lỗi!");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(16));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(firstNumberLabel, 0, 0);
        gridPane.add(firstNumberField, 1, 0);
        gridPane.add(secondNumberLabel, 0, 1);
        gridPane.add(secondNumberField, 1, 1);
        gridPane.add(sumButton, 0, 2);
        gridPane.add(resultLabel, 1, 2);

        Scene scene = new Scene(gridPane, 360, 180);
        primaryStage.setTitle("Máy Tính Cộng Hai Số");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
