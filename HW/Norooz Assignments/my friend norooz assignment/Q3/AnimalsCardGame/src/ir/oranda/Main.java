package ir.oranda;

import java.io.IOException;

/**
 * Main class - program starts from here
 *
 * @author MMKH
 * @version 1.0.0
 */
public class Main {

    /**
     * Start point
     * @param args Not used.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Everything happens inside of GameManager
        GameManager gm = new GameManager();
        gm.start();

    }
}
