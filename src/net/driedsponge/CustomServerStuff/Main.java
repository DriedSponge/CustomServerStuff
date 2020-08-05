package net.driedsponge.CustomServerStuff;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
public final class Main extends JavaPlugin{
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	@Override
	public void onEnable() {
		if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
			getServer().getPluginManager().registerEvents(new Events(), this);
			this.saveDefaultConfig();
			this.getCommand("log").setExecutor(new PlayerCommands());
			this.getCommand("z").setExecutor(new PlayerCommands());
			this.getCommand("driedplugin").setExecutor(new AdminCommands());
			Bukkit.getLogger().info("[DriedPlugin] DriedSponge's Custom Server Stuff Loaded");
			new TimerThing().main();
		} else {
			Bukkit.getLogger().warning("[DriedPlugin] Could not find PlaceholderAPI! This plugin is required. This plugin has been disabled");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	@Override
	public void onDisable() {
	}
	
}
