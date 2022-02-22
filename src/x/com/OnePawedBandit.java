package x.com;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class OnePawedBandit extends Application {

    private GridPane gridPane;
    private VBox col1, col2, col3;
    private HBox row1, row2;
    private TextField yourAmount;
    private int totalValue = 0;
    private ImageView imageView1, imageView2, imageView3, defaultImg;
    private Label wonSpin, wonTotal;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("ONE-PAWED BANDIT");

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 50, 50, 50));
        gridPane.setHgap(10);
        gridPane.setVgap(12);

        row1 = new HBox(10);
        row2 = new HBox(10);
        gridPane.add(row1, 0, 0);
        gridPane.add(row2, 0, 1);

        String path = "x/com/avocato.jpg";
        Image image = new Image(path);
        defaultImg = new ImageView(image);
        defaultImg.setFitHeight(200);
        defaultImg.setFitWidth(200);
        row1.getChildren().addAll(defaultImg);

        col1 = new VBox(10);
        col2 = new VBox(10);
        col3 = new VBox(10);

        col1.setPadding(new Insets(10, 10, 10, 10));
        col2.setPadding(new Insets(10, 10, 10, 10));
        col3.setPadding(new Insets(10, 10, 10, 10));

        row2.getChildren().addAll(col1, col2, col3);

        Button spinButton = new Button();
        spinButton.setStyle("-fx-background-color: #00cc00;");
        spinButton.setText("SPIN");

        Label putAmount = new Label("Declare amount: ");
        yourAmount = new TextField();

        wonSpin = new Label("Won on this round: zł");
        wonTotal = new Label("Your total prize: zł");

        Label emptyField = new Label("You must declare amount first!");
        emptyField.setVisible(false);

        col1.getChildren().addAll(putAmount, yourAmount, emptyField);
        col2.getChildren().addAll(spinButton);
        col3.getChildren().addAll(wonSpin, wonTotal);

        primaryStage.setScene(new Scene(gridPane, 700, 350));
        primaryStage.show();

        spinButton.setOnAction(event -> {

            emptyField.setVisible(false);

            try {
                int spinAmt = Integer.parseInt(yourAmount.getText());

                displayImgAndPoints(getRandomInt(10, 1), getRandomInt(10, 1),
                        getRandomInt(10, 1), spinAmt);
            } catch (NumberFormatException e) {
                emptyField.setVisible(true);
            }
        });
    }

    public void displayImgAndPoints(int random1, int random2, int random3, int spinAmt) {

        row1.getChildren().clear();

        //image 1
        String path = "/images/" + random1 + ".jpg";
        Image image = new Image(path);
        imageView1 = new ImageView(image);
        row1.getChildren().addAll(imageView1);

        //image 2
        path = "/images/" + random2 + ".jpg";
        image = new Image(path);
        imageView2 = new ImageView(image);
        row1.getChildren().addAll(imageView2);

        //image 3
        path = "/images/" + random3 + ".jpg";
        image = new Image(path);
        imageView3 = new ImageView(image);
        row1.getChildren().addAll(imageView3);

        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);
        imageView2.setFitHeight(200);
        imageView2.setFitWidth(200);
        imageView3.setFitHeight(200);
        imageView3.setFitWidth(200);


        if(random1 == random2 && random1 == random3) {
            spinAmt = spinAmt * 3;
        } else
        if(random1 == random2 || random1 == random3 || random2 == random3) {
            spinAmt = spinAmt * 2;
        } else
            spinAmt = 0;


        totalValue = totalValue + spinAmt;

        wonSpin.setText("Won on this turn: " + spinAmt + " zł");
        wonTotal.setText("Your total prize: " + totalValue + " zł");

    }

    public int getRandomInt(int max, int min) {
        return ((int)(Math.random() * (max - min))) + min;
    }
}
