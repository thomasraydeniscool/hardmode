package club.hardmode.spawn;

import club.hardmode.spawn.listeners.BuildListener;
import club.hardmode.spawn.listeners.DamageListener;
import club.hardmode.spawn.listeners.EntityListener;
import club.hardmode.spawn.listeners.PlayerListener;
import club.hardmode.spawn.session.SessionManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class HardmodeSpawn extends JavaPlugin {

    private static HardmodeSpawn instance;
    private static SessionManager session;

    public HardmodeSpawn() {
        final HardmodeSpawn plugin = instance = this;
    }

    public static HardmodeSpawn getInstance() {
        return instance;
    }

    public static boolean isInsideSpawn(Location location) {
        if (location.distanceSquared(location.getWorld().getSpawnLocation()) < Math.pow(HardmodeSpawn.getInstance().getServer().getSpawnRadius(), 2)) {
            return true;
        }
        return false;
    }

    @Override()
    public void onEnable() {
        /**
         * Initialize Player Session Manager
         */
        instance.initSession(new SessionManager());

        /**
         * Register Event Listeners
         */
        instance.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        instance.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        instance.getServer().getPluginManager().registerEvents(new BuildListener(), this);
        instance.getServer().getPluginManager().registerEvents(new EntityListener(), this);
    }

    public void initSession(SessionManager newSession) {
        session = newSession;
    }

    public SessionManager getSession() {
        return session;
    }

}
