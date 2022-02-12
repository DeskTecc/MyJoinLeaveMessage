package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public final class MyJoinLeaveMessage extends JavaPlugin {

    public Custom data;
    public Save_DATA save_data;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.data = new Custom(this);
        this.save_data = new Save_DATA(this);
        Objects.requireNonNull(this.getCommand("my")).setExecutor(new Commands());
        Bukkit.getConsoleSender().sendMessage("[" + ChatColor.YELLOW + "MyJoin/LeaveMessage" + ChatColor.WHITE + "]" + ChatColor.GREEN + " Successful enabled");
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("["+ChatColor.YELLOW+"MyJoin/LeaveMessage"+ChatColor.WHITE +"]"+ChatColor.DARK_RED+" Disabled!");
    }

}
