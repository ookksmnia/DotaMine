package com.jabyftw.dotamine.commands;

import com.jabyftw.dotamine.DotaMine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Rafael
 */
public class JoinCommand implements CommandExecutor {

    private final DotaMine pl;

    public JoinCommand(DotaMine plugin) {
        this.pl = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("dotamine.play")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length > 0) {
                    int attackType;
                    if(args[0].startsWith("r")) { // Ranged = 2
                        attackType = 2;
                        p.sendMessage(pl.getLang("lang.settedRanged"));
                    } else {
                        attackType = 1; // Meele = 1
                        p.sendMessage(pl.getLang("lang.settedMeele"));
                    }
                    if (pl.gameStarted) {
                        pl.addPlayer(p, attackType);
                        return true;
                    } else {
                        pl.addtoPlayerQueue(p, attackType);
                        return true;
                    }
                } else {
                    p.sendMessage(pl.getLang("lang.usePlayCommand"));
                    return true;
                }
            } else {
                sender.sendMessage(pl.getLang("lang.onlyIngame"));
                return true;
            }
        } else {
            sender.sendMessage(pl.getLang("lang.noPermission"));
            return true;
        }
    }

}
