package br.com.jhegner.smp.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.FileChooser;
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
	private Hyperlink abrirLink;
	
	@FXML
	protected void voltar(ActionEvent event) {
		log.debug("Exibindo a tela principal");
		this.goMainView(event);
	}
	
	@FXML
	protected void abrir(ActionEvent event) {
		
		log.debug("Abrindo caixa de selecao de arquivo");
		
		Scene scene = abrirLink.getScene();
		
		Stage stage = new Stage();
        stage.setResizable(Boolean.FALSE);
        stage.setScene(scene);
		
		// abre janela de dialogo
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir");
		fileChooser.showOpenDialog(stage);	
	}

	@Override
	void goMainView(ActionEvent currentEvent) {
		
		fecharJanela(currentEvent);
		
		App app = new App();
		app.start(new Stage());	
	}
}
