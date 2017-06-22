package main;

import view.Game;

/**
 * <h1>The Class Main.</h1>
 *
 * @author Groupe remy thibault pierre aurelien 
 * @version 1.0
 */

public class BoulderDash {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     * @throws Exception 
     */
	public static void main(final String[] args) throws Exception {
        /*final ControllerFacade controller = new BoulderDashController(new Game(), new Map());

        try {
            controller.start();
        } catch (final SQLException exception) {
            exception.printStackTrace();
        }*/
		
        new Game();
        
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	GFrame view = new GFrame();
            }
        });*/
    }
}
