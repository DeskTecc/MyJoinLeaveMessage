package tech7.myjlmessage.my_joinleave_message;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        YamlConfiguration custom = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/custom.yml"));
        FileConfiguration save_data = new YamlConfiguration();
        try {
            save_data.load("plugins/MyJoinLeaveMessage/data.yml");
        } catch(IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
        Player player = event.getPlayer();
        String enter_msg = (String) custom.get("Enter");
        assert enter_msg != null;
        enter_msg = enter_msg.replaceAll("%player%", player.getName()).replaceAll("&","§");
        String pname = save_data.getString(player.getDisplayName()+".player-name");
        if (Objects.equals(pname, player.getDisplayName())){
            if(save_data.get(player.getDisplayName()+".join") == "default"){
                System.out.println("é default");
                event.setJoinMessage(enter_msg);
            } else{
                System.out.println("não é default");
                String enter_msg_dataj = (String) save_data.get(player.getDisplayName()+".join");
                assert enter_msg_dataj != null;
                enter_msg_dataj = enter_msg_dataj.replaceAll("%player%", player.getName()).replaceAll("&","§");
                event.setJoinMessage(enter_msg_dataj);

            }
        }else {
            save_data.createSection(player.getDisplayName());
            save_data.set(player.getDisplayName() + ".player-name", player.getDisplayName());
            UUID uuid = player.getUniqueId();
            String p_uuid = uuid.toString();
            save_data.set(player.getDisplayName() + ".UUID", p_uuid);
            save_data.set(player.getDisplayName() + ".join", "default");
            save_data.set(player.getDisplayName() + ".leave", "default");
            save_data.save("plugins/MyJoinLeaveMessage/data.yml");
            event.setJoinMessage(enter_msg);
        }

    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        YamlConfiguration custom = YamlConfiguration.loadConfiguration(new File("plugins/MyJoinLeaveMessage/custom.yml"));
        Player player = event.getPlayer();
        String quit_msg = (String) custom.get("Leave");
        assert quit_msg != null;
        quit_msg = quit_msg.replaceAll("%player%", player.getName()).replaceAll("&","§");
        FileConfiguration save_data = new YamlConfiguration();
        try {
            save_data.load("plugins/MyJoinLeaveMessage/data.yml");
        } catch(IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
        String pname = save_data.getString(player.getDisplayName()+".player-name");
        if (Objects.equals(pname, player.getDisplayName())){
            if(save_data.get(player.getDisplayName()+".leave") == "default"){
                event.setQuitMessage(quit_msg);
            } else{
                String enter_msg_datal = (String) save_data.get(player.getDisplayName()+".leave");
                assert enter_msg_datal != null;
                enter_msg_datal = enter_msg_datal.replaceAll("%player%", player.getName()).replaceAll("&","§");
                event.setQuitMessage(enter_msg_datal);
            }
        }
        event.setQuitMessage(quit_msg);
    }
}
