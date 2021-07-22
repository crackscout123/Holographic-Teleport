package de.crackscout123.Commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.crackscout123.Main.Main;
import de.crackscout123.Utils.msg;

public class command_tpaccept implements CommandExecutor{
	
	public static HashMap<ArrayList<ArmorStand>, Player> currentCountdowns = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if(s instanceof Player) {
			final Player  p = (Player)s;
			if(p.hasPermission("crackscout123.holotp.tpaccept") || p.hasPermission("crackscout123.holotp.*") || p.hasPermission("crackscout123.*")) {
				if(a.length == 0) {
					if(command_tpa.requests.containsValue(p)) {
						Player T = null;
						for(Player target : command_tpa.requests.keySet()) {
							if(command_tpa.requests.get(target).equals(p)) {
								T = target;
								break;
							}
						}
						final Player t = T;
						if (T.isOnline()) {
				              final ArrayList<ArmorStand> lines = new ArrayList<>();
				              for (int lineNr = 0; lineNr < 3; lineNr++) {
				                Location location = p.getLocation();
				                location.setY(location.getY() - (lineNr / 2.0F));
				                ArmorStand line = (ArmorStand)p.getLocation().getWorld()
				                  .spawnEntity(location, EntityType.ARMOR_STAND);
				                line.setGravity(false);
				                line.setCanPickupItems(false);
				                line.setCustomName(Main.cfg.getString("messages.teleportcountdownline" + Integer.toString(lineNr + 1))
				                    .replaceAll("%player%", t.getDisplayName())
				                    .replaceAll("%time%", Integer.toString(msg.teleportcountdown)));
				                line.setCustomNameVisible(true);
				                line.setVisible(false);
				                lines.add(line);
				              } 
				              command_tpa.requests.remove(t, p);
				              p.sendMessage(msg.acceptedrequest.replaceAll("%player%", t.getDisplayName().replaceAll("%time%", Integer.toString(msg.teleportcountdown))));
				              t.sendMessage(msg.gotrequestaccepted.replaceAll("%player%", p.getDisplayName().replaceAll("%time%", Integer.toString(msg.teleportcountdown))));
				              currentCountdowns.put(lines, t);
				              (new BukkitRunnable() {
								int time;
								@Override
				                  public void run() {
				                    if (!command_tpaccept.currentCountdowns.containsKey(lines)) {
					                    cancel();
					                    return;
				                    } 
				                    int lineNr;
				                    for (lineNr = 0; lineNr < 3; lineNr++)
				                      ((ArmorStand)lines.get(lineNr)).setCustomName(Main.cfg.getString("messages.teleportcountdownline" + Integer.toString(lineNr + 1))
				                          .replaceAll("%player%", t.getDisplayName())
				                          .replaceAll("%time%", Integer.toString(this.time))); 
				                    if (this.time == 0) {
				                      t.teleport(((ArmorStand)lines.get(0)).getLocation());
				                      for (lineNr = 0; lineNr < 3; lineNr++)
				                        ((ArmorStand)lines.get(lineNr)).remove(); 
				                      p.sendMessage(msg.teleportedto.replaceAll("%player%", t.getDisplayName()));
				                      t.sendMessage(msg.beenteleported.replaceAll("%player%", p.getDisplayName()));
				                      cancel();
				                      return;
				                    } 
				                    this.time--;
				                  }
				                }).runTaskTimer(Main.getInstance(), 0L, 20L);
				              
							} else {
								p.sendMessage(msg.playernotonline.replaceAll("%player%", t.getDisplayName()));							}
						}
					} else if(a.length == 1) {
				          final Player t = Bukkit.getPlayerExact(a[0]);
				          if (t != null && command_tpa.requests.containsKey(t)) {
				            t.teleport(p.getLocation());
				            p.sendMessage(msg.acceptedrequest.replaceAll("%player%", t.getDisplayName()));
				            t.sendMessage(msg.gotrequestaccepted.replaceAll("%player%", p.getDisplayName()));
				            command_tpa.requests.remove(t, p);
				          } else {
				            p.sendMessage(msg.norequestbyplayer.replaceAll("%player%", (t == null) ? a[0] : t.getDisplayName()));
				          }
					} else {
						p.sendMessage("&7Use &c/tpaccept <player>&7!");
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
* @created 21.07.2021 (00:03:35)
*/
