package project.view.controllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import project.logic.Field2D;
import project.logic.Particle;
import project.logic.Vector2D;
import project.utils.AnimTask;
import project.view.FieldSimulation;

public class AppController {

    @FXML
    private Canvas canvas;

    @FXML
    private TextField inputText;

    @FXML
    private Button removeButton;

    @FXML
    private Button updateButton;

    @FXML
    private AnchorPane canvasPane;
    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem clearButton;

    Field2D field;
    FieldSimulation simulation;
    @FXML
    void initialize(){
        field = new Field2D(40, 40);
        simulation = new FieldSimulation(field, canvas);

        AnimTask task = new AnimTask(()->{
            simulation.update();
            simulation.render();
        });
        task.start();

        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());


       
    }
    @FXML
    void remove(ActionEvent event) {
        if(simulation.getSelectedParticle()!=null){
            field.getParticles().remove(simulation.getSelectedParticle());
            simulation.selectParticle(null);
        }

    }

    @FXML
    void update(ActionEvent event) {
        if(simulation.getSelectedParticle()!=null){
            double Q=1;
            if(inputText.getText()==null || inputText.getText().equals("")) Q=1;
            else{
                try{
                    Q = Double.parseDouble(inputText.getText());
                } catch(NumberFormatException ex){
                    error("неправильно введен заряд");
                }
            }
            simulation.getSelectedParticle().setQ(Q);
        }
    }

    @FXML
    void menuSelect(ActionEvent event) {

    }
    @FXML
    void updateInput(ActionEvent event) {

    }

    @FXML
    void clearAll(ActionEvent event) {
        field.getParticles().clear();
        simulation.selectParticle(null);
    }


    @FXML
    void canvasClick(MouseEvent e) {
        System.out.println("aa");
        try{
        //Platform.runLater(()->{
            Vector2D coords = simulation.convertScreenCoordsToField(Vector2D.fromCoords(e.getX(), e.getY()));

            double Q=1;
            if (e.getButton()==MouseButton.PRIMARY) {
                if(inputText.getText()==null || inputText.getText().equals("")) Q=1;
                else{
                    try{
                        Q = Double.parseDouble(inputText.getText());
                    } catch(NumberFormatException ex){
                        error("неправильно введен заряд");
                    }
                }
                field.getParticles().add(new Particle(coords, Q));
                
            }
            else {
                for(Particle p: field.getParticles()){
                    if(coords.sub(p.getPos()).len()<10){
                        simulation.selectParticle(p);
                    }
                }
            }
            
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    
    @FXML
    void renderPotentialLines(ActionEvent event) {
        
    }

    public void error(String s){
        Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ОШИБКА");
		//alert.setHeaderText(s);
		alert.setContentText(s);
        alert.setHeaderText(null);
		alert.showAndWait();
    }

    public void info(String s){
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("ИНФОРМАЦИЯ");
		//alert.setHeaderText(s);
		alert.setContentText(s);
        alert.setHeaderText(null);
		alert.showAndWait();
    }

    @FXML void about(){
        info(
                "Над проектом работали студенты P32081:\n\n" + 
                "Леденцов Дмитрий\n" + 
                "Аталян Александр"
        );
    }

}
