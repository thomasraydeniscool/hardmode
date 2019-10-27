package club.hardmode.spawn.listeners;

import club.hardmode.spawn.HardmodeSpawn;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import javax.swing.text.html.parser.Entity;

public class BuildListener implements Listener {

    private boolean hasBuildPermission(CommandSender sender) {
        return sender.isOp() || sender.hasPermission("hardmode.spawn.build");
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlockPlaced().getLocation();

        if (HardmodeSpawn.isInsideSpawn(location) && !hasBuildPermission(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        if (HardmodeSpawn.isInsideSpawn(location) && !hasBuildPermission(player)) {
            event.setCancelled(true);
        }
    }

}
