package br.com.jhegner.smp.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public final class ResourceUtil {
	
	public static Parent getMainResource() throws IOException {
		return FXMLLoader.load(ResourceUtil.class.getResource("/fxml/main.fxml"));
	}
	
	public static Parent getSplashResource() throws IOException {
		return FXMLLoader.load(ResourceUtil.class.getResource("/fxml/splash.fxml"));
	}
	
	public static Parent getMediaPlayerResource() throws IOException {
		return FXMLLoader.load(ResourceUtil.class.getResource("/fxml/media-player.fxml"));
	}
	
}
