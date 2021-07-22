package de.crackscout123.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.crackscout123.Utils.msg;

public class command_tpa implements CommandExecutor{
	

	public static HashMap<Player, Player> requests = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if(s instanceof Player) {
			Player p = (Player)s;
			if(p.hasPermission("crackscout123.holotp.tpa") || p.hasPermission("crackscout123.holotp.*") || p.hasPermission("crackscout123.*")) {
				if(a.length == 1) {
					Player t = Bukkit.getPlayerExact(a[0]);
					if(t != null) {
						if(!requests.containsKey(p)){
							requests.put(p, t);
							p.sendMessage(msg.sendrequest.replace("%player%", p.getDisplayName()));
							t.sendMessage(msg.getrequest.replace("%player%", t.getDisplayName()));
						}else {
							p.sendMessage(msg.alreadysentrequest.replace("%player%", t.getDisplayName()));
						}
					} else {
						p.sendMessage(msg.playernotonline.replace("%player%", a[0]));
					}
				} else {
					p.sendMessage("&7Use &c/tpa <player>&7!");
				}
			} else {
				p.sendMessage(msg.nopermissions);
			}
		} else {
			s.sendMessage(msg.onlyplayers);
		}
		return true;
	}

}


/**
* @author JOE_
*
* @created 20.07.2021 (23:43:45)
*/
