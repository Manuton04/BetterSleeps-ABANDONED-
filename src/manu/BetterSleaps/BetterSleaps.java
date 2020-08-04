package manu.BetterSleaps;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import Sleap.Sleap;
import manu.BetterSleaps.Events.OnEnter;
import manu.BetterSleaps.Events.PhamtomsSpawn;
import manu.BetterSleaps.comandos.ComandoSleap;
import manu.BetterSleaps.comandos.PrincipalCommand;

public class BetterSleaps extends JavaPlugin {
	
	PluginDescriptionFile pdffile = getDescription();
	
	public String rutaConfig;
	public String version = ChatColor.YELLOW+pdffile.getVersion();
	public String latestversion;
	public String name = ChatColor.GOLD+"["+ChatColor.YELLOW+pdffile.getName()+ChatColor.GOLD+"]";          //[BetterSleaps]

	
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "************************************************");
		Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " Has been enabled "+ ChatColor.YELLOW +"(version: " + version + ")");
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "************************************************");
		registerCommands();
		registerConfig();
		registerEvents();
		updateChecker();
		
		
		int pluginId = 8394; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);
        
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
	    
 }
	

	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "*************************************************");
		Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " Has been disabled "+ ChatColor.YELLOW +"(version: " + version + ")");
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "*************************************************");
		
	}

    public void registerCommands() {
    	
		
		this.getCommand("sleep").setExecutor(new ComandoSleap(this));
		this.getCommand("bettersleep").setExecutor(new PrincipalCommand(this));
		this.getCommand("bs").setExecutor(new PrincipalCommand(this));
    }
    
    public void registerEvents() {
    	PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents(new Sleap(this), this);
    	pm.registerEvents(new OnEnter(this), this);
    	pm.registerEvents(new PhamtomsSpawn(this), this);
    }
    
    public void registerConfig() {
    	File config = new File(this.getDataFolder(),"config.yml");
    	rutaConfig = config.getPath();
    	
    	if(!config.exists()) {
    		this.getConfig().options().copyDefaults(true);
    		saveConfig();
    		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "*****************************");
    		Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " Config created.");
    		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "*****************************");
    	}
    }
    
    public String getVersion() {
    	
    	return this.version;
    }
    
    public String getLatestVersion() {
    	
    	return this.latestversion;
    }
    
    
    public void updateChecker(){		  
		  try {
			  HttpURLConnection con = (HttpURLConnection) new URL(
	                  "https://api.spigotmc.org/legacy/update.php?resource=82243").openConnection();
	          int timed_out = 1250;
	          con.setConnectTimeout(timed_out);
	          con.setReadTimeout(timed_out);
	          latestversion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
	          if (latestversion.length() <= 7) {
	        	  if(!version.equals(latestversion)){
	        		  Bukkit.getConsoleSender().sendMessage(name+ " "+ChatColor.RED +"There is a new version available. "+ChatColor.YELLOW+
	        				  "("+ChatColor.GRAY+latestversion+ChatColor.YELLOW+")");
	        		  Bukkit.getConsoleSender().sendMessage(name+ " "+ChatColor.RED+"You can download it at: "+ChatColor.WHITE+"https://www.spigotmc.org/resources/bettersleeps.82243/");  
	        	  }      	  
	          }
	      } catch (Exception ex) {
	    	  Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED +"Error while checking update.");
	      }
	  }
    
}

