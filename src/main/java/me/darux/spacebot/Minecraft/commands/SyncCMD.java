package me.darux.spacebot.Minecraft.commands;

import me.darux.spacebot.Main;
import me.darux.spacebot.Minecraft.Utils.Utils;
import me.darux.spacebot.Minecraft.file.FileCreator;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SyncCMD implements CommandExecutor {
    private Main plugin;

    public SyncCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> list=plugin.getSync();
        for(int i=0;i<list.size();i++){
            String[] split=list.get(i).split(";");
            String nick=split[0];
            String tag=split[1];
            String id=split[2];
            if(id==null){
                Bukkit.broadcastMessage("a");
            }

            if(nick.equalsIgnoreCase(sender.getName())){


                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getName().equalsIgnoreCase(nick)){
                        p.sendMessage(Utils.translate("&aHas vinculado tu cuenta a &e"+tag));
                        FileCreator config=plugin.getSyncs();
                        JDA jda=plugin.getDiscord();
                        if(jda==null) Bukkit.broadcastMessage("a");








                        config.set("Syncs."+p.getName(),tag);




                        config.save();
                        plugin.getSync().remove(i);
                        return true;
                    }
                }

                sender.sendMessage(Utils.translate("&cNo tienes ninguna solicitud de esa persona o ha caducado"));




                return true;
            }

        }
        return false;
    }
}
