package fr.jonadead.Oplist;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.PluginEnableEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class OplistListener implements Listener{
	
	private Oplist plugin;
	

	public OplistListener(Oplist plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPluginEnable(PluginEnableEvent e){
		
		if(plugin.pManager == null && Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
		    plugin.pManager = PermissionsEx.getPermissionManager();
		}
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
		
		Player p = e.getPlayer();
		String message = e.getMessage();
		
		String[] params = message.split(" ");
		
		if(params[0].equalsIgnoreCase("/isop")){
			e.setCancelled(true);
			
			if(!plugin.hasPermission(p, "Oplist.isop")){
				p.sendMessage(ChatColor.RED + "Vous n'avez pas les permissions pour cette commande !");
				return;
				
			}
			
			if(params.length == 1){
				
				p.sendMessage(ChatColor.RED + "You are Op: " + p.isOp());
				
			}
			
		}
			
		if(params[0].equalsIgnoreCase("/oplist")){
			e.setCancelled(true);
			
			if(!plugin.hasPermission(p, "Oplist.oplist")){
				p.sendMessage(ChatColor.RED + "Vous n'avez pas les permissions pour cette commande !");
				return;
				
			}
			
			if(params.length == 1){

				@SuppressWarnings("rawtypes")
				Set Oplist = Bukkit.getServer().getOperators();
				Object[] Op_array = Oplist.toArray();
				String Ops = "";
				
				for(int i=0;i < Op_array.length;i++)
				{
					Object[] Op_split = Op_array[i].toString().split("name=");
					Op_split[1] = Op_split[1].toString().replace("]", "");
					Op_split[1] = Op_split[1].toString().replace("}", "");
					if(!(Ops == "")) 
					{
						Ops += ", ";
					}
					Ops +=Op_split[1];
				}
				
				p.sendMessage(ChatColor.DARK_RED + "[Oplist]: " + ChatColor.WHITE + Ops);
				return;
				
			}
			
		}
		
	}
	
}
