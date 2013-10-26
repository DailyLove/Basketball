package com.DailyLove.basketball; 
  
import org.bukkit.ChatColor; 
import org.bukkit.event.EventHandler; 
import org.bukkit.event.Listener; 
import org.bukkit.event.entity.ItemSpawnEvent; 
import org.bukkit.inventory.ItemStack; 
import org.bukkit.inventory.meta.ItemMeta; 
  
public class ItemDrop implements Listener { 
    public ItemDrop(Basketball instance) { 
        plugin = instance; 
    } 
      
    @EventHandler
    public void onDrop(ItemSpawnEvent e) { 
        ItemStack i = new ItemStack(plugin.getConfig().getInt("Item")); 
        ItemMeta iMeta = i.getItemMeta();  
        iMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Item name")));  
        iMeta.setLore(plugin.getConfig().getStringList("Item lore"));  
        i.setItemMeta(iMeta); 
        if(!e.getEntity().getItemStack().equals(i)) { 
            return; 
        } 
        e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(5)); 
    } 
      
    public Basketball plugin; 
} 
