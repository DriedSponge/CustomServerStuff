package net.driedsponge.CustomServerStuff;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R1.EntityPlayer;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R1.CraftServer;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import java.util.Random;
import me.clip.placeholderapi.PlaceholderAPI;

public class Events implements Listener{
	Functions f = new Functions();
	Plugin plugin = Main.getPlugin(Main.class);
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		String PlayerName = player.getDisplayName();
		EntityDamageEvent.DamageCause DeathCause = event.getEntity().getLastDamageCause().getCause();
		String DeathMessage = "";
		switch (DeathCause){
			case FALL:
				DeathMessage = "&b%player_name% &6forgot how to walk.";
				break;
			case FIRE:
				DeathMessage = "&b%player_name% &6was cooked to death.";
				break;
			case DROWNING:
				DeathMessage = "&b%player_name% &6tried to become a fish and failed miserably.";
				break;
			case LIGHTNING:
				DeathMessage = "&b%player_name% &6was struck by lightning. That's 1 in 1,222,000 chance!";
				break;
			case LAVA:
				DeathMessage = "&b%player_name% &6swam in lava.";
				break;
			case WITHER:
				DeathMessage = "&b%player_name% &6got BRUHED by the WIther.";
				break;
			case STARVATION:
				DeathMessage = "&b%player_name% &6 starved to death.";
				break;
			case BLOCK_EXPLOSION:
				DeathMessage = "&b%player_name% &6was exploded.";
				break;
			case ENTITY_EXPLOSION:
				DeathMessage = "&b%player_name% &6was exploded.";
				break;
			case VOID:
				DeathMessage = "&b%player_name% &6has been sent to Brazil.";
				break;
			case ENTITY_ATTACK:
					EntityPlayer nmsPlayer = ((CraftPlayer) event.getEntity()).getHandle();
					Entity entity = CraftEntity.getEntity((CraftServer) event.getEntity().getServer(), nmsPlayer.getKillingEntity());
					LivingEntity livingEntity = (LivingEntity) entity;
					switch (livingEntity.getType()){
						case PLAYER:
							DeathMessage ="&b%player_name% &6was beat to death by &b"+player.getKiller().getDisplayName();
							break;
						case ZOMBIE:
							DeathMessage ="&b%player_name% &6was mauled by a &b"+livingEntity.getType().toString().toLowerCase();
							break;
						case WOLF:
							DeathMessage ="&b%player_name% &6was mauled by a &b"+livingEntity.getType().toString().toLowerCase();
							break;
						case ENDERMAN:
							DeathMessage ="&b%player_name% &6was killed by an &bEnderman";
							break;
						default:
							DeathMessage ="&b%player_name% &6was killed by a &b"+livingEntity.getType().toString().toLowerCase();
							break;
					}
				break;
			default:
				DeathMessage = "&b%player_name% &6died.";
				break;
		}
		event.setDeathMessage(f.Color(PlaceholderAPI.setPlaceholders(player,DeathMessage+" &c&lLOL")));
		player.sendMessage(f.Color(PlaceholderAPI.setPlaceholders(player,"&6You died. That sucks. Here is where you died: &aX:%player_x% Y:%player_y% Z:%player_z%")));
	}
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event) {
		//here is the code that will be run once the event is triggered
		Player player = event.getPlayer();
		player.setPlayerListName(PlaceholderAPI.setPlaceholders(event.getPlayer(),plugin.getConfig().getString("tab-list.format")));
		String playername = player.getDisplayName();
		String motd = f.Color(plugin.getConfig().getString("player-join.motd"));
		TextComponent message = new TextComponent(PlaceholderAPI.setPlaceholders(event.getPlayer(), motd));
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/help"));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Click here to run /help").color(ChatColor.GRAY).italic(false).create()));
		player.spigot().sendMessage(message);
		String[] DefaultMessage =plugin.getConfig().getStringList("player-join.join-messages").toArray(new String[0]);
		Random r=new Random();
		int randomNumber=r.nextInt(DefaultMessage.length);
		String JoinMessage = f.Color(DefaultMessage[randomNumber]);
		event.setJoinMessage(PlaceholderAPI.setPlaceholders(event.getPlayer(), JoinMessage));
	}
	@EventHandler
	public void BlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Material material = event.getBlock().getType();
		switch (material){
			case DIAMOND_ORE:
				System.out.println("[DriedPlugin] "+player.getDisplayName()+" mined diamond ore");
				break;
			case DIAMOND_BLOCK:
				System.out.println("[DriedPlugin] "+player.getDisplayName()+" mined a diamond block");
				break;

		}
	}
	@EventHandler
	public void ChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		String format = plugin.getConfig().getString("chat.format");
		//event.setMessage(format);
		//event.setFormat(f.Color(PlaceholderAPI.setPlaceholders(player,format)));
		event.setFormat(f.Color(PlaceholderAPI.setPlaceholders(player,format)));
	}
}
