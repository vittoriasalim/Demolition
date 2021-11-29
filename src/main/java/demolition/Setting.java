package demolition;

import java.io.File; 
import java.io.FileNotFoundException;  

import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Scanner;


public class Setting {
    /**Json object is used to parse and process json fles */
    private JSONObject json;
    /**List of level path in a form of file path */
    private ArrayList <String> path ; 
    /**total number of lives bomb guy has */
    private int lives ;
    /**Total time for each levels */
    private ArrayList <Integer> time;
    /**
     * default constructor
     */
    public Setting(){
        this.time = new ArrayList<>();
        this.path = new ArrayList<>();
    }
    /**
     * Get total number of levels based on json files
     * @return size of level 
     */
    public int numLevel(){
        return this.path.size();
    }
    /**
     * Get total lives based on json files
     * @return number of lives
     */
    public int getLives(){
        return this.lives;
    }
    /**
     * Get time offered based on levels
     * @param lvl level to extract 
     * @return total time for each level
     */
    public int getTime(int lvl){
        return this.time.get(lvl);
      
    }
    /**
     * Get filename to read based on levels
     * @param index level to extract to get files
     * @return filename according to its index
     */
    public String getPath(int index){
        return this.path.get(index);

    }
    /**
     * Read and extract json files user processing.data
     * @param loadJs processing library
     * @param configFile the directory of file to read
     */
    public void readJSON(PApplet loadJs,String configFile) {
        this.time.removeAll(this.path);
        this.path.removeAll(this.path);
        this.lives =0;
    
        json = loadJs.loadJSONObject(configFile);
        this.lives = json.getInt("lives");
    
        JSONArray items = json.getJSONArray("levels");

        for (int i = 0; i < items.size(); i++) {
            JSONObject obj = items.getJSONObject(i); 
            String path = obj.getString("path");
            int time = obj.getInt("time");
            this.path.add(path);
            this.time.add(time);            
        }
    }
    /**
     * Read level file name and store it in a an array list
     * @param filename, filename to read
     * @return graph : the map of the game stored in array list
     */
    public ArrayList<String[]> getGrid(String filename){
        ArrayList<String[]> graph = new ArrayList<>();
        try {
            File f = new File(filename);
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String [] info = data.split("");
                graph.add(info);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }

}
