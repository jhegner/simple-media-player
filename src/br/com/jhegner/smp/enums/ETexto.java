package br.com.jhegner.smp.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ETexto {
	
	TITULO("Simple Media Player");

	private String texto;
	
	public String getTexto() {
		return texto;
	}

}
