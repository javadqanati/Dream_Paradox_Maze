package Data;

import Audio.AudioManager;
import Launcher.GamePanel;

import java.io.*;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            if(gp.fullScreenOn()){
                bw.write("Full Screen On");
            } else {
                bw.write("Full Screen Off");
            }
            bw.newLine();
            bw.write(String.valueOf(AudioManager.isMusicMuted()));
            bw.newLine();
            bw.write(String.valueOf(AudioManager.isSfxMuted()));
            bw.newLine();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadConfig(){
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
            String s;

            // First line - Full Screen
            s = br.readLine();
            if (s != null) {
                if (s.equals("Full Screen On")) {
                    System.out.println("Full Screen On");
                   // gp.setFullScreenOn(true);  // <- You should have a method to enable fullscreen
                } else if (s.equals("Full Screen Off")) {
                  //  gp.setFullScreenOn(false); // <- or disable fullscreen
                    System.out.println("Full Screen Off");
                }
            }

            // Second line - Music muted
            s = br.readLine();
            if (s != null) {
                AudioManager.setMusicMuted(Boolean.parseBoolean(s));
            }

            // Third line - SFX muted
            s = br.readLine();
            if (s != null) {
                AudioManager.setSfxMuted(Boolean.parseBoolean(s));
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
