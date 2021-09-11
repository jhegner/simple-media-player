package br.com.jhegner.smp.domain;

import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;

import br.com.jhegner.smp.enums.EMedia;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representacao de um arquivo de media. 
 * 
 * @author jhegner
 *
 */
@Getter
@AllArgsConstructor
public class Arquivo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String nome;
	private final String local;
	private final long tamanho;
	private final EMedia midia;
	
	public String getNomeBase() {
		return FilenameUtils.getBaseName(getNome());
	}
	
	public String getNomeCompleto() {
		return (getLocal());
	}
	
	public String getExtensao() {
		return FilenameUtils.getExtension(getNome());
	}
	
	@Override
	public String toString() {
		return getNome(); // usado no listView
	}
}
