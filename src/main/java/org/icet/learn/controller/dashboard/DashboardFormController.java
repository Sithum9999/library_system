package org.icet.learn.controller.dashboard;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.icet.learn.util.AppModule;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController  {

    @FXML
    private AnchorPane dashboardAncorpane;

    private final Injector injector;

    public DashboardFormController() {
        this.injector = Guice.createInjector(new AppModule());
    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        loadBookView();
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        loadUserView();
    }

    private void loadUserView() throws IOException {
        URL resource = getClass().getResource("/view/user.fxml");
        if (resource != null) {
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(injector::getInstance);
            Parent loadedView = loader.load();

            dashboardAncorpane.getChildren().clear();
            // Load user.fxml inside the dashboardAncorpane
            dashboardAncorpane.getChildren().add(loadedView);
        } else {
            System.err.println("Error: user.fxml not found!");
        }
    }

    private void loadBookView() throws IOException {
        URL resource = getClass().getResource("/view/book.fxml");
        if (resource != null) {
            dashboardAncorpane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(injector::getInstance);
            Parent loadedView = loader.load();

            // Load user.fxml inside the dashboardAncorpane
            dashboardAncorpane.getChildren().add(loadedView);
        } else {
            System.err.println("Error: user.fxml not found!");
        }
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/borrowbooks.fxml");
        if (resource != null) {
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(injector::getInstance);
            Parent loadedView = loader.load();

            dashboardAncorpane.getChildren().clear();
            // Load user.fxml inside the dashboardAncorpane
            dashboardAncorpane.getChildren().add(loadedView);
        } else {
            System.err.println("Error: user.fxml not found!");
        }
    }
}
