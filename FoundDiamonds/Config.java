package FoundDiamonds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import FoundDiamonds.Main;

public class Config {
	static Properties prop = new Properties();
    static OutputStream output = null;
    public static File config = new File("mods/FoundDiamonds/config.properties");

    public static List<Integer> parseListAsInt(String input) {
    	String[] strArray = input.split(",");
    	List<Integer> intList = new ArrayList<>();
    	for(int i = 0; i < strArray.length; i++) {
    	    intList.add(Integer.parseInt(strArray[i]));
    	}
    	return intList;
    }
    
    public static boolean readConfig() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            try {
                input = new FileInputStream(config);
            } catch (FileNotFoundException e) {
                createConfig();
                return false;
            }

            prop.load(input);

            Main.notification = prop.getProperty("notification-template").replace("&", "§");
            Main.alertAll = Boolean.parseBoolean(prop.getProperty("alert-everyone"));
            Main.blocks = parseListAsInt(prop.getProperty("block-ids"));
            Main.enabledMessage = prop.getProperty("enable-message").replace("&", "§");
            Main.disabledMessage = prop.getProperty("disable-message").replace("&", "§");

        } catch (IOException ex) {
            System.out.println("[FoundDiamonds/WARN]: Disabled! Configuration error. " + ex.getMessage());
        }
        try {
            input.close();
        } catch (IOException e) {
        	System.out.println("[FoundDiamonds/WARN]: Disabled! Configuration error. " + e.getMessage());
            return false;
        }
        System.out.println("[FoundDiamonds/INFO]: Config: OK");
        return true;
    }

    public static void createConfig() {
        try {
            config.getParentFile().mkdirs();

            output = new FileOutputStream(config);

            prop.setProperty("notification-template", "&c&l(!) &6%player% &7found &6%ore%&7!");
            prop.setProperty("alert-everyone", "true");
            prop.setProperty("block-ids", "14,15,16,21,56,129");
            prop.setProperty("enable-message", "&7&l[FD]: &aEnabled notifications.");
            prop.setProperty("disable-message", "&7&l[FD]: &cDisabled notifications.");

            prop.store(output, "FoundDiamonds Configuration");

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                    System.out.println("[FoundDiamonds/INFO]: Configuration file created.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
