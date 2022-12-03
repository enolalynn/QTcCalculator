package com.enola.controller;

import java.io.IOException;

import com.enola.QtcStart;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class QTcController {
	@FXML
	void img_main(MouseEvent event) throws IOException {
		QtcStart.changeScene("view/Calculator.fxml", "QTc Calculator");		
 }
	
}
