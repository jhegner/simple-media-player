package br.com.jhegner.smp.util;

import javafx.util.Duration;

public final class DateTimeUtil {
	
	public static String formataTempo(final Duration tempoTotal, final Duration tempoCorrente) {

		final long tempoCorrenteEmSegundos = new Double(tempoCorrente.toSeconds()).longValue();

		long horasCorrente = 0;
		long minutosCorrente = 0;
		long segundosCorrente = 0;
		
		// -- atribui o valor das horas corrente
		horasCorrente = (tempoCorrenteEmSegundos / 3600);
		if (horasCorrente < 0) {
			horasCorrente = 0;
		}

		// -- atribui o valor dos minutos corrente
		minutosCorrente = ((tempoCorrenteEmSegundos % 3600) / 60);
		if (minutosCorrente < 0) {
			horasCorrente = 0;
		}

		// -- atribui o valor dos segundos correntes
		segundosCorrente = (tempoCorrenteEmSegundos % 60);
		if (segundosCorrente < 0) {
			segundosCorrente = 0;
		}

		return String.format("%02d:%02d:%02d", horasCorrente, minutosCorrente, segundosCorrente);

	}

}
