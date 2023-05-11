package venox.bot.utils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CanvasSnapshotToByteArray {
    public static byte[] convertToByteArray(Canvas canvas) {
        SnapshotParameters params = new SnapshotParameters();
        WritableImage image = canvas.snapshot(params, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
