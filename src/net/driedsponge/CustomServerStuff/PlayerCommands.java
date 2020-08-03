package net.driedsponge.CustomServerStuff;
import org.bukkit.command.CommandExecutor;
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
        return false;
    }
}
