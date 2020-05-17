package br.com.jhegner.smp.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EBotao {
	
	PLAY("#play", "Play", "/img/play-32.png"),
	STOP("#stop", "Stop", "/img/stop-32.png"),
	PROXIMO("#proximo", "Proximo", "/img/media-skip-forward-32.png"),
	ANTERIOR("#anterior", "Anterior", "/img/media-skip-backward-32.png"),
	REPETIR("#repetir", "Repetir", "/img/recurring-appointment-32.png"),
	ALEATORIO("#aleatorio", "Aleatorio", "/img/random-32.png"),
	SILENCIO("#silencio", "Silencio", "/img/mute-3-32.png");

	private String id;
	private String tooltip;
	private String imgPath;

	public String getId() {
		return id;
	}

	public String getTooltip() {
		return tooltip;
	}

	public String getImgPath() {
		return imgPath;
	}

}
