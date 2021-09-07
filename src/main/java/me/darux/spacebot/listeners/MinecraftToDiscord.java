package me.darux.spacebot.listeners;

import me.darux.spacebot.Main;
import me.darux.spacebot.Utils.Utils;
import me.darux.spacebot.file.FileCreator;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MinecraftToDiscord implements Listener {
    private Main plugin;

    public MinecraftToDiscord(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        JDA discord=plugin.getDiscord();
        FileCreator functions=plugin.getFunctions();
        if(functions.getBoolean("Chat.Activado")){
        TextChannel textChannel=discord.getTextChannelById(functions.getString("Chat.channelid"));

            EmbedBuilder eb=new EmbedBuilder();
            eb.setTitle(functions.getString("Chat.MinecraftToDiscord.Embed.titulo").replaceAll("%nick%",e.getPlayer().getName()));
            eb.setDescription(functions.getString("Chat.MinecraftToDiscord.Embed.descripcion").replaceAll("%mensaje%",e.getMessage()));
            eb.setColor(Utils.color(functions.getString("Chat.MinecraftToDiscord.Embed.color")));
            eb.setThumbnail("https://crafatar.com/avatars/"+e.getPlayer().getUniqueId());
            textChannel.sendMessage(eb.build()).queue();



    }}
}
