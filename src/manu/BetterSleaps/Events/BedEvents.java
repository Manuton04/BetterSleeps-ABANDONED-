package manu.BetterSleaps.Events;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import manu.BetterSleaps.BetterSleaps;




    public class BedEvents implements Listener {
	
	private BetterSleaps plugin;
	
	public BedEvents(BetterSleaps plugin) {
		this.plugin = plugin;
		
		
		
	}
	
	
	
	
	int sleeping = 0;
	
	
	
	@EventHandler
	public void atBed(PlayerBedEnterEvent atbed) {
		
		
		plugin.reloadBedLocations();
		
		String path = "Config.language";
		FileConfiguration config = plugin.getConfig();
		
        if(config.getString(path).equals("english")){
			
			FileConfiguration messages = plugin.getMessagesEn();
			
			String text1 = "Messages.sleeping-message";
			String text2 = "Messages.sleep-success-message";
			String total1 = "Config.required-sleeping";
			int total = Integer.parseInt(config.getString(total1));
			Player player = atbed.getPlayer();
			World world = Bukkit.getWorlds().get(0);
			
			if(player.getLocation().getWorld().getEnvironment() == Environment.NORMAL){
				
				
				if (atbed.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK){
					
					
					

						sleeping++;
						String sleeping1 = String.valueOf(sleeping);
						
					  if(sleeping >= total) {
						
						 
						  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
						  world.setTime(0);
						  world.setWeatherDuration(0);
				          world.setThunderDuration(0);
				          world.setThundering(false);
				          world.setStorm(false);

						  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text2)).replaceAll("%player%", player.getName()).replaceAll("%total%", total1).replaceAll("%sleeping%", sleeping1));
			              
						
					  }else {
						
						
						  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
					  }
					
					
					
				}
				
				String w = player.getLocation().getWorld().getName();
			    
				String name = player.getName();
				
				double x = player.getLocation().getX();
				double y = player.getLocation().getY();
				double z = player.getLocation().getZ();
				
				String bedSet = "Messages.bedset";
				
				plugin.BedLocations.set(name + "." + "world", w);
		        plugin.BedLocations.set(name + "." + "x", x);
		        plugin.BedLocations.set(name + "." + "y", y);
		        plugin.BedLocations.set(name + "." + "z", z);
		        
		        try {
		            plugin.BedLocations.save(plugin.BedsFile);
		          } catch (IOException ex) {
		            ex.printStackTrace();
		          }
		        player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(bedSet)));
				
				
			  }else {
				  
				  String path1 = "Config.bed-explodes-on-nether-or-end";
				  
				  if(config.getString(path1).equals("false")) {
		    			
			    		atbed.setCancelled(true);
			    		
			    		String nl = "Messages.nosleep-nether-end-message";
						
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nl)).replaceAll("%player%", player.getName()));
		    			
		    		}else if(config.getString(path1).equals("true")) {
				
				 
				  atbed.setCancelled(false);
				  
		    		}
				
			}
			
			
			
			  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			  
		  }else if(config.getString(path).equals("español")) {
			
			FileConfiguration messages = plugin.getMessagesEs();
			
			String text1 = "Messages.sleeping-message";
			String text2 = "Messages.sleep-success-message";
			String total1 = "Config.required-sleeping";
			int total = Integer.parseInt(config.getString(total1));
			Player player = atbed.getPlayer();
			World world = Bukkit.getWorlds().get(0);
			
			if(player.getLocation().getWorld().getEnvironment() == Environment.NORMAL){
				
				if (atbed.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK){
					
					
					

						sleeping++;
						String sleeping1 = String.valueOf(sleeping);
						
					  if(sleeping >= total) {
						
						 
						  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
						  world.setTime(0);
						  world.setWeatherDuration(0);
				          world.setThunderDuration(0);
				          world.setThundering(false);
				          world.setStorm(false);

						  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text2)).replaceAll("%player%", player.getName()).replaceAll("%total%", total1).replaceAll("%sleeping%", sleeping1));
			              
						
					  }else {
						
						
						  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
					
					}
					
				
					
				}
				
				
				
				
				String w = player.getLocation().getWorld().getName();
			    
				String name = player.getName();
				
				double x = player.getLocation().getX();
				double y = player.getLocation().getY();
				double z = player.getLocation().getZ();
				
				String bedSet = "Messages.bedset";
				
				plugin.BedLocations.set(name + "." + "world", w);
		        plugin.BedLocations.set(name + "." + "x", x);
		        plugin.BedLocations.set(name + "." + "y", y);
		        plugin.BedLocations.set(name + "." + "z", z);
		        
		        try {
		            plugin.BedLocations.save(plugin.BedsFile);
		          } catch (IOException ex) {
		            ex.printStackTrace();
		          }
		        player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(bedSet)));
				
				
			}else {
				  
				  String path1 = "Config.bed-explodes-on-nether-or-end";
				  
				  if(config.getString(path1).equals("false")) {
		    			
			    		atbed.setCancelled(true);
			    		
			    		String nl = "Messages.nosleep-nether-end-message";
						
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nl)).replaceAll("%player%", player.getName()));
		    			
		    		}else if(config.getString(path1).equals("true")) {
				
				 
				  atbed.setCancelled(false);
				  
		    		}
				
			}
			

		
		
		
	        
		

		
	}else if(config.getString(path).equals("chinese")) {
		
		FileConfiguration messages = plugin.getMessagesCn();
		
		
		String text1 = "Messages.sleeping-message";
		String text2 = "Messages.sleep-success-message";
		String total1 = "Config.required-sleeping";
		int total = Integer.parseInt(config.getString(total1));
		Player player = atbed.getPlayer();
		World world = Bukkit.getWorlds().get(0);
		
		if(player.getLocation().getWorld().getEnvironment() == Environment.NORMAL){
			
			if (atbed.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK){
				
				
				

					sleeping++;
					String sleeping1 = String.valueOf(sleeping);
					
				  if(sleeping >= total) {
					
					 
					  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
					  world.setTime(0);
					  world.setWeatherDuration(0);
			          world.setThunderDuration(0);
			          world.setThundering(false);
			          world.setStorm(false);

					  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text2)).replaceAll("%player%", player.getName()).replaceAll("%total%", total1).replaceAll("%sleeping%", sleeping1));
		              
					
				  }else {
					
					
					  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
				
				}
				
			
				
			}
			
			
			
			
			String w = player.getLocation().getWorld().getName();
		    
			String name = player.getName();
			
			double x = player.getLocation().getX();
			double y = player.getLocation().getY();
			double z = player.getLocation().getZ();
			
			String bedSet = "Messages.bedset";
			
			plugin.BedLocations.set(name + "." + "world", w);
	        plugin.BedLocations.set(name + "." + "x", x);
	        plugin.BedLocations.set(name + "." + "y", y);
	        plugin.BedLocations.set(name + "." + "z", z);
	        
	        try {
	            plugin.BedLocations.save(plugin.BedsFile);
	          } catch (IOException ex) {
	            ex.printStackTrace();
	          }
	        player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(bedSet)));
			
			
		}else {
			  
			  String path1 = "Config.bed-explodes-on-nether-or-end";
			  
			  if(config.getString(path1).equals("false")) {
	    			
		    		atbed.setCancelled(true);
		    		
		    		String nl = "Messages.nosleep-nether-end-message";
					
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nl)).replaceAll("%player%", player.getName()));
	    			
	    		}else if(config.getString(path1).equals("true")) {
			
			 
			  atbed.setCancelled(false);
			  
	    		}
			
		}


	}
        }
	
	
	
	
	
	@EventHandler
	public void ofBed(PlayerBedLeaveEvent ofbed) {
		
		World world = Bukkit.getWorlds().get(0);
		
		if(world.getTime() >= 13000) {
	       sleeping--;	
	
		}
	}
	

	
	
}
