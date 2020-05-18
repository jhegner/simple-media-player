package br.com.jhegner.smp.view;

import java.io.IOException;

import br.com.jhegner.smp.enums.EMidia;
import br.com.jhegner.smp.enums.ETexto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller do fxml - play
 * 
 * @author jhegner
 *
 */
@Slf4j
public class MidiaPlayerController extends AbstractController{
	
	public MidiaPlayerController() {
		super();
	}
	
	@FXML
	protected void goMainView(ActionEvent event) {

		this.goMain(event);
	}

	@Override
	void goMain(ActionEvent currentEvent) {
		
		fecharJanela(currentEvent);
		
		App app = new App();
		app.start(new Stage());		
	}
}
