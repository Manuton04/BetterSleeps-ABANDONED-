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

	
	private BetterSleaps plugin;
	
	
	public PrincipalCommand(BetterSleaps plugin) {
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
		
		String versionm = "Messages.version-command-message";
		Player player = (Player) sender;
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("version")) {
				
				player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(versionm)).replaceAll("%player%", player.getName()).replaceAll("%version%", plugin.version));
				
			}else if(args[0].equalsIgnoreCase("reload")) {
				String text2 = "Messages.reload-message";
				if(player.hasPermission("bettersleeps.reload")) {
				plugin.reloadConfig();
				plugin.reloadBedLocations();
				plugin.reloadMessagesEn();
				player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text2)).replaceAll("%player%", player.getName()));
				}
			}else if(args[0].equalsIgnoreCase("forcesleep")){
				
				
				if(player.hasPermission("bettersleeps.forcesleep")) {
				World world = Bukkit.getWorlds().get(0);
				String text3 = "Messages.force-sleep-message";
				
				Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text3)).replaceAll("%player%", player.getName()));
				  world.setTime(0);
				  world.setWeatherDuration(0);
		          world.setThunderDuration(0);
		          world.setThundering(false);
		          world.setStorm(false);
				}else {
					
					String noperms = "Messages.no-perms";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));

					
				}
				
				
			}
			else if(args[0].equalsIgnoreCase("setsleepers")) {
				
				
				if(player.hasPermission("bettersleeps.setsleepers")) {
					
					String text4 = "Messages.change-required-sleepers-message";
					
					
					if(args.length == 2 && !(args.length == 1)) {
						
						if(args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase("5") || args[1].equalsIgnoreCase("6") || args[1].equalsIgnoreCase("7") || args[1].equalsIgnoreCase("8") || args[1].equalsIgnoreCase("9") || args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase("11") || args[1].equalsIgnoreCase("12") || args[1].equalsIgnoreCase("13") || args[1].equalsIgnoreCase("14") || args[1].equalsIgnoreCase("15") || args[1].equalsIgnoreCase("16") || args[1].equalsIgnoreCase("17") || args[1].equalsIgnoreCase("18") || args[1].equalsIgnoreCase("19") || args[1].equalsIgnoreCase("20") || args[1].equalsIgnoreCase("21") || args[1].equalsIgnoreCase("22") || args[1].equalsIgnoreCase("23") || args[1].equalsIgnoreCase("24") || args[1].equalsIgnoreCase("25") || args[1].equalsIgnoreCase("26") || args[1].equalsIgnoreCase("27") || args[1].equalsIgnoreCase("28") || args[1].equalsIgnoreCase("29") || args[1].equalsIgnoreCase("30") || args[1].equalsIgnoreCase("31") || args[1].equalsIgnoreCase("32")) {
							
							config.set("Config.required-sleeping", args[1]);
							plugin.saveConfig();
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text4)).replaceAll("%player%", player.getName()).replaceAll("%total%", (args[1])));
							
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

					String noperms = "Messages.no-perms";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
					
				}
				
			}
               else if(args[0].equalsIgnoreCase("phantoms")) {
				

				
				if(player.hasPermission("bettersleeps.setphantoms")) {
					
					
					String text5 = "Messages.phantoms-change-message";
					
					if(args.length == 2 && !(args.length == 1)) {
					
					  if(args[1].equalsIgnoreCase("true")) {
						
						config.set("Config.phantoms.spawn", args[1]);
						plugin.saveConfig();
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
						
					  }else if(args[1].equalsIgnoreCase("false")) {
						
						config.set("Config.phantoms.spawn", args[1]);
						plugin.saveConfig();
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
						
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
			    
					String noperms = "Messages.no-perms";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
					
				}
				
			}
               else if(args[0].equalsIgnoreCase("language")) {
            	   
            	   if(player.hasPermission("bettersleeps.language")) {
            		   
            		   if(args.length == 2) {
            			   
            			   if (args[1].equalsIgnoreCase("english")) {
            
            				   config.set("Config.language", "english");
            				   plugin.saveConfig();
            				   
            				   String cLan = "Messages.change-language-message";
            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
            				   
            			   }else if(args[1].equalsIgnoreCase("español")) {
            				   
            				   config.set("Config.language", "español");
            				   plugin.saveConfig();
            				   
            				   String cLan = "Messages.change-language-message";
            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
            				   
            			   }else if(args[1].equalsIgnoreCase("chinese")) {
            				   
            				   config.set("Config.language", "chinese");
            				   plugin.saveConfig();
            				   
            				   String cLan = "Messages.change-language-message";
            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
            				   
            			   }
            			   
            			   
            			   else {
            				   
            				   player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs language (language).");
            				   
            			   }
            			   
            			   
            			   
            		   }else {
            			   
            			   player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs language (language).");
            			   
            		   }
            		   
            	   }else {
            		   
   					String noperms = "Messages.no-perms";
   					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
            		   
            	   }
            		   
            	   
               }
			
			
               else if(args[0].equalsIgnoreCase("explode")) {
   				

   				
   				if(player.hasPermission("bettersleeps.explode")) {
   					
   					
   					String text6 = "Messages.explode-change-message";
   					
   					if(args.length == 2 && !(args.length == 1)) {
   					
   					  if(args[1].equalsIgnoreCase("true")) {
   						
   						config.set("Config.bed-explodes-on-nether-or-end", args[1]);
   						plugin.saveConfig();
   						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text6)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
   						
   					  }else if(args[1].equalsIgnoreCase("false")) {
   						
   						config.set("Config.bed-explodes-on-nether-or-end", args[1]);
   						plugin.saveConfig();
   						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text6)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
   						
   					  }else {
   						
   						  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
   						
   					 }
   				  }else if(args.length == 1) {
   					  
   					  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
   					  
   				  }else {
   					  
   					  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
   					  
   				  }
   					
   				}else {
   					
   					String noperms = "Messages.no-perms";
   					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
   					
   				}
   				
              }
			
			
			else {
				
				String nocommand = "Messages.no-command";
				player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nocommand)));

			}
			
			
			
		}else {
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep or /bs to see this page.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /sleep to ask for someone to sleep.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bed to teleport to your bed.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bed locate to see where is your bed.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep explode to change if beds explode in Nether and End.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep language to change the language(only english, español and chinese).");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep setsleepers to change the number of players required to sleep.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep phantoms to set if you want phantoms to spawn.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep version to see the plugin version.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep forcesleep to force the day.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep reload to reload the config of the plugin.");
			
		}
	return true;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
        }else if(config.getString(path).equals("español")) {
	
    FileConfiguration messages = plugin.getMessagesEs();
	
    if(!(sender instanceof Player)) {
		
		Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + "You can do that in the console!!");
		
		
		return false;
	}else {
		
		String versionm = "Messages.version-command-message";
		Player player = (Player) sender;
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("version")) {
				
				player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(versionm)).replaceAll("%player%", player.getName()).replaceAll("%version%", plugin.version));
				
			}else if(args[0].equalsIgnoreCase("reload")) {
				String text2 = "Messages.reload-message";
				if(player.hasPermission("bettersleeps.reload")) {
				plugin.reloadConfig();
				plugin.reloadBedLocations();
				plugin.reloadMessagesEn();
				player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text2)).replaceAll("%player%", player.getName()));
				}
			}else if(args[0].equalsIgnoreCase("forcesleep")){
				
				
				if(player.hasPermission("bettersleeps.forcesleep")) {
				World world = Bukkit.getWorlds().get(0);
				String text3 = "Messages.force-sleep-message";
				
				Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text3)).replaceAll("%player%", player.getName()));
				  world.setTime(0);
				  world.setWeatherDuration(0);
		          world.setThunderDuration(0);
		          world.setThundering(false);
		          world.setStorm(false);
				}else {
					
					String noperms = "Messages.no-perms";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));

				}
				
				
			}
			else if(args[0].equalsIgnoreCase("setsleepers")) {
				
				
				if(player.hasPermission("bettersleeps.setsleepers")) {
					
					String text4 = "Messages.change-required-sleepers-message";
					
					
					if(args.length == 2 && !(args.length == 1)) {
						
						if(args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase("5") || args[1].equalsIgnoreCase("6") || args[1].equalsIgnoreCase("7") || args[1].equalsIgnoreCase("8") || args[1].equalsIgnoreCase("9") || args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase("11") || args[1].equalsIgnoreCase("12") || args[1].equalsIgnoreCase("13") || args[1].equalsIgnoreCase("14") || args[1].equalsIgnoreCase("15") || args[1].equalsIgnoreCase("16") || args[1].equalsIgnoreCase("17") || args[1].equalsIgnoreCase("18") || args[1].equalsIgnoreCase("19") || args[1].equalsIgnoreCase("20") || args[1].equalsIgnoreCase("21") || args[1].equalsIgnoreCase("22") || args[1].equalsIgnoreCase("23") || args[1].equalsIgnoreCase("24") || args[1].equalsIgnoreCase("25") || args[1].equalsIgnoreCase("26") || args[1].equalsIgnoreCase("27") || args[1].equalsIgnoreCase("28") || args[1].equalsIgnoreCase("29") || args[1].equalsIgnoreCase("30") || args[1].equalsIgnoreCase("31") || args[1].equalsIgnoreCase("32")) {
							
							config.set("Config.required-sleeping", args[1]);
							plugin.saveConfig();
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text4)).replaceAll("%player%", player.getName()).replaceAll("%total%", (args[1])));
							
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

					String noperms = "Messages.no-perms";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
					
				}
				
			}
               else if(args[0].equalsIgnoreCase("phantoms")) {
				

				
				if(player.hasPermission("bettersleeps.setphantoms")) {
					
					
					String text5 = "Messages.phantoms-change-message";
					
					if(args.length == 2 && !(args.length == 1)) {
					
					  if(args[1].equalsIgnoreCase("true")) {
						
						config.set("Config.phantoms.spawn", args[1]);
						plugin.saveConfig();
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
						
					  }else if(args[1].equalsIgnoreCase("false")) {
						
						config.set("Config.phantoms.spawn", args[1]);
						plugin.saveConfig();
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
						
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

					String noperms = "Messages.no-perms";
					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
					
				}
				
			}
               else if(args[0].equalsIgnoreCase("language")) {
            	   
            	   if(player.hasPermission("bettersleeps.language")) {
            		   
            		   if(args.length == 2) {
            			   
            			   if (args[1].equalsIgnoreCase("english")) {
            				   
            				   
            				   
            				   config.set("Config.language", "english");
            				   plugin.saveConfig();
            				   
            				   String cLan = "Messages.change-language-message";
            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
            				   
            			   }else if(args[1].equalsIgnoreCase("español")) {
            				   
            				   config.set("Config.language", "español");
            				   plugin.saveConfig();
            				   
            				   String cLan = "Messages.change-language-message";
            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
            				   
            			   }else if(args[1].equalsIgnoreCase("chinese")) {
            				   
            				   config.set("Config.language", "chinese");
            				   plugin.saveConfig();
            				   
            				   String cLan = "Messages.change-language-message";
            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
            				   
            			   }
            			   
            			   
            			   else {
            				   
            				   player.sendMessage(plugin.name + ChatColor.RED+" Uso: /bs language (language).");
            				   
            			   }
            			   
            			   
            			   
            		   }else {
            			   
            			   player.sendMessage(plugin.name + ChatColor.RED+" Uso: /bs language (language).");
            			   
            		   }
            		   
            	   }else {
            		   
   					String noperms = "Messages.no-perms";
   					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
            		   
            	   }
            		   
            	   
               } 
               else if(args[0].equalsIgnoreCase("explode")) {
      				

      				
      				if(player.hasPermission("bettersleeps.explode")) {
      					
      					
      					String text6 = "Messages.explode-change-message";
      					
      					if(args.length == 2 && !(args.length == 1)) {
      					
      					  if(args[1].equalsIgnoreCase("true")) {
      						
      						config.set("Config.bed-explodes-on-nether-or-end", args[1]);
      						plugin.saveConfig();
      						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text6)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
      						
      					  }else if(args[1].equalsIgnoreCase("false")) {
      						
      						config.set("Config.bed-explodes-on-nether-or-end", args[1]);
      						plugin.saveConfig();
      						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text6)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
      						
      					  }else {
      						
      						  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
      						
      					 }
      				  }else if(args.length == 1) {
      					  
      					  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
      					  
      				  }else {
      					  
      					  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
      					  
      				  }
      					
      				}else {
      					
      					String noperms = "Messages.no-perms";
      					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
      					
      				}
      				
                 }
			
			else {
				
				String nocommand = "Messages.no-command";
				player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nocommand)));

			}
			
			
			
		}else {
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep or /bs to see this page.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /sleep to ask for someone to sleep.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bed to teleport to your bed.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bed locate to see where is your bed.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep explode to change if beds explode in Nether and End.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep language to change the language(only english, español and chinese).");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep setsleepers to change the number of players required to sleep.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep phantoms to set if you want phantoms to spawn.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep version to see the plugin version.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep forcesleep to force the day.");
			player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep reload to reload the config of the plugin.");
			
		}
	return true;
	}
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
}
            else if(config.getString(path).equals("chinese")) {
			
			FileConfiguration messages = plugin.getMessagesCn();
			
			if(!(sender instanceof Player)) {
				
				Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + "You can do that in the console!!");
				
				
				return false;
			}else {
				
				String versionm = "Messages.version-command-message";
				Player player = (Player) sender;
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("version")) {
						
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(versionm)).replaceAll("%player%", player.getName()).replaceAll("%version%", plugin.version));
						
					}else if(args[0].equalsIgnoreCase("reload")) {
						String text2 = "Messages.reload-message";
						if(player.hasPermission("bettersleeps.reload")) {
						plugin.reloadConfig();
						plugin.reloadBedLocations();
						plugin.reloadMessagesEn();
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text2)).replaceAll("%player%", player.getName()));
						}
					}else if(args[0].equalsIgnoreCase("forcesleep")){
						
						
						if(player.hasPermission("bettersleeps.forcesleep")) {
						World world = Bukkit.getWorlds().get(0);
						String text3 = "Messages.force-sleep-message";
						
						Bukkit.broadcastMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text3)).replaceAll("%player%", player.getName()));
						  world.setTime(0);
						  world.setWeatherDuration(0);
				          world.setThunderDuration(0);
				          world.setThundering(false);
				          world.setStorm(false);
						}else {
							
							String noperms = "Messages.no-perms";
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));

						}
						
						
					}
					else if(args[0].equalsIgnoreCase("setsleepers")) {
						
						
						if(player.hasPermission("bettersleeps.setsleepers")) {
							
							String text4 = "Messages.change-required-sleepers-message";
							
							
							if(args.length == 2 && !(args.length == 1)) {
								
								if(args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase("5") || args[1].equalsIgnoreCase("6") || args[1].equalsIgnoreCase("7") || args[1].equalsIgnoreCase("8") || args[1].equalsIgnoreCase("9") || args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase("11") || args[1].equalsIgnoreCase("12") || args[1].equalsIgnoreCase("13") || args[1].equalsIgnoreCase("14") || args[1].equalsIgnoreCase("15") || args[1].equalsIgnoreCase("16") || args[1].equalsIgnoreCase("17") || args[1].equalsIgnoreCase("18") || args[1].equalsIgnoreCase("19") || args[1].equalsIgnoreCase("20") || args[1].equalsIgnoreCase("21") || args[1].equalsIgnoreCase("22") || args[1].equalsIgnoreCase("23") || args[1].equalsIgnoreCase("24") || args[1].equalsIgnoreCase("25") || args[1].equalsIgnoreCase("26") || args[1].equalsIgnoreCase("27") || args[1].equalsIgnoreCase("28") || args[1].equalsIgnoreCase("29") || args[1].equalsIgnoreCase("30") || args[1].equalsIgnoreCase("31") || args[1].equalsIgnoreCase("32")) {
									
									config.set("Config.required-sleeping", args[1]);
									plugin.saveConfig();
									player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text4)).replaceAll("%player%", player.getName()).replaceAll("%total%", (args[1])));
									
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

							String noperms = "Messages.no-perms";
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
							
						}
						
					}
		               else if(args[0].equalsIgnoreCase("phantoms")) {
						

						
						if(player.hasPermission("bettersleeps.setphantoms")) {
							
							
							String text5 = "Messages.phantoms-change-message";
							
							if(args.length == 2 && !(args.length == 1)) {
							
							  if(args[1].equalsIgnoreCase("true")) {
								
								config.set("Config.phantoms.spawn", args[1]);
								plugin.saveConfig();
								player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
								
							  }else if(args[1].equalsIgnoreCase("false")) {
								
								config.set("Config.phantoms.spawn", args[1]);
								plugin.saveConfig();
								player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text5)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
								
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

							String noperms = "Messages.no-perms";
							player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
							
						}
						
					}
		               else if(args[0].equalsIgnoreCase("language")) {
		            	   
		            	   if(player.hasPermission("bettersleeps.language")) {
		            		   
		            		   if(args.length == 2) {
		            			   
		            			   if (args[1].equalsIgnoreCase("english")) {
		            				   
		            				   
		            				   
		            				   config.set("Config.language", "english");
		            				   plugin.saveConfig();
		            				   
		            				   String cLan = "Messages.change-language-message";
		            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
		            				   
		            			   }else if(args[1].equalsIgnoreCase("español")) {
		            				   
		            				   config.set("Config.language", "español");
		            				   plugin.saveConfig();
		            				   
		            				   String cLan = "Messages.change-language-message";
		            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
		            				   
		            			   }else if(args[1].equalsIgnoreCase("chinese")) {
		            				   
		            				   config.set("Config.language", "chinese");
		            				   plugin.saveConfig();
		            				   
		            				   String cLan = "Messages.change-language-message";
		            				   player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(cLan)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
		            				   
		            			   }
		            			   
		            			   else {
		            				   
		            				   player.sendMessage(plugin.name + ChatColor.RED+" Uso: /bs language (language).");
		            				   
		            			   }
		            			   
		            			   
		            			   
		            		   }else {
		            			   
		            			   player.sendMessage(plugin.name + ChatColor.RED+" Uso: /bs language (language).");
		            			   
		            		   }
		            		   
		            	   }else {
		            		   
		   					String noperms = "Messages.no-perms";
		   					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
		            		   
		            	   }
		            		   
		            	   
		               } 
		               else if(args[0].equalsIgnoreCase("explode")) {
		      				

		      				
		      				if(player.hasPermission("bettersleeps.explode")) {
		      					
		      					
		      					String text6 = "Messages.explode-change-message";
		      					
		      					if(args.length == 2 && !(args.length == 1)) {
		      					
		      					  if(args[1].equalsIgnoreCase("true")) {
		      						
		      						config.set("Config.bed-explodes-on-nether-or-end", args[1]);
		      						plugin.saveConfig();
		      						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text6)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
		      						
		      					  }else if(args[1].equalsIgnoreCase("false")) {
		      						
		      						config.set("Config.bed-explodes-on-nether-or-end", args[1]);
		      						plugin.saveConfig();
		      						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(text6)).replaceAll("%player%", player.getName()).replaceAll("%choice%", (args[1])));
		      						
		      					  }else {
		      						
		      						  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
		      						
		      					 }
		      				  }else if(args.length == 1) {
		      					  
		      					  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
		      					  
		      				  }else {
		      					  
		      					  player.sendMessage(plugin.name + ChatColor.RED+" Usage: /bs explode true/false.");
		      					  
		      				  }
		      					
		      				}else {
		      					
		      					String noperms = "Messages.no-perms";
		      					player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(noperms)));
		      					
		      				}
		      				
		                 }
					
					else {
						
						String nocommand = "Messages.no-command";
						player.sendMessage(plugin.name + " " + ChatColor.translateAlternateColorCodes('&', messages.getString(nocommand)));

					}
					
					
					
				}else {
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep or /bs to see this page.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /sleep to ask for someone to sleep.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bed to teleport to your bed.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bed locate to see where is your bed.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep explode to change if beds explode in Nether and End.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep language to change the language(only english, español and chinese).");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep setsleepers to change the number of players required to sleep.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep phantoms to set if you want phantoms to spawn.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep version to see the plugin version.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep forcesleep to force the day.");
					player.sendMessage(plugin.name + ChatColor.YELLOW+" Use /bettersleep reload to reload the config of the plugin.");
					
				}
			return true;
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
        return Arrays.asList("forcesleep", "version", "reload", "setsleepers", "phantoms", "language", "explode");
    }
	
}
