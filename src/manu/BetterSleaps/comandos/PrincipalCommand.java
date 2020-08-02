package manu.BetterSleaps.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;

import manu.BetterSleaps.BetterSleaps;

public class PrincipalCommand extends ServerEvent implements CommandExecutor{
	
	public String noperms = " You don't have the required permission!";

	
	private BetterSleaps plugin;
	
	
	public PrincipalCommand(BetterSleaps plugin) {
		this.plugin = plugin;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		
		if(!(sender instanceof Player)) {
			
			Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + "You can do that in the console!!");
			
			
			return false;
		}else {
			Player player = (Player) sender;
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("version")) {
					
					player.sendMessage(plugin.name + ChatColor.YELLOW+" The version of the plugin is: " + plugin.version );
					
				}else if(args[0].equalsIgnoreCase("reload")) {
					String text2 = "Config.reload-message";
					FileConfiguration config = plugin.getConfig();
					if(player.hasPermission("bettersleeps.reload")) {
					plugin.reloadConfig();
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text2)).replaceAll("%player%", player.getName()));
					}else {
				    player.sendMessage(plugin.name + ChatColor.RED+noperms);
					}
				}else if(args[0].equalsIgnoreCase("forcesleep")){
					
					
					if(player.hasPermission("bettersleeps.forcesleep")) {
					World world = Bukkit.getWorlds().get(0);
					FileConfiguration config = plugin.getConfig();
					String text3 = "Config.force-sleep-message";
					
					Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text3)).replaceAll("%player%", player.getName()));
					  world.setTime(0);
					  world.setWeatherDuration(0);
			          world.setThunderDuration(0);
			          world.setThundering(false);
			          world.setStorm(false);
					}else {
						
						player.sendMessage(plugin.name + ChatColor.RED+noperms);
					}
					
					
				}else {
					
					player.sendMessage(plugin.name + ChatColor.RED+"That command doesn't exist!");
				}
				
				
				
			}else {
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep or /bs to see this page.");
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /sleep to ask for someone to sleep.");
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep version to see the plugin version.");
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep forcesleep to force the day.");
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep reload to reload the config of the plugin.");
				
			}
			return true;
		}
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

}
