package uk.ac.cam.cst.rdw48.weatherapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Application extends javafx.application.Application {

  private static ArrayList<WeatherData> weatherDataList;

  public static List<WeatherData> getWeatherData() {
    if (weatherDataList != null) {
      return weatherDataList;
    }

    String responseBody = "";

    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openweathermap.org/data/2.5/forecast?lat=52.205276&lon=0.119167&appid=b76c485ab461112dd75fafbdd0334228"))
            .GET()
            .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      responseBody = response.body();

      System.out.println(responseBody);
    } catch (Exception e) {
      e.printStackTrace();
    }

    JSONObject json = new JSONObject(responseBody);

    String data = json.getJSONArray("list").getJSONObject(0).getJSONObject("main").toString();
    LocalDateTime sunrise = LocalDateTime.ofInstant(Instant.ofEpochSecond(json.getJSONObject("city").getLong("sunrise")), ZoneId.systemDefault()) ;
    LocalDateTime sunset = LocalDateTime.ofInstant(Instant.ofEpochSecond(json.getJSONObject("city").getLong("sunset")),ZoneId.systemDefault()) ;

    weatherDataList = new ArrayList<>();
    for (int i = 0; i < json.getJSONArray("list").length(); i++) {
      JSONObject weatherObject = json.getJSONArray("list").getJSONObject(i);
      LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(weatherObject.getLong("dt")), ZoneId.systemDefault());
      double temperature = weatherObject.getJSONObject("main").getDouble("temp");
      double temperatureFeelsLike = weatherObject.getJSONObject("main").getDouble("feels_like");
      double pressure = weatherObject.getJSONObject("main").getDouble("pressure");
      double humidity = weatherObject.getJSONObject("main").getDouble("humidity");
      String cloudCoverDescription = weatherObject.getJSONArray("weather").getJSONObject(0).getString("description");
      double cloudCoverPercent = weatherObject.getJSONObject("clouds").getDouble("all");
      double windSpeed = weatherObject.getJSONObject("wind").getDouble("speed");
      double windDirection = weatherObject.getJSONObject("wind").getDouble("deg");
      double visibility = weatherObject.getDouble("visibility");

      WeatherData weatherData = new WeatherData(time, sunrise, sunset, temperature, temperatureFeelsLike, pressure, humidity,
          cloudCoverDescription, cloudCoverPercent, windSpeed, windDirection,
          visibility);
      weatherDataList.add(weatherData);
    }

    return weatherDataList;
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Cyclist Weather App");
    stage.setScene(scene);
    stage.show();


  }

  public static void main(String[] args) {
    launch();
  }
}