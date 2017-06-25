/**
 * Sample Skeleton for 'SerieA.fxml' Controller Class
 */

package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SerieAController {
	private Model model;
	private Season ultimaSeasonCaricata;
	

    public void setModel(Model model) {
		this.model = model;
		boxSeason.getItems().addAll(model.getStagioni());
		
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxSeason"
    private ChoiceBox<Season> boxSeason; // Value injected by FXMLLoader

    @FXML // fx:id="boxTeam"
    private ChoiceBox<Team> boxTeam; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCarica(ActionEvent event) {
    	if(boxSeason.getValue()!=null){
    		ultimaSeasonCaricata=boxSeason.getValue();
    		model.CreaGrafo(ultimaSeasonCaricata);
    		txtResult.appendText(model.getClassifica()+"\n");
    		boxTeam.getItems().clear();
    		boxTeam.getItems().addAll(model.getTeam());
    	}else{
    		txtResult.appendText("Selezionare una stagione ! \n");
    	}

    }

    @FXML
    void handleDomino(ActionEvent event) {
    	if(boxSeason.getValue()==null||boxSeason.getValue()!=ultimaSeasonCaricata){
    		txtResult.appendText("Caricare il grafo per la stagione di cui si desidera calcolare il domino ! \n");
    		return;
    	}
    	if(boxTeam.getValue()==null){
    		model.RiduciGrafo();
    		ArrayList<Team> result=model.interfacciaRicorsione();
    		txtResult.appendText("Domino: "+result+"\n");
    		txtResult.appendText("Lunghezza: "+result.size()+"\n");
    	}else{
    		model.RiduciGrafo();
    		ArrayList<Team> result=model.interfacciaRicorsione(boxTeam.getValue());
    		txtResult.appendText("Domino: "+result+"\n");
    		txtResult.appendText("Lunghezza: "+result.size()+"\n");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxSeason != null : "fx:id=\"boxSeason\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxTeam != null : "fx:id=\"boxTeam\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";
    }
}
