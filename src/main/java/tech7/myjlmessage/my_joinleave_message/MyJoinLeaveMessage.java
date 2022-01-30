package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.reload;

public final class MyJoinLeaveMessage extends JavaPlugin {

    public Language data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.data = new Language(this);
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("Mensagem de boas-vindas", true);
        config.options().copyDefaults(true);
        this.saveConfig();
        Bukkit.getConsoleSender().sendMessage("[" + ChatColor.YELLOW + "MyJoin/LeaveMessage" + ChatColor.WHITE + "]" + ChatColor.GREEN + " Successful enabled");
        if (config.getBoolean("Mensagem de boas-vindas")){
            Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        }else {
            Bukkit.getConsoleSender().sendMessage("Disabled messages");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("["+ChatColor.YELLOW+"MyJoin/LeaveMessage"+ChatColor.WHITE +"]"+ChatColor.DARK_RED+" Disabled!");
    }
}
