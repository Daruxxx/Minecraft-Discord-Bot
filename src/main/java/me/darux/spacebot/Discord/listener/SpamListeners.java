package me.darux.spacebot.Discord.listener;

import me.darux.spacebot.SpaceBotMain;
import me.darux.spacebot.Minecraft.Utils.Utils;
import me.darux.spacebot.Minecraft.file.FileCreator;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;

import java.awt.*;

public class SpamListeners {
    private SpaceBotMain plugin;

    public SpamListeners(SpaceBotMain plugin) {
        this.plugin = plugin;
    }


    public void spam(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                FileCreator config=plugin.getFunctions();
                if(plugin.getData().getBoolean("DISCOUNT-SPAM")){
                    TextChannel textChannel=plugin.getDiscord().getTextChannelById(config.getString("Discountspam.channelid"));
                           // .sendMessage(config.getString("Discountspam.message").replaceAll("%discount%", plugin.getData().getString("DISCOUNT-SPAM-AMOUNT"))).queue();
                String titulo=plugin.getFunctions().getString("Discountspam.Embed.title").replaceAll("%discount%",plugin.getData().getString("DISCOUNT-SPAM-AMOUNT"));
                String desc=plugin.getFunctions().getString("Discountspam.Embed.description").replaceAll("%discount%",plugin.getData().getString("DISCOUNT-SPAM-AMOUNT"));
                Color color= Utils.color(plugin.getFunctions().getString("Discountspam.Embed.color"));
                    EmbedBuilder eb=Utils.embedBuilder(titulo,desc,color);
                    eb.setThumbnail(plugin.getDiscord().getSelfUser().getAvatarUrl());
                textChannel.sendMessage(eb.build()).queue();



                }

            }
        },40,plugin.getFunctions().getInt("Discountspam.spam-delay")*20);
    }
}
