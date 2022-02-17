package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class Command_save implements CommandExecutor, Listener  {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            YamlConfiguration custom = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/custom.yml"));
            FileConfiguration save_data = new YamlConfiguration();
            try {
                save_data.load("plugins/MyJoinLeaveMessage/data.yml");
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
            save_data.get(player.getDisplayName() + ".join");
            String frase = "";
            for (int i = 0; i < args.length; i++) {
                frase = frase + args[i] + " ";
            }
            save_data.set(player.getDisplayName() + ".join", frase);
            try {
                save_data.save("plugins/MyJoinLeaveMessage/data.yml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
