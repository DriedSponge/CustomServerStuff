package net.driedsponge.CustomServerStuff;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
public class PlayerCommands implements CommandExecutor {
    Functions f = new Functions();
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("log")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(f.Color("&6Go to &a&nhttps://driedsponge.net/mc &r&6to view the server status and console log."));
                return true;
            } else {
                return true;
            }
        }
        if (label.equalsIgnoreCase("z")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(player.isSleeping()){
                    for(Player ply: Bukkit.getOnlinePlayers()){
                        if(!ply.isSleeping()){
                            ply.sendMessage("&b"+player.getDisplayName()+"&r wants you to go to sleep.");
                        }
                    }
                    f.Title(" {\"text\":\"ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ\",\"bold\":true,\"color\":\"red\"}",5);
                    return true;
                }else{
                    player.sendMessage(f.Color("&cYou must be sleeping to use this command!"));
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

}
