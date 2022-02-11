package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        YamlConfiguration custom = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/locales/custom.yml"));
        Player player = event.getPlayer();
        String enter_msg = (String) custom.get("Enter");
        assert enter_msg != null;
        enter_msg = enter_msg.replaceAll("%player%", player.getName()).replaceAll("&","§");
        event.setJoinMessage(enter_msg);

    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        YamlConfiguration custom = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/locales/custom.yml"));
        Player player = event.getPlayer();
        String enter_msg = (String) custom.get("Leave");
        assert enter_msg != null;
        enter_msg = enter_msg.replaceAll("%player%", player.getName()).replaceAll("&","§");
        event.setQuitMessage(enter_msg);

    }
}
