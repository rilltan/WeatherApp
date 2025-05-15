module uk.ac.cam.cst.rdw48.weatherapp {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.kordamp.bootstrapfx.core;
  requires java.net.http;
  requires org.json;

  opens uk.ac.cam.cst.rdw48.weatherapp to javafx.fxml;
  exports uk.ac.cam.cst.rdw48.weatherapp;
}