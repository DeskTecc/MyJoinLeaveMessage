package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Language {
    private MyJoinLeaveMessage plugin;
    private FileConfiguration lang = null;
    private File langfile = null;

    public Language(MyJoinLeaveMessage plugin){
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadlang(){
        if(this.langfile == null) {
            this.langfile = new File(this.plugin.getDataFolder(), "lang.yml");
        }
        this.lang = YamlConfiguration.loadConfiguration(this.langfile);
        InputStream defaultStream = this.plugin.getResource("lang.yml");
        if(defaultStream != null){
            YamlConfiguration defaultconfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.lang.setDefaults(defaultconfig);
        }
    }
    public FileConfiguration getConfig(){
        if(this.lang == null)
            reloadlang();
        return this.lang;
    }
    public void saveConfig(){
        if(this.lang == null || this.langfile == null)
            return;
        try{
            this.getConfig().save(this.langfile);
        } catch (IOException e){
            plugin.getLogger().log(Level.SEVERE, "Sorry, are missing or corrupted files:" + this.langfile, e);
        }
    }
    public void saveDefaultConfig(){
        if (this.langfile == null)
            this.langfile = new File(this.plugin.getDataFolder(), "lang.yml");
        if (!this.langfile.exists()){
            this.plugin.saveResource("lang.yml", false);
        }
    }
}
