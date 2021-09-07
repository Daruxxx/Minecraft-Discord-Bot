package me.darux.spacebot.Discord;

import me.darux.spacebot.Main;
import me.darux.spacebot.Utils.Utils;
import me.darux.spacebot.file.FileCreator;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
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


                    if(status.startsWith(lista.get(i).replaceAll("%minecraft_online%","").replace("%online%",""))){


                        if(i==lista.size()-1){
                            plugin.getDiscord().getPresence().setActivity(Activity.playing(lista.get(0)
                            .replaceAll("%minecraft_online%",String.valueOf(Bukkit.getOnlinePlayers().size()))


                            ));
                        }else {
                            plugin.getDiscord().getPresence().setActivity(Activity.playing(lista.get(i + 1)
                                    .replaceAll("%minecraft_online%",String.valueOf(Bukkit.getOnlinePlayers().size()))

                            ));
                        }
                        return;

                    }

                }
                Bukkit.getConsoleSender().sendMessage(Utils.translate("&cHa ocurrido un error cargando las presencias, avisame (D4RUX)"));



            }
        },0,plugin.getBotsettings().getInt("STATUS-CHANGE-DELAY")*20);
    }
}
