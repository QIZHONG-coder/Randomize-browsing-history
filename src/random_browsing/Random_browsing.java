
package random_browsing;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Qi Zhong
 * search phrases are stored in a text file "searchphrases.txt
 * the phrases are separated by blank spaces
 * this outputs a series of web pages on the default web browser
 * each page displays the search results from Google search on the phrase provided
 * a total ten pages or less on each run
 * the order of the Google search is not necessarily the same order of the phrases
 */
class functions{
    static String getPhrases(){
        String phrases=new String();
        try {
        File objFile = new File("./searchPhrases.txt");
          try (Scanner myReader = new Scanner(objFile)) {
              while (myReader.hasNextLine()) {
                  phrases += myReader.nextLine();
              }   }
        } catch (FileNotFoundException e) {
          System.out.println("Please create a text file with phrases separated by commas.");
        }  
        return phrases;
    }
    static void getWeb() throws IOException {
        Integer n;
        String phrases=getPhrases();
        String[] searchTerms=phrases.split(",", 0);
        List<String> shuffleList = Arrays.asList(searchTerms);
        Collections.shuffle(shuffleList);
        shuffleList.toArray(searchTerms);
        java.awt.Desktop desktopBrowser = java.awt.Desktop.getDesktop();
        String GOOGLE_SEARCH_URL = "https://www.google.com/search";
        if (searchTerms.length >10){
            n=10;
        }else{
            n=searchTerms.length;
        }
        for (int i = 0; i < n; i++) {
            String s=searchTerms[i].replace(" ", "%20");
            try {
                java.net.URI newPage = new java.net.URI(GOOGLE_SEARCH_URL + "?q=" + s + "&num=10");
                 desktopBrowser.browse(newPage);
            } catch (URISyntaxException ex) {
                Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Random_browsing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        functions.getWeb();
        
    }  
}
