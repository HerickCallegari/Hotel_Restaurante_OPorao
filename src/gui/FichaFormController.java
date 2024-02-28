package gui;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.City;
import model.entities.Client;
import model.entities.State;
import model.services.ClientService;
import model.services.StateService;

public class FichaFormController implements Initializable{
	
	private Client entity;
	
	private ClientService service;

	private StateService serviceState;
	
	private FichaListController listController;
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtEndereco;
	
	@FXML
	private TextField txtIdentifyCode;
	
	@FXML
	private TextField txtCidade;
	
	@FXML
	private TextField txtNumero;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private ComboBox<State> comboBoxState;
	
	private ObservableList<State> obsList;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancel;
	
	@FXML
	public void onSalvarBtAction() {
		System.out.println("save");
		listController.updateTableView();
	}
	
	@FXML
	public void onCancelarBtAction() {
		System.out.println("Cancelar");
		listController.updateTableView();
	}
	
	@FXML
	public void onComboBoxStatesSelect() {
		State value = comboBoxState.getValue();
		System.out.println("niggaaaaaaa");
		autoFildCities(value);
	}

	public void loadComboBox() {
		obsList = FXCollections.observableArrayList(serviceState.findAll());
		comboBoxState.setItems(obsList);
	}
	
	public void autoFildCities(State obj) {
		List<String> words = new ArrayList<String>();
		serviceState.findAllCities(obj).forEach(x -> words.add(x.getName()));
		words.add("nigga");
		words.add("nigga");
		words.add("nigga");
		words.add("nigga");
		words.forEach(System.out::println);
		TextFields.bindAutoCompletion(txtCidade, words);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public Client getEntity() {
		return entity;
	}

	public void setEntity(Client entity) {
		this.entity = entity;
	}

	public void setServices(ClientService service, StateService serviceState) {
		this.service = service;
		this.serviceState = serviceState;
	}
	
	public void setFichaList(FichaListController listController) {
		this.listController = listController;
	}
	
}
