
/**
 * A representation of a player on a sports team
 * @author Luke Tollefson
 * @version February 1, 2019
 */

public class Player {
    private String name;
    private String positionPlayed;
    private int jerseyNumber;

    /**
     * Constructor to create a new instance of Player
     * @param name the player's name
     * @param positionPlayed the player's position
     * @param jerseyNumber  the player's jersey number
     */
    public Player(String name, String positionPlayed, int jerseyNumber) {
        this.name = name;
        this.positionPlayed = positionPlayed;
        this.jerseyNumber = jerseyNumber;
    }
        
    /**
     * 
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name the player's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return the player's position
     */
    public String getPositionPlayed() {
        return positionPlayed;
    }

    /**
     * 
     * @param positionPlayed the player's new position
     */
    public void setPositionPlayed(String positionPlayed) {
        this.positionPlayed = positionPlayed;
    }

    /**
     * 
     * @return the player's jersey number
     */
    public int getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * 
     * @param jerseyNumber the player's new jersey number
     */
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
    
    /**
     * 
     * @return a string representation of the player
     */
    public String toString() {
        return getClass().getName() + "@" 
                + name + ":" + positionPlayed + ":" + jerseyNumber;
    }
    
    /**
     * 
     * @param o any object
     * @return true if o is identical in content to the Player
     */
    public boolean equals(Object o) {
        if (!(o instanceof Player)) {
            return false;
        }
        Player p = (Player) o;
        return name.equals(p.name) &&
                positionPlayed.equals(p.positionPlayed) &&
                jerseyNumber == p.jerseyNumber;
    }
}
