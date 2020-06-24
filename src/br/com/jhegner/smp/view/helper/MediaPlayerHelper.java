package br.com.jhegner.smp.view.helper;

import java.io.File;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.listener.MediaEmReproducaoChangeListener;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerHelper {

	private MediaPlayer mp;
	private static MediaPlayerHelper instance;
	private MediaPlayerViewHelper mpvh;
	
	public static final double MIN_SOUND_VOLUME = 0.0;
	public static final double MAX_SOUND_VOLUME = 1.0;

	private Arquivo arquivo;
	
	private Scene scene;

	private MediaPlayerHelper(Arquivo arquivo, Scene scene) {
		this.arquivo = arquivo;
		this.scene = scene;
		this.mpvh = new MediaPlayerViewHelper(arquivo);
	}

	public void play() {

		if (null == mp) {

			mp = new MediaPlayer(new Media(new File(arquivo.getNomeCompleto()).toURI().toString()));
			mp.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					if (mpIstNotNull()) {
						mp.stop();
						mp.dispose();
						mp = null;
						resetarConfigurarInfoReproducao();
					}
				}
			});
			
			MediaEmReproducaoChangeListener mcl = new MediaEmReproducaoChangeListener(mp, scene);
			mp.currentTimeProperty().addListener(mcl.currentTimeListener());
		}
		mp.play();
	}

	public void stop() {
		if (mpIstNotNull()) {
			mp.stop();
			mp.dispose();
			mp = null;
			instance.arquivo = null;
			resetarConfigurarInfoReproducao();
		}
	}

	private void resetarConfigurarInfoReproducao() {
		mpvh.configuraBotoesParar(scene);
	}

	public void pause() {
		if (mpIstNotNull()) {
			mp.pause();
		}
	}
	
	public void muteOrRemoveMute() {
		Boolean bool = mp.isMute() ? Boolean.FALSE : Boolean.TRUE;
		if (mpIstNotNull()) {
			mp.setMute(bool);
		}
	}
	
	public boolean isMute() {
		return mp.isMute();
	}
	
	private boolean mpIstNotNull() {
		return (mp != null);
	}

	public static MediaPlayerHelper getInstance() {
		return instance;
	}

	public static MediaPlayerHelper getInstance(Arquivo arquivo, Scene scene) {
		if (instance == null || instance.arquivo != null) {
			instance = new MediaPlayerHelper(arquivo, scene);
		}
		return instance;
	}
}
