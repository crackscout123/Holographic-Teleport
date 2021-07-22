package de.crackscout123.Events;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import de.crackscout123.Commands.command_tpaccept;
import de.crackscout123.Main.Main;

public class PlayerInteractAtEntityListener implements Listener{
	
	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("holoteleport.canceltpa")) {
		      FileConfiguration config = Main.cfg;
		      boolean found = false;
		      for (ArrayList<ArmorStand> lines : (Iterable<ArrayList<ArmorStand>>)command_tpaccept.currentCountdowns.keySet()) {
		        for (ArmorStand as : lines) {
		          if (e.getRightClicked().equals(as)) {
		            found = true;
		            break;
		          } 
		        } 
		        if (found) {
		          for (ArmorStand as : lines)
		            as.remove(); 
		          Player t = (Player)command_tpaccept.currentCountdowns.get(lines);
		          p.sendMessage(config.getString("messages.cancelledtpa")
		              .replaceAll("%player%", t.getDisplayName()));
		          if (t.isOnline())
		            t.sendMessage(config.getString("messages.tpagotcancelled")
		                .replaceAll("%player%", p.getDisplayName())); 
		          command_tpaccept.currentCountdowns.remove(lines);
		          break;
		        } 
		      } 
		    } 
		  }
		}


/**
* @author JOE_
*
* @created 21.07.2021 (00:29:37)
*/
