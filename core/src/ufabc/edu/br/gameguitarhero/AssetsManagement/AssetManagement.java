package ufabc.edu.br.gameguitarhero.AssetsManagement;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

public class AssetManagement {
	public static AssetManager assetManager;
	
	static {
		//Classe de carregamento dos modelos, texturas e sounds utilizados no jogo.
		assetManager = new AssetManager();
		assetManager.load("models/sky2.g3db", Model.class);
		assetManager.load("models/Doomsday/doom.g3db", Model.class);
		assetManager.load("models/Doomsday/Agony.g3db", Model.class);
		assetManager.load("models/Doomsday/Angry Point.g3db", Model.class);
		assetManager.load("models/Doomsday/Cheering (1).g3db", Model.class);
		assetManager.load("models/Doomsday/Cheering.g3db", Model.class);
		assetManager.load("models/Doomsday/Clapping.g3db", Model.class);
		assetManager.load("models/Doomsday/Defeat.g3db", Model.class);
		assetManager.load("models/Doomsday/Defeated.g3db", Model.class);
		assetManager.load("models/Doomsday/Guitar Playing.g3db", Model.class);
		assetManager.load("models/Doomsday/Idle.g3db", Model.class);
		assetManager.load("models/Doomsday/Shaking Head No.g3db", Model.class);
		assetManager.load("models/Doomsday/Shoulder Rubbing.g3db", Model.class);
		assetManager.load("models/Doomsday/Silly Dancing (1).g3db", Model.class);
		assetManager.load("models/Doomsday/Silly Dancing.g3db", Model.class);
		assetManager.load("models/Doomsday/Standing Taunt Battlecry.g3db", Model.class);
		assetManager.load("models/Doomsday/Threatening.g3db", Model.class);
		assetManager.load("models/Doomsday/Victory.g3db", Model.class);
		assetManager.load("models/Doomsday/Warrior Idle.g3db", Model.class);
		assetManager.load("models/Dahaka/Idle.g3db", Model.class);
		assetManager.load("models/Dahaka/Agony.g3db", Model.class);
		assetManager.load("models/Dahaka/Cheering.g3db", Model.class);
		assetManager.load("models/Dahaka/Cocky Head Turn.g3db", Model.class);
		assetManager.load("models/Dahaka/Crazy Gesture.g3db", Model.class);
		assetManager.load("models/Dahaka/Getting Up.g3db", Model.class);
		assetManager.load("models/Dahaka/Guitar Playing.g3db", Model.class);
		assetManager.load("models/Dahaka/Look Away Gesture.g3db", Model.class);
		assetManager.load("models/Dahaka/Looking Around.g3db", Model.class);
		assetManager.load("models/Dahaka/Mutant Dying.g3db", Model.class);
		assetManager.load("models/Dahaka/No.g3db", Model.class);
		assetManager.load("models/Dahaka/Roar.g3db", Model.class);
		assetManager.load("models/Dahaka/Shake Fist.g3db", Model.class);
		assetManager.load("models/Dahaka/Shrugging.g3db", Model.class);
		assetManager.load("models/Dahaka/Sitting Idle.g3db", Model.class);
		assetManager.load("models/Dahaka/Standing To Crouched.g3db", Model.class);
		assetManager.load("models/Dahaka/Victory.g3db", Model.class);
		assetManager.load("models/Dahaka/Yelling Out.g3db", Model.class);
		assetManager.load("models/blue.g3db", Model.class);
		assetManager.load("models/green.g3db", Model.class);
		assetManager.load("models/red.g3db", Model.class);
		assetManager.load("models/yellow.g3db", Model.class);
		assetManager.load("models/orange.g3db", Model.class);
		
		assetManager.load("textures/braco.jpg", Texture.class);
		assetManager.load("textures/guitar_1.jpg", Texture.class);
		assetManager.load("textures/guitar_2.jpg", Texture.class);
		assetManager.load("textures/guitar_3.jpg", Texture.class);
		assetManager.load("textures/guitar_4.jpg", Texture.class);
		assetManager.load("textures/guitar_5.jpg", Texture.class);
		
		MusicLoader.MusicParameter loaderParam;
		loaderParam = new MusicLoader.MusicParameter();
		
		MusicLoader loader = new MusicLoader(new InternalFileHandleResolver());
		
		AssetManagement.assetManager.setLoader(Music.class, loader);
		AssetManagement.assetManager.load("music/Megadeth - A Tout Le Monde (Encore).ogg", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/musicInit.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/Button_Error01.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/audience_cheering.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/Crowd_Applause.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/Crowd_Laughing.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/decreasing.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/final_audio.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/losing.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/Talk_YoureOut.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/Talk_Oh.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/itemselect1.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/itemselect2.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/itemselect3.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/point1.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/point2.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/selectItem.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/transition1.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/transition2.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/transition3.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("soundFX/transition4.mp3", Music.class, loaderParam);
		AssetManagement.assetManager.load("music/Nothing.mp3", Music.class, loaderParam);
	}
}
