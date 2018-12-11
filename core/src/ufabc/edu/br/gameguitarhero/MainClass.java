package ufabc.edu.br.gameguitarhero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;

import ufabc.edu.br.gameguitarhero.screen.AbstractScreen;
import ufabc.edu.br.gameguitarhero.screen.CreditsScreen;
import ufabc.edu.br.gameguitarhero.screen.DifficultScreen;
import ufabc.edu.br.gameguitarhero.screen.GameScreen;
import ufabc.edu.br.gameguitarhero.screen.LoadingScreen;
import ufabc.edu.br.gameguitarhero.screen.MenuScreen;
import ufabc.edu.br.gameguitarhero.screen.SelectModelScreen;
import ufabc.edu.br.gameguitarhero.screen.StartScreen;
import ufabc.edu.br.gameguitarhero.util.GameControllers;
import ufabc.edu.br.gameguitarhero.util.Menus;
import ufabc.edu.br.gameguitarhero.util.SoundFXMenus;

public class MainClass extends Game  {
	private AbstractScreen currentScreen;
	private GameControllers gameControllers;
	
	@Override
	public void create() {
		//Set StartScreen como a tela inicial do jogo
		currentScreen = new StartScreen("START");
		//Set dos Controllers do jogo
		gameControllers = new GameControllers();
		Gdx.input.setInputProcessor(gameControllers);
		Controllers.addListener(gameControllers);
		
	}
	
	public void render() {
		currentScreen.render(Gdx.graphics.getDeltaTime());
		//Lógica de sequenciamento de telas do jogo.
		if (currentScreen.isDone()) {
			if (currentScreen.getId().equals("START")) {
				currentScreen = new MenuScreen("MENU");
			}
			else if (currentScreen.getId().equals("MENU")) {
				MenuScreen menu = (MenuScreen) currentScreen;
				switch (menu.getSelectedScreen()) {
				case Menus.NOT_SELECTED:
					menu.setDone(false);
					currentScreen = menu;
					break;
				case Menus.PLAY:
					SoundFXMenus.getSoundbyName("MENU_TO_DIFF").play();
					currentScreen = new DifficultScreen("DIFFICULT", menu.getModel());
					break;
				case Menus.MODEL_SELECT:
					SoundFXMenus.getSoundbyName("MENU_TO_MODEL").play();
					currentScreen = new SelectModelScreen("MODEL", menu);
					break;
				case Menus.EXIT:
					SoundFXMenus.getSoundbyName("DIFF_TO_GAME").play();
					currentScreen.dispose();
					break;
				}
			}
			else if (currentScreen.getId().equals("DIFFICULT")) {
				DifficultScreen diff = (DifficultScreen) currentScreen;
				SoundFXMenus.getSoundbyName("DIFF_TO_GAME").play();
				currentScreen = new LoadingScreen("LOADING", diff.getModel(), diff.getSelectedScreen(), diff.getTexture());
			}
			else if (currentScreen.getId().equals("LOADING")) {
				LoadingScreen loading = (LoadingScreen) currentScreen;
				currentScreen = new GameScreen("GAME", loading.getModel(),
													   loading.getNotes(), 
													   loading.getDiscos(), 
													   loading.getBlackDiscos(), 
													   loading.getPista(), 
													   loading.getCamera(), 
													   loading.getParticles(),
													   loading.getDifficult());
			}
			else if (currentScreen.getId().equals("MODEL")) {
				SelectModelScreen selectModel = (SelectModelScreen) currentScreen;
				selectModel.getMenu().setDone(false);
				SoundFXMenus.getSoundbyName("MODEL_TO_MENU").play();
				currentScreen = selectModel.getMenu();
			}
			else if (currentScreen.getId().equals("GAME")) {
				GameScreen game = (GameScreen) currentScreen;
				if (game.getMenuSelect() == Menus.RESTART) {
					currentScreen = new LoadingScreen("LOADING",
													  game.getModel(), 
													  game.getDifficult(), 
													  game.getParticles(), 
													  game.getDiscos(), 
													  game.getBlackDiscos());
				}
				else if (game.getMenuSelect() == Menus.CREDITS) {
					currentScreen = new CreditsScreen("CREDITS");
				}
				else {
					currentScreen = new MenuScreen("MENU");
				}
			}
			else if (currentScreen.getId().equals("CREDITS")) {
				currentScreen = new MenuScreen("MENU");
			}
			else {
				currentScreen = new StartScreen("START");
			}
		}
		
	}
}
