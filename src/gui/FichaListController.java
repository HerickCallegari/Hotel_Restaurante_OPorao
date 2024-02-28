package gui;

import java.awt.IllegalComponentStateException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Client;
import model.services.ClientService;
import model.services.StateService;

public class FichaListController implements Initializable{
	
	private ClientService service;
	
	private ObservableList<Client> obsList;

	@FXML
	private Button btNew;
	
	@FXML
	private TableView<Client> tableViewClient;
	
	@FXML
	private TableColumn<Client, Date> tableColumnDate;
	
	@FXML
	private TableColumn<Client, String> tableColumnName;
	
	@FXML
	private TableColumn<Client, String> tableColumnIdentifyCode;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage stage = Utils.currentStage(event);
		CreateDialogForm(new Client(), "/gui/FichaForm.fxml" , stage);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnIdentifyCode.setCellValueFactory(new PropertyValueFactory<>("identifyCode"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewClient.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void setClientService(ClientService service) {
		this.service = service;
	}
	
	public void updateTableView() {
		if ( service == null) {
			throw new IllegalComponentStateException("Service was Null");
		}
		
		obsList = FXCollections.observableArrayList(service.findAll());
		tableViewClient.setItems(obsList);
		
	}
	
	public void CreateDialogForm(Client obj, String absolutePath, Stage parentStage) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutePath));
		Pane pane = loader.load();
		
		FichaFormController controller = loader.getController();
		controller.setEntity(obj);
		controller.setServices(new ClientService(), new StateService());
		controller.setFichaList(this);
		controller.loadComboBox();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de Cliente");
		dialogStage.setScene(new Scene(pane));
		dialogStage.setResizable(false);
		dialogStage.initOwner(parentStage);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.showAndWait();
		
		}catch(IOException e) {
			e.printStackTrace();
			Alerts.showAlert("Carregar Formulario", "Falha no Formulario", e.getMessage(), AlertType.ERROR);
		}
	}

	

}
