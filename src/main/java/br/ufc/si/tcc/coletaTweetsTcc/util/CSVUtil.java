package br.ufc.si.tcc.coletaTweetsTcc.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.UserMentionEntity;

public class CSVUtil {

	BufferedWriter bw;
	
	public void createFile(Date date, int countSearch){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = df.format(date);
		
		String nameFile = Constants.PATH+dateString+"/"+countSearch+".csv";
		Path pathFile = Paths.get(nameFile);
		
		try {
			
			if(!Files.exists(pathFile)){
				Files.createFile(pathFile);
			}
			
			bw = new BufferedWriter(new FileWriter(nameFile, true));
			
			bw.write("id;status;data;usuarioId;nome;username;localizacao;mencionados;hashtags\n"); 
			bw.close();
			
		} catch (FileNotFoundException ex){
			ex.printStackTrace(); 
		}catch (IOException e){
			e.printStackTrace(); 
		}
		
	}
	
	public void addLine(Status status, Date date, int count){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = df.format(date);
		String line = toString(status);
		
		try {

			Path path = Paths.get(Constants.PATH+dateString+"/"+count+".csv");
			
			bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			
			bw.write(line);
			bw.append("\n");
			bw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String toString(Status status) {
		String id =  "\"" + String.valueOf(status.getId())+"\"";
		String st = status.getText();
		st = st.replaceAll("[\"\\n]","");
		st = "\"" + st + "\"";
		DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");
		String date = "\"" + df.format(status.getCreatedAt()) + "\"";
		String userId = "\"" + String.valueOf(status.getUser().getId()) + "\"";
		String name = "\"" + status.getUser().getName() + "\"";
		String userName = "\"" + status.getUser().getScreenName() + "\"";
		String location = "\"" + status.getUser().getLocation() + "\"";
		String latitude, longitude;
		try {
			latitude = "\"" + status.getGeoLocation().getLatitude() + "\"";
		} catch (Exception e) {
			latitude = "\"" + "" + "\""; 
		}
		try {
			longitude = "\"" + status.getGeoLocation().getLongitude() + "\"";
		} catch (Exception e) {
			longitude = "\"" + "" + "\"";
		}
		
		String mentioned = "";
		String hashTags = "";
		
		UserMentionEntity[] ume = status.getUserMentionEntities();
		if(ume.length > 0){
			for(int i = 0; i<ume.length; i++){
				String mention = ume[i].getScreenName();
				mentioned += mention + " ";
			}
		}
		
		mentioned = "\"" + mentioned + "\"";
		
		HashtagEntity[] hte = status.getHashtagEntities();

		if(hte.length > 0){
			for (int i = 0; i < hte.length; i++) {
				String tag = hte[i].getText();
				hashTags += tag + " ";
			}
		}

		hashTags = "\"" + hashTags + "\"";
		
		StringBuffer string = new StringBuffer();
		string.append(id + ";");
		string.append(st + ";");
		string.append(date + ";");
		string.append(userId + ";");
		string.append(name + ";");
		string.append(userName + ";");
		string.append(location + ";");
		string.append(latitude + ";");
		string.append(longitude + ";");
		string.append(mentioned + ";");
		string.append(hashTags + ";");
		
		return string.toString();
	
	}
	
}
