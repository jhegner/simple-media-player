package br.com.jhegner.smp.domain;

import br.com.jhegner.smp.enums.EMidia;
import lombok.Getter;

/**
 * Representacao de um arquivo de media de audio. 
 * 
 * @author jhegner
 *
 */
@Getter
public class ArquivoAudio extends Arquivo{

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String duracao;
	private String album;
	private String artista;
	
	public ArquivoAudio(String nome, String local, long tamanho, EMidia midia) {
		super(nome, local, tamanho, midia);
	}
	
}
