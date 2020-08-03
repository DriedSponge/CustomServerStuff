//For anyone viewing this file, please note that this was added for sattire
package net.driedsponge.CustomServerStuff;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
public class FortniteItemShopGenerator extends TimerTask{
    Functions f = new Functions();
    public void run() {
        String text = "Stuff";
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"ITEM SHOP CHANGE\",\"bold\":true,\"color\":\"red\"}");
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(5, 10 * 20, 5);
        for(Player player: Bukkit.getOnlinePlayers()){
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
            player.playSound(player.getLocation(), "minecraft:entity.ender_dragon.death", 1.0f , 1.0f );

        }
        Bukkit.broadcastMessage(f.Color("&6Go to &a&nhttps://fnbr.co/shop&r&6 to see the cool skins in the shop!"));
    }
}
class TimerThing {
    public static void main() {
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.AM_PM, Calendar.PM);
        date.set(Calendar.HOUR, 5);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        timer.schedule(
                new FortniteItemShopGenerator(),
                date.getTime(),
                1000 * 60 * 60 * 24
        );
    }
}