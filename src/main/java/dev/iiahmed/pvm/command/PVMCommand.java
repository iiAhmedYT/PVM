package dev.iiahmed.pvm.command;

import dev.iiahmed.pvm.PVM;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;

import java.util.*;

public class PVMCommand implements TabExecutor {

    private final List<String> values = new ArrayList<>();

    public PVMCommand() {
        List<String> all = Arrays.asList("FISHING_HOOK", "ENDER_PEARL", "EGG",
                "SNOWBALL", "ARROW", "SPLASH_POTION", "WITHER_SKULL", "FIREBALL");

        for (String val : all) {
            if(isEntity(val)) {
                values.add(val);
            }
        }
    }

    private boolean isEntity(String val) {
        try {
            EntityType.valueOf(val);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String helpMessage =
                "§f==============================\n" +
                "§f/pvm §breload\n" +
                "§f/pvm §bset §ePROJECTILE float(ex. 1.325)\n" +
                "§f==============================";
        if(args.length == 0) {
            sender.sendMessage(helpMessage);
            return true;
        }
        String subcmd = args[0].toLowerCase();
        switch (subcmd) {
            case "reload":
                PVM.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Reloaded config successfully.");
                return true;
            case "set":
                if(args.length < 3) {
                    sender.sendMessage(ChatColor.RED + "USAGE: /pvm set PROJECTILE float");
                    return true;
                }
                String val = args[1].toUpperCase();
                if(!values.contains(val)) {
                    sender.sendMessage(ChatColor.RED + "Value \"" + val + "\" isn't a valid value.");
                    return true;
                }

                float f;
                try {
                    f = Float.parseFloat(args[2]);
                } catch (Exception ignored) {
                    sender.sendMessage(ChatColor.RED + "Value \"" + args[2] + "\" isn't a valid float.");
                    return true;
                }

                PVM.getInstance().getConfig().set(val, f);
                PVM.getInstance().saveConfig();
                return true;
            default:
                sender.sendMessage(helpMessage);
                return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            return Arrays.asList("reload", "set");
        }
        if (args.length == 1 && Objects.equals(args[0], "set")) {
            return values;
        }
        return null;
    }

}
