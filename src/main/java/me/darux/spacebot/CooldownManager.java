package me.darux.spacebot;

import org.bukkit.Bukkit;

import java.util.List;

public class CooldownManager {
    private Main plugin;

    public CooldownManager(Main plugin) {
        this.plugin = plugin;
    }

    int time = 120;
    public void startCooldownSync(String name){

      int taskID=Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
          @Override
          public void run() {
              if(time == 0){
                  List<String> list=plugin.getSync();
                  for(int i=0;i<list.size();i++){
                      String[] split=list.get(i).split(";");
                      if(split[0].equalsIgnoreCase(name)){
                          list.remove(i);
                      }
                  }
                  plugin.setSync(list);
                  return;

              }else{
                  time--;
              }
          }
      },0,20);

    }

}
