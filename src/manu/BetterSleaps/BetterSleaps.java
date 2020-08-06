package manu.BetterSleaps;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import manu.BetterSleaps.Events.BedEvents;
import manu.BetterSleaps.Events.OnEnter;
import manu.BetterSleaps.Events.PhamtomsSpawn;
import manu.BetterSleaps.comandos.BedCommand;
import manu.BetterSleaps.comandos.ComandoSleap;
import manu.BetterSleaps.comandos.PrincipalCommand;

public class BetterSleaps extends JavaPlugin {
		
	PluginDescriptionFile pdffile = getDescription();
	
	public FileConfiguration BedLocations = null;
	public File BedsFile = null;
	
	public FileConfiguration messagesEn = null;
	public File messagesEnFile = null;
	
	public FileConfiguration messagesEs = null;
	public File messagesEsFile = null;
	
	public FileConfiguration messagesCn = null;
	public File messagesCnFile = null;
	
	public String rutaConfig;
	public String version = ChatColor.YELLOW+pdffile.getVersion();
	public String latestversion;
	public String name = ChatColor.GOLD+"["+ChatColor.YELLOW+pdffile.getName()+ChatColor.GOLD+"]";          //[BetterSleaps]
    public String updater = "Config.auto-update";
	
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "************************************************");
		Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " Has been enabled "+ ChatColor.YELLOW +"(version: " + version + ")");
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "************************************************");
		registerCommands();
		registerConfig();
		registerEvents();
		updateChecker();
		registerBedLocations();
		registerMessagesEs();
		registerMessagesEn();
		registerMessagesCn();
		
		
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
		this.getCommand("bed").setExecutor(new BedCommand(this));
    }
    
    public void registerEvents() {
    	PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents(new OnEnter(this), this);
    	pm.registerEvents(new PhamtomsSpawn(this), this);
    	pm.registerEvents(new BedEvents(this), this);
    }
    
    public void registerConfig() {
    	File config = new File(this.getDataFolder(),"config.yml");
    	rutaConfig = config.getPath();
    	
    	if(!config.exists()) {
    		
    		this.getConfig().options().copyDefaults(true);
    		this.getConfig().options().header("######################################################################################################################\n#Languages avalaibles: english, español, chinese #\n######################################################################################################################");
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
    
    public FileConfiguration getBedLocations() {
    	
    	if(BedLocations == null) {
    		reloadBedLocations();
    		
    	}
    	return BedLocations;
    }
    
    public void reloadBedLocations() {
    	
    	if(BedLocations == null){
			BedsFile = new File(getDataFolder(),"BedLocations.yml");
		}
		BedLocations = YamlConfiguration.loadConfiguration(BedsFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(this.getResource("BedLocations.yml"),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				BedLocations.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
    			
    }
    
    
    public void saveBedLocations(){
		try{
			BedLocations.save(BedsFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
 
	public void registerBedLocations(){
		BedsFile = new File(this.getDataFolder(),"BedLocations.yml");
		if(!BedsFile.exists()){
			this.getBedLocations().options().copyDefaults(true);
			saveBedLocations();
		}
	}
	
	
	
	public FileConfiguration getMessagesEn(){
		if(messagesEn == null){
			reloadMessagesEn();
		}
		return messagesEn;
	}
 
	public void reloadMessagesEn(){
		if(messagesEn == null){
			messagesEnFile = new File(getDataFolder(),"messagesEn.yml");
		}
		messagesEn = YamlConfiguration.loadConfiguration(messagesEnFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(this.getResource("messagesEn.yml"),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				messagesEn.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
 
	public void saveMessagesEn(){
		try{
			messagesEn.save(messagesEnFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
 
	public void registerMessagesEn(){
		messagesEnFile = new File(this.getDataFolder(),"messagesEn.yml");
		if(!messagesEnFile.exists()){
			this.getMessagesEn().options().copyDefaults(true);
			saveMessagesEn();
		}
	}
	
	
	public FileConfiguration getMessagesEs(){
		if(messagesEs == null){
			reloadMessagesEs();
		}
		return messagesEs;
	}
 
	public void reloadMessagesEs(){
		if(messagesEs == null){
			messagesEsFile = new File(getDataFolder(),"messagesEs.yml");
		}
		messagesEs = YamlConfiguration.loadConfiguration(messagesEsFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(this.getResource("messagesEs.yml"),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				messagesEs.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
 
	public void saveMessagesEs(){
		try{
			messagesEs.save(messagesEsFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
 
	public void registerMessagesEs(){
		messagesEsFile = new File(this.getDataFolder(),"messagesEs.yml");
		if(!messagesEsFile.exists()){
			this.getMessagesEs().options().copyDefaults(true);
			saveMessagesEs();
		}
	}
	
	
	public FileConfiguration getMessagesCn(){
		if(messagesCn == null){
			reloadMessagesCn();
		}
		return messagesCn;
	}
 
	public void reloadMessagesCn(){
		if(messagesCn == null){
			messagesCnFile = new File(getDataFolder(),"messagesCn.yml");
		}
		messagesCn = YamlConfiguration.loadConfiguration(messagesCnFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(this.getResource("messagesCn.yml"),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				messagesCn.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
 
	public void saveMessagesCn(){
		try{
			messagesCn.save(messagesCnFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
 
	public void registerMessagesCn(){
		messagesCnFile = new File(this.getDataFolder(),"messagesCn.yml");
		if(!messagesCnFile.exists()){
			this.getMessagesCn().options().copyDefaults(true);
			saveMessagesCn();
		}
	}
	
    
}

