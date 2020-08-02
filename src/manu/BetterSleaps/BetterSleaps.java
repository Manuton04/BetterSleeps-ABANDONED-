package manu.BetterSleaps;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Sleap.Sleap;
import manu.BetterSleaps.comandos.ComandoSleap;
import manu.BetterSleaps.comandos.PrincipalCommand;

public class BetterSleaps extends JavaPlugin {
	
	PluginDescriptionFile pdffile = getDescription();
	
	public String rutaConfig;
	public String version = ChatColor.YELLOW+pdffile.getVersion();
	public String name = ChatColor.GOLD+"["+ChatColor.YELLOW+pdffile.getName()+ChatColor.GOLD+"]";          //[BetterSleaps]

	
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "************************************************");
		Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " Has been enabled "+ ChatColor.YELLOW +"(version: " + version + ")");
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "************************************************");
		registerCommands();
		registerConfig();
		registerEvents();
		
	    
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
    }
    
    public void registerConfig() {
    	File config = new File(this.getDataFolder(),"config.yml");
    	rutaConfig = config.getPath();
    	
    	if(!config.exists()) {
    		this.getConfig().options().copyDefaults(true);
    		saveConfig();
    	}
    }
}

