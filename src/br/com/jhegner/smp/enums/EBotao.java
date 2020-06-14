package br.com.jhegner.smp.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EBotao {
	
	REPRODUZIR("#reproduzir", "Reproduzir", "/img/play-16.png"),
	PAUSAR("#pausar", "Pausar", "/img/pause-16.png"),
	PARAR("#parar", "Parar", "/img/stop-16.png"),
	PROXIMO("#proximo", "Proximo", "/img/media-skip-forward-16.png"),
	ANTERIOR("#anterior", "Anterior", "/img/media-skip-backward-16.png"),
	REPETIR("#repetir", "Repetir", "/img/recurring-appointment-16.png"),
	ALEATORIO("#aleatorio", "Aleatorio", "/img/random-16.png"),
	SILENCIO("#silencio", "Silencio", "/img/mute-3-16.png");

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
