public class viewPlayer extends viewCharacter {

    /**
     * Constructs a new PlayerView for the specified player
     * @param player the player to be displayed
     */
    public viewPlayer(Player player) {
        super(player);
    }

    /**
     * Updates the player view based on changes in the model.
     * @param model the game model that has changed
     */
    @Override
    public void refresh(Model model) {
        // animations logic for player
    }
}
