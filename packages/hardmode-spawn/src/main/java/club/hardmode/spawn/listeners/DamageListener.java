package club.hardmode.spawn.listeners;

import club.hardmode.spawn.HardmodeSpawn;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class DamageListener implements Listener {

    /**
     * If the player is still protected with spawn protection.
     *
     * Players can lose spawn protection by leaving the spawn
     * area and by being attacked 'tagged' by another player.
     *
     * @param player The player
     * @return True if the player still has spawn protection
     */
    private boolean isProtected(Player player) {
        return HardmodeSpawn.getInstance().getSession().get(player).isProtected();
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        Entity victim = event.getEntity();

        if (victim instanceof Player) {
            Player player = (Player) victim;

            if (isProtected(player)) {
                event.setCancelled(true);
                player.setFireTicks(0);
            } else {
                if (event instanceof EntityDamageByEntityEvent) {
                    EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                    Entity cause = e.getDamager();

                    if (cause instanceof Player) {
                        Player attacker = (Player) cause;

                        HardmodeSpawn.getInstance().getSession().get(attacker).setPvpTagged(true);
                        HardmodeSpawn.getInstance().getSession().get(player).setPvpTagged(true);
                    }
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityCombust(EntityCombustEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;

            if (isProtected(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;

            if (event.getFoodLevel() < player.getFoodLevel() && isProtected(player)) {
                event.setCancelled(true);
            }
        }
    }

}
