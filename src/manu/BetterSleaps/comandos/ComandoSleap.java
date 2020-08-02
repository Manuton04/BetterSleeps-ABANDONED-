package manu.BetterSleaps.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import manu.BetterSleaps.BetterSleaps;

public class ComandoSleap implements CommandExecutor{

	
	private BetterSleaps plugin;
	
	
	public ComandoSleap(BetterSleaps plugin) {
		this.plugin = plugin;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		
		
		if(!(sender instanceof Player)) {
			
			Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + "A new day is appearing!");
			
			
			return false;
		}else {
			FileConfiguration config = plugin.getConfig();
			String text1 = "Config.sleep-command-message";
			Player player = (Player) sender;
			Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text1)).replaceAll("%player%", player.getName()));
			
			return true;
		}
	}

}
