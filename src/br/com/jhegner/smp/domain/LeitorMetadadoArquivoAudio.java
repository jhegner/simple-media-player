package br.com.jhegner.smp.domain;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import lombok.extern.slf4j.Slf4j;

/**
 * Le os metadados de um arquivo de media de audio. 
 * 
 * @author jhegner
 *
 */
@Slf4j
public final class LeitorMetadadoArquivoAudio {
	
	private static LeitorMetadadoArquivoAudio instance;
	
	private LeitorMetadadoArquivoAudio() {
	}
	
	public void le(ArquivoAudio arquivo) {
		
		AudioFile audioFile;
		
		try {
			
			audioFile = AudioFileIO.read(new File(arquivo.getNomeCompleto()));

			Tag tag = audioFile.getTag();
	
			final String artista = tag.getFirst(FieldKey.ARTIST);
			final String album = tag.getFirst(FieldKey.ALBUM);
			final String titulo = tag.getFirst(FieldKey.TITLE);
//			final String numero = tag.getFirst(FieldKey.TRACK);
			
			final long totalSecs = audioFile.getAudioHeader().getTrackLength();
	
			final long hours = totalSecs / 3600;
			final long minutes = (totalSecs % 3600) / 60;
			final long seconds = totalSecs % 60;
	
			byte[] imageByte = audioFile.getTag().getFirstArtwork().getBinaryData();
			
			arquivo.setArtista(artista);
			arquivo.setAlbum(album);
			arquivo.setTitulo(titulo);
			arquivo.setDuracao(preparaNumeracao(hours) + ":" + preparaNumeracao(minutes) + ":" + preparaNumeracao(seconds));
			arquivo.setImageAlbumByte(imageByte);
		
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			log.error(e.getMessage());
		}

	}
	
	private String preparaNumeracao(long hours) {
		
		if(hours < 10) {
			return "0" + String.valueOf(hours);
		}
		
		return String.valueOf(hours);
	}

	public static LeitorMetadadoArquivoAudio getInstance() {
		if(null == instance) {
			instance = new LeitorMetadadoArquivoAudio();
		}
		return instance;
	}
}
