package project;
import java.io.IOError;
import java.io.IOException;

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
        
        Pane root = (Pane)appLoader.load();
        AppController appController = appLoader.getController();
        
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        
        // Create the Canvas

         // Get the graphics context of the canvas
        

        // Create the Pane
  
        // Set the Style-properties of the Pane
        
        // Add the Canvas to the Pane
    
        
       // Jfoeni
        // Create the Scene
        //root.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        Scene scene = new Scene(root,650,400);
        //JMetro jMetro = new JMetro(Style.LIGHT);
        //jMetro.setScene(scene); 
        /*final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                           JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                           MainDemo.class.getResource("/css/jfoenix-main-demo.css").toExternalForm());
        // Add the Scene to the Stage*/
        //scene.getStylesheets().add(JMetroStyleClass.BACKGROUND);
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("симуляция электрического поля");
        // Display the Stage
        stage.show();    
        


        
    }
}