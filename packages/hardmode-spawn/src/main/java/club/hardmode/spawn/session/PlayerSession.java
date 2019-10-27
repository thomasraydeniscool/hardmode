package club.hardmode.spawn.session;

import club.hardmode.spawn.HardmodeSpawn;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerSession {

    private Player player;

    private boolean pvpTagged = false;

    private long taggedTime;
    private long SAFE_DELAY = 30000;

    public PlayerSession(Player p) {
        player = p;
    }

    public boolean isPvpTagged() {
        if (pvpTagged && taggedTime > System.currentTimeMillis()) {
            return true;
        } else if (taggedTime < System.currentTimeMillis()) {
            setPvpTagged(false);
        }
        return false;
    }

    public void setPvpTagged(boolean tagged) {
        this.pvpTagged = tagged;
        if (tagged) {
            taggedTime = System.currentTimeMillis() + SAFE_DELAY;
        } else {
            taggedTime = 0;
        }
    }

    public boolean isProtected() {
        if (HardmodeSpawn.isInsideSpawn(player.getLocation()) && !isPvpTagged()) {
            return true;
        }
        return false;
    }
}
