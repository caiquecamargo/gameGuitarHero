package ufabc.edu.br.gameguitarhero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.screen.AbstractScreen;
import ufabc.edu.br.gameguitarhero.screen.GameScreen;
import ufabc.edu.br.gameguitarhero.screen.LoadingScreen;
import ufabc.edu.br.gameguitarhero.screen.MenuScreen;
import ufabc.edu.br.gameguitarhero.screen.SelectModelScreen;
import ufabc.edu.br.gameguitarhero.screen.StartScreen;
import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.LoadMusic;
import ufabc.edu.br.gameguitarhero.util.Menus;

public class MainClass extends Game implements InputProcessor, ControllerListener {
	private AbstractScreen currentScreen;
	public static AssetManager assetManager;
	private LoadMusic		 loadMusic;
	@Override
	public void create() {
		assetManager  = new AssetManager();		
		assetManager.load("sky2.g3db", Model.class);
		//assetManager.load("models/bigvegas.g3db", Model.class);
		assetManager.load("models/fellguard.g3db", Model.class);
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
		assetManager.load("models/knight.g3db", Model.class);
		//assetManager.load("yaku.g3db", Model.class);
		assetManager.load("models/eletricGuitar.g3db", Model.class);
		assetManager.load("models/blue.g3db", Model.class);
		assetManager.load("models/green.g3db", Model.class);
		assetManager.load("models/red.g3db", Model.class);
		assetManager.load("models/yellow.g3db", Model.class);
		assetManager.load("models/orange.g3db", Model.class);
		assetManager.load("music/Megadeth - A Tout Le Monde (Encore).ogg", Music.class);
		assetManager.load("soundFX/Button_Error01.mp3", Music.class);
		assetManager.load("soundFX/Button_Error02.mp3", Music.class);
		assetManager.load("braco.jpg", Texture.class);
		currentScreen = new StartScreen("START");
		Gdx.input.setInputProcessor(this);
		Controllers.addListener(this);
		
	}
	
	public void render() {
		currentScreen.render(Gdx.graphics.getDeltaTime());
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
					currentScreen = new LoadingScreen("LOADING", menu.getModel());
					break;
				case Menus.MODEL_SELECT:
					currentScreen = new SelectModelScreen("MODEL", menu);
					break;
				case Menus.EXIT:
					break;
				}
				//currentScreen = new CreditsScreen("CREDITS");
			}
			else if (currentScreen.getId().equals("LOADING")) {
				LoadingScreen loading = (LoadingScreen) currentScreen;
				currentScreen = new GameScreen("GAME", loading.getModel(),
													   loading.getNotes(), 
													   loading.getDiscos(), 
													   loading.getBlackDiscos(), 
													   loading.getPista(), 
													   loading.getCamera(), 
													   loading.getMusics(),
													   loading.getParticles());
			}
			else if (currentScreen.getId().equals("MODEL")) {
				SelectModelScreen selectModel = (SelectModelScreen) currentScreen;
				selectModel.getMenu().setDone(false);
				currentScreen = selectModel.getMenu();
			}
			else {
				currentScreen = new StartScreen("START");
			}
		}
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.UP) {
			Commands.commands[Commands.UP] = true;
			return true;
		}
		if (keycode == Input.Keys.DOWN) {
			Commands.commands[Commands.DOWN] = true;
			return true;
		}
		if (keycode == Input.Keys.LEFT) {
			Commands.commands[Commands.LEFT] = true;
			return true;
		}
		if (keycode == Input.Keys.RIGHT) {
			Commands.commands[Commands.RIGHT] = true;
			return true;
		}
		if (keycode == Input.Keys.A) {
			Commands.commands[Commands.BTN_1] = true;
			return true;
		}
		if (keycode == Input.Keys.S) {
			Commands.commands[Commands.BTN_2] = true;
			return true;
		}
		if (keycode == Input.Keys.D) {
			Commands.commands[Commands.BTN_3] = true;
			return true;
		}
		if (keycode == Input.Keys.F) {
			Commands.commands[Commands.BTN_4] = true;
			return true;
		}
		if (keycode == Input.Keys.G) {
			Commands.commands[Commands.BTN_5] = true;
			return true;
		}
		if (keycode == Input.Keys.ENTER) {
			Commands.commands[Commands.ENTER] = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.UP) {
			Commands.commands[Commands.UP] = false;
			Commands.pressed[Commands.UP] = false;
			return true;
		}
		if (keycode == Input.Keys.DOWN) {
			Commands.commands[Commands.DOWN] = false;
			Commands.pressed[Commands.DOWN] = false;
			return true;
		}
		if (keycode == Input.Keys.LEFT) {
			Commands.commands[Commands.LEFT] = false;
			Commands.pressed[Commands.LEFT] = false;
			return true;
		}
		if (keycode == Input.Keys.RIGHT) {
			Commands.commands[Commands.RIGHT] = false;
			Commands.pressed[Commands.RIGHT] = false;
			return true;
		}
		if (keycode == Input.Keys.A) {
			Commands.commands[Commands.BTN_1] = false;
			Commands.pressed[Commands.BTN_1] = false;
			return true;
		}
		if (keycode == Input.Keys.S) {
			Commands.commands[Commands.BTN_2] = false;
			Commands.pressed[Commands.BTN_2] = false;
			return true;
		}
		if (keycode == Input.Keys.D) {
			Commands.commands[Commands.BTN_3] = false;
			Commands.pressed[Commands.BTN_3] = false;
			return true;
		}
		if (keycode == Input.Keys.F) {
			Commands.commands[Commands.BTN_4] = false;
			Commands.pressed[Commands.BTN_4] = false;
			return true;
		}
		if (keycode == Input.Keys.G) {
			Commands.commands[Commands.BTN_5] = false;
			Commands.pressed[Commands.BTN_5] = false;
			return true;
		}
		if (keycode == Input.Keys.ENTER) {
			Commands.commands[Commands.ENTER] = false;
			Commands.pressed[Commands.ENTER] = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		
	}
}
