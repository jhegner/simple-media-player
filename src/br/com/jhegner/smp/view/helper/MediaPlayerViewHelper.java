package br.com.jhegner.smp.view.helper;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.domain.ArquivoAudio;
import javafx.scene.Scene;
import javafx.scene.control.Label;

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
		
		}
		
	}

}
