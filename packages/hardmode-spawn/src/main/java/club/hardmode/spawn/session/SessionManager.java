package club.hardmode.spawn.session;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SessionManager {

    private HashMap<UUID, PlayerSession> sessions = new HashMap<>();

    public void newSession(Player player) {
        sessions.put(player.getUniqueId(), new PlayerSession(player));
    }

    public PlayerSession get(Player player) {
        return sessions.get(player.getUniqueId());
    }

}
