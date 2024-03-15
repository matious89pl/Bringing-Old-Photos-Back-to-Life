package com.everis.rec.menu.link.cofiguration.constants;
import java.util.HashMap;

public class IconConstants {

	    public final HashMap<String, String> iconsValues;

	    public IconConstants() {
	    	iconsValues = new HashMap<>();
	        String[][] iconList = { 
	            {"", "Select the icon name"},
	            {"None", "None"},
	            {"add_document", "Document plus"},
	            {"bell-white", "Bell"},
	            {"book_open", "Book open"},
	            {"user_friend_party", "User friend"},
	            {"user-white", "User"},
	            {"user-black", "User black"},
	            {"user-cog-white", "User cog"},
	            {"user-cog-black", "User cog black"},
	            {"calendar", "Calendar"},
	            {"category", "Category"},
	            {"code-branch-big", "Code branch"},
	            {"cog-white", "Cog"},
	            {"cog-black", "Cog black"},
	            {"document", "Document"},
	            {"download-white", "Download"},
	            {"envelope-white", "Envelope"},
	            {"exclamation-white", "Exclamation"},
	            {"expand", "Expand"},
	            {"file-white", "File"},
	            {"grid-white", "Grid"},
	            {"home-white", "Home"},
	            {"home-black", "Home black"},
	            {"lamp-white", "Lamp"},
	            {"link-white", "Link"},
	            {"log-white", "Log"},
	            {"network", "Network"},
	            {"network-black", "Network black"},
	            {"pencil-white", "Pencil"},
	            {"phone-white", "Phone"},
	            {"question-white", "Question"},
	            {"tools-white", "Tools"},
	            {"tools-black", "Tools black"}
	        };
	        for (String[] iconPair : iconList) {
	        	iconsValues.put(iconPair[0], iconPair[1]);
	        }
	    }
	}

