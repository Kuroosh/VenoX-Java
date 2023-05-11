// Quellcode-Kodierung: UTF-8
package venox.bot.admin;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;
import org.apache.commons.lang3.StringEscapeUtils;
import venox.bot.globals.Constants;
import venox.bot.models.Command;
import venox.bot.models.user.VenoxUser;
import java.util.List;
import static venox.bot.Main.InitializeBotData;
import static venox.bot.globals.Constants.adminRankNames;

public class ProjectLeader {
    @Command(name = "admins")
    public static void SendAdminList(Whatsapp whatsapp, MessageInfo info){
        List<VenoxUser> adminListArray = new java.util.ArrayList<>(Constants.AdminList.values().stream().toList());
        adminListArray.sort((a, b) -> Integer.compare(b.getAdminLevel(), a.getAdminLevel()));
        StringBuilder txt = new StringBuilder("╔══✪〘 *ADMINS* 〙✪══\n");
        for (VenoxUser admin : adminListArray) {
            String adminUsername = admin.getFormattedName();
            if (adminUsername.contains("@s.whatsapp.net")) adminUsername = adminUsername.replaceAll("[^0-9]+", "");
            txt.append("\n Username: ").append(adminUsername);
            txt.append("\n Rank: ").append(StringEscapeUtils.unescapeJava(adminRankNames.getOrDefault(admin.getAdminLevel(), null)));
            txt.append("\n wa.me/").append(admin.getId()).append("\n");
        }
        txt.append("\n╚═〘 *V E N O X - B O T* 〙");
        whatsapp.sendMessage(info.chatJid(), txt.toString());
    }

    @Command(name = "jointest")
    public static void SendAdminList(Whatsapp whatsapp, MessageInfo info, String link){
        whatsapp.acceptGroupInvite(link);
        System.out.println("Ich bin der Gruppe Beigetreten Nahui. | " + link);
    }

    @Command(name = "refreshdata")
    public static void RefreshBotData(Whatsapp api, MessageInfo info){
        InitializeBotData(api);
    }

    @Command(name = "ping")
    public static void SendPing(Whatsapp whatsapp, MessageInfo info){
        var date = System.nanoTime();
        var difference = date - (info.timestamp().get().getNano());
        whatsapp.sendMessage(info.chatJid(), "Received Ping!\n*Start:* " + info.timestamp().get().getNano() + "\n*Stop:* " + date + "\n*Speed:* " + difference + " nano-seconds");
    }

    @Command(name = "penis")
    public static void SendPenisList(Whatsapp whatsapp, MessageInfo info, String nonParsedNumb){
        if(nonParsedNumb == null)
            whatsapp.sendMessage(info.chatJid(), "Gib einen Text an!");
        else
            System.out.println("SendPenisList() wurde aufgerufen mit : " + Long.parseLong(nonParsedNumb));
    }
}
