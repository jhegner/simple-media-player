package br.com.jhegner.smp.view;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.domain.ArquivoAudio;
import br.com.jhegner.smp.domain.LeitorMetadadoArquivoAudio;
import br.com.jhegner.smp.enums.EMedia;
import br.com.jhegner.smp.listener.ListViewReproducaoChangeListener;
import br.com.jhegner.smp.view.helper.MediaPlayerHelper;
import br.com.jhegner.smp.view.helper.MediaPlayerViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
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
public class MediaPlayerController extends AbstractController {

	private EMedia midia;
	private List<File> files;
	private List<Arquivo> arquivos;

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

		Stage stage = (Stage) listView.getScene().getWindow();

		FileChooser fileChooser = configuraFileChooser(stage);
		this.files = fileChooser.showOpenMultipleDialog(stage);

		leArquivos(this.files);

		configuraComponentes(listView.getScene());
	}

	@FXML
	protected void reproduzir(ActionEvent event) {

		Arquivo arquivo = listView.getSelectionModel().getSelectedItem();

		if (null != arquivo) {

			log.debug("Reproduzindo a media");

			MediaPlayerHelper mdh = MediaPlayerHelper.getInstance(arquivo);
			mdh.play();

			MediaPlayerViewHelper mpvh = new MediaPlayerViewHelper();
			mpvh.configuraBotoesReproducao(this.listView.getScene());
		}
	}

	@FXML
	protected void pausar(ActionEvent event) {

		Arquivo arquivo = listView.getSelectionModel().getSelectedItem();

		if (null != arquivo) {

			log.debug("Pausando a media");

			MediaPlayerHelper.getInstance().pause();

			MediaPlayerViewHelper mpvh = new MediaPlayerViewHelper();
			mpvh.configuraBotoesPausarParar(this.listView.getScene());
		}
	}

	@FXML
	protected void parar(ActionEvent event) {

		Arquivo arquivo = listView.getSelectionModel().getSelectedItem();

		if (null != arquivo) {

			log.debug("Parando a media");

			MediaPlayerHelper.getInstance().stop();

			MediaPlayerViewHelper mpvh = new MediaPlayerViewHelper();
			mpvh.configuraBotoesPausarParar(this.listView.getScene());

		}
	}

	@SuppressWarnings("unchecked")
	private void configuraComponentes(Scene scene) {

		ImageView imageView = (ImageView) scene.lookup("#imageViewAlbumImage");
		imageView.setVisible(Boolean.TRUE);

		Label labelMedia = (Label) scene.lookup("#labelTitulo");
		labelMedia.setVisible(Boolean.TRUE);

		Label labelAlbum = (Label) scene.lookup("#labelAlbum");
		labelAlbum.setVisible(Boolean.TRUE);

		Label labelDuracao = (Label) scene.lookup("#labelDuracao");
		labelDuracao.setVisible(Boolean.TRUE);

		ProgressBar progressBar = (ProgressBar) scene.lookup("#progressBar");
		progressBar.setVisible(Boolean.TRUE);

		ListView<Arquivo> listView = (ListView<Arquivo>) scene.lookup("#listView");
		listView.setVisible(Boolean.TRUE);

	}

	private void leArquivos(List<File> files) {

		if (null != files && files.size() != 0) {
			preencheListView(files);
			configuraListView();
		}
	}

	private void configuraListView() {
		this.listView.getSelectionModel().selectedItemProperty()
				.addListener(new ListViewReproducaoChangeListener(this.listView.getScene()));
	}

	private void preencheListView(List<File> files) {

		this.arquivos = new LinkedList<>();

		for (File file : files) {

			Arquivo arquivo;

			if (EMedia.MUSICA.equals(this.midia)) {
				arquivo = new ArquivoAudio(file.getName(), file.getPath(), file.length(), this.midia);
				LeitorMetadadoArquivoAudio.getInstance().le((ArquivoAudio) arquivo);
			} else {
				arquivo = new Arquivo(file.getName(), file.getParent(), file.length(), this.midia);
			}

			this.arquivos.add(arquivo);
		}

		this.listView.setItems(null);

		ObservableList<Arquivo> obsList = FXCollections.observableArrayList(this.arquivos);
		this.listView.setItems(obsList);

		this.listView.getSelectionModel().select(0);
		this.listView.scrollTo(0);

		MediaPlayerViewHelper helper = new MediaPlayerViewHelper();
		helper.exibeMetadadosMedia(this.arquivos.get(0), this.listView.getScene());
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

			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"),
					new FileChooser.ExtensionFilter("WAV", "*.wav"));

			break;

		case IMAGEM:

			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
					new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

			break;

		case VIDEO:
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP4", "*.mp4"));

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

		if (null != files) {
			this.files = null;
		}

		if (null != arquivos) {
			this.arquivos.clear();
			this.arquivos = null;
		}
	}
}
