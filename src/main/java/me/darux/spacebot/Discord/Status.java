package me.darux.spacebot.Discord;

import me.darux.spacebot.Main;
import me.darux.spacebot.Utils.Utils;
import me.darux.spacebot.file.FileCreator;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;

import java.util.List;

public class Status {
    private Main plugin;

    public Status(Main plugin) {
        this.plugin = plugin;
    }

    public void a(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {

                FileCreator botsettings=plugin.getBotsettings();
                List<String> lista =botsettings.getStringList("STATUS");
                for(int i=0;i<lista.size();i++){
                    String status=plugin.getDiscord().getPresence().getActivity().getName();


                    if(status.equalsIgnoreCase(lista.get(i))){
                        if(i==lista.size()-1){
                            plugin.getDiscord().getPresence().setActivity(Activity.playing(lista.get(0)));
                        }else {
                            plugin.getDiscord().getPresence().setActivity(Activity.playing(lista.get(i + 1)));
                        }
                        return;

                    }

                }
                Bukkit.getConsoleSender().sendMessage(Utils.translate("&cHa ocurrido un error cargando las presencias, avisame (D4RUX)"));



            }
        },0,plugin.getBotsettings().getInt("STATUS-CHANGE-DELAY")*20);
    }
}
