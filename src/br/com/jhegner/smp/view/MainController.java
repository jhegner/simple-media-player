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
	protected void goMusicaView(ActionEvent event) {
		
		try {
			
			fecharJanela(event);
			
			abreMidiaPlayerView(EMidia.MUSICA);
		
		} catch (IOException e) {
			log.error("Erro ao abrir a view musica", e);
		}
	}
	
	@FXML
	protected void goImagemView(ActionEvent event) {
		
		try {
			
			fecharJanela(event);
			
			abreMidiaPlayerView(EMidia.IMAGEM);
			
		} catch (IOException e) {
			log.error("Erro ao abrir a view imagem", e);
		}
	}
	
	@FXML
	protected void goVideoView(ActionEvent event) {
		
		try {
			
			fecharJanela(event);
			
			abreMidiaPlayerView(EMidia.VIDEO);
			
		} catch (IOException e) {
			log.error("Erro ao abrir a view video", e);
		}
	}

	private void abreMidiaPlayerView(EMidia midia) throws IOException {
		
		Parent fxmlRoot = FXMLLoader.load(getClass().getResource("fxml/midia-player.fxml"));
		Scene scene = new Scene(fxmlRoot);
		
		Stage stage = new Stage();

        stage.setResizable(Boolean.FALSE);
        stage.setScene(scene);

        stage.setTitle(ETexto.TITULO.getTexto() + " - " + midia.name().toLowerCase());
        stage.getIcons().add(new Image("/img/logo.png"));
        
        configuraBotoes(scene, midia);
        
        stage.show();
	}

	@Override
	void goMain(ActionEvent currentEvent) {
		throw new UnsupportedOperationException("Operacao nao suportada");
	}
}
