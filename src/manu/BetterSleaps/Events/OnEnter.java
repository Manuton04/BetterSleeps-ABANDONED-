package manu.BetterSleaps.Events;

import org.bukkit.ChatColor;
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
		
	
		if(player.isOp() && !(plugin.getVersion().equals(plugin.getLatestVersion()))) {
			
			
		 player.sendMessage(plugin.name+ " "+ChatColor.RED +"There is a new version available. "+ChatColor.YELLOW+
  				  "("+ChatColor.GRAY+plugin.latestversion+ChatColor.YELLOW+")");
  		 player.sendMessage(plugin.name+ " "+ChatColor.RED+"You can download it at: "+ChatColor.WHITE+"https://www.spigotmc.org/resources/bettersleeps.82243/");
			
		}
		
		
	}
	
	
}
