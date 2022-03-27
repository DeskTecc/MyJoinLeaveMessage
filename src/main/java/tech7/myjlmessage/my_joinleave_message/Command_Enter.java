package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.ChatColor;
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

public class Command_Enter implements CommandExecutor, Listener  {


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
            boolean oncustom = custom.getBoolean("On-Custom-Player");
            String frase = "";
            for (int i = 0; i < args.length; i++) {
                frase = frase + args[i] + " ";
            }
            if (frase.equals(" ") || frase.equals("")) {
                player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD + "ERROR: "+ChatColor.RESET+""+ChatColor.RED+ "NO JOIN MESSAGE");
                player.sendMessage(ChatColor.YELLOW + "Please use: /myjoin <yourjoinmessage>" + ChatColor.RESET);
            } else {
                if (!oncustom) {
                    save_data.set(player.getDisplayName() + ".join", frase);
                }else{
                    save_data.set(player.getDisplayName() + ".join", "%player% "+frase);
                }
                try {
                    save_data.save("plugins/MyJoinLeaveMessage/data.yml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String enter_msg_dataj = (String) save_data.get(player.getDisplayName() + ".join");
                assert enter_msg_dataj != null;
                enter_msg_dataj = enter_msg_dataj.replaceAll("%player%", player.getName()).replaceAll("&", "§");
                String y_j_m = (String) custom.get("Your-Join-Message");
                player.sendMessage(y_j_m + ": " + enter_msg_dataj);
            }
        }
        return true;
    }

}
