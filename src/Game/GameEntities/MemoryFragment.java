package Game.GameEntities;

import Launcher.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class MemoryFragment extends GameEntities{

    public MemoryFragment(GamePanel gp) {
        super(gp);
        setName("Memory Fragment");
        try{
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/key.png"))));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
