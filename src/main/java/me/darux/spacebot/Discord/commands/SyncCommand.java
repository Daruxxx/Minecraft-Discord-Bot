package me.darux.spacebot.Discord.commands;

import me.darux.spacebot.CooldownManager;
import me.darux.spacebot.Discord.Utils.Comando;
import me.darux.spacebot.Main;
import me.darux.spacebot.Minecraft.Utils.Utils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class SyncCommand extends ListenerAdapter {
    private Main plugin;

    public SyncCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e){


        String[] alias={"sincronizar","vincular"};
        Comando comando=new Comando("sync",alias, Permission.VOICE_CONNECT,e.getMember(),e.getChannel(),e.getMessage().getContentRaw(),e.getAuthor(),plugin);

        if(comando.ejecutando()) {
            e.getMessage().delete();

            String[] args = comando.args();



            String nick="";
            try {
                nick = args[1];
            }catch (IndexOutOfBoundsException ee){
                comando.getChannel().sendMessage("Debes escribir tu nick de minecraft \n**Ejemplo**: !sync D4RUX").queue();
                return;
            }
                if(nick==null)return;
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getName().equalsIgnoreCase(nick)){
                        p.sendMessage(" ");
                        p.sendMessage(Utils.translate("&6Tienes una solicitud de vincular cuenta de discord con &e"+comando.getAuthor().getAsTag()));

                        TextComponent mensaje=new TextComponent();
                        mensaje.setText(Utils.translate("&a&lVINCULAR"));
                        mensaje.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/sync "+comando.getAuthor().getAsTag()));

                        mensaje.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("Acepta la solicitud").color(ChatColor.GREEN).create()));
                        p.spigot().sendMessage(mensaje);
                        p.sendMessage(" ");
                        comando.getChannel().sendMessage("Clica en el mensaje de vincular en minecraft para vincular la cuenta").queue();

                        plugin.getSync().add(p.getName()+";"+comando.getAuthor().getAsTag()+";"+comando.getAuthor().getId().toString());
                        CooldownManager cooldownManager=new CooldownManager(plugin);
                        cooldownManager.startCooldownSync(p.getName());

                        return;
                    }
                }
            comando.reply("No he podido encontrar a ese jugador, para poder usar el comando debes estar conectado a la modalidad de survival");
            }



        }




}
