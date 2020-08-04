package net.driedsponge.CustomServerStuff;

import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin{
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
		this.getCommand("log").setExecutor(new PlayerCommands());
		this.getCommand("z").setExecutor(new PlayerCommands());
		System.out.println(ANSI_CYAN+"DriedSponge's Custom Server Stuff Loaded"+ANSI_WHITE);
		new TimerThing().main();
	}
	@Override
	public void onDisable() {
	}
	// /hello <-- Hey Welcome!
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("broadcast")) {
			if (sender instanceof Player) {
				//player
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&1HAve fun"));
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&1Test Broad cst"));
					return true;
				}else {
					player.sendMessage(ChatColor.AQUA+"Invalid Perms");
					return true;
				}
			}else {
				// console
				sender.sendMessage("Hello console");
				return true;
			}
		}
		return false;
	}
	
}
