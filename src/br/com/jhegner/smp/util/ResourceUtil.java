package br.com.jhegner.smp.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public final class ResourceUtil {
	
	public final static Parent getMain() throws IOException {
		return FXMLLoader.load(ResourceUtil.class.getResource("../view/fxml/main.fxml"));
	}
	
	public final static Parent getSplashResource() throws IOException {
		return FXMLLoader.load(ResourceUtil.class.getResource("../view/fxml/splash.fxml"));
	}
	
	public final static Parent getMediaPlayerResource() throws IOException {
		return FXMLLoader.load(ResourceUtil.class.getResource("../view/fxml/media-player.fxml"));
	}
	
}
