package project;
import javafx.application.Application;
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
        
        Field2D field = new Field2D(40, 40);
        FieldSimulation animation = new FieldSimulation(field, canvas);
        
    

        
        AnimTask task = new AnimTask(()->{
            animation.update();
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
        
        
        canvas.setOnMouseClicked((e)->{
            e.consume();
            //Platform.runLater(()->{
                Vector2D coords = animation.convertScreenCoordsToField(Vector2D.fromCoords(e.getX(), e.getY()));
                //System.out.println(coords.toString());
                double Q = 5;
                if (e.getButton()==MouseButton.PRIMARY) Q=5;
                else Q=-5;
                field.getParticles().add(new Particle(coords, Q));

            //});
        });

        scene.setOnKeyPressed((e)->{
            if(e.getCode()==KeyCode.C) field.getParticles().clear();
        });
    }
}