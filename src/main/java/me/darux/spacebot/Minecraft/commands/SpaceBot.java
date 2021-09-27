package me.darux.spacebot.Minecraft.commands;

import me.darux.spacebot.Main;
import me.darux.spacebot.Minecraft.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpaceBot implements CommandExecutor {
    private Main plugin;

    public SpaceBot(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            plugin.getFunctions().reload();
            plugin.getCommands().reload();
            plugin.getBotsettings().reload();
            sender.sendMessage(Utils.translate("&aHas recargado los archivos de configuración"));
        }
        return false;
    }
}
