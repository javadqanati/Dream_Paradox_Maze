package Data;

import Game.GameEntities.Player;
import Game.GameEntities.Powerup.PowerUp;
import Game.GameEntities.Powerup.PowerUpFactory;
import java.io.*;


public class PlayerData {
    private final Player player;

    public PlayerData(Player player) {
        this.player = player;
    }

    public void saveConfig() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("playerConfig.txt"))) {

            if (!player.getPowerUps().isEmpty()) {
                for (PowerUp powerUp : player.getPowerUps()) {
                    bw.write(powerUp.getName());
                    bw.newLine();
                }
            } else {
                bw.write("no power ups");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try (BufferedReader br = new BufferedReader(new FileReader("playerConfig.txt"))) {
            String s;

            while ((s = br.readLine()) != null) {
                if (s.equals("no power ups")) {
                    break;
                }
                player.addPowerUp(PowerUpFactory.create(s, player.getGp()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
