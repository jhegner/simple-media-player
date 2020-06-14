package br.com.jhegner.smp.view.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.domain.ArquivoAudio;
import br.com.jhegner.smp.domain.ArquivoVideo;
import br.com.jhegner.smp.enums.EBotao;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MediaPlayerViewHelper {

	private Arquivo arquivo;
	
	public MediaPlayerViewHelper() {
		super();
	}

	public MediaPlayerViewHelper(Arquivo arquivo) {
		this();
		this.arquivo = arquivo;
	}

	public void exibeMetadadosMedia(Scene scene) {

		if (arquivo instanceof ArquivoAudio) {

			ArquivoAudio arquivoAudio = (ArquivoAudio) arquivo;

			final Label labelTitulo = (Label) scene.lookup("#labelTitulo");
			labelTitulo.setText(arquivoAudio.getTitulo());

			final Label labelAlbum = (Label) scene.lookup("#labelAlbum");
			labelAlbum.setText(arquivoAudio.getAlbum());

			final Label labelDuracao = (Label) scene.lookup("#labelDuracao");
			labelDuracao.setText(arquivoAudio.getDuracao());

			final ImageView ivAudio = (ImageView) scene.lookup("#imageViewAlbumImage");

			if (arquivoAudio.getImageAlbumByte() != null && arquivoAudio.getImageAlbumByte().length > 0) {

				InputStream is = new ByteArrayInputStream(arquivoAudio.getImageAlbumByte());
				Image imageAudio = new Image(is);

				ivAudio.setImage(imageAudio);

				try {
					is.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			} else {
				ivAudio.setImage(null);
			}
		}
	}

	public void ocultaLabelsInfoMediaPlayer(Scene scene) {

		final Label labelTitulo = (Label) scene.lookup("#labelTitulo");
		labelTitulo.setVisible(Boolean.FALSE);

		final Label labelAlbum = (Label) scene.lookup("#labelAlbum");
		labelAlbum.setVisible(Boolean.FALSE);

		final Label labelDuracao = (Label) scene.lookup("#labelDuracao");
		labelDuracao.setVisible(Boolean.FALSE);
	}

	public void exibeLabelsInfoMediaPlayer(Scene scene) {

		final Label labelTitulo = (Label) scene.lookup("#labelTitulo");
		labelTitulo.setVisible(Boolean.TRUE);

		final Label labelAlbum = (Label) scene.lookup("#labelAlbum");
		labelAlbum.setVisible(Boolean.TRUE);

		final Label labelDuracao = (Label) scene.lookup("#labelDuracao");
		labelDuracao.setVisible(Boolean.TRUE);
	}

	public void configuraBotoesReproducao(Scene scene) {

		final ButtonBase btnReproduzir = (ButtonBase) scene.lookup(EBotao.REPRODUZIR.getId());
		final Label labelReproduzir = (Label) scene.lookup("#labelReproduzir");
		btnReproduzir.setVisible(Boolean.FALSE);
		labelReproduzir.setVisible(Boolean.FALSE);

		final ButtonBase btnPausar = (ButtonBase) scene.lookup(EBotao.PAUSAR.getId());
		final Label labelPausar = (Label) scene.lookup("#labelPausar");
		btnPausar.setVisible(Boolean.TRUE);
		labelPausar.setVisible(Boolean.TRUE);
	}

	public void configuraBotoesParar(Scene scene) {
		
		configuraBotoesPausarParar(scene);
		
		final Label labelDuracao = (Label) scene.lookup("#labelDuracao");
		if(this.arquivo instanceof ArquivoAudio) {
			labelDuracao.setText(((ArquivoAudio)arquivo).getDuracao());
		} else if(this.arquivo instanceof ArquivoVideo) {
			labelDuracao.setText(((ArquivoVideo)arquivo).getDuracao());
		}
		
		final Slider sliderReproducao = (Slider) scene.lookup("#sliderProgress");
		sliderReproducao.setValue(0.0);
	}
	
	public void configuraBotoesPausarParar(Scene scene) {

		final ButtonBase btnReproduzir = (ButtonBase) scene.lookup(EBotao.REPRODUZIR.getId());
		final Label labelReproduzir = (Label) scene.lookup("#labelReproduzir");
		btnReproduzir.setVisible(Boolean.TRUE);
		labelReproduzir.setVisible(Boolean.TRUE);

		final ButtonBase btnPausar = (ButtonBase) scene.lookup(EBotao.PAUSAR.getId());
		final Label labelPausar = (Label) scene.lookup("#labelPausar");
		btnPausar.setVisible(Boolean.FALSE);
		labelPausar.setVisible(Boolean.FALSE);
	}

	public void configuraBotaoSilencio(Scene scene, Boolean isMute) {

		final ButtonBase btnSilencio = (ButtonBase) scene.lookup(EBotao.SILENCIO.getId());

		if (isMute) {
			String imgPath = "/img/mute-3-16_on.png";
			btnSilencio.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(imgPath))));
		} else {
			btnSilencio
					.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(EBotao.SILENCIO.getImgPath()))));
		}
	}

	public void atualizarTempoExecucaoMidia(Scene scene, String duracao) {
		final Label labelDuracao = (Label) scene.lookup("#labelDuracao");
		labelDuracao.setText(duracao);
	}

}
