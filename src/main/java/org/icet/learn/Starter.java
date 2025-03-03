package org.icet.learn;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.icet.learn.util.AppModule;

import java.io.IOException;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        loader.setControllerFactory(injector::getInstance); // Guice provides controllers

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}
