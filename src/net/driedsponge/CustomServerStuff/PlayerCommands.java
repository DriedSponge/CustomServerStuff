package net.driedsponge.CustomServerStuff;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import net.md_5.bungee.api.ChatColor;
import net.driedsponge.CustomServerStuff.Main;

import java.util.HashMap;
import java.util.Map;

public class PlayerCommands implements CommandExecutor {
    Map<String, Long> zcooldown = new HashMap<String, Long>();

    Functions f = new Functions();
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("log")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TextComponent message = new TextComponent(f.Color("&6Go to &a&nhttps://driedsponge.net/mc &r&6to view the server status and console log.") );
                message.setBold(true);
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://driedsponge.net/mc"));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to open the url").color(ChatColor.GOLD).italic(true).create()));
                player.spigot().sendMessage(message);
                return true;
            } else {
                return true;
            }
        }
        if (label.equalsIgnoreCase("z")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(!player.isSleeping()){
                    player.sendMessage(f.Color("&cYou must be sleeping to use this command!"));
                    return true;
                }
                if(zcooldown.containsKey(player.getName())){
                    if(zcooldown.get(player.getName()) > System.currentTimeMillis()){
                        //They still have cool down
                        long timeleft = (zcooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
                        player.sendMessage(f.Color("&cYou can use this command again in "+timeleft+" second(s)"));
                        return true;
                    }
                }
                zcooldown.put(player.getName(), System.currentTimeMillis() + (30*1000));
                for(Player ply: Bukkit.getOnlinePlayers()){
                    if(!ply.isSleeping()){
                        ply.sendMessage(f.Color("&b"+player.getDisplayName()+"&r &6wants you to go to sleep."));
                    }
                }
                f.Title(" {\"text\":\"ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ\",\"bold\":true,\"color\":\"red\"}",5);
                return true;
            } else {
                return true;
            }
        }
        if (label.equalsIgnoreCase("chatpos")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.chat(f.Color(PlaceholderAPI.setPlaceholders(player,"&6My coordinates are: &aX:%player_x% Y:%player_y% Z:%player_z%")));
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

}
