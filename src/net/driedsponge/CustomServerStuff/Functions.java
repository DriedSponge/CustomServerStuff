package net.driedsponge.CustomServerStuff;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Functions{
    public String Color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public void Title(String json, int seconds){
        String text = "Stuff";
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a(json);
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(5, seconds * 20, 5);
        for(Player player: Bukkit.getOnlinePlayers()){
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
        }
    }
}
