package com.jabyftw.dotamine.commands;

import com.jabyftw.dotamine.DotaMine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Rafael
 */
public class DotaCommand implements CommandExecutor {
    
    private final DotaMine pl;
    
    public DotaCommand(DotaMine plugin) {
        this.pl = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length < 1) {
            return false;
        } else if(args[1].equalsIgnoreCase("forcestart")) {
            if(sender.hasPermission("dotamine.forcestart")) {
                if(pl.gameStarted) {
                    sender.sendMessage(pl.getLang("lang.alreadyStarted"));
                    return true;
                } else {
                    pl.startGame();
                    return true;
                }
            } else {
                sender.sendMessage(pl.getLang("lang.noPermission"));
                return true;
            }
        }
        return false; // TODO: more commands (scoreboard, ranking)
    }
    
}