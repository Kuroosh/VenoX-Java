package venox.bot.security.verify_sytem;

import javafx.scene.image.Image;
import venox.bot.Config;

import javax.imageio.ImageIO;
import java.io.File;

public class Constants {
    public static Image VerifyBackground;
    public static void Initialize(){
        try{
            VerifyBackground = new Image(Config.AssetPath + "/images/verify/background.jpg");
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}

