package com.example.haiterog;

import com.example.haiterog.domain.Intrebare;
import com.example.haiterog.repository.RepositoryException;
import com.example.haiterog.service.ServiceIntrebare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class HelloController {
    @FXML
    public ListView lstMessage;
    @FXML
    public Button afiseazaIntrebari;
    @FXML
    public Button addIntrebare;
    @FXML
    public TextField id_intrebare;
    @FXML
    public TextField text;
    @FXML
    public TextField raspunsA;
    @FXML
    public TextField raspunsB;
    @FXML
    public TextField raspunsC;
    @FXML
    public TextField corect;
    @FXML
    public TextField punctaj;
    @FXML
    public Button removeIntrebare;
    @FXML
    public TextField id_intrebare_mod;
    @FXML
    public TextField punctajminim;
    @FXML
    public TextField punctajmaxim;
    @FXML
    public TextField nrintrebari;
    @FXML
    public Button updateIntrebare;
    @FXML
    public Button generareTest;
    @FXML
    ObservableList<String> list = FXCollections.observableList(new ArrayList<>());

    ServiceIntrebare serviceIntrebare = new ServiceIntrebare();

    @FXML
    public void getAllIntrebari(ActionEvent actionEvent) throws RepositoryException {
        list.clear();
        Collection<Intrebare> lista = serviceIntrebare.getAllI();
        for(Intrebare i:lista) {
            list.add(i.getId()+" "+i.getText()+" "+i.getPunctaj());
        }
        lstMessage.setItems(list);
    }

    public void addIntrebare(ActionEvent actionEvent) {
        int id;
        String text;
        String raspunsA;
        String raspunsB;
        String raspunsC;
        String corect;
        int punctaj;
        int max = 0;
        try{
            Collection<Intrebare> lista = serviceIntrebare.getAllI();
            for(Intrebare i:lista) {
                if(max < i.getId())
                {
                    max = i.getId();
                }
            }
            id = max+1;
            text= this.text.getText();
            raspunsA= this.raspunsA.getText();
            raspunsB= this.raspunsB.getText();
            raspunsC= this.raspunsC.getText();
            corect= this.corect.getText();
            punctaj = Integer.parseInt(this.punctaj.getText());
            if(text.isEmpty() || raspunsA.isEmpty() || raspunsB.isEmpty() || raspunsC.isEmpty() || corect.isEmpty() )
            {
                Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti dateeee!!!!");
                hello.show();
            }
            else
            {
                serviceIntrebare.addIntrebare(id,text,raspunsA,raspunsB,raspunsC,corect,punctaj);
            }
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();
        }catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }
    }

    public void removeIntrebare(ActionEvent actionEvent) {
        int id;
        try{
            id = Integer.parseInt(this.id_intrebare_mod.getText());
            serviceIntrebare.removeIntrebare(id);
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();
        }catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }
    }

    public void updateIntrebare(ActionEvent actionEvent) {
        int id;
        String text;
        String raspunsA;
        String raspunsB;
        String raspunsC;
        String corect;
        int punctaj;
        try {
            id = Integer.parseInt(this.id_intrebare_mod.getText());
            text= this.text.getText();
            raspunsA= this.raspunsA.getText();
            raspunsB= this.raspunsB.getText();
            raspunsC= this.raspunsC.getText();
            corect= this.corect.getText();
            punctaj = Integer.parseInt(this.punctaj.getText());
            serviceIntrebare.updateIntrebare(id,text,raspunsA,raspunsB,raspunsC,corect,punctaj);
        }
        catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();}
    }

    public void generareTest(ActionEvent actionEvent) {
        int minim;
        int maxim;
        int nr;
        try {
            minim = Integer.parseInt(this.punctajminim.getText());
            maxim = Integer.parseInt(this.punctajmaxim.getText());
            nr = Integer.parseInt(this.nrintrebari.getText());
            Collection<Intrebare> lista = serviceIntrebare.getAllI();
            Collection<Intrebare> rez = new ArrayList<Intrebare>();
            for(Intrebare i:lista) {
                if(i.getPunctaj()>=minim && i.getPunctaj()<=maxim)
                {
                    if(nr!=0)
                    {
                        rez.add(i);
                        nr--;
                    }
                }
            }
            if(nr!=0)
            {
                Alert hello = new Alert(Alert.AlertType.ERROR,"Nu sunt suficiente intrebari");
                hello.show();
            }
            else {
                BufferedWriter writer = new BufferedWriter(new FileWriter("rezultate.txt"));

                for (Intrebare i : rez) {
                    writer.write(i.toString());
                    writer.newLine();
                }
                writer.close();
            }
        }
        catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();}
        catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }
        catch (IOException e) {
            Alert hello = new Alert(Alert.AlertType.ERROR, "Error writing to file: " + e.getMessage());
            hello.show();
        }
    }
}