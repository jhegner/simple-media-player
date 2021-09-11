package br.com.jhegner.smp.view.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.domain.ArquivoAudio;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MediaPlayerViewHelper {
	
	public void exibeMetadadosMedia(Arquivo arquivo, Scene scene) {
		
		if(arquivo instanceof ArquivoAudio) {
			
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

}
