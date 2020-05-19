package br.com.jhegner.smp.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller do fxml - play
 * 
 * @author jhegner
 *
 */
@Slf4j
@NoArgsConstructor
public class MidiaPlayerController extends AbstractController{
	
	@FXML
	protected void goMainView(ActionEvent event) {
		log.debug("Exibindo a tela principal");
		this.goMain(event);
	}

	@Override
	void goMain(ActionEvent currentEvent) {
		
		fecharJanela(currentEvent);
		
		App app = new App();
		app.start(new Stage());		
	}
}
