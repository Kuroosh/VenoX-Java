package venox.bot.security.verify_sytem;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import venox.bot.globals.Constants;
import venox.bot.models.user.VenoxUser;
import java.io.ByteArrayInputStream;
import java.util.Base64;

public class UserPreviewUi {
    VenoxUser user;
    public UserPreviewUi(VenoxUser user){
        this.user = user;
    }
    public Canvas draw(){
        Group root = new Group();
        final Canvas canvas = new Canvas(1366,768);
        GraphicsContext ctx = canvas.getGraphicsContext2D();


        if (this.user.getMe_background().length() > 1 && this.user.getVip_package() >= Constants.VipLevel.Wolf.ordinal()) {
            byte[] imageBytes = Base64.getDecoder().decode(this.user.getMe_background().replace("data:image/png;base64,", "").replace("data:undefined;base64,", ""));
            Image bg = new Image(new ByteArrayInputStream(imageBytes));
            ctx.drawImage(bg, 0, 0, canvas.getWidth(), canvas.getHeight());
        }
        else
            ctx.drawImage(venox.bot.security.verify_sytem.Constants.VerifyBackground, 0, 0, canvas.getWidth(), canvas.getHeight());
        // /me UI

        ctx.setGlobalAlpha(0.15);
        ctx.setFill(Color.rgb(0, 0, 0));
        ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        ctx.setGlobalAlpha(1);

        ctx.setFill(Color.rgb(0, 200, 255));
        ctx.fillRect(0, 0, canvas.getWidth(), 5);

        ctx.setFill(Color.rgb(0, 0, 0));
        ctx.fillRect(0, 5, canvas.getWidth(), 30);

        ctx.setFont(Font.font("22px 'Segoe UI New', -apple-system, BlinkMacSystemFont, Roboto, Helvetica, Arial, sans-serif;"));
        ctx.setFill(Color.rgb(255,255,255));
        ctx.setTextAlign(TextAlignment.CENTER);
        ctx.fillText("𝒱𝑒𝓃𝑜𝒳 𝒰𝓈𝑒𝓇 𝒫𝓇𝑒𝓋𝒾𝑒𝓌", canvas.getWidth() / 2, 27);

        ctx.setGlobalAlpha(0.75);
        ctx.setFill(Color.rgb(0, 0, 0));
        ctx.beginPath();
        Shadow shadow = new Shadow();
        shadow.setColor(Color.rgb(0, 150, 200));
        shadow.setHeight(15);
        shadow.setRadius(15);
        shadow.setWidth(15);
        ctx.setEffect(shadow);
        ctx.arc(canvas.getWidth() / 2 - 300, canvas.getHeight() / 2 - 25, 250, 0, 2 * Math.PI, 0);
        //ctx.fill();
        ctx.setGlobalAlpha(1);

		double circleX = canvas.getWidth() / 2 - 350;
        double circleY = canvas.getHeight() / 2 - 25;
        double circleRadius = 250;

        ctx.setGlobalAlpha(0.75);
        ctx.setFill(Color.rgb(0, 0, 0));
        ctx.beginPath();
        ctx.setEffect(shadow);
        ctx.arc(circleX, circleY, circleRadius, 0, 2 * Math.PI, 0);
        ctx.fill();
        ctx.setGlobalAlpha(1);

        root.getChildren().add(canvas);
        return canvas;
    }
}
