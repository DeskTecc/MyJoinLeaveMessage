package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SaveDATA {
    private MyJoinLeaveMessage plugin;
    private FileConfiguration sdata = null;
    private File datafile = null;

    public SaveDATA(MyJoinLeaveMessage plugin){
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void saveDefaultConfig(){
        if (this.datafile == null)
            this.datafile = new File(this.plugin.getDataFolder(), "data.yml");
        if (!this.datafile.exists()){
            this.plugin.saveResource("data.yml", false);
        }
    }
    public void reloaddata(){
        if(this.datafile == null) {
            this.datafile = new File(this.plugin.getDataFolder(), "custom.yml");
        }
        this.sdata = YamlConfiguration.loadConfiguration(this.datafile);
        InputStream defaultStream = this.plugin.getResource("data.yml");
        if(defaultStream != null){
            YamlConfiguration defaultconfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.sdata.setDefaults(defaultconfig);
        }
    }
    public FileConfiguration getConfig(){
        if(this.sdata == null)
            reloaddata();
        return this.sdata;
    }
}
