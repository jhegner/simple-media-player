package br.com.jhegner.smp.listener;

import br.com.jhegner.smp.util.DateTimeUtil;
import br.com.jhegner.smp.util.NumeroUtil;
import br.com.jhegner.smp.view.helper.MediaPlayerHelper;
import br.com.jhegner.smp.view.helper.MediaPlayerViewHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaEmReproducaoChangeListener {

	private Scene scene;
	private MediaPlayer mp;
	private MediaPlayerViewHelper viewHelper;
	
	public MediaEmReproducaoChangeListener() {
		super();
		this.viewHelper = new MediaPlayerViewHelper();
	}

	public MediaEmReproducaoChangeListener(MediaPlayer mp, Scene scene) {
		this();
		this.mp = mp;
		this.scene = scene;
	}

	public ChangeListener<? super Duration> currentTimeListener() {

		final Slider sliderReproducao = (Slider) scene.lookup("#sliderProgress");

		sliderReproducao.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (mp != null && !MediaPlayer.Status.DISPOSED.equals(mp.getStatus())
						&& !MediaPlayer.Status.STOPPED.equals(mp.getStatus())) {

					if (NumeroUtil.getDoubleFormatado(newValue.doubleValue() - oldValue.doubleValue()) > 0.1) { // avanco

						mp.setVolume(MediaPlayerHelper.MIN_SOUND_VOLUME); // tira o som durante o avanco
						mp.seek(Duration.seconds(newValue.doubleValue())); // posiciona o avanco
						mp.setVolume(MediaPlayerHelper.MAX_SOUND_VOLUME); // tira o som depois do avanco :)

					} else if (oldValue.doubleValue() > newValue.doubleValue()) { // recuo

						mp.setVolume(MediaPlayerHelper.MAX_SOUND_VOLUME); // tira o som durante o recuo
						mp.seek(Duration.seconds(newValue.doubleValue())); // posiciona o recuo
						mp.setVolume(MediaPlayerHelper.MAX_SOUND_VOLUME); // tira o som depois do reuco :)
					}
				}
			}
		});

		ChangeListener<Duration> progressChangeListener = new ChangeListener<Duration>() {

			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

				if (mp != null) {
					sliderReproducao.setMax(mp.getTotalDuration().toSeconds());
					sliderReproducao.increment(); // incrementa 0.1
					String duracao = DateTimeUtil.formataTempo(mp.getTotalDuration(), mp.getCurrentTime());
					viewHelper.atualizarTempoExecucaoMidia(scene, duracao);
				}
			}
		};

		return progressChangeListener;
	}
}
