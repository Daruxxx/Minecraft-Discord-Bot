package me.darux.spacebot.Discord.Utils;

import me.darux.spacebot.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Member;

public class Comando {

    String nombre;
    String[] alias;
    Permission permission;
    Member miembro;
    MessageChannel channel;
    String message;
    User author;
    Main plugin;

    public Comando(String nombre, String[] alias, Permission permission, Member miembro, MessageChannel channel, String message, User author, Main plugin) {
        this.nombre = nombre;
        this.alias = alias;
        this.permission = permission;
        this.miembro = miembro;
        this.channel = channel;
        this.message = message;
        this.author = author;
        this.plugin = plugin;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Member getMiebro() {
        return miembro;
    }

    public void setMiebro(Member miebro) {
        this.miembro = miebro;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public void setChannel(MessageChannel channel) {
        this.channel = channel;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public Member getMiembro() {
        return miembro;
    }

    public void setMiembro(Member miembro) {
        this.miembro = miembro;
    }

    public boolean ejecutando(){
        String prefix=plugin.getBotsettings().getString("PREFIX");
        for(String alias : alias){
            if(message.startsWith(prefix+nombre) || message.startsWith(prefix+alias) && miembro.hasPermission(permission)){
                return true;
            }
        }

        return false;
    }

    public String[] args(){
        String prefix=plugin.getBotsettings().getString("PREFIX");
        String mensaje="";
        for(String alias : alias){

            mensaje=message.replace(prefix,"");


                mensaje=message.replace(nombre+" ","");


                mensaje=message.replace(alias+" ","");


    }
        if(mensaje.equals("")){
            return null;
        }
        return mensaje.split(" ");
}
public void reply(String message){
        channel.sendMessage(message).queue();
}


}
