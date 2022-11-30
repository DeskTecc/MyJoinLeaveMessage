package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class BadwordsDATA {
    private MyJoinLeaveMessage plugin;
    private FileConfiguration cust = null;
    private File custfile = null;

    public BadwordsDATA(MyJoinLeaveMessage plugin){
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadcustom(){
        if(this.custfile == null) {
            this.custfile = new File(this.plugin.getDataFolder(), "badwords.yml");
        }
        this.cust = YamlConfiguration.loadConfiguration(this.custfile);
        InputStream defaultStream = this.plugin.getResource("badwords.yml");
        if(defaultStream != null){
            YamlConfiguration defaultconfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.cust.setDefaults(defaultconfig);
        }
    }
    public FileConfiguration getConfig(){
        if(this.cust == null)
            reloadcustom();
        return this.cust;
    }
    public void saveConfig(){
        if(this.cust == null || this.custfile == null)
            return;
        try{
            this.getConfig().save(this.custfile);
        } catch (IOException e){
            plugin.getLogger().log(Level.SEVERE, "Sorry, are missing or corrupted files:" + this.custfile, e);
        }
    }
    public void saveDefaultConfig(){
        if (this.custfile == null)
            this.custfile = new File(this.plugin.getDataFolder(), "badwords.yml");
        if (!this.custfile.exists()){
            this.plugin.saveResource("badwords.yml", false);
        }
    }
}
