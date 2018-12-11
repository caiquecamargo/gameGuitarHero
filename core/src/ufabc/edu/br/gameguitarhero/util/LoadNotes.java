package ufabc.edu.br.gameguitarhero.util;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.model.Notes;

public class LoadNotes extends Thread{
	private ArrayList<Notes> notes;
	private GetData			 data;
	private boolean			 done = false;
	private int				 difficult;
	
	public LoadNotes (int difficult) {
		this.difficult = difficult;
	}
	
	public void run(){
		data = new GetData();
		notes = new ArrayList<Notes>();
		System.out.println("DIFFICULT: " + difficult);
		if (difficult == GameDifficult.BEGGINER) {
			int i = 0;
			if(data.getTemp() != null) {
				for (String[] p: data.getTemp()) {
					if (i > 7) i = 0;
					int temp = Integer.parseInt(p[3]);
					Notes note = null;
					if (temp > 2) {
						temp = (i < 2)?i:5;
						i++;
					}
					if (temp < 5) note = new Notes("NOTE", temp);
					if(note != null) {
						note.getCurrent().transform.translate(-0.2f + temp/10.0f, 0.001f, -0.9f - (Integer.parseInt(p[0])/(Parameters.VELOCIDADE * 324.5f)));
						if((-0.9f - (Integer.parseInt(p[0])/Parameters.VELOCIDADE/1000.0f)) > -2.0f) note.setRenderize(true);
						note.getCurrent().transform.rotate(Vector3.X, -90);
						note.getCurrent().transform.scale(0.4f, 0.4f, 0.4f);
						notes.add(note);
					}
				}
			}
		}
		else if (difficult == GameDifficult.NORMAL) {
			if(data.getTemp() != null) {
				int i = 0;
				for (String[] p: data.getTemp()) {
					if (i > 5) i = 0;
					int temp = Integer.parseInt(p[3]);
					Notes note = null;
					if (temp > 2) {
						temp = (i < 3)?i:5;
						i++;
					}
					if (temp < 5) note = new Notes("NOTE", temp);
					if(note != null) {
						note.getCurrent().transform.translate(-0.2f + temp/10.0f, 0.001f, -0.9f - (Integer.parseInt(p[0])/(Parameters.VELOCIDADE * 324.5f)));
						if((-0.9f - (Integer.parseInt(p[0])/Parameters.VELOCIDADE/1000.0f)) > -2.0f) note.setRenderize(true);
						note.getCurrent().transform.rotate(Vector3.X, -90);
						note.getCurrent().transform.scale(0.4f, 0.4f, 0.4f);
						notes.add(note);
					}
				}
			}
		}
		else if (difficult == GameDifficult.HARD) {
			if(data.getTemp() != null) {
				int i = 0;
				for (String[] p: data.getTemp()) {
					if (i > 5) i = 0;
					int temp = Integer.parseInt(p[3]);
					Notes note = null;
					if (temp > 3) {
						temp = (i < 4)?i:5;
						i++;
					}
					if (temp < 5) note = new Notes("NOTE", temp);
					if(note != null) {
						note.getCurrent().transform.translate(-0.2f + temp/10.0f, 0.001f, -0.9f - (Integer.parseInt(p[0])/(Parameters.VELOCIDADE * 324.5f)));
						if((-0.9f - (Integer.parseInt(p[0])/Parameters.VELOCIDADE/1000.0f)) > -2.0f) note.setRenderize(true);
						note.getCurrent().transform.rotate(Vector3.X, -90);
						note.getCurrent().transform.scale(0.4f, 0.4f, 0.4f);
						notes.add(note);
					}
				}
			}		
		}
		else if (difficult == GameDifficult.EXPERT) {
			if(data.getTemp() != null) {
				for (String[] p: data.getTemp()) {
					int temp = Integer.parseInt(p[3]);
					Notes note = null;
					if (temp < 5) note = new Notes("NOTE", temp);
					if(note != null) {
						note.getCurrent().transform.translate(-0.2f + temp/10.0f, 0.001f, -0.9f - (Integer.parseInt(p[0])/(Parameters.VELOCIDADE * 324.5f)));
						if((-0.9f - (Integer.parseInt(p[0])/Parameters.VELOCIDADE/1000.0f)) > -2.0f) note.setRenderize(true);
						note.getCurrent().transform.rotate(Vector3.X, -90);
						note.getCurrent().transform.scale(0.4f, 0.4f, 0.4f);
						notes.add(note);
					}
				}
			}
		}
		
		done = true;
	}
	
	public ArrayList<Notes> getNotes(){
		return notes;
	}
	
	public boolean getDone() {
		return done;
	}
}
