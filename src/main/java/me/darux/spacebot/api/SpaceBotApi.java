package me.darux.spacebot.api;

import me.darux.spacebot.Main;
import net.dv8tion.jda.api.JDA;

public class SpaceBotApi {

    private Main plugin;

    public SpaceBotApi(Main plugin) {
        this.plugin = plugin;
    }

    private JDA discord=plugin.getDiscord();

    public JDA getDiscord(){
        return discord;
    }




}
