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
import java.util.Objects;

public class CommandLeave implements CommandExecutor, Listener  {

    public boolean hav_bad = false;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            YamlConfiguration custom = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/custom.yml"));
            FileConfiguration save_data = new YamlConfiguration();
            YamlConfiguration badwordslist = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/badwords.yml"));
            FileConfiguration badlist = new YamlConfiguration();
            try {
                save_data.load("plugins/MyJoinLeaveMessage/data.yml");
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
            save_data.get(player.getDisplayName() + ".leave");
            boolean oncustom = custom.getBoolean("On-Custom-Player");
            boolean badwords = custom.getBoolean("Badwords");
            String frase = "";
            for (int i = 0; i < args.length; i++) {
                frase = frase + args[i] + " ";
            }
            if (frase.equals(" ") || frase.equals("")) {
                player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD + "ERROR: "+ChatColor.RESET+""+ChatColor.RED+ "NO LEAVE MESSAGE");
                player.sendMessage(ChatColor.YELLOW + "Please use: /myleave <yourleavemessage>" + ChatColor.RESET);
            } else {
                if (!oncustom) {
                    if(!badwords){
                        save_data.set(player.getDisplayName() + ".leave", frase);
                    }else{
                        for(int count = 0; count< Objects.requireNonNull(badwordslist.getList("words")).size(); count++) {
                            if (frase.contains((String) Objects.requireNonNull(Objects.requireNonNull(badwordslist.getList("words")).get(count)))) {
                                player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"ERROR: "+ChatColor.RESET+""+ChatColor.RED+ "BANNED WORD: "+ChatColor.BOLD+ Objects.requireNonNull(badwordslist.getList("words")).get(count));
                                hav_bad = true;
                                break;
                            }else{ hav_bad = false;}
                        } if(!hav_bad) {
                            save_data.set(player.getDisplayName() + ".leave", frase);
                        }
                    }
                }else{
                    if(!badwords) {
                        save_data.set(player.getDisplayName() + ".leave", "%player% " + frase);
                    }else{
                        for(int count = 0; count< Objects.requireNonNull(badwordslist.getList("words")).size(); count++) {
                            if (frase.contains((String) Objects.requireNonNull(Objects.requireNonNull(badwordslist.getList("words")).get(count)))) {
                                player.sendMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"ERROR: "+ChatColor.RESET+""+ChatColor.RED+ "BANNED WORD: "+ChatColor.BOLD+ Objects.requireNonNull(badwordslist.getList("words")).get(count));
                                hav_bad = true;
                                break;
                            }else{ hav_bad = false;}
                        } if(!hav_bad) {
                            save_data.set(player.getDisplayName() + ".leave", frase);
                        }
                    }
                }
                try {
                    save_data.save("plugins/MyJoinLeaveMessage/data.yml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String enter_msg_datal = (String) save_data.get(player.getDisplayName() + ".leave");
                assert enter_msg_datal != null;
                enter_msg_datal = enter_msg_datal.replaceAll("%player%", player.getName()).replaceAll("&", "§");
                String y_l_m = (String) custom.get("Your-Leave-Message");
                player.sendMessage(y_l_m + ": " + enter_msg_datal);
            }
        }
        return true;
    }

}
