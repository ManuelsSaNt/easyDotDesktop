package com.example.easypointdesktop;

import com.dlsc.formsfx.model.structure.NodeElement;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class HelloController {

    private final String user = System.getProperty("user.name");

    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private final FileChooser fileChooser = new FileChooser();

    private String appDir = "/home/" + user;

    private Scene getActuaStage(Control elem){
        return (Scene) elem.getScene();
    }


    @FXML
    private TextField path;

    @FXML
    private TextField exec;

    @FXML
    private TextField icon;

    @FXML
    private TextField name;

    public void getFolderPath(ActionEvent actionEvent) {
        Scene myScene = getActuaStage(name);

        String tar = actionEvent.getTarget().toString();
        String cutTar = tar.substring(tar.indexOf("'") + 1);
        String txtF = cutTar.replace("'", "").split("]")[0];

        TextField myField = (TextField) myScene.lookup("#" + txtF);

        directoryChooser.setInitialDirectory(new File("/home/" + user));

        try {
            File selectedDirectory = directoryChooser.showDialog(myScene.getWindow());
            myField.setText(selectedDirectory.getAbsolutePath());
            appDir = selectedDirectory.getAbsolutePath();
        } catch (Exception ex) {
            System.out.println("An error ocurred or you dont select a valid path");
        }

    }


    public void writeDotDesktop(ActionEvent actionEvent) {

        String myPath = "/home/" + user + "/.local/share/applications/";
        String appName = name.getText() + ".desktop";

        String dataWriter = "[Desktop Entry]" + "\n" +
                           "Name=" + name.getText() + "\n" +
                           "Path=" + path.getText() + "\n" +
                           "Type=Application" + "\n" +
                           "Comment= this launcher was made with easyDotDesktop" + "\n" +
                           "Icon=" + icon.getText() + "\n" +
                           "Exec=" + exec.getText() + "\n" +
                           "Terminal=false" + "\n" +
                            "Categories=Develop";

        try {
            File miDotDesk = new File(myPath + appName);

            if(miDotDesk.createNewFile()){
                PrintWriter pencil = new PrintWriter(miDotDesk);
                pencil.println(dataWriter);
                pencil.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void getFilePath(ActionEvent actionEvent) {
        Scene myScene = getActuaStage(name);

        String tar = actionEvent.getTarget().toString();
        String cutTar = tar.substring(tar.indexOf("'") + 1);
        String txtF = cutTar.replace("'","").split("]")[0];

        TextField myField = (TextField) myScene.lookup("#" + txtF);

        fileChooser.setInitialDirectory(new File(appDir));

        try {
            File selectedFile = fileChooser.showOpenDialog(myScene.getWindow());
            myField.setText(selectedFile.getAbsolutePath());
        } catch (Exception ex) {
            System.out.println("An error ocurred or you dont select a valid path");
        }
    }


}