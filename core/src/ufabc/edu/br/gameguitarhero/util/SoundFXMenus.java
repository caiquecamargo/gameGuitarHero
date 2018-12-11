package ufabc.edu.br.gameguitarhero.util;

import java.util.HashMap;

import com.badlogic.gdx.audio.Music;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;

public final class SoundFXMenus {	
	private static HashMap<String, Music> soundFX = new HashMap<String, Music>();
	
	static {
		soundFX.put("MENU_SELECT", (Music) AssetManagement.assetManager.get("soundFX/selectItem.mp3"));
		soundFX.put("MENU_TO_MODEL", (Music) AssetManagement.assetManager.get("soundFX/transition2.mp3"));
		soundFX.put("MODEL_SELECT_SOUND1", (Music) AssetManagement.assetManager.get("soundFX/point1.mp3"));
		soundFX.put("MODEL_SELECT_SOUND2", (Music) AssetManagement.assetManager.get("soundFX/point2.mp3"));
		soundFX.put("MODEL_TO_MENU", (Music) AssetManagement.assetManager.get("soundFX/transition1.mp3"));
		soundFX.put("MENU_TO_DIFF", (Music) AssetManagement.assetManager.get("soundFX/transition3.mp3"));
		soundFX.put("DIFF_SELECT_SOUND1", (Music) AssetManagement.assetManager.get("soundFX/itemselect1.mp3"));
		soundFX.put("DIFF_SELECT_SOUND2", (Music) AssetManagement.assetManager.get("soundFX/itemselect2.mp3"));
		soundFX.put("DIFF_SELECT_SOUND3", (Music) AssetManagement.assetManager.get("soundFX/itemselect3.mp3"));
		soundFX.put("DIFF_TO_GAME", (Music) AssetManagement.assetManager.get("soundFX/transition4.mp3"));
	}
	
	public static Music getSoundbyName(String name) {
		return soundFX.get(name);
	}
}
