package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simple.Boruvka;
import simple.Edge;
import simple.Graph;

public class GUI extends JFrame{

	
	private static final long serialVersionUID = 1L;
	public int x, y, i;
	protected GraphPainter GraphPanel;
	protected JPanel contents;
	protected JPanel text;
	protected Graph graph;
	protected Edge[] sorted;
	
	public GUI(){
		
		super("GraphicDisplay");
		setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        Graph graph = new Graph();
        GraphPainter GraphPanel = new  GraphPainter();
        GraphPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        GraphPanel.setBackground(Color.WHITE);
        
        JPanel text = new JPanel();
        JPanel contents = new JPanel();
    	
        JButton button1 = new JButton("Читать из файла");
		JButton button2 = new JButton("Добавить вершину");
		JButton button3 = new JButton("Добавить ребро");
		JButton button = new JButton("Очистить");
		JButton nextButton = new JButton("Вперед");
		//BoruvkaVizualization runAlg = new BoruvkaVizualization("Вперед"); 
		
		nextButton.setEnabled(false);
		//runAlg.setEnabled(false);

		String[] messages = {"Применить алгоритм", "Результат", "Визуализация"};
		
		JComboBox<String> cb = new JComboBox<String>(messages);
        cb.setSelectedIndex(0);
        
        JTextField textField = new JTextField();
        textField.setColumns(50);
      
        ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graph.readFromFile();
				GraphPanel.GraphPaint(graph);
			}
		};

		ActionListener actionListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				
				String t = textField.getText();
				String[] entryValues = t.split(" ");
				for (int i = 0; i < graph.getVertNum(); i++) {
					if (graph.getVertNames()[i].equals(entryValues[0])) {System.out.println("Incorrect"); return;};
				}
				GraphPanel.addNode(entryValues[0], x, y-50);
				graph.addNode(entryValues[0], x, y-50);
			}
		};
		
		ActionListener actionListener3 = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
	
				String t = textField.getText();
				String[] entryValues = t.split(" ");
				int src = 0;
				int dest = 0;
				System.out.println(entryValues[0]);
				System.out.println(entryValues[1]);
				for(int i = 0; i < graph.getVertNum(); i++) {
					
					if((graph.getVertNames()[i]).equals(entryValues[0])) {
						src = i;
					}
					if((graph.getVertNames()[i]).equals(entryValues[1])) {
						dest = i;
					}
						
				}
				GraphPanel.addEdge(src, dest, Integer.parseInt(entryValues[2]));
				graph.addEdge( src, dest, Integer.parseInt(entryValues[2]));
        		
			}
		};
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphPainter NewGraphPanel = new  GraphPainter();
				  System.out.println("sfdsf");
		        NewGraphPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		        NewGraphPanel.setBackground(Color.WHITE);
				
				
				
			}
		};
		ActionListener algorithm = new ActionListener() {
			
        	public void actionPerformed(ActionEvent e) {
        		
        			nextButton.setEnabled(false);
        			//runAlg.setEnabled(false);
        			String msg = (String)cb.getSelectedItem();
        			switch(msg) {
        			case "Результат" :Boruvka boruvka = new Boruvka();boruvka.boruvkaMST(graph, GraphPanel, false); break;
        			case "Визуализация" : nextButton.setEnabled(true);break;
        				//runAlg.boruvkaMST(graph, GraphPanel);
        			}
        	}
		};
		i = 0;
		ActionListener vizualize = new ActionListener() {
			
        	public void actionPerformed(ActionEvent e) {
        		//GraphPanel.fillEdge(sorted[i].getSrc(), sorted[i].getDest(), sorted[i].getWeight());
        		//i++;
        		Boruvka boruvka = new Boruvka();
        		Edge[] sorted = new Edge[100];
        		sorted = boruvka.boruvkaMST(graph, GraphPanel, true);
        		//for(Edge r : sorted) {
        		//	System.out.println(r.getSrc()+","+ r.getDest() + "," + r.getWeight());
        		//}
        		GraphPanel.fillEdge(sorted[i].getSrc(), sorted[i].getDest(), sorted[i].getWeight());
        		i++;
        			
        	}
		};
		MouseListener ml = new MouseListener() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		x = e.getX();
        		y = e.getY();
        		
        		System.out.println(e.getX() + "," + e.getY());
       		
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
        
        button1.addActionListener(actionListener1);
		button2.addActionListener(actionListener2);
		button3.addActionListener(actionListener3);
		button.addActionListener(actionListener);
		nextButton.addActionListener(vizualize);
		cb.addActionListener(algorithm);
		
        contents.add(button1);
    	contents.add(button2);
    	contents.add(button3);
    	contents.add(cb);
        text.add(textField);
        text.add(button);
        text.add(nextButton);
        //text.add(runAlg);
        
        add(text, BorderLayout.SOUTH);
        add(contents, BorderLayout.NORTH);
        add( GraphPanel, BorderLayout.CENTER);
        addMouseListener(ml);
        
        pack();
        setSize(800, 600);
        setVisible(true);
        
	}
}
