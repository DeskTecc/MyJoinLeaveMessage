package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MyJoinLeaveMessage extends JavaPlugin {

    public Custom data;
    public Save_DATA save_data;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.data = new Custom(this);
        this.save_data = new Save_DATA(this);
        int pluginId = 14357;
        Metrics metrics = new Metrics(this, pluginId);
        Objects.requireNonNull(this.getCommand("myjl")).setExecutor(new Commands());
        Objects.requireNonNull(this.getCommand("myjl help")).setExecutor(new Commands());
        Objects.requireNonNull(this.getCommand("MyJoinLeaveMessage")).setExecutor(new Commands());
        Objects.requireNonNull(this.getCommand("MyJoinLeaveMessage help")).setExecutor(new Commands());
        Bukkit.getConsoleSender().sendMessage("[" + ChatColor.YELLOW + "MyJoin/LeaveMessage" + ChatColor.WHITE + "]" + ChatColor.GREEN + " Successful enabled");
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Objects.requireNonNull(this.getCommand("myjoin")).setExecutor(new Command_Enter());
        Objects.requireNonNull(this.getCommand("myleave")).setExecutor(new Command_Leave());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("["+ChatColor.YELLOW+"MyJoin/LeaveMessage"+ChatColor.WHITE +"]"+ChatColor.DARK_RED+" Disabled!");
    }

}
