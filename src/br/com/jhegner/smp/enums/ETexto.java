package br.com.jhegner.smp.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ETexto {
	
	TITULO("Simple Media Player"),
	TITULO_JANElA_SELECAO_ARQUIVO("Simple Media Player - Abrir arquivo de media");

	private String texto;
	
	public String getTexto() {
		return texto;
	}

}
