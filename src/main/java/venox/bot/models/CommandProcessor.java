package venox.bot.models;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;
import it.auties.whatsapp.util.Json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private Map<String, Method> commands = new HashMap<>();

    public void register(Object obj) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command cmd = method.getAnnotation(Command.class);
                commands.put(cmd.name(), method);
            }
        }
    }

    public void process(Whatsapp whatsapp, MessageInfo info, String input, String[] args) {
        String cmdName = input.substring(1);
        System.out.println((args.length));
        String[] parts = args.length >= 1 ? Arrays.copyOfRange(args, 0, args.length) : new String[] {};

        Method cmdMethod = commands.get(cmdName);
        if (cmdMethod == null) {
            System.out.println("Unknown command: " + cmdName);
            return;
        }
        try {
            Class<?>[] paramTypes = cmdMethod.getParameterTypes();
            Object[] params = new Object[paramTypes.length];
            params[0] = whatsapp;
            params[1] = info;
            System.out.println("parts length : " + parts.length);
            for (int i = 0; i < parts.length; i++) {
                int paramIndex = i + 2;
                if (paramIndex >= params.length) break; // stop if we run out of params
                if(paramTypes[paramIndex].isArray() && paramTypes[paramIndex].getComponentType() == String.class)
                    params[paramIndex] = Arrays.copyOfRange(parts, i, parts.length);
                else
                    params[paramIndex] = parts[i];

                System.out.println(params[paramIndex] + " | " + i);

            }

            cmdMethod.invoke(null, params);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
