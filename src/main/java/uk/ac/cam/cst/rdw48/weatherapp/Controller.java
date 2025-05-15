package uk.ac.cam.cst.rdw48.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Controller {
  @FXML
  public Label api_sunrise;
  public Label api_sunset;
  @FXML
  private Label welcomeText;


  @FXML
  public void apiCall(ActionEvent actionEvent) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.openweathermap.org/data/2.5/forecast?lat=52.205276&lon=0.119167&appid=b76c485ab461112dd75fafbdd0334228"))
        .GET()
        .build();

    String responseBody = "";
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      responseBody = response.body();
      JSONObject json = new JSONObject(responseBody);

      String data = json.getJSONArray("list").getJSONObject(0).getJSONObject("main").toString();
      LocalDateTime sunrise = LocalDateTime.ofInstant(Instant.ofEpochSecond(json.getJSONObject("city").getLong("sunrise")),ZoneId.systemDefault()) ;
      LocalDateTime sunset = LocalDateTime.ofInstant(Instant.ofEpochSecond(json.getJSONObject("city").getLong("sunset")),ZoneId.systemDefault()) ;

      System.out.println(data);
      api_sunrise.setText("Sunrise: " + sunrise.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
      api_sunset.setText("Sunset: " + sunset.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}