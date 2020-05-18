package br.com.jhegner.smp.view;

import java.io.IOException;

import br.com.jhegner.smp.enums.ETexto;
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
import lombok.extern.slf4j.Slf4j;

/**
 * Classe de inicio do app.
 * 
 * @author jhegner
 *
 */
@Slf4j
public class App extends Application {

	private static boolean exibirSplash = Boolean.TRUE;

	@Override
	public void start(final Stage stage) {
		
		log.info("Iniciando o Simple Media Player");

		try {
			
			final Stage splashStage = new Stage();

			if (exibirSplash) {
				
				log.debug("Preparando Splash para exibicao");

				// splash (tela boas vindas)
				Parent splash = FXMLLoader.load(getClass().getResource("fxml/splash.fxml"));

				Scene scene = new Scene(splash);
				scene.setCursor(Cursor.WAIT);
				
				splashStage.setScene(scene);
				splashStage.centerOnScreen();
				splashStage.initStyle(StageStyle.UNDECORATED);
				splashStage.getIcons().add(new Image("/img/logo.png"));

				log.debug("Exibindo tela splash");
				splashStage.show();
			}

			// tela principal
			Parent main = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
			Scene scene = new Scene(main);

			stage.setTitle(ETexto.TITULO.getTexto());
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.setResizable(Boolean.FALSE);
			stage.getIcons().add(new Image("/img/logo.png"));

			// botoes
			configuraBotoes(scene);

			if (exibirSplash) {
				
				log.debug("Configurando tempo de exibicao da janela Splash");
				log.debug("Duracao de 3000 millisecond");
				
				// transicao do splash
				PauseTransition ps = new PauseTransition(new Duration(3000));
				ps.setOnFinished(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						log.info("Fechando a janela Splash e exibindo a janela principal");
						// fecha o palco do splash
						splashStage.close();
						// exibe o palco principal
						stage.show();
					}
				});

				ps.play();

				exibirSplash = Boolean.FALSE;

			} else {
				stage.show();
			}
			
		} catch (IOException e) {
			log.error("Erro ao abrir arquivo fxml", e);
		}
	}

	private void configuraBotoes(Scene scene) {
		
		log.debug("Configurando botoes da tela principal");

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
