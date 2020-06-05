package br.com.jhegner.smp.domain;

/**
 * Player padrao
 * 
 * @author jhegner
 *
 */
public interface Player {
	
    public void reproduzir();
    
    public void pausar();
    
    public void parar();
    
    public void reproduzirAnterior();
    
    public void reproduzirProximo();

}
