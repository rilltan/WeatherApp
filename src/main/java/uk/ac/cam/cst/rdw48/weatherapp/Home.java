package uk.ac.cam.cst.rdw48.weatherapp;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main VBox
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #FAFAFA;");

        // Main Weather Display
        VBox mainWeather = new VBox(10);
        mainWeather.setAlignment(Pos.CENTER);
        mainWeather.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        mainWeather.setPadding(new Insets(20));

        List<ImageView> LargeWeather = new ArrayList<ImageView>(); // Get what the current weather is
        LargeWeather.add(new ImageView(new Image("sun.png")));
        LargeWeather.add(new ImageView(new Image("rain.png")));
        LargeWeather.add(new ImageView(new Image("wind.png")));
        LargeWeather.add(new ImageView(new Image("hail.png")));
        LargeWeather.add(new ImageView(new Image("cloudy.png")));
        LargeWeather.add(new ImageView(new Image("icy.png")));
        int currentWeather = 0;

        List<String> LargeDescriptor = new ArrayList<String>();
        LargeDescriptor.add("Sunny");
        LargeDescriptor.add("Rainy");
        LargeDescriptor.add("Windy");
        LargeDescriptor.add("Hail");
        LargeDescriptor.add("Cloudy");
        LargeDescriptor.add("Icy");

        Label weatherLabel = new Label(LargeDescriptor.get(currentWeather));
        weatherLabel.setFont(Font.font(20));


        Label tempLabel = new Label(temperature.ToString() + "°"); //Temperature from API
        tempLabel.setFont(Font.font(50));

        Label visibilityLabel = new Label("Visibility: " + visibility.ToString() + "miles away"); //Visibility from API
        visibilityLabel.setFont(Font.font(12));
        visibilityLabel.setTextFill(Color.GRAY);

        mainWeather.getChildren().addAll(LargeWeather.get(currentWeather), weatherLabel, tempLabel, visibilityLabel);

        // Recommendation and Wind
        HBox tipsBox = new HBox(20);
        tipsBox.setAlignment(Pos.CENTER);

        VBox recommendationBox = new VBox(5);
        recommendationBox.setAlignment(Pos.CENTER);
        recommendationBox.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        recommendationBox.setPadding(new Insets(10));

        List<ImageView> AdviceIcon = new ArrayList<ImageView>(); // calculate based off of current weather condition
        AdviceIcon.add(new ImageView(new Image("water.png")));
        AdviceIcon.add(new ImageView(new Image("coat.png")));
        AdviceIcon.add(new ImageView(new Image("windvane.png")));
        AdviceIcon.add(new ImageView(new Image("coat.png")));
        AdviceIcon.add(new ImageView(new Image("gust.png")));
        AdviceIcon.add(new ImageView(new Image("takecare.png")));


        List<String> Advice = new ArrayList<String>();
        Advice.add("Bring Water!");
        Advice.add("Bring Coat!");
        Advice.add("");
        Advice.add("Bring Coat!");
        Advice.add("");
        Advice.add("Take Care");

        Label tipText = new Label(Advice.get(currentWeather));
        recommendationBox.getChildren().addAll(AdviceIcon.get(currentWeather), tipText);

        VBox windBox = new VBox(5);
        windBox.setAlignment(Pos.CENTER);
        windBox.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        windBox.setPadding(new Insets(10));

        ImageView windMeter = new ImageView(new Image("wind.png"));
        windMeter.setFitWidth(60);
        windMeter.setFitHeight(30);
        Label windLabel = new Label("Wind");
        windBox.getChildren().addAll(windMeter, windLabel);

        tipsBox.getChildren().addAll(recommendationBox, windBox);

        // Rain Info
        VBox rainBox = new VBox(10);
        rainBox.setAlignment(Pos.CENTER);
        rainBox.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        rainBox.setPadding(new Insets(15));
        ImageView rainIcon = new ImageView(new Image("rain.png"));
        rainIcon.setFitWidth(50);
        rainIcon.setFitHeight(40);
        Label rainChance = new Label("% RAIN: 0%");
        Label rainStart = new Label("EXP. START AT: 08:00 AM");
        rainBox.getChildren().addAll(rainIcon, rainChance, rainStart);

        // Sunrise and Sunset
        HBox sunTimesBox = new HBox(20);
        sunTimesBox.setAlignment(Pos.CENTER);

        VBox sunriseBox = new VBox(5);
        sunriseBox.setAlignment(Pos.CENTER);
        sunriseBox.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        sunriseBox.setPadding(new Insets(10));
        ImageView sunriseIcon = new ImageView(new Image("sunrise.png"));
        sunriseIcon.setFitWidth(30);
        sunriseIcon.setFitHeight(30);
        Label sunriseLabel = new Label("Sunrise\n05:00 AM");
        sunriseBox.getChildren().addAll(sunriseIcon, sunriseLabel);

        VBox sunsetBox = new VBox(5);
        sunsetBox.setAlignment(Pos.CENTER);
        sunsetBox.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        sunsetBox.setPadding(new Insets(10));
        ImageView sunsetIcon = new ImageView(new Image("sunset.png"));
        sunsetIcon.setFitWidth(30);
        sunsetIcon.setFitHeight(30);
        Label sunsetLabel = new Label("Sunset\n06:00 PM");
        sunsetBox.getChildren().addAll(sunsetIcon, sunsetLabel);

        sunTimesBox.getChildren().addAll(sunriseBox, sunsetBox);

        root.getChildren().addAll(mainWeather, tipsBox, rainBox, sunTimesBox);

        Scene scene = new Scene(root, 350, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Weather Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
