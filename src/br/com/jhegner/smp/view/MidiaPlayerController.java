package br.com.jhegner.smp.view;

import java.io.File;
import java.util.List;

import br.com.jhegner.smp.enums.EMidia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
		
		Stage stage = (Stage) abrirLink.getScene().getWindow();
        
        FileChooser fileChooser = configuraFileChooser(stage);
        List<File> files = fileChooser.showOpenMultipleDialog(stage);
        
        leArquivos(files);
	}

	private void leArquivos(List<File> files) {
		
		if(null != files && files.size() != 0) {
			// TODO preencher list view
		}
		else {
			
		}
	}

	private FileChooser configuraFileChooser(final Stage stage) {
				
		// abre janela de dialogo
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		configuraExtensao(fileChooser, stage);

		return fileChooser;
	}

	private void configuraExtensao(final FileChooser fileChooser, final Stage stage) {
		
		EMidia midia = (EMidia) stage.getUserData();
		
		switch (midia) {

			case MUSICA:
				
	            fileChooser.getExtensionFilters().addAll(
	                    new FileChooser.ExtensionFilter("MP3", "*.mp3"),
	                    new FileChooser.ExtensionFilter("WAV", "*.wav")
	                );				
				
				break;
	
			case IMAGEM:
				
	            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
                );
				
				break;
	
			case VIDEO:
	            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("MP4", "*.mp4")
                );
	
				break;
		}
		
	}

	@Override
	void goMainView(ActionEvent currentEvent) {
		
		fecharJanela(currentEvent);
		
		App app = new App();
		app.start(new Stage());	
	}
}
