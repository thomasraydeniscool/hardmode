package club.hardmode.spawn.listeners;

import club.hardmode.spawn.HardmodeSpawn;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        Location location = event.getLocation();

        if (HardmodeSpawn.isInsideSpawn(location)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        Location location = event.getBlock().getLocation();

        if (HardmodeSpawn.isInsideSpawn(location)) {
            event.setCancelled(true);
        }
    }

}
