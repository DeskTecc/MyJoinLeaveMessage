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
        event.setJoinMessage(player.getDisplayName() + ChatColor.DARK_RED + " " + custom.get("Enter"));

    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        YamlConfiguration custom = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/locales/custom.yml"));
        Player player = event.getPlayer();
        event.setQuitMessage(player.getDisplayName() + ChatColor.DARK_RED + " " + custom.get("Leave"));

    }
}
