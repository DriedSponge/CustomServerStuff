package net.driedsponge.CustomServerStuff;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.Configuration;

public class AdminCommands implements CommandExecutor{
    Functions f = new Functions();
    Plugin plugin = Main.getPlugin(Main.class);
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("customstuff")) {
            if(args.length == 0){
                sender.sendMessage("&cUsage: /customstuff reload");
                return true;
            }
            if(args[0].equalsIgnoreCase("reload")){
                plugin.reloadConfig();
                sender.sendMessage(f.Color(plugin.getConfig().getString("reload.message")));
                return true;
            }
            return false;

        }
        return false;
    }
}
