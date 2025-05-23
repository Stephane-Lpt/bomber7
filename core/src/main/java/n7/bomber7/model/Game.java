public class Game {
    // nbPlayers gotten through controller
    int nbPlayers;
    String mapFilepath;

    Game(int nbPlayers, String mapFilepath){
        this.nbPlayers = nbPlayers;
        // Load map from file
        // Initialize game state
        // Other functionnalities to come ?
    }

    public int tick(){
        // Update game state
        // Perhaps pulled from game engine ?
        return 0;
    } 
}
