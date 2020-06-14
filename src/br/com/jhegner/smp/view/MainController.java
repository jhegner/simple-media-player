package br.com.jhegner.smp.view;

import java.io.IOException;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.enums.EMedia;
import br.com.jhegner.smp.enums.ETexto;
import br.com.jhegner.smp.util.ResourceUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
			
			abreMidiaPlayerView(EMedia.MUSICA);
		
		} catch (IOException e) {
			log.error("Erro ao abrir a view musica", e);
		}
	}
	
	@FXML
	protected void goImagemView(ActionEvent event) {
		
		try {
			
			fecharJanela(event);
			
			abreMidiaPlayerView(EMedia.IMAGEM);
			
		} catch (IOException e) {
			log.error("Erro ao abrir a view imagem", e);
		}
	}
	
	@FXML
	protected void goVideoView(ActionEvent event) {
		
		try {
			
			fecharJanela(event);
			
			abreMidiaPlayerView(EMedia.VIDEO);
			
		} catch (IOException e) {
			log.error("Erro ao abrir a view video", e);
		}
	}

	private void abreMidiaPlayerView(EMedia media) throws IOException {
		
		Parent fxmlRoot = ResourceUtil.getMediaPlayerResource();
		Scene scene = new Scene(fxmlRoot);
		
		Stage stage = new Stage();

        stage.setResizable(Boolean.FALSE);
        stage.setScene(scene);

        stage.setTitle(ETexto.TITULO.getTexto());
        stage.getIcons().add(new Image("/img/logo.png"));
        
        stage.setUserData(media);
        
        // label para identificar o tipo media
        Label labelMedia = (Label) scene.lookup("#lblMedia");
        labelMedia.setText(media.name());
        
        configuraBotoes(scene, media);
        
        configuraComponentes(scene);
        
        stage.show();
	}

	@SuppressWarnings("unchecked")
	private void configuraComponentes(Scene scene) {
		
		ImageView imageView = (ImageView) scene.lookup("#imageViewAlbumImage");
		imageView.setVisible(Boolean.FALSE);
		
		Label labelMedia = (Label) scene.lookup("#labelTitulo");
		labelMedia.setVisible(Boolean.FALSE);
		
		Label labelAlbum = (Label) scene.lookup("#labelAlbum");
		labelAlbum.setVisible(Boolean.FALSE);
		
		Label labelDuracao = (Label) scene.lookup("#labelDuracao");
		labelDuracao.setVisible(Boolean.FALSE);
		
		Slider slider = (Slider) scene.lookup("#sliderProgress");
		slider.setVisible(Boolean.FALSE);
		
		ListView<Arquivo> listView = (ListView<Arquivo>) scene.lookup("#listView");
		listView.setVisible(Boolean.FALSE);
		
	}

	@Override
	void goMainView(ActionEvent currentEvent) {
		throw new UnsupportedOperationException("Operacao nao suportada");
	}
}
