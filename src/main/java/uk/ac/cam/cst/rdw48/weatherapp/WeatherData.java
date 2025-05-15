package uk.ac.cam.cst.rdw48.weatherapp;

import java.time.LocalDateTime;

public class WeatherData {
  final public LocalDateTime time;
  final public LocalDateTime sunrise;
  final public LocalDateTime sunset;
  final public double temperature;
  final public double temperatureFeelsLike;
  final public double pressure;
  final public double humidity;
  final public String cloudCoverDescription;
  final public double cloudCoverPercent;
  final public double windSpeed;
  final public double windDirection;
  final public double visibility;

  public WeatherData(LocalDateTime time, LocalDateTime sunrise, LocalDateTime sunset, double temperature, double temperatureFeelsLike, double pressure, double humidity,
      String cloudCoverDescription, double cloudCoverPercent, double windSpeed, double windDirection,
      double visibility) {
    this.time = time;
    this.sunrise = sunrise;
    this.sunset = sunset;
    this.temperature = temperature;
    this.temperatureFeelsLike = temperatureFeelsLike;
    this.pressure = pressure;
    this.humidity = humidity;
    this.cloudCoverDescription = cloudCoverDescription;
    this.cloudCoverPercent = cloudCoverPercent;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.visibility = visibility;
  }
}
