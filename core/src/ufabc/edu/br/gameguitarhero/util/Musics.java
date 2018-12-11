package ufabc.edu.br.gameguitarhero.util;

import java.util.HashMap;

import com.badlogic.gdx.audio.Music;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;

public final class Musics {
	private static HashMap<String, Music> musics = new HashMap<String, Music>();
	
	static {
		musics.put("MUSIC", (Music) AssetManagement.assetManager.get("music/Megadeth - A Tout Le Monde (Encore).ogg"));
		musics.put("MUSIC_INIT", (Music) AssetManagement.assetManager.get("soundFX/musicInit.mp3"));
		musics.put("ERROR", (Music) AssetManagement.assetManager.get("soundFX/Button_Error01.mp3"));
		musics.put("ERROR_AUDIENCE", (Music) AssetManagement.assetManager.get("soundFX/Talk_Oh.mp3"));
		musics.put("GAMEOVER", (Music) AssetManagement.assetManager.get("soundFX/Talk_YoureOut.mp3"));
		musics.put("DECREASING", (Music) AssetManagement.assetManager.get("soundFX/decreasing.mp3"));
		musics.put("AUDIENCE_CHEERING", (Music) AssetManagement.assetManager.get("soundFX/audience_cheering.mp3"));
		musics.put("FINAL", (Music) AssetManagement.assetManager.get("soundFX/final_audio.mp3"));
		musics.put("LOSING", (Music) AssetManagement.assetManager.get("soundFX/losing.mp3"));
		musics.put("APPLAUSE", (Music) AssetManagement.assetManager.get("soundFX/Crowd_Applause.mp3"));
		musics.put("LAUGHING", (Music) AssetManagement.assetManager.get("soundFX/Crowd_Laughing.mp3"));
		musics.put("CREDITS", (Music) AssetManagement.assetManager.get("music/Nothing.mp3"));
	}
	
	public static Music getMusicbyName(String name) {
		return musics.get(name);
	}
}
