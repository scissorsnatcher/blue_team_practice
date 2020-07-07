package simple;
import javax.swing.*;
import gui.*;

public class Main
	{
		public static void main(String[] args) {
			
			JFrame.setDefaultLookAndFeelDecorated(true);
		    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		        public void run() {
		          GUI ui = new GUI();
		        }
		    });
		    
		}
	}

