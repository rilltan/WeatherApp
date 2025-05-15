package uk.ac.cam.cst.rdw48.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class Controller {
  @FXML
  public Label api_sunrise;
  public Label api_sunset;
  @FXML
  private Label welcomeText;

  public void initialize() {
    Application app = new Application();
    List<WeatherData> weatherDataList = Application.getWeatherData();

    if (!weatherDataList.isEmpty()) {
      WeatherData firstData = weatherDataList.get(0);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      api_sunrise.setText("Sunrise: " + firstData.sunrise.format(formatter));
      api_sunset.setText("Sunset: " + firstData.sunset.format(formatter));
    }
  }
}