package tech7.myjlmessage.my_joinleave_message;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class CommandHelp implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GOLD + "================="+ChatColor.AQUA+ "MyJoinLeaveMessage"+ ChatColor.GOLD+"===================\n"+ ChatColor.DARK_GREEN +"/myjoin - custom your join message\n/myleave - custom your leave message"+ChatColor.GOLD + "\n====================================================="+ ChatColor.RESET);
        }
        return true;
    }

    
}
