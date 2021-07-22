package de.crackscout123.Main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.crackscout123.Commands.command_tpa;
import de.crackscout123.Commands.command_tpaccept;
import de.crackscout123.Events.PlayerInteractAtEntityListener;
import de.crackscout123.Events.PlayerMoveListener;
import de.crackscout123.Utils.msg;

public class Main extends JavaPlugin {
	private static Main instance;
	
	public Main() {
		instance = this;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static File file = new File("plugins/crackscout123/HolographicTeleport", "config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	@Override
	public void onEnable() {
		cfg.addDefault("messages.cfg_error", "&cSomething went wrong with the config file.");
	    cfg.addDefault("messages.nopermissions", "&7You are &cnot allowed &7to run this command!");
	    cfg.addDefault("messages.onlyplayers", "&7Only players are allowed to run this command!");
	    cfg.addDefault("messages.getrequest", "&a%player% &7has sent a teleport request to you. Use &a/tpaccept");
	    cfg.addDefault("messages.sendrequest", "&7You sent a teleport request to &a%player%&7.");
	    cfg.addDefault("messages.alreadysentrequest", "&7You have already sent &a%player% &7a teleport request.");
	    cfg.addDefault("messages.playernotonline", "&7The player &a%player% &7is currently not online.");
	    cfg.addDefault("messages.norequest", "&cNoone has sent you a teleport request.");
	    cfg.addDefault("messages.norequestbyplayer", "&7The player &a%player% &7has not sent you a teleport request.");
	    cfg.addDefault("messages.acceptedrequest", "&7You've accepted the request of &a%player%&7. He'll be teleported in &a%time% &7seconds.");
	    cfg.addDefault("messages.gotrequestaccepted", "&7The player &a%player% &7has accepted your request. You'll be teleported in &a%time% &7seconds.");
	    cfg.addDefault("messages.teleportedto", "&7The player &a%player% &7has been teleported to you.");
	    cfg.addDefault("messages.beenteleported", "&7You've been teleported to &a%player%&7.");
	    cfg.addDefault("messages.teleportcountdownline1", "&7The player &a%player% &7will appear in");
	    cfg.addDefault("messages.teleportcountdownline2", "&a%time% &7seconds");
	    cfg.addDefault("messages.teleportcountdownline3", "&cHit to cancel the teleportation!");
	    cfg.addDefault("messages.cancelledtpa", "&7You cancelled the teleportation of &a%player%&7.");
	    cfg.addDefault("messages.tpagotcancelled", "&7Your teleportation was cancelled by &a%player%&7.");
	    cfg.addDefault("messages.playermoved", "&7Your teleporation was cancelled, because you moved.");
	    cfg.addDefault("settings.teleportcountdown", Integer.valueOf(10));
	    cfg.addDefault("settings.playermovement", Boolean.valueOf(false));
		cfg.options().copyDefaults(true);		
		try {
			cfg.save(file); // SAVING CONFIG FILE!
		} catch (IOException e) {
			System.out.println(msg.cfg_error);
			e.printStackTrace();
		}
		
		getServer().getPluginManager().registerEvents(new PlayerInteractAtEntityListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
		getCommand("tpa").setExecutor(new command_tpa());
		getCommand("tpaccept").setExecutor(new command_tpaccept());
	}
	
	@Override
	public void onDisable() {
	}
	
}


/**
* @author JOE_
*
* @created 20.07.2021 (23:32:15)
*/
