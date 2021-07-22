package de.crackscout123.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.crackscout123.Commands.command_tpaccept;
import de.crackscout123.Utils.msg;

public class PlayerMoveListener implements Listener {
	
	  @EventHandler
	  public void onPlayerMove(PlayerMoveEvent e) {
	    if (e.getFrom().getX() == e.getTo().getX() && 
	      e.getFrom().getY() == e.getTo().getY() && 
	      e.getFrom().getZ() == e.getTo().getZ())
	      return; 
	    if ((msg.playermovement) && 
	      command_tpaccept.currentCountdowns.containsValue(e.getPlayer())) {
	      Player p = e.getPlayer();
	      List<ArrayList<ArmorStand>> foundLists = new ArrayList<>();
	      for (ArrayList<ArmorStand> lines : (Iterable<ArrayList<ArmorStand>>)command_tpaccept.currentCountdowns.keySet()) {
	        if ((command_tpaccept.currentCountdowns.get(lines)).equals(p))
	          foundLists.add(lines); 
	      } 
	      for (ArrayList<ArmorStand> list : foundLists) {
	        for (ArmorStand as : list)
	          as.remove(); 
	        command_tpaccept.currentCountdowns.remove(list);
	      } 
	      p.sendMessage(msg.playermoved);
	    } 
	  }
	}

/**
* @author JOE_
*
* @created 21.07.2021 (00:33:36)
*/
