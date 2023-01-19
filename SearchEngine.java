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
                result += s + " ";
            }
            return result;
        } else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s") && parameters.length > 1) {
                inventory.add(parameters[1]);
                return String.format("%s is added!", parameters[1]);
            }
            return "404 not found!";
        } else if(url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");   
            if(parameters[0].equals("s") && parameters.length > 1) {
                String searchResult = "Result: ";
                for(String s : inventory) {
                    if(s.contains(parameters[1])) {
                        searchResult +=  s + " and ";
                    }
                }
                if(searchResult.length() > 8) {
                    searchResult = searchResult.substring(0, searchResult.length() - 4); //Elminate the " and " at the end
                    return searchResult;
                } else {
                    return "Sorry, no result is found";
                }
            }
            return "404 not found!";
        } else {
            return "404 not found!";
        }
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new SearchHandler());
    }
}