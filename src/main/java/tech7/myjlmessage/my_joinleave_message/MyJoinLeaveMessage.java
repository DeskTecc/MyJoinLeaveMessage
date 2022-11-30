package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MyJoinLeaveMessage extends JavaPlugin {

    public CustomDATA data;
    public SaveDATA save_data;
    public BadwordsDATA badwords_data;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("[" + ChatColor.YELLOW + "MyJoin/LeaveMessage" + ChatColor.WHITE + "]" + ChatColor.GREEN + " Successful enabled");
        this.data = new CustomDATA(this);
        this.save_data = new SaveDATA(this);
        this.badwords_data = new BadwordsDATA(this);
        int pluginId = 14357;
        Metrics metrics = new Metrics(this, pluginId);
        Objects.requireNonNull(this.getCommand("myjl")).setExecutor(new CommandHelp());
        Objects.requireNonNull(this.getCommand("myjl help")).setExecutor(new CommandHelp());
        Objects.requireNonNull(this.getCommand("MyJoinLeaveMessage")).setExecutor(new CommandHelp());
        Objects.requireNonNull(this.getCommand("MyJoinLeaveMessage help")).setExecutor(new CommandHelp());
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Objects.requireNonNull(this.getCommand("myjoin")).setExecutor(new CommandEnter());
        Objects.requireNonNull(this.getCommand("myleave")).setExecutor(new CommandLeave());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("["+ChatColor.YELLOW+"MyJoin/LeaveMessage"+ChatColor.WHITE +"]"+ChatColor.DARK_RED+" Disabled!");
    }

}
