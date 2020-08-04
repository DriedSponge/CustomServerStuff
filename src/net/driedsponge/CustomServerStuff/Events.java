package net.driedsponge.CustomServerStuff;

import net.minecraft.server.v1_16_R1.EntityPlayer;
import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import sun.jvm.hotspot.opto.Block;
import java.util.Random;
public class Events implements Listener{
	Functions f = new Functions();
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		String PlayerName = player.getDisplayName();
		EntityDamageEvent.DamageCause DeathCause = event.getEntity().getLastDamageCause().getCause();
		String DeathMessage = "";
		switch (DeathCause){
			case FALL:
				DeathMessage = "&b%playername% &6forgot how to walk.";
				break;
			case FIRE:
				DeathMessage = "&b%playername% &6was cooked to death.";
				break;
			case SUICIDE:
				DeathMessage = "&6Due to the lack of out reach for people with depression, &b%playername%&6 committed suicide.";
				break;
			case DROWNING:
				DeathMessage = "&b%playername% &6tried to become a fish and failed miserably.";
				break;
			case LIGHTNING:
				DeathMessage = "&b%playername% &6was struck by lightning. That's 1 in 1,222,000 chance!";
				break;
			case LAVA:
				DeathMessage = "&b%playername% &6swam in lava.";
				break;
			case WITHER:
				DeathMessage = "&b%playername% &6got BRUHED by the WIther.";
				break;
			case STARVATION:
				DeathMessage = "&b%playername% &6 starved to death.";
				break;
			case BLOCK_EXPLOSION:
				DeathMessage = "&b%playername% &6was exploded.";
				break;
			case ENTITY_EXPLOSION:
				DeathMessage = "&b%playername% &6was exploded.";
				break;
			case VOID:
				DeathMessage = "&b%playername% &6has been sent to Brazil.";
				break;
			case ENTITY_ATTACK:
					EntityPlayer nmsPlayer = ((CraftPlayer) event.getEntity()).getHandle();
					Entity entity = CraftEntity.getEntity((CraftServer) event.getEntity().getServer(), nmsPlayer.getKillingEntity());
					LivingEntity livingEntity = (LivingEntity) entity;
					switch (livingEntity.getType()){
						case PLAYER:
							DeathMessage ="&b%playername% &6was beat to death by &b"+player.getKiller().getDisplayName();
							break;
						case ZOMBIE:
							DeathMessage ="&b%playername% &6was mauled by a &b"+livingEntity.getType().toString().toLowerCase();
							break;
						case WOLF:
							DeathMessage ="&b%playername% &6was mauled by a &b"+livingEntity.getType().toString().toLowerCase();
							break;
						case ENDERMAN:
							DeathMessage ="&b%playername% &6was raped by an &bEnderman";
							break;
						default:
							DeathMessage ="&b%playername% &6was killed by a &b"+livingEntity.getType().toString().toLowerCase();
							break;
					}
				break;
			default:
				DeathMessage = "&b%playername% &6died.";
				break;
		}
		event.setDeathMessage(f.Color(DeathMessage.replace("%playername%", PlayerName) + " &c&lLOL"));
	}
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event) {
		//here is the code that will be run once the event is triggered
		Player player = event.getPlayer();
		String playername = player.getDisplayName();
		player.sendMessage(f.Color("&6Welcome back &b"+playername+"&6. &c10 &6Reddit Karma has been withdrawn from your account!"));
		String[] DefaultMessage = {
				"we missed you",
				"great you're back",
				"woo hoo",
				"yay",
				"&cyou will die"
		};
		Random r=new Random();
		int randomNumber=r.nextInt(DefaultMessage.length);
		event.setJoinMessage(f.Color("&b"+playername+" &6joined! "+DefaultMessage[randomNumber]));
	}
	@EventHandler
	public void BlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Material material = event.getBlock().getType();
		switch (material){
			case DIAMOND_ORE:
				System.out.println("[CustomServerStuff] "+player.getDisplayName()+" mined diamond ore");
				break;
			case DIAMOND_BLOCK:
				System.out.println("[CustomServerStuff] "+player.getDisplayName()+" mined a diamond block");
				break;

		}
	}
}
