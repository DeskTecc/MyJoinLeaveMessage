package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class MyJoinLeaveMessage extends JavaPlugin {

    public Language data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.data = new Language(this);
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("default-language", "en");
        config.options().copyDefaults(true);
        this.saveConfig();
        Bukkit.getConsoleSender().sendMessage("[" + ChatColor.YELLOW + "MyJoin/LeaveMessage" + ChatColor.WHITE + "]" + ChatColor.GREEN + " Successful enabled");
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("["+ChatColor.YELLOW+"MyJoin/LeaveMessage"+ChatColor.WHITE +"]"+ChatColor.DARK_RED+" Disabled!");
    }
}
