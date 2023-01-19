import java.io.IOException; 
import java.net.URI;
import java.util.*;

class SearchHandler implements URLHandler {
    ArrayList<String> inventory;
    public SearchHandler() {
        inventory = new ArrayList<>();
    }
    public String handleRequest(URI url) {
        if(url.getPath().equals("/")) {
            String result = "Inventory: ";
            for(String s : inventory) {
                result += s;
            }
            return result;
        }
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                inventory.add(parameters[1]);
                return String.format("%s is added!", parameters[1]);
            }
        } else if(url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");   
            if(parameters[0].equals("s")) {
                String searchResult = "";
                for(String s : inventory) {
                    if(s.contains(parameters[1])) {

                    }
                }
            }
        }
    }

}