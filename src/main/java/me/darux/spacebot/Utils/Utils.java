package me.darux.spacebot.Utils;

import me.darux.spacebot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;

public class Utils {
    private Main plugin;

    public Utils(Main plugin) {
        this.plugin = plugin;
    }

    public static Color color(String a){
        Color color=Color.black;
        switch (a){
            case "AZUL":
                color=Color.BLUE;
                break;
            case "CIAN":
                color=Color.CYAN;
                break;
            case "ROJO":
                color=Color.RED;
                break;
            case "VERDE":
                color=Color.GREEN;
                break;
            case "AMARILLO":
                color=Color.YELLOW;
                break;
            case "ROSA":
              color=Color.MAGENTA;
            break;
        }
        return color;
    }
    public static String translate(String a){
        return ChatColor.translateAlternateColorCodes('&',a);
    }

public static EmbedBuilder embedBuilder(String titulo, String desc, Color color){
        EmbedBuilder eb=new EmbedBuilder();
        eb.setTitle(titulo);
        eb.setDescription(desc);
        eb.setColor(color);
        eb.setFooter("SpaceMc Bot ");
        return eb;
}

public static OnlineStatus status(String a){
    OnlineStatus onlineStatus=OnlineStatus.DO_NOT_DISTURB;

        switch (a){
            case "DND":
                onlineStatus=OnlineStatus.DO_NOT_DISTURB;
                break;
            case "ONLINE":
                onlineStatus=OnlineStatus.ONLINE;
                break;
            case "AUSENTE":
                onlineStatus=OnlineStatus.IDLE;
                break;

        }

        return onlineStatus;
}

}
