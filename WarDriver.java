/*Sara Piette
*CS 110: Final Project
*War Game: Driver
*/
import javax.swing.JFrame;
import java.awt.*;

public class WarDriver {

	public static void main(String[] args)
	{
		
		JFrame frame = new WarGameGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setMinimumSize(new Dimension(900,750));
		frame.validate();
		frame.setVisible(true);
		
	}
		
}

