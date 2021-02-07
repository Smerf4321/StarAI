package starai;
import mechanics.Game;
import player.ComputerPlayer;
import player.HumanPlayer;

/**
 *
 * @author Patryk
 */
public class StarAI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game(new HumanPlayer(), new ComputerPlayer());
    }
    
}
