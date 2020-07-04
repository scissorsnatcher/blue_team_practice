import javax.swing.*;
import java.awt.*;

public class Test extends JFrame
{
   public static void main(String[] args) {gui();}

    public static void gui()
    {
        JFrame frame = new JFrame("GraphicDisplay");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
        //Graphics2D g2d = (Graphics2D) super.getGraphics();
        //Graph graph = new Graph();
    }
}
