package venox.bot.globals;
import venox.bot.models.user.VenoxUser;
import java.util.HashMap;
import java.util.Map;

import static venox.bot.database.Index.InsertDatabaseUser;

public class Constants {


    public static HashMap<Long, VenoxUser> AdminList = new HashMap<Long, VenoxUser>();
    public static HashMap<Long, VenoxUser> Userlist = new HashMap<Long, VenoxUser>();
    public static HashMap<Integer, String> adminRankNames = new HashMap<Integer, String>() {{
        put(7, "🟥 Project Leader 🟥");
        put(6, "🟥 Rep. Project Leader 🟥");
        put(5, "🟧 Administrator 🟧");
        put(4, "🟦 Moderator 🟦");
        put(3, "🟩 Supporter 🟩");
        put(2, "🟪 Ticket - Supporter 🟪");
        put(1, "❄️ Donator ❄️");
        put(0, "✅ User ✅");
    }};
    public static HashMap<Integer, String> adminRankColors = new HashMap<>() {{
        put(7, "#B40000");
        put(6, "#EC0000");
        put(5, "#E8AE00");
        put(4, "#002DE0");
        put(3, "#006600");
        put(2, "#C800C8");
        put(1, "#FFFFFF");
        put(0, "#FFFFFF");
    }};
    public static HashMap<Integer, String> vipLevelNames = new HashMap<>() {{
        put(9, "Polar Bear 🐻‍❄️");
        put(8, "Lion 🦁");
        put(7, "Gorilla 🦍");
        put(6, "Wolf 🐺");
        put(5, "Unicorn 🦄");
        put(4, "Kitty 🐱");
        put(3, "Honey Bee 🐝");
        put(2, "Snail 🐌");
        put(1, "Ant 🐜");
        put(0, "None ❌");
    }};
    public static HashMap<Integer, String> vipLevelColors = new HashMap<>() {{
        put(9, "#00C8FF");
        put(8, "#FFC900");
        put(7, "#8DABBC");
        put(6, "#5089A9");
        put(5, "#E400DD");
        put(4, "#A2B500");
        put(3, "#737300");
        put(2, "#42A000");
        put(1, "#A06D00");
        put(0, "#FFFFFF");
    }};

    public static enum VipLevel {
        PolarBear(9),
        Lion(8),
        Gorilla(7),
        Wolf(6),
        Unicorn(5),
        Kitty(4),
        Bee(3),
        Snail(2),
        Ant(1),
        None(0);

        private final int level;

        VipLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }
    public static VenoxUser getUser(Long id, String chatId) {
        VenoxUser user = Userlist.getOrDefault(id, null);
        if(user != null) return user;
        return InsertDatabaseUser(id, id.toString(), chatId);
    }
}
