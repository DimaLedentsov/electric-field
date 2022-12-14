package project.view.controllers.management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.App;
import project.view.controllers.AppController;
@Deprecated
public class ControllerManager {

    private Map<String, Controller> controllers;
    private Map<String, Stage> stages;
    private FXMLLoader loader;
    private App app;
    public ControllerManager(App a){
        app = a;
        controllers = new HashMap<>();
        stages = new HashMap<>();
        loader = new FXMLLoader();
    }

    public void addController(String k, Controller c){
        controllers.put(k, c);
    }
    public Stage switchOn(String name){
       
        Parent root;
        try {
            loader.setLocation(getClass().getResource(name));
            root = loader.load();
            //controllers.get("aa").setApp(app);
            if(!controllers.containsKey(name)) {
                AppController controller = loader.getController();
                if(controller instanceof Controller){
                    /*controller.setApp(app);
                    controllers.put(name, controller);*/

                } else{
                    throw new RuntimeException("controller doesnt implement Controller");
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        Stage newStage;
        if(stages.containsKey(name)) newStage = stages.get(name);
        else {
            newStage  = new Stage();

            Scene scene = new Scene(root);
            newStage.setScene(scene);

            stages.put(name, newStage);
            
        }
        return newStage;    
    }

    public Stage getStage(String s){
        return stages.get(s);
    }



}
