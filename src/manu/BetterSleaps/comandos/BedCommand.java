package manu.BetterSleaps.comandos;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

public class BedCommand extends ServerEvent implements CommandExecutor, TabExecutor {

	
	   
	
	
	
		

	
		private BetterSleaps plugin;
	
	
		public BedCommand(BetterSleaps plugin) {
		this.plugin = plugin;
		
	}
		
		
		
		
		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
            String path = "Config.language";
            FileConfiguration config = plugin.getConfig();

            if(config.getString(path).equals("english")){
		
		    FileConfiguration messages = plugin.getMessagesEn();
		    
		    
			
			
			if(!(sender instanceof Player)) {
				
				Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + "You can do that in the console!!");
				
				
				return false;
			}else {
				
				Player player = (Player) sender;
				String name = player.getName();
				

				
				if(plugin.BedLocations.contains(name)) {
				
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("Locate")) {
						if(player.hasPermission("bettersleeps.bed.locate")) {
							
							double x = Double.valueOf(plugin.BedLocations.getString((name) +  ".x"));
					        double y = Double.valueOf(plugin.BedLocations.getString((name) +  ".y"));
					        double z = Double.valueOf(plugin.BedLocations.getString((name) +  ".z"));
					
				            int xInt = (int) Math.round(x);
				            int yInt = (int) Math.round(y);
				            int zInt = (int) Math.round(z);
				          
				          String bedCoords = "Messages.bed-coords";
				          
				          player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(bedCoords)));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "X: " + ChatColor.GOLD + Integer.toString(xInt));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "Y: " + ChatColor.GOLD + Integer.toString(yInt));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "Z: " + ChatColor.GOLD + Integer.toString(zInt));
				          
				         
						}else {
							
							String noperms = "Messages.no-perms";
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
						}
				}
					
				          
				}else if(args.length == 0) {
					
					if(player.hasPermission("bettersleeps.bed")) {
						
						String stringworld = plugin.getBedLocations().getString(name + "." + "world");
					    World w = plugin.getServer().getWorld(stringworld);
						
					    
						double x = Double.valueOf(plugin.BedLocations.getString((name) +  ".x"));
				        double y = Double.valueOf(plugin.BedLocations.getString((name) +  ".y"));
				        double z = Double.valueOf(plugin.BedLocations.getString((name) +  ".z"));
				
			            int xInt = (int) Math.round(x);
			            int yInt = (int) Math.round(y);
			            int zInt = (int) Math.round(z);
					  	
					    
				          String teleport = "Messages.teleport-message";
				          
						Location l = new Location(w, xInt, yInt, zInt);
						player.teleport(l);
						
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(teleport)));
						
					}else {
						
						String noperms = "Messages.no-perms";
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
						
					}
					
				}else if(!(args.length == 0)) {
					
					String nocommand = "Messages.no-command";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nocommand)));
					
				}
			  }else {
				  
				  String noBed = "Messages.no-bed";

				  player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noBed)));
				  
			  }

		
			}
		 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
	       }else if(config.getString(path).equals("español")) {
		
		   FileConfiguration messages = plugin.getMessagesEs();
		   
		   
			
			
			if(!(sender instanceof Player)) {
				
				Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + "You can do that in the console!!");
				
				
				return false;
			}else {
				
				Player player = (Player) sender;
				String name = player.getName();
				

				
				if(plugin.BedLocations.contains(name)) {
				
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("Locate")) {
						if(player.hasPermission("bettersleeps.bed.locate")) {
							
							double x = Double.valueOf(plugin.BedLocations.getString((name) +  ".x"));
					        double y = Double.valueOf(plugin.BedLocations.getString((name) +  ".y"));
					        double z = Double.valueOf(plugin.BedLocations.getString((name) +  ".z"));
					
				            int xInt = (int) Math.round(x);
				            int yInt = (int) Math.round(y);
				            int zInt = (int) Math.round(z);
				          
				          String bedCoords = "Messages.bed-coords";
				          
				          player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(bedCoords)));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "X: " + ChatColor.GOLD + Integer.toString(xInt));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "Y: " + ChatColor.GOLD + Integer.toString(yInt));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "Z: " + ChatColor.GOLD + Integer.toString(zInt));
				          
				         
						}else {
							
							String noperms = "Messages.no-perms";
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
						}
				}
					
				          
				}else if(args.length == 0) {
					
					if(player.hasPermission("bettersleeps.bed")) {
						
						String stringworld = plugin.getBedLocations().getString(name + "." + "world");
					    World w = plugin.getServer().getWorld(stringworld);
						
					    
						double x = Double.valueOf(plugin.BedLocations.getString((name) +  ".x"));
				        double y = Double.valueOf(plugin.BedLocations.getString((name) +  ".y"));
				        double z = Double.valueOf(plugin.BedLocations.getString((name) +  ".z"));
				
			            int xInt = (int) Math.round(x);
			            int yInt = (int) Math.round(y);
			            int zInt = (int) Math.round(z);
					    
				          String teleport = "Messages.teleport-message";
				          
						Location l = new Location(w, xInt, yInt, zInt);
						player.teleport(l);
						
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(teleport)));
						
					}else {
						
						String noperms = "Messages.no-perms";
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
						
					}
					
				}else if(!(args.length == 0)) {
					
					String nocommand = "Messages.no-command";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nocommand)));
					
				}
			  }else {
				  
				  String noBed = "Messages.no-bed";

				  player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noBed)));
				  
			  }

		
			}
		
		
	  }
	       
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	       
	       else if(config.getString(path).equals("chinese")) {
			
			FileConfiguration messages = plugin.getMessagesCn();
			
			
                if(!(sender instanceof Player)) {
				
				Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + "You can do that in the console!!");
				
				
				return false;
			}else {
				
				Player player = (Player) sender;
				String name = player.getName();
				

				
				if(plugin.BedLocations.contains(name)) {
				
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("Locate")) {
						if(player.hasPermission("bettersleeps.bed.locate")) {
							
							double x = Double.valueOf(plugin.BedLocations.getString((name) +  ".x"));
					        double y = Double.valueOf(plugin.BedLocations.getString((name) +  ".y"));
					        double z = Double.valueOf(plugin.BedLocations.getString((name) +  ".z"));
					
				            int xInt = (int) Math.round(x);
				            int yInt = (int) Math.round(y);
				            int zInt = (int) Math.round(z);
				          
				          String bedCoords = "Messages.bed-coords";
				          
				          player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(bedCoords)));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "X: " + ChatColor.GOLD + Integer.toString(xInt));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "Y: " + ChatColor.GOLD + Integer.toString(yInt));
				          player.sendMessage(plugin.name+" "+ChatColor.RED + "Z: " + ChatColor.GOLD + Integer.toString(zInt));
				          
				         
						}else {
							
							String noperms = "Messages.no-perms";
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
						}
				}
					
				          
				}else if(args.length == 0) {
					
					if(player.hasPermission("bettersleeps.bed")) {
						
						String stringworld = plugin.getBedLocations().getString(name + "." + "world");
					    World w = plugin.getServer().getWorld(stringworld);
						
					    
						double x = Double.valueOf(plugin.BedLocations.getString((name) +  ".x"));
				        double y = Double.valueOf(plugin.BedLocations.getString((name) +  ".y"));
				        double z = Double.valueOf(plugin.BedLocations.getString((name) +  ".z"));
				
			            int xInt = (int) Math.round(x);
			            int yInt = (int) Math.round(y);
			            int zInt = (int) Math.round(z);
					    
				          String teleport = "Messages.teleport-message";
				          
						Location l = new Location(w, xInt, yInt, zInt);
						player.teleport(l);
						
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(teleport)));
						
					}else {
						
						String noperms = "Messages.no-perms";
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
						
					}
					
				}else if(!(args.length == 0)) {
					
					String nocommand = "Messages.no-command";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nocommand)));
					
				}
			  }else {
				  
				  String noBed = "Messages.no-bed";

				  player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noBed)));
				  
			  }

		
			}


		}

			
			
			
			
			
			return false;
			
		}
	
	
	
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) return null;
        return Arrays.asList("locate");
    }
	
}
