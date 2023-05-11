package venox.bot;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;
import it.auties.whatsapp.model.message.standard.TextMessage;
import it.auties.whatsapp.model.mobile.VerificationCodeMethod;
import it.auties.whatsapp.model.privacy.PrivacySettingType;
import it.auties.whatsapp.model.privacy.PrivacySettingValue;
import javafx.application.Platform;
import venox.bot.models.Command;
import venox.bot.models.CommandProcessor;
import venox.bot.models.Session;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static venox.bot.database.Index.CreateDatabaseConnection;

// This is the main class of our bot
public class Main {
    public static CommandProcessor manager = new CommandProcessor();
    public static Session[] sessionId = {
            //new Session("VenoX - Bot - 650 - 700", 436502093808700L),
            //new Session("VenoX - Bot - 650 - 701", 436502093808701L),
            new Session("VenoX - Bot - 650 - 702", 436502093808702L, true),
            new Session("VenoX - Bot - 650 - 704", 436502093808704L, true),
            new Session("VenoX - Bot - 650 - 705", 436502093808705L, true),
    };

    public static void RegisterNewSession(Session session){
        Whatsapp.mobileBuilder()
                .knownConnection(UUID.nameUUIDFromBytes(session.Name.getBytes(StandardCharsets.UTF_8)))
                .unregistered()
                .register(session.Number,   VerificationCodeMethod.CALL, Main::onScanCode)
                .join()
                .addLoggedInListener(Main::InitializeBotData)
                .addDisconnectedListener(reason -> System.out.printf("Disconnected: %s%n", reason))
                .addNewMessageListener(Main::OnMessage)
                .connect()
                .join();
    }
    public static void LoadSession(Session session){
        Whatsapp.mobileBuilder()
                .knownConnection(UUID.nameUUIDFromBytes(session.Name.getBytes(StandardCharsets.UTF_8)))
                .registered()
                .addLoggedInListener(Main::onConnected)
                .addDisconnectedListener(reason -> System.out.printf("Disconnected: %s%n", reason))
                .addNewMessageListener(Main::OnMessage)
                .connect()
                .join();

    }

    public static void OnMessage(Whatsapp whatsapp, MessageInfo info){
        if(info.fromMe()) return;
        if (info.message().content() instanceof TextMessage textMessage) {
            System.out.println("[MESSAGE]" + textMessage.text().toLowerCase());
            //whatsapp.markRead(info);

            boolean isCmd = textMessage.text().startsWith("/");
            if(isCmd){
                String command = textMessage.text().toLowerCase().split(" ")[0];
                String[] args = Arrays.stream(textMessage.text().replaceAll("&nbsp;", " ").split(" "))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .skip(1)
                        .toArray(String[]::new);

                manager.process(whatsapp, info, command, args);
            }
            //whatsapp.sendMessage(info.chatJid(), "[DEBUG]: Deine Nachricht war: " + textMessage.text());
        }
    }

    private static void RegisterAllCommands() throws IOException {
        try{
        // Verzeichnis angeben, in dem sich die Klassen mit den Befehlen befinden
        //Path commandsDir = Paths.get("src", "main", "java", "venox.bot");
        Path commandsDir = Paths.get("C:\\Users\\kuroosh\\OneDrive\\Desktop\\VenoX-Java\\target\\classes\\venox\\bot");
        //Path commandsDir = Path.of("src/main/java/venox.bot/");

        // Rekursiv alle Klassen-Dateien durchsuchen
        Files.walk(commandsDir)
                .filter(path -> path.toString().endsWith(".class"))
                .forEach(path -> {
                    try {
                        System.out.println(path);
                        //System.out.println(path.toString().substring(commandsDir.toString().length() + 1, path.toString().length() - 6));

                        // Pfad zur Klasse in einen Klassenname umwandeln
                            /*String className = path.toString()
                                    .substring(commandsDir.toString().length() + 1, path.toString().length() - 6)
                                    .replace(File.separatorChar, '.');*/
                        String className = path.toString()
                                .substring(commandsDir.toString().length() + 1, path.toString().length() - 6)
                                .replace(File.separatorChar, '.');
                        className = "venox.bot." + className;
                        //System.out.println(className);
                        // Klasse laden und nach Befehlen durchsuchen
                        Class<?> clazz = Class.forName(className);
                        for (Method method : clazz.getDeclaredMethods()) {
                            if (method.isAnnotationPresent(Command.class)) {
                                var newInstance = clazz.newInstance();
                                System.out.println(newInstance);
                                // Befehl im CommandManager registrieren
                                manager.register(newInstance);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) throws ExecutionException, InterruptedException, IOException {
        venox.bot.security.verify_sytem.Constants.Initialize();
        System.setProperty("file.encoding", "UTF-8");
        venox.bot.utils.Platform.InitPlatformData();




        RegisterAllCommands();
        CreateDatabaseConnection();

        for (Session session : sessionId) {
            //System.out.println("SESSION: " + session.Name + " | " + session.Number);
            if (session.IsRegistered)
                LoadSession(session);
            else
                RegisterNewSession(session);
        }
    }

    public static void InitializeBotData(Whatsapp api){
        try {
            api.changeName("VenoX");
            api.changeStatus("Nutze /join LINK um den Bot in deiner Gruppe hinzuzufügen. | bsp : /join https://chat.whatsapp.com/XXXXXXXXXXXXXX");
            api.changePrivacySetting(PrivacySettingType.PROFILE_PIC, PrivacySettingValue.EVERYONE);
            api.changePrivacySetting(PrivacySettingType.ADD_ME_TO_GROUPS, PrivacySettingValue.CONTACTS);
            api.changePrivacySetting(PrivacySettingType.STATUS, PrivacySettingValue.EVERYONE);
            api.changePrivacySetting(PrivacySettingType.LAST_SEEN, PrivacySettingValue.EVERYONE);
            api.changePrivacySetting(PrivacySettingType.READ_RECEIPTS, PrivacySettingValue.EVERYONE);
            byte[] imageBytes = Files.readAllBytes(Paths.get("./assets/images/venox.png"));
            api.changeProfilePicture(imageBytes);
            onConnected(api);
        }
        catch(Exception ex){
            System.out.print(ex);
        }
    }

    private static void onConnected(Whatsapp api) {
        System.out.println("Connected to mobile api");
    }

    private static CompletableFuture<String> onScanCode() {
        System.out.println("Enter OTP: ");
        var scanner = new Scanner(System.in);
        return CompletableFuture.completedFuture(scanner.nextLine().trim());
    }

}