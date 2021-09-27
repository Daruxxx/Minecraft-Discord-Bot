package me.darux.spacebot.Discord.listener;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BoostListener extends ListenerAdapter {
    @Override
    public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent e){
        User user=e.getUser();

        if(user==null){
            return;
        }



    }

}
