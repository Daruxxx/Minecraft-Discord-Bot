package me.darux.spacebot.Discord.listener;

import me.darux.spacebot.Main;
import me.darux.spacebot.Utils.Utils;
import me.darux.spacebot.file.FileCreator;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.awt.image.ReplicateScaleFilter;

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
        if(e.getChannel().getId().equals(channel.getId())){
            FileCreator functions= plugin.getFunctions();

            Bukkit.broadcastMessage(functions.getString("Chat.DiscordToMinecraft.message")
            .replace("%nick%",e.getAuthor().getName())
                            .replace("%message%",e.getMessage().getContentRaw())

            );


        }
    }


}
