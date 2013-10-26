package com.DailyLove.basketball; 
  
import java.util.logging.Logger; 
  
import org.bukkit.ChatColor; 
import org.bukkit.command.Command; 
import org.bukkit.command.CommandSender; 
import org.bukkit.configuration.file.YamlConfiguration; 
import org.bukkit.entity.Player; 
import org.bukkit.inventory.ItemStack; 
import org.bukkit.inventory.meta.ItemMeta; 
import org.bukkit.plugin.PluginManager; 
import org.bukkit.plugin.java.JavaPlugin; 
  
public class Basketball extends JavaPlugin { 
    public void onEnable() { 
        YamlConfiguration config = (YamlConfiguration) getConfig(); 
        config.options().copyDefaults(true); 
        saveDefaultConfig(); 
        PluginManager pm = getServer().getPluginManager(); 
        pm.registerEvents(new ItemDrop(this), this); 
        version = "v" + config.getString("version") + " "; 
        Logger log = getLogger(); 
        log.info(name + version + "Enabled!"); 
    } 
    public void onDisable() { 
        Logger log = getLogger(); 
        log.info(name + version + "Disabled!"); 
        reloadConfig(); 
    } 
    public Basketball() { 
        name = "Basketball "; 
    } 
      
    public boolean onCommand(CommandSender s, Command cmd, String commandLabel, String[] args) { 
        if(!(s instanceof Player)) { 
            s.sendMessage("Only players can use that command"); 
            return true; 
        } 
        Player p = (Player) s; 
        if(cmd.getName().equalsIgnoreCase("bb")) { 
            if(!p.hasPermission("basketball.use")) { 
                p.sendMessage(ChatColor.RED + "You don't have permission to do that."); 
                return true; 
            } 
            ItemStack i = new ItemStack(getConfig().getInt("Item")); 
            ItemMeta iMeta = i.getItemMeta();  
            iMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Item name")));  
            iMeta.setLore(getConfig().getStringList("Item lore"));  
            i.setItemMeta(iMeta); 
            if(p.getInventory().contains(i)) { 
                p.sendMessage(ChatColor.RED + "You already have one in your inventory."); 
                return true; 
            } 
            p.sendMessage(ChatColor.GREEN + "Basketball has been given to you."); 
            p.getInventory().addItem(i); 
        } 
        return true; 
    } 
      
    public String name; 
    public String version; 
} 
