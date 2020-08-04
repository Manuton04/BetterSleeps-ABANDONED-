package manu.BetterSleaps.comandos;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import manu.BetterSleaps.BetterSleaps;

public class PrincipalCommand extends ServerEvent implements CommandExecutor, TabExecutor{
	
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
					
					
				}
				else if(args[0].equalsIgnoreCase("setsleepers")) {
					
					FileConfiguration config = plugin.getConfig();
					
					if(player.hasPermission("bettersleeps.setsleepers")) {
						
						String text4 = "Config.change-required-sleepers-message";
						
						
						if(args.length == 2 && !(args.length == 1)) {
							
							if(args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase("5") || args[1].equalsIgnoreCase("6") || args[1].equalsIgnoreCase("7") || args[1].equalsIgnoreCase("8") || args[1].equalsIgnoreCase("9") || args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase("11") || args[1].equalsIgnoreCase("12") || args[1].equalsIgnoreCase("13") || args[1].equalsIgnoreCase("14") || args[1].equalsIgnoreCase("15") || args[1].equalsIgnoreCase("16") || args[1].equalsIgnoreCase("17") || args[1].equalsIgnoreCase("18") || args[1].equalsIgnoreCase("19") || args[1].equalsIgnoreCase("20") || args[1].equalsIgnoreCase("21") || args[1].equalsIgnoreCase("22") || args[1].equalsIgnoreCase("23") || args[1].equalsIgnoreCase("24") || args[1].equalsIgnoreCase("25") || args[1].equalsIgnoreCase("26") || args[1].equalsIgnoreCase("27") || args[1].equalsIgnoreCase("28") || args[1].equalsIgnoreCase("29") || args[1].equalsIgnoreCase("30") || args[1].equalsIgnoreCase("31") || args[1].equalsIgnoreCase("32")) {
								
								config.set("Config.required-sleeping", args[1]);
								plugin.saveConfig();
								player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text4)).replaceAll("%player%", player.getName()).replaceAll("%total%", (args[1])));
								
							}else {
								
								player.sendMessage(plugin.name + ChatColor.RED+" You have to choose a number between 1 and 32.");
								
							}
							
							
							
						}else if(args.length == 1) {
							
							player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs setsleepers <number 1-32>.");
							
						}else {
							
							player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs setsleepers <number 1-32>.");
							
						}
						
						
						
					}
					
					else {
				    player.sendMessage(plugin.name + ChatColor.RED+noperms);
					}
					
				}
                   else if(args[0].equalsIgnoreCase("phantoms")) {
					
					FileConfiguration config = plugin.getConfig();
					
					if(player.hasPermission("bettersleeps.setphantoms")) {
						
						
						String text5 = "Config.phantoms.change-message";
						
						if(args.length == 2 && !(args.length == 1)) {
						
						  if(args[1].equalsIgnoreCase("true")) {
							
							config.set("Config.phantoms.spawn", args[1]);
							plugin.saveConfig();
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
							
						  }else if(args[1].equalsIgnoreCase("false")) {
							
							config.set("Config.phantoms.spawn", args[1]);
							plugin.saveConfig();
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
							
						  }else {
							
							  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs phantoms true/false.");
							
						 }
					  }else if(args.length == 1) {
						  
						  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs phantoms true/false.");
						  
					  }else {
						  
						  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs phantoms true/false.");
						  
					  }
						
					}
					
					else {
				    player.sendMessage(plugin.name + ChatColor.RED+noperms);
					}
					
				}
				else {
					
					player.sendMessage(plugin.name + ChatColor.RED+"That command doesn't exist!");
				}
				
				
				
			}else {
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep or /bs to see this page.");
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /sleep to ask for someone to sleep.");
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep setsleepers to change the number of players required to sleep.");
				player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep phantoms to set if you want phantoms to spawn.");
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
	
	@Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) return null;
        return Arrays.asList("forcesleep", "version", "reload", "setsleepers", "phantoms");
    }
	
}
