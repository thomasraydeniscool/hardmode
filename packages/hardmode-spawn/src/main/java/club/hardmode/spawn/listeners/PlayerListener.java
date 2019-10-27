package club.hardmode.spawn.listeners;

import club.hardmode.spawn.HardmodeSpawn;
import club.hardmode.spawn.session.PlayerSession;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        HardmodeSpawn.getInstance().getSession().newSession(player);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerSession session = HardmodeSpawn.getInstance().getSession().get(player);

        if (session.isPvpTagged()) {
            player.setHealth(0);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        PlayerSession session = HardmodeSpawn.getInstance().getSession().get(player);

        if (session.isProtected()) {
            event.setCancelled(true);
        }
    }

}
