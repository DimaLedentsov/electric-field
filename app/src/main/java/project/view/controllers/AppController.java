package project.view.controllers;

import java.io.File;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import project.logic.Field2D;
import project.logic.Particle;
import project.logic.Plane;
import project.logic.PotentialLine;
import project.logic.Vector2D;
import project.script.ParseException;
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

    @FXML
    private ChoiceBox<ItemType> choiceBox;

    @FXML
    private HBox chargeSettings;

    @FXML
    private HBox lineSettings;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox showFieldCheckBox;

    @FXML
    private CheckBox showLinesCheckBox;

    
    @FXML
    private TextField chargeDensity;

    @FXML
    private HBox planeSettings;

    @FXML
    private CheckBox planeSign;

    @FXML
    private TextArea script;
    
    @FXML
    private Text scriptOutput;
    @FXML
    private TitledPane interpreterPane;

    
    @FXML
    private CheckMenuItem showInterpreter;

    Field2D field;
    FieldSimulation simulation;
    Vector2D lastClick;
    void updateSettings(){
        if(!(choiceBox.getSelectionModel().getSelectedItem()==ItemType.PARTICLE)){
            chargeSettings.setVisible(false);
            chargeSettings.setManaged(false);
        } else{
            chargeSettings.setVisible(true);
            chargeSettings.setManaged(true);
        }

        if(!(choiceBox.getSelectionModel().getSelectedItem()==ItemType.POTENTIONAL_LINE)){
            lineSettings.setVisible(false);
            lineSettings.setManaged(false);
        } else{
            lineSettings.setVisible(true);
            lineSettings.setManaged(true);
        }
        if(!(choiceBox.getSelectionModel().getSelectedItem()==ItemType.PLANE)){
            planeSettings.setVisible(false);
            planeSettings.setManaged(false);
        } else{
            planeSettings.setVisible(true);
            planeSettings.setManaged(true);
        }

        
    }
    @FXML
    void initialize(){


        field = new Field2D(40, 40);
        simulation = new FieldSimulation(field, canvas);

        field.getPlanes().add(new Plane(Vector2D.fromCoords(-10, -10),Vector2D.fromCoords(10, 10),0.001,false));
        field.getPlanes().add(new Plane(Vector2D.fromCoords(-40, -10),Vector2D.fromCoords(-20, 10),0.001,true));
        //field.getParticles().add(new Particle(Vector2D.fromCoords(10.1, 10), 0.0001));
        simulation.updateField();
        AnimTask task = new AnimTask(()->{
            simulation.update();
            simulation.render();
        });
        task.start();

        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());

        choiceBox.getItems().addAll(ItemType.values());
        choiceBox.getSelectionModel().select(ItemType.PARTICLE);
        updateSettings();
        updateInterpreter();
        choiceBox.setOnAction((e)->{
            updateSettings();
        });

        menu.getItems().forEach((e)->{
            if (e instanceof CustomMenuItem){
                CustomMenuItem cmi = (CustomMenuItem)e;
                cmi.setHideOnClick(false);

            }
        });
    
       
    }
    @FXML
    void showField(ActionEvent event) {
        simulation.setShowField(showFieldCheckBox.isSelected());
    }

    @FXML
    void showLines(ActionEvent event) {
        simulation.setShowLines(showLinesCheckBox.isSelected());
    }
    @FXML
    void remove(ActionEvent event) {
        if(simulation.getSelectedParticle()!=null){
            field.getParticles().remove(simulation.getSelectedParticle());
            simulation.updateField();
            field.getLines().clear();
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
            simulation.updateField();
            field.getLines().clear();
        }
    }

    @FXML
    void menuSelect(ActionEvent event) {
        event.consume();
    }
    @FXML
    void updateInput(ActionEvent event) {

    }

    @FXML
    void removePlane(ActionEvent event) {
        lastClick=null;
        field.getPlanes().clear();
        simulation.updateField();
        field.getLines().clear();
    }
    @FXML
    void changeSign(ActionEvent event) {
        if(inputText.getText().startsWith("-")){
            inputText.setText(inputText.getText().replace("-", ""));
        }
        else{
            inputText.setText("-" + inputText.getText());
        }
    }

    @FXML
    void clearLines(ActionEvent event) {
        field.getLines().clear();
    }
    @FXML
    void clearAll(ActionEvent event) {
        field.getLines().clear();
        field.getParticles().clear();
        field.getPlanes().clear();
        simulation.selectParticle(null);
        simulation.updateField();
    }


    @FXML
    void canvasClick(MouseEvent e) {
        e.consume();
        try{
        //Platform.runLater(()->{
            Vector2D coords = simulation.convertScreenCoordsToField(Vector2D.fromCoords(e.getX(), e.getY()));

            double Q=1;
            double density = 0.1;
            if (e.getButton()==MouseButton.PRIMARY) {
                if(choiceBox.getSelectionModel().getSelectedItem()==ItemType.PARTICLE){
                    //if(inputText.getText()==null || inputText.getText().equals("")) Q=1;
                    //else{
                        try{
                            Q = Double.parseDouble(inputText.getText());
                        } catch(NumberFormatException ex){
                            error("неправильно введен заряд");
                            return;
                        }
                    //}
                    Vector2D nearest = simulation.convertScreenCoordsToFieldAndFindNearestGridPoint(Vector2D.fromCoords(e.getX(), e.getY()));
                    Particle particle = new Particle(nearest, Q);

                    if(field.getParticles().stream().filter((p)->p.getPos().equals(particle.getPos())).count()==0) {
                        field.getParticles().add(particle);
                    }
                    //System.out.println(p);
                    simulation.updateField();
                    field.getLines().clear();
                }
                else if(choiceBox.getSelectionModel().getSelectedItem()==ItemType.POTENTIONAL_LINE){
         
                    Platform.runLater(()->{
                        List<Vector2D> line = simulation.getPrunedLine(simulation.getPotentionalLine(coords));
                        field.getLines().add(new PotentialLine(colorPicker.getValue(), coords,simulation.potential(coords), line));
                        simulation.setPotentionalLineColor(colorPicker.getValue());
                    });
                    
                } else if (choiceBox.getSelectionModel().getSelectedItem()==ItemType.PLANE){
                    if(lastClick ==null){
                        lastClick = coords;
                    } else{
                        //if(chargeDensity.getText()==null || chargeDensity.getText().equals("")) density=0.01;
                        //else{
                            try{
                                density = Double.parseDouble(chargeDensity.getText());
                                if(density<0) throw new NumberFormatException();
                            } catch(NumberFormatException ex){
                                error("неправильно задана поверхностная плотность");
                                return;
                            }
                        //}
                        
                        field.getPlanes().add(new Plane(coords,lastClick,density,planeSign.isSelected()));
                        simulation.updateField();
                        field.getLines().clear();
                        lastClick=null;
                    }
                }
                
            }
            else {
                choiceBox.getSelectionModel().select(ItemType.PARTICLE);
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

    @FXML
    void evalScript(ActionEvent event) {
        String text = script.getText();
        //System.out.println(text);
        try{
            simulation.getParser().execute(text);
            field.getLines().clear();
            simulation.updateField();
            scriptOutput.setText("скрипт выполнен!");
            scriptOutput.setFill(Color.GREEN);
            //scriptOutput.setStyle("-fx-text-inner-color: green;");
        } catch (ParseException e){
            scriptOutput.setText(e.getMessage());
            scriptOutput.setFill(Color.RED);
            //scriptOutput.setStyle("-fx-text-inner-color: red;");
            System.out.println(e.getMessage());
        }
    }

    void updateInterpreter(){
        boolean flag = showInterpreter.isSelected();
        interpreterPane.setVisible(flag);
        interpreterPane.setManaged(flag);
    }
    @FXML
    void showHideInterpreter(ActionEvent event) {
        updateInterpreter();
    }

}
