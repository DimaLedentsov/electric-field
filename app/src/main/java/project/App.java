package project;
import java.io.IOError;
import java.io.IOException;

import javafx.application.Application;
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
        
        
        // Create the Canvas

         // Get the graphics context of the canvas
        

        // Create the Pane
  
        // Set the Style-properties of the Pane
        
        // Add the Canvas to the Pane
    
        
        // Create the Scene
        Scene scene = new Scene(root,400,400);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("симуляция электрического поля");
        // Display the Stage
        stage.show();    
        


        
    }
}