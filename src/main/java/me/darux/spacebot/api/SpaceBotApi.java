package me.darux.spacebot.api;

import me.darux.spacebot.SpaceBotMain;
import net.dv8tion.jda.api.JDA;

public class SpaceBotApi {

    private SpaceBotMain plugin;

    public SpaceBotApi(SpaceBotMain plugin) {
        this.plugin = plugin;
    }

    private JDA discord=plugin.getDiscord();

    public JDA getDiscord(){
        return discord;
    }




}
