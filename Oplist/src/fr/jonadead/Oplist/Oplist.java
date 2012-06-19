package fr.jonadead.Oplist;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionManager;

public class Oplist extends JavaPlugin{
	@SuppressWarnings("unused")
	private OplistListener listener;
	public PermissionManager pManager;

	@Override
	public void onDisable(){
		
		System.out.println("[Oplist] Unloaded !");
		
	}
	
	@Override
	public void onEnable(){
		
		System.out.println("[Oplist] Loaded !");
		
		PluginManager pm = getServer().getPluginManager();	
		pm.registerEvents(new OplistListener(this), this);
		
	}
	
	public boolean hasPermission(Player p, String perm){
		if(!p.isOp() && !(pManager != null && pManager.has(p, perm))){
			return false;
		}else{
			return true;
		}
	}

}
