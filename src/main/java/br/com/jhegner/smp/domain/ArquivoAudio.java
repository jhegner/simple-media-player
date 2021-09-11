package br.com.jhegner.smp.domain;

import br.com.jhegner.smp.enums.EMedia;
import lombok.Getter;
import lombok.Setter;

/**
 * Representacao de um arquivo de media de audio. 
 * 
 * @author jhegner
 *
 */
@Getter
@Setter
public class ArquivoAudio extends Arquivo{

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String duracao;
	private String album;
	private String artista;
	private byte[] imageAlbumByte;
	
	public ArquivoAudio(String nome, String local, long tamanho, EMedia midia) {
		super(nome, local, tamanho, midia);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(titulo).append(".").append(getExtensao()).append(" - ").append(artista);
		return sb.toString();
	}
}
