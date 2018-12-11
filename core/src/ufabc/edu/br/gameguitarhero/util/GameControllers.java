package ufabc.edu.br.gameguitarhero.util;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public class GameControllers implements InputProcessor, ControllerListener{

	@Override
	public boolean keyDown(int keycode) {;
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
		if (keycode == Input.Keys.ESCAPE) {
			Commands.commands[Commands.ESCAPE] = true;
			return true;
		}
		if (keycode == 74) {
			Commands.commands[Commands.DOWN] = true;
			Commands.commands[Commands.LEFT] = true;
			return true;
		}
		if (keycode == 75) {
			Commands.commands[Commands.UP] = true;
			Commands.commands[Commands.RIGHT] = true;
			return true;
		}
		if (keycode == 73) {
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
		if (keycode == Input.Keys.ESCAPE) {
			Commands.commands[Commands.ESCAPE] = false;
			Commands.pressed[Commands.ESCAPE] = false;
			return true;
		}
		if (keycode == 74) {
			Commands.commands[Commands.DOWN] = false;
			Commands.commands[Commands.LEFT] = false;
			Commands.pressed[Commands.DOWN] = false;
			Commands.pressed[Commands.LEFT] = false;
			return true;
		}
		if (keycode == 75) {
			Commands.commands[Commands.UP] = false;
			Commands.commands[Commands.RIGHT] = false;
			Commands.pressed[Commands.UP] = false;
			Commands.pressed[Commands.RIGHT] = false;
			return true;
		}
		if (keycode == 73) {
			Commands.commands[Commands.ENTER] = false;
			Commands.pressed[Commands.ENTER] = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		if (buttonCode == 0) {
        	Commands.commands[Commands.BTN_5] = true;
        	return true;
        }
        if (buttonCode == 4) {
        	Commands.commands[Commands.BTN_2] = true;
        	return true;
        }
        if (buttonCode == 5) {
        	Commands.commands[Commands.BTN_3] = true;
        	return true;
        }
        if (buttonCode == 6) {
        	Commands.commands[Commands.BTN_1] = true;
        	return true;
        }
        if (buttonCode == 7) {
        	Commands.commands[Commands.BTN_4] = true;
        	return true;
        }
        if (buttonCode == 13) {
        	Commands.commands[Commands.ENTER] = true;
        	return true;
        }
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		if (buttonCode == 0) {
        	Commands.commands[Commands.BTN_5] = false;
        	Commands.pressed[Commands.BTN_5] = false;
        	return true;
        }
        if (buttonCode == 4) {
        	Commands.commands[Commands.BTN_2] = false;
        	Commands.pressed[Commands.BTN_2] = false;
        	return true;
        }
        if (buttonCode == 5) {
        	Commands.commands[Commands.BTN_3] = false;
        	Commands.pressed[Commands.BTN_3] = false;
        	return true;
        }
        if (buttonCode == 6) {
        	Commands.commands[Commands.BTN_1] = false;
        	Commands.pressed[Commands.BTN_1] = false;
        	return true;
        }
        if (buttonCode == 7) {
        	Commands.commands[Commands.BTN_4] = false;
        	Commands.pressed[Commands.BTN_4] = false;
        	return true;
        }
        if (buttonCode == 13) {
        	Commands.commands[Commands.ENTER] = false;
        	Commands.pressed[Commands.ENTER] = false;
        	return true;
        }
        return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		if (axisCode == 1) {
			if (value >= 0.9f) {
				Commands.commands[Commands.RIGHT] = true;
				return true;
			}
			else if (value <= -0.9f) {
				Commands.commands[Commands.LEFT] = true;
				return true;
			}
			else {
				Commands.commands[Commands.RIGHT] = false;
				Commands.pressed[Commands.RIGHT] = false;
				Commands.commands[Commands.LEFT] = false;
				Commands.pressed[Commands.LEFT] = false;
				return true;
			}
		}
		else if (axisCode == 0) {
			if (value >= 0.9f) {
				Commands.commands[Commands.DOWN] = true;
				return true;
			}
			else if (value <= -0.9f) {
				Commands.commands[Commands.UP] = true;
				return true;
			}
			else {
				Commands.commands[Commands.UP] = false;
				Commands.pressed[Commands.UP] = false;
				Commands.commands[Commands.DOWN] = false;
				Commands.pressed[Commands.DOWN] = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	@Override
	public void connected(Controller controller) {
		
	}

	@Override
	public void disconnected(Controller controller) {
		
	}

}
