import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.jar.JarEntry;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    boolean x = false;
    public void onEnable() {
        Bukkit.getPluginCommand("pvptoggle").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length == 0) {
                if (x)
                    sender.sendMessage("disabled pvp.");
                else sender.sendMessage("enabled pvp.");
                x = !x;
                return true;
            } else return false;
        } else sender.sendMessage("Unknown command. Type \"/help\" for help.");
        return true;
    }

    @EventHandler
    public void damage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player))
            return;
        if (!x)
            e.setCancelled(true);
    }
}
