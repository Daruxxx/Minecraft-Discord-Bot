package me.darux.spacebot.Discord.listener;

import me.darux.spacebot.Main;
import me.darux.spacebot.file.FileCreator;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;

public class SpamListeners {
    private Main plugin;

    public SpamListeners(Main plugin) {
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
                textChannel
                        .sendMessage(config.getString("Discountspam.message")
                .replaceAll("%discount%",plugin.getData().getString("DISCOUNT-SPAM-AMOUNT"))

                ).queue();
                }

            }
        },40,plugin.getFunctions().getInt("Discountspam.spam-delay")*20);
    }
}
