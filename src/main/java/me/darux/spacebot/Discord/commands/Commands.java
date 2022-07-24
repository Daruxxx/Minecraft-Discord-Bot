package me.darux.spacebot.Discord.commands;

import me.darux.spacebot.Main;
import me.darux.spacebot.Minecraft.Utils.Utils;
import me.darux.spacebot.Minecraft.file.FileCreator;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;

public class Commands extends ListenerAdapter {
    private Main plugin;

    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(e.getMessage().getAuthor().isBot()){
            return;
        }
        FileCreator config=plugin.getBotsettings();
        FileCreator commands=plugin.getCommands();
        String prefix=config.getString("PREFIX");
        MessageChannel channel=e.getChannel();

        if(commands.getBoolean("Ip.Activado")){

        if(e.getMessage().getContentRaw().startsWith(prefix+commands.getString("Ip.comando"))){
            EmbedBuilder eb=new EmbedBuilder();
            eb.setTitle(commands.getString("Ip.Embed.Titulo"));
            eb.setDescription(commands.getString("Ip.Embed.Descripcion"));
            eb.setColor(Utils.color(commands.getString("Ip.Embed.Color")));
            eb.setAuthor(e.getAuthor().getName(),null,e.getAuthor().getAvatarUrl());
            eb.setThumbnail(plugin.getDiscord().getSelfUser().getAvatarUrl());


            e.getChannel().sendMessage(eb.build()).queue();
        }





        }
        if(commands.getBoolean("Discount.Activado")) {
            if (e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (e.getMessage().getContentRaw().startsWith(prefix + commands.getString("Discount.comando"))) {
                    String argss = e.getMessage().getContentRaw().replaceAll(prefix + commands.getString("Discount.comando"), "");
                    String[] args = argss.split(" ");
                    FileCreator data = plugin.getData();
                    if (data.getBoolean("DISCOUNT-SPAM")) {
                        data.set("DISCOUNT-SPAM", false);
                        channel.sendMessage("Has desactivado el spam de descuento").queue();
                    } else {

                        if (args.length <1) {
                            channel.sendMessage("Debes poner el porcentaje descontado \n Ejemplo: !discount 20").queue();
                            return;
                        }
                        data.set("DISCOUNT-SPAM", true);
                        data.set("DISCOUNT-SPAM-AMOUNT", args[1] + "%");
                        data.save();
                        channel.sendMessage("Has activado el spam de descuento del " + args[1] + "%").queue();


                    }

                }
            }
        }

        if(e.getMember().hasPermission(Permission.ADMINISTRATOR)){

            if(e.getMessage().getContentRaw().startsWith(prefix+commands.getString("Executecommand.comando"))){
                String args = e.getMessage().getContentRaw().replaceAll(prefix + commands.getString("Executecommand.comando")+" ", "");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),args);
                String titulo=commands.getString("Executecommand.Embed.Titulo");
                String desc=commands.getString("Executecommand.Embed.Descripcion").replace("%comando%",args);
                Color color=Utils.color(commands.getString("Executecommand.Embed.Color"));
                EmbedBuilder eb=Utils.embedBuilder(titulo,desc,color);
                eb.setThumbnail(plugin.getDiscord().getSelfUser().getAvatarUrl());
                e.getChannel().sendMessage(eb.build()).queue();




            }
        }

        if(e.getMember().hasPermission(Permission.ADMINISTRATOR)){

            if(e.getMessage().getContentRaw().startsWith(prefix+commands.getString("Online.comando"))){
                if(commands.getBoolean("Online.Activado")){
                    String titulo=commands.getString("Online.Embed.Titulo");
                    String descripcion="";
                    Color color=Utils.color(commands.getString("Online.Embed.Color"));
                    for(Player p : Bukkit.getOnlinePlayers()){
                        descripcion=descripcion+commands.getString("Online.Embed.Player-separator")+p.getName();
                    }
                    titulo.replace("%online%",String.valueOf(Bukkit.getOnlinePlayers().size()));
                    EmbedBuilder eb=Utils.embedBuilder(titulo,descripcion,color);
                    eb.setThumbnail(plugin.getDiscord().getSelfUser().getAvatarUrl());
                    channel.sendMessage(eb.build()).queue();
                }
            }
        }




    }
}
