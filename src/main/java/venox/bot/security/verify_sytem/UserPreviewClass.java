package venox.bot.security.verify_sytem;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;
import it.auties.whatsapp.model.message.standard.ImageMessage;
import venox.bot.globals.Constants;
import venox.bot.models.Command;
import venox.bot.models.user.VenoxUser;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class UserPreviewClass {

    public static Runnable SendTestImage(Canvas img, String text, Whatsapp whatsapp, MessageInfo info){

        return null;
    }
    @Command(name = "me")
    public static void showUserPreview(Whatsapp whatsapp, MessageInfo info){
        System.out.println(Long.parseLong(info.senderJid().user()));
        System.out.println(info.chatJid().toString());
        VenoxUser user = Constants.getUser(Long.parseLong(info.senderJid().user()), info.chatJid().toString());
        var userPreview = new UserPreviewUi(user).draw();
        Platform.runLater(() -> {
            var image = ImageMessage.simpleBuilder() // Create a new image message builder
                    .media(venox.bot.utils.CanvasSnapshotToByteArray.convertToByteArray(userPreview)) // Set the image of this message
                    .caption("") // Set the caption of this message
                    .build(); // Create the message
            whatsapp.sendMessage(info.chatJid(), image);
            // Verlagern des Plattform-Exit-Aufrufs in einen separaten Thread oder Callback
            // Überprüfen, ob die JavaFX-Plattform noch aktiv ist
            if (!Platform.isFxApplicationThread()) {
                // Beenden der JavaFX-Plattform (nur wenn nicht bereits beendet)
                Platform.exit();
            }
        });
    }
}
