package br.com.jhegner.smp.domain;

import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;

import br.com.jhegner.smp.enums.EMidia;
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
	private final EMidia midia;
	
	public String getNomeBase() {
		return FilenameUtils.getBaseName(getNome());
	}
	
	public String getNomeCompleto() {
		return (getNome() + getLocal());
	}
	
	public String getExtensao() {
		return FilenameUtils.getExtension(getNome());
	}
}
