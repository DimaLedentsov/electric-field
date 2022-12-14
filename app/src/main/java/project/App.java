package project;
import java.io.IOError;
import java.io.IOException;
import java.util.Locale;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//import jfxtras.styles.jmetro.JMetro;
//import jfxtras.styles.jmetro.JMetroStyleClass;
//import jfxtras.styles.jmetro.Style;
import project.logic.Field2D;
import project.logic.Particle;
import project.logic.Vector2D;
import project.utils.AnimTask;
import project.utils.ResizableCanvas;
import project.view.FieldSimulation;
import project.view.controllers.AppController;
import project.view.controllers.ControllerType;
import project.view.controllers.management.ControllerManager;

public class App extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     

    @Override
    public void start(Stage stage) throws IOException
    { 

        FXMLLoader appLoader = new FXMLLoader();
        appLoader.setLocation(getClass().getResource("/app.fxml"));
        
        Parent root = appLoader.load();
        AppController appController = appLoader.getController();
        
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        
     
        Scene scene = new Scene(root,650,400);
             stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("симуляция электрического поля");
        // Display the Stage
        stage.show();    
        /*ControllerManager controllerManager = new ControllerManager(this);
        controllerManager.switchOn(ControllerType.APP).show();      */
    }
}