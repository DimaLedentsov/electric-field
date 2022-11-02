package project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.logic.Field2D;
import project.logic.Vector2D;
import project.utils.AnimTask;
import project.utils.ResizableCanvas;
import project.view.FieldAnimation;

public class App extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     
    @Override
    public void start(Stage stage) 
    { 
        // Create the Canvas
        ResizableCanvas canvas = new ResizableCanvas(400, 400);
        // Set the width of the Canvas

         

         // Get the graphics context of the canvas
        
        Field2D field = new Field2D(30, 30);
        FieldAnimation animation = new FieldAnimation(field, canvas);
        
        
        AnimTask task = new AnimTask(()->{
            animation.render();
        });
        task.start();
        // Create the Pane
        Pane root = new Pane();
    
        // Set the Style-properties of the Pane
        
        // Add the Canvas to the Pane
        root.getChildren().add(canvas);
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        // Create the Scene
        Scene scene = new Scene(root,400,400);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Creation of a Canvas");
        // Display the Stage
        stage.show();       
    }
}