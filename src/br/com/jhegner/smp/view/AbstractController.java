package br.com.jhegner.smp.view;

import br.com.jhegner.smp.enums.EBotao;
import br.com.jhegner.smp.enums.EMidia;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Abstract Controller fxml
 * 
 * @author jhegner
 *
 */
public abstract class AbstractController {
	
	void fecharJanela(ActionEvent currentEvent) {
		Node source = (Node) currentEvent.getSource();
		Stage current = (Stage) source.getScene().getWindow();
		current.close();
	}
	
	void configuraBotoes(Scene scene, EMidia midia) {
		
		for(EBotao botao : EBotao.values()) {
			ButtonBase btnConf = (ButtonBase) scene.lookup(botao.getId());
			btnConf.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(botao.getImgPath()))));
			btnConf.setTooltip(new Tooltip(botao.getTooltip()));
		}
	}
	
	abstract void goMain(ActionEvent currentEvent);
}
