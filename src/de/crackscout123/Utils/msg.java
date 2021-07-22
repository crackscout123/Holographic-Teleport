package de.crackscout123.Utils;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class msg {

	
	public static File file = new File("plugins/crackscout123/HolographicTeleport", "config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static String cfg_error = cfg.getString("messages.cfg_error").replace('&', '§');
	public static String sendrequest = cfg.getString("messages.sendrequest").replace('&', '§');
	public static String getrequest = cfg.getString("messages.getrequest").replace('&', '§');
	public static String alreadysentrequest = cfg.getString("messages.alreadysentrequest").replace('&', '§');
	public static String playernotonline = cfg.getString("messages.playernotonline").replace('&', '§');
	public static String nopermissions = cfg.getString("messages.nopermissions").replace('&', '§');
	public static String onlyplayers = cfg.getString("messages.onlyplayers").replace('&', '§');
	public static String norequest = cfg.getString("messages.norequest").replace('&', '§');
	public static String norequestbyplayer = cfg.getString("messages.norequestbyplayer").replace('&', '§');
	public static String acceptedrequest = cfg.getString("messages.acceptedrequest").replace('&', '§');
	public static String gotrequestaccepted = cfg.getString("messages.gotrequestaccepted").replace('&', '§');
	public static String teleportedto = cfg.getString("messages.teleportedto").replace('&', '§');
	public static String beenteleported = cfg.getString("messages.beenteleported").replace('&', '§');
	public static String teleportcountdownline1 = cfg.getString("messages.teleportcountdownline1").replace('&', '§');
	public static String teleportcountdownline2 = cfg.getString("messages.teleportcountdownline2").replace('&', '§');
	public static String teleportcountdownline3 = cfg.getString("messages.teleportcountdownline3").replace('&', '§');
	public static String cancelledtpa = cfg.getString("messages.cancelledtpa").replace('&', '§');
	public static String tpagotcancelled = cfg.getString("messages.tpagotcancelled").replace('&', '§');
	public static String playermoved = cfg.getString("messages.playermoved").replace('&', '§');
	public static Integer teleportcountdown = cfg.getInt("settings.teleportcountdown");
	public static Boolean playermovement = cfg.getBoolean("settings.playermovement");
}


/**
* @author JOE_
*
* @created 20.07.2021 (23:39:07)
*/
