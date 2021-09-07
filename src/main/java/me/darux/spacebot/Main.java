package me.darux.spacebot;




import me.darux.spacebot.Discord.Status;
import me.darux.spacebot.Discord.commands.Commands;
import me.darux.spacebot.Discord.listener.DiscordToMc;
import me.darux.spacebot.Discord.listener.SpamListeners;
import me.darux.spacebot.Utils.Utils;
import me.darux.spacebot.commands.SpaceBot;
import me.darux.spacebot.file.FileCreator;
import me.darux.spacebot.listeners.MinecraftToDiscord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sun.jvm.hotspot.gc.shared.Space;

import javax.print.attribute.standard.Compression;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.List;


public class Main extends JavaPlugin {


private JDA discord;
private FileCreator botsettings;
private FileCreator commands;
private FileCreator data;
private FileCreator temporaldata;
private FileCreator functions;

File folder=new File("plugins/SpaceBot/data/");






    public void onEnable(){
        folder.mkdir();
        botsettings=new FileCreator(this,"BotSettings",".yml");
        commands=new FileCreator(this,"commands");
        data=new FileCreator(this,"data",".yml",folder);
        temporaldata=new FileCreator(this,"temporaldata",".yml",folder);
        functions=new FileCreator(this,"functions");

        SpamListeners spamListeners=new SpamListeners(this);
        spamListeners.spam();
        Status status=new Status(this);
        status.a();

        try {
            discord=JDABuilder.createDefault("ODg0MTY4Nzc2NzIyMDI2NTI3.YTUkVw.Hk0UuQ6y2cADr2rjm0cDyKcluRQ")
                    .setActivity(Activity.playing(getBotsettings().getStringList("STATUS").get(1)))
                    .setStatus(Utils.status(getBotsettings().getString("STATUS-MODE")))
                    .addEventListeners(new Commands(this))
                    .addEventListeners(new DiscordToMc(this))
                    .build();


        } catch (LoginException e) {
            e.printStackTrace();
        }


        registerEvents();
        registrarComandos();
    }


    public void onDisable(){

        getDiscord().shutdown();

    }



    public void registrarComandos() {
        this.getCommand("spacebot").setExecutor(new SpaceBot(this));

    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MinecraftToDiscord(this),this);


    }

    public FileCreator getBotsettings() {
        return botsettings;
    }

    public JDA getDiscord() {
        return discord;
    }

    public FileCreator getCommands() {
        return commands;
    }

    public FileCreator getData() {
        return data;
    }

    public FileCreator getFunctions() {
        return functions;
    }

    public FileCreator getTemporaldata() {
        return temporaldata;
    }
}
