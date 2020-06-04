package br.com.jhegner.smp.view;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.domain.ArquivoAudio;
import br.com.jhegner.smp.enums.EMedia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
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
	
	private EMedia midia;
	private List<File> files;
	private List<Arquivo> arquivos;
	
	@FXML
	private Hyperlink abrirLink;
	
	@FXML
	private ListView<Arquivo> listView;
	
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
        this.files = fileChooser.showOpenMultipleDialog(stage);
        
        leArquivos(this.files);
	}

	private void leArquivos(List<File> files) {
		
		if(null != files && files.size() != 0) {
			preencheListView(files);
		}
	}

	private void preencheListView(List<File> files) {
		
		this.arquivos = new LinkedList<>();
		
		for(File file : files) {
			
			Arquivo arquivo;
			
			if(EMedia.MUSICA.equals(this.midia)) {
				arquivo = new ArquivoAudio(file.getName(), file.getPath(), file.length(), this.midia);
			}
			else {
				arquivo = new Arquivo(file.getName(), file.getParent(), file.length(), this.midia);
			}
			
			this.arquivos.add(arquivo);
		}
		
		ObservableList<Arquivo> obsList = FXCollections.observableArrayList(this.arquivos);
		this.listView.setItems(obsList);
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
		
		this.midia = (EMedia) stage.getUserData();
		
		switch (this.midia) {

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
		
		limparObjs();
		fecharJanela(currentEvent);
		
		App app = new App();
		app.start(new Stage());	
	}

	private void limparObjs() {
		
		if(null != files) {
			this.files.clear();
			this.files = null;
		}
		
		if(null != arquivos) {
			this.arquivos.clear();
			this.arquivos = null;
		}
	}
}
