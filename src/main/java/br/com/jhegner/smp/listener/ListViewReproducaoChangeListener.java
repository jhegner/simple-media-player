package br.com.jhegner.smp.listener;

import br.com.jhegner.smp.domain.Arquivo;
import br.com.jhegner.smp.view.helper.MediaPlayerViewHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

/**
 * List View Reproducao Change Listener.
 * 
 * @author jhegner
 *
 */
public class ListViewReproducaoChangeListener implements ChangeListener<Arquivo>{
	
    private final Scene scene;

    public ListViewReproducaoChangeListener(Scene scene) {
        this.scene = scene;
    }

	@Override
	public void changed(ObservableValue<? extends Arquivo> observable, Arquivo oldValue, Arquivo newValue) {
		
		MediaPlayerViewHelper helper = new MediaPlayerViewHelper();
		helper.exibeMetadadosMedia(newValue, scene);
		
	}

}
