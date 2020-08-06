package manu.BetterSleaps.Events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import manu.BetterSleaps.BetterSleaps;

public class OnEnter implements Listener {

	private BetterSleaps plugin;
	
	public OnEnter(BetterSleaps plugin) {	
		this.plugin = plugin;
	}

	
	
	
	@EventHandler
	public void onEnter(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
        String path = "Config.language";
        FileConfiguration config = plugin.getConfig();

        if(config.getString(path).equals("english")){
	
	   if(player.isOp() && !(plugin.getVersion().equals(plugin.getLatestVersion()))) {
			
			 player.sendMessage(plugin.name+ " "+ChatColor.RED +"There is a new version available. "+ChatColor.YELLOW+
	  				  "("+ChatColor.GRAY+plugin.latestversion+ChatColor.YELLOW+")");
	  		 player.sendMessage(plugin.name+ " "+ChatColor.RED+"You can download it at: "+ChatColor.WHITE+"https://www.spigotmc.org/resources/bettersleeps.82243/");
				
			}
	   
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   
        }else if(config.getString(path).equals("español")) {
	
        	if(player.isOp() && !(plugin.getVersion().equals(plugin.getLatestVersion()))) {
    			
       		 player.sendMessage(plugin.name+ " "+ChatColor.RED +"Hay una nueva version disponible. "+ChatColor.YELLOW+
         				  "("+ChatColor.GRAY+plugin.latestversion+ChatColor.YELLOW+")");
         		 player.sendMessage(plugin.name+ " "+ChatColor.RED+"Podes descargarla en: "+ChatColor.WHITE+"https://www.spigotmc.org/resources/bettersleeps.82243/");
       			
       		}
	    
	    
	    
	
}else {
	
	
	
	if(player.isOp() && !(plugin.getVersion().equals(plugin.getLatestVersion()))) {
		
		 player.sendMessage(plugin.name+ " "+ChatColor.RED +"There is a new version available. "+ChatColor.YELLOW+
 				  "("+ChatColor.GRAY+plugin.latestversion+ChatColor.YELLOW+")");
 		 player.sendMessage(plugin.name+ " "+ChatColor.RED+"You can download it at: "+ChatColor.WHITE+"https://www.spigotmc.org/resources/bettersleeps.82243/");
			
		}
	
	
}
		
		
		
	
		
		
		
	}
	
	
}
