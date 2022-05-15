
package com.example.denma.controllers;

import com.example.denma.App;
import com.example.denma.base.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.Statement;

public class AddingPatientAttenteController {

    Stage APACStage=null;

    ObservableList<String> sex = FXCollections.observableArrayList("Masculin","Féminin");
    @FXML
    TextField nomc;
    @FXML
    TextField prenomc;
    @FXML
    TextField cinc;
    @FXML
    TextField idcouvc;
    @FXML
    TextField typecouvc;

    @FXML
    DatePicker datenaic;
    @FXML
    ChoiceBox<String> sexec;
    
    @FXML
    Button ajBu;

    AssistantMenuPageController ampc;
    int a;
    @FXML
    void ajBuPressed(ActionEvent event) {
        CouvertureMedicale cmed = new CouvertureMedicale(idcouvc.getText(), typecouvc.getText());
        char cc=(sexec.getValue().compareTo("Masculin")==0)?'M':'F';
        Patient pat = new Patient(DenMaCore.nbrePatients(),datenaic.getValue(), cinc.getText(), nomc.getText(), prenomc.getText(),cc,cmed);
        PatientAttente patAtt = new PatientAttente(nomc.getText(), prenomc.getText(),cinc.getText(),100,datenaic.getValue(),  cc,cmed);
        DenMaSQL.insererNouveauPatient(pat);
        DenMaSQL.insererNouveauPatientAttente(patAtt);
        DenMaCore.incrNbrePatients();
        ampc.fillPatientAttenteTV();
        ampc.fillPatientTV();
        APACStage.close();
    }

    public AddingPatientAttenteController(AssistantMenuPageController ampcc) {
        APACStage=new Stage();
        ampc=ampcc;
        try{

            FXMLLoader APACLoader = new FXMLLoader(App.class.getResource("addPatient.fxml"));
            APACLoader.setController(this);
            APACStage.setScene(new Scene(APACLoader.load()));
            APACStage.setTitle("Ajout d'un nouveau patient");
            APACStage.getIcons().add(new Image("DenMa.png"));
            sexec.setValue("Masculin");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showStage(){APACStage.show();}

    @FXML
    private  void initialize()
    {
        sexec.setItems(sex);
    }

}
