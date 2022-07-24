package me.darux.spacebot.Discord.listener;

import me.darux.spacebot.Main;
import me.darux.spacebot.Minecraft.file.FileCreator;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordToMc extends ListenerAdapter {
    private Main plugin;

    public DiscordToMc(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(e.getAuthor().isBot()){
            return;
        }
        TextChannel channel=plugin.getDiscord().getTextChannelById(plugin.getFunctions().getString("Chat.channelid"));
        try {
            if(e.getChannel().getId().equals(channel.getId())){
                FileCreator functions= plugin.getFunctions();

                Bukkit.broadcastMessage(functions.getString("Chat.DiscordToMinecraft.message")
                        .replace("%nick%",e.getAuthor().getName())
                        .replace("%message%",e.getMessage().getContentRaw())

                );


            }
        }catch (NullPointerException ee){
            return;
        }

    }


}
