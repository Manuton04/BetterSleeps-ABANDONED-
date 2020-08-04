package manu.BetterSleaps.Events;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import manu.BetterSleaps.BetterSleaps;





public class PhamtomsSpawn implements Listener{
	
	private BetterSleaps plugin;

	public PhamtomsSpawn(BetterSleaps plugin) {
		this.plugin = plugin;
				
	}

	

	@EventHandler(priority = EventPriority.LOWEST)
	public void phamtomSpawn(EntitySpawnEvent spawnEvent) {
		
		FileConfiguration Config = plugin.getConfig();
		String phantoms = "Config.phantoms.spawn";
		
		if(Config.getString(phantoms).equals("false")){
			
			if (spawnEvent.getEntityType() == EntityType.PHANTOM)
	            spawnEvent.setCancelled(true);
			
		}
 }	
}
