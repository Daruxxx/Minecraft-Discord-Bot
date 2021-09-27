package me.darux.spacebot;




import me.darux.spacebot.Discord.Status;
import me.darux.spacebot.Discord.commands.Commands;
import me.darux.spacebot.Discord.commands.SyncCommand;
import me.darux.spacebot.Discord.listener.DiscordToMc;
import me.darux.spacebot.Discord.listener.SpamListeners;
import me.darux.spacebot.Minecraft.Utils.Utils;
import me.darux.spacebot.Minecraft.commands.SpaceBot;
import me.darux.spacebot.Minecraft.commands.SyncCMD;
import me.darux.spacebot.Minecraft.file.FileCreator;
import me.darux.spacebot.Minecraft.listeners.MinecraftToDiscord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main extends JavaPlugin {


private JDA discord;
private FileCreator botsettings;
private FileCreator commands;
private FileCreator data;
private FileCreator temporaldata;
private FileCreator functions;
private FileCreator syncs;
File folder=new File("plugins/SpaceBot/data/");
List<String> sync=new ArrayList<>();






    public void onEnable(){
        folder.mkdir();
        syncs=new FileCreator(this,"syncs");
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
                    .setActivity(Activity.playing(getBotsettings().getStringList("STATUS").get(0)))
                    .setStatus(Utils.status(getBotsettings().getString("STATUS-MODE")))
                    .addEventListeners(new Commands(this))
                    .addEventListeners(new DiscordToMc(this))
                    .addEventListeners(new SyncCommand(this))

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
        this.getCommand("sync").setExecutor(new SyncCMD(this));



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

    public List<String> getSync() {
        return sync;
    }

    public void setSync(List<String> sync) {
        this.sync = sync;
    }

    public FileCreator getSyncs() {
        return syncs;
    }
}
