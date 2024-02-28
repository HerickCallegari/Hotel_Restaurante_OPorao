package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.ClientService;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemFichas;	
	
	@FXML
	private MenuItem menuItemQuartos;
	
	@FXML
	private MenuItem menuItemRegistros;
	
	@FXML
	public void onMenuItemFichasAction() {
		loadView("/gui/FichasListView.fxml", (FichaListController controller) -> {
		controller.setClientService(new ClientService());
		controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemQuartosAction() {
		System.out.println("QUARTOS");
	}
	
	@FXML
	public void onMenuItemRegistrosAction() {
		System.out.println("Registros");
	}
	
	//Troca de tela
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializeController) {
		try {
			//Recebe o loader da tela que vai ser carregada
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVBox = loader.load();
		
		//Recebe a mainScene
		Scene mainScene = Main.getMainScene();
		//Recebe o VBox (tela atual)
		VBox mainVBox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();
		
		//Guarda a MenuBar (Isto acontece porque o MenuBar deve ficar em todas as telas)
		Node mainMenu = mainVBox.getChildren().get(0);
		
		//Retira todos o conteudo da VBox atual
		mainVBox.getChildren().clear();
		//Adciona o MenuBar
		mainVBox.getChildren().add(mainMenu);
		//Carrega todo o Conteudo do newVBox(tela que se deseja carregar) e carrega na mainVBox(tela atual)
		mainVBox.getChildren().addAll(newVBox.getChildren());
		
		initializeController.accept(loader.getController());
		
		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("Error load view", "Load Error" , e.getMessage(), AlertType.ERROR);
		} 
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

}
