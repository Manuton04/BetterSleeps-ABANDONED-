package Sleap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import manu.BetterSleaps.BetterSleaps;




public class Sleap implements Listener {
	
	private BetterSleaps plugin;
	
	public Sleap(BetterSleaps plugin) {
		this.plugin = plugin;
		
		
		
	}
	
	int sleeping = 0;
	
	
	
	@EventHandler
	public void atBed(PlayerBedEnterEvent atbed) {
		
		
		FileConfiguration config = plugin.getConfig();
		String text1 = "Config.sleeping-message";
		String text2 = "Config.sleep-success-message";
		String total1 = "Config.required-sleeping";
		int total = Integer.parseInt(config.getString(total1));
		Player player = atbed.getPlayer();
		World world = Bukkit.getWorlds().get(0);
		
		if(world.getTime() >= 13000) {

			sleeping++;
			String sleeping1 = String.valueOf(sleeping);
			
		  if(sleeping >= total) {
			
			 
			  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
			  world.setTime(0);
			  world.setWeatherDuration(0);
	          world.setThunderDuration(0);
	          world.setThundering(false);
	          world.setStorm(false);

			  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text2)).replaceAll("%player%", player.getName()).replaceAll("%total%", total1).replaceAll("%sleeping%", sleeping1));
              
			
		  }else {
			
			
			  Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', config.getString(text1)).replaceAll("%player%", player.getName()).replaceAll("%total%", config.getString(total1)).replaceAll("%sleeping%", sleeping1));
		
		}
		
	}
	
}
	
	
	
	@EventHandler
	public void ofBed(PlayerBedLeaveEvent ofbed) {
		
	sleeping--;	
	
	}
}
