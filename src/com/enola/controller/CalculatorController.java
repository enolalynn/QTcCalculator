package com.enola.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.enola.QtcStart;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class CalculatorController implements Initializable {
	@FXML
    private ComboBox<String> cbo_formula;


    @FXML
    private TextField txt_HR;

    @FXML
    private TextField txt_QTinterval;

    @FXML
    private TextField txt_result;
    
    private Double result,heartRate,qtInterval,RR= null;
    @FXML
    void btn_calculate(MouseEvent event) {
    	try {
    		if(txt_HR.getText().isEmpty()) {
        		showAlert(AlertType.ERROR, "Heart rate is required!");	
        		return ;
        	}
        	if(txt_QTinterval.getText().isEmpty()) {
        		showAlert(AlertType.ERROR, "QT Interval is required!");
        		return ;
        	}
    		heartRate = Double.parseDouble(txt_HR.getText());
        	qtInterval = Double.parseDouble(txt_QTinterval.getText());
        	
        	RR = 60/heartRate;
        	var formula = cbo_formula.getSelectionModel().getSelectedItem();
        	
        	if(formula == "Bazett") {
        		result = Bazett();
        	}else if(formula == "Fridericia") {
        		result = Fridericia();
        	}else if(formula == "Framingham") {
        		result = Framingham();
        	}else if(formula == "Hodges") {
        		result = Hodges();
        	}else{
        		result = Rautaharju();
        	}
        	
        	txt_result.setText(String.valueOf(Math.round(result)));
        	
        	
		} catch (Exception e) {
			e.printStackTrace();
			showAlert(AlertType.ERROR, e.getMessage());
		}
    	
    }

    private Double Rautaharju() {
    	result = qtInterval*(120+heartRate)/180;
		return result;
	}

	private Double Hodges() {
    	result = qtInterval+(0.154*(1-RR));
		return result;
	}

	private Double Framingham() {
    	result = qtInterval+(0.154*(1-RR));
		return result;
	}

	private Double Fridericia() {
		result = qtInterval/Math.cbrt(RR);
		return result;
	}

	private Double Bazett() {
    	
    	result = qtInterval/Math.sqrt(RR); 
		return result;
	
	}

	private Optional<ButtonType> showAlert(AlertType type, String msg) {
		Alert alert = new Alert(type);
		alert.setContentText(msg);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		
		return alert.showAndWait();
	}

	@FXML
    void btn_reset(MouseEvent event) {
		txt_HR.setText(null);
		txt_QTinterval.setText(null);
		txt_result.setText(null);
    }

    @FXML
    void btn_back(ActionEvent event) throws IOException {
    	QtcStart.changeScene("view/QtcStart.fxml", "QTc Calculator");	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<String> formulas = List.of("Bazett", "Fridericia" , "Framingham" ,"Hodges" , "Rautaharju");
		cbo_formula.setItems(FXCollections.observableArrayList(formulas));
	}
}
