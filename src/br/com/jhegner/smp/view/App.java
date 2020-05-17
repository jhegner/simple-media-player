package br.com.jhegner.smp.view;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Classe de inicio do app.
 * 
 * @author Jidlafe Hegner
 * @since 11/12/2018
 *
 */
public class App extends Application {

	private static boolean exibirSplash = Boolean.TRUE;

	@Override
	public void start(final Stage stage) throws Exception {

		final Stage splashStage = new Stage();

		if (exibirSplash) {

			// splash (tela boas vindas)
			Parent splash = FXMLLoader.load(getClass().getResource("fxml/splash.fxml"));

			Scene scene = new Scene(splash);
			scene.setCursor(Cursor.WAIT);
			splashStage.setScene(scene);

			splashStage.centerOnScreen();
			splashStage.initStyle(StageStyle.UNDECORATED);
			stage.getIcons().add(new Image("/img/logo.png"));

			splashStage.show();

		}

		// tela principal
		Parent main = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
		Scene scene = new Scene(main);

		stage.setTitle("Simple Media Player");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setResizable(Boolean.FALSE);

		// botoes
		configuraBotoes(scene);

		if (exibirSplash) {

			// transicao do splash
			PauseTransition ps = new PauseTransition(new Duration(5000));
			ps.setOnFinished(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent event) {
					// fecha o palco do splash
					splashStage.close();
					// exibe o palco principal
					stage.show();
				}
			});

			ps.play();

			exibirSplash = Boolean.TRUE;

		} else {
			stage.show();
		}
	}

	private void configuraBotoes(Scene scene) {

		// conf
		ButtonBase btnConf = (ButtonBase) scene.lookup("#" + "btnConf");
		btnConf.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/cog-16.png"))));
		btnConf.setTooltip(new Tooltip("Configuração"));

		// musica
		ButtonBase btnMusica = (ButtonBase) scene.lookup("#" + "btnMusica");
		btnMusica.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/note-128.png"))));
		btnMusica.setTooltip(new Tooltip("Musica"));

		// imagem
		ButtonBase btnImagem = (ButtonBase) scene.lookup("#" + "btnImagem");
		btnImagem.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/photo-128.png"))));
		btnImagem.setTooltip(new Tooltip("Imagem"));

		// video
		ButtonBase btnVideo = (ButtonBase) scene.lookup("#" + "btnVideo");
		btnVideo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/video-call-128.png"))));
		btnVideo.setTooltip(new Tooltip("Video"));

	}

	public static void main(String[] args) {
		launch(args);
	}

}
