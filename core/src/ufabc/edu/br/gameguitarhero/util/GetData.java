package ufabc.edu.br.gameguitarhero.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class GetData {
	
	private ArrayList<String[]> tempoNotas = new ArrayList<String[]>();

	public GetData() {
		System.out.println("Lendo arquivo de notas da Música Selecionada...");
		
		String musicNotes = Gdx.files.internal("music/Megadeth - A Tout Le Monde (Encore).chart").toString();
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(musicNotes));
			while(in.ready()) {
				String line = in.readLine();
				String[] parts = line.split(" ");
				tempoNotas.add(parts);
			}
			in.close();
			in = null;
		} catch (IOException ex) {
			//ex.printStackTrace();
			System.err.print("<Arquivo de Notas vazio: "+ex.toString()+">");
		}
		System.out.println(" Leitura finalizada!");
		
//		for(String[] nota: tempoNotas) {
//			System.out.println(nota[0]+nota[2]+nota[3]+nota[4]);	
//		}
		//return tempoNotas;
	}
	
	public ArrayList<String[]> getTemp(){
		return tempoNotas;
	}

}
