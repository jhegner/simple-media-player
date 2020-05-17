package br.com.jhegner.smp.view;

import java.io.IOException;

import br.com.jhegner.smp.enums.EMidia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller do fxml - main
 * 
 * @author jhegner
 *
 */
@Slf4j
public class MainController extends AbstractController{
	
	public MainController() {
		super();
	}
	
	@FXML
	protected void goMusicView(ActionEvent event) {
		
		try {
			
			fecharJanela(event);
			
			abreJanelaMusicView();
		
		} catch (IOException e) {
			log.error("Erro ao abrir a view music", e);
		}
	}

	private void abreJanelaMusicView() throws IOException {
		
		// splash (tela boas vindas)
		Parent fxmlRoot = FXMLLoader.load(getClass().getResource("fxml/music.fxml"));
		Scene scene = new Scene(fxmlRoot);
		
		Stage stage = new Stage();

        stage.setResizable(Boolean.FALSE);
        stage.setScene(scene);

        stage.setTitle("Simple Media Player");
        stage.getIcons().add(new Image("/img/logo.png"));
        
        configuraBotoes(scene, EMidia.MUSICA);
        
        stage.show();
		
	}
}
