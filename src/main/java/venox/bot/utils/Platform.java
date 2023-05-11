package venox.bot.utils;

import java.util.concurrent.CountDownLatch;

public class Platform {

    public static void InitPlatformData(){
        // Initialisierung der JavaFX-Plattform
        CountDownLatch latch = new CountDownLatch(1);
        javafx.application.Platform.startup(() -> latch.countDown());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
