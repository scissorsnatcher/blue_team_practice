package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import simple.Boruvka;
import simple.Edge;
import simple.Graph;

public class GUI extends JFrame{

	
	private static final long serialVersionUID = 1L;
	public int x, y, i = 0;
	protected GraphPainter GraphPanel;
	protected JPanel contents;
	protected JPanel text;
	protected Graph graph;
	protected Edge[] sorted;
	private boolean flag = false;
	private int vert;
	private String s;
	
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
        text.setBackground(Color.DARK_GRAY);
        JPanel contents = new JPanel();
        contents.setBackground(Color. LIGHT_GRAY);
    	
        JButton button1 = new JButton("Загрузить");
        //JButton save = new JButton("Сохранить");
		JButton button2 = new JButton("Добавить вершину");
		button2.setBackground(Color. ORANGE);
		button2.setForeground(Color. BLACK);
		//button2.setBackground(Color.pink);
		contents.setBackground(Color. DARK_GRAY);
		JButton button5 = new JButton("Удалить вершину");
		JButton button3 = new JButton("Соединить вершины");
		button3.setBackground(Color. LIGHT_GRAY);
		button3.setForeground(Color. BLACK);
		JButton button4 = new JButton("Сгенерировать граф");
		button4.setBackground(Color. LIGHT_GRAY);
		button4.setForeground(Color. BLACK);
		JButton button = new JButton("Очистить");
		JButton throwoff = new JButton("Сбросить");
		throwoff.setBackground(Color. LIGHT_GRAY);
		throwoff.setForeground(Color. BLACK);
		JButton nextButton = new JButton("Вперед");
		 nextButton.setBackground(Color. LIGHT_GRAY);
		 nextButton.setForeground(Color. BLACK);
		
		nextButton.setEnabled(false);

		String[] messages = {"Применить алгоритм", "Результат", "Визуализация"};
		String[] change_mes = {"Удалить", "Удалить вершину", "Удалить ребро", "Очистить полотно"};
		String[] file = {"Файл", "Загрузить", "Сохранить"};
		
		JPanel myPanel = new JPanel();
	    JTextField field1 = new JTextField(5);
	    JTextField field2 = new JTextField(5);
	    JTextField field3 = new JTextField(5);
	    JLabel label1 = new JLabel("Соединить вершину"); 
	    JLabel label2 = new JLabel("с вершиной"); 
	    JLabel label3 = new JLabel("ребром весом"); 
	    myPanel.add(label1);
	    myPanel.add(field1);
	    myPanel.add(label2);
	    myPanel.add(field2);
	    myPanel.add(label3);
	    myPanel.add(field3);
	    
	    JPanel myPanel1 = new JPanel();
	    JTextField field4 = new JTextField(5);
	    JTextField field5 = new JTextField(5);
	    JLabel label4 = new JLabel("Сгенерировать случайный граф с входным параметрами: количество вершин -"); 
	    JLabel label5 = new JLabel("; количество ребер -");
	    myPanel1.add(label4);
	    myPanel1.add(field4);
	    myPanel1.add(label5);
	    myPanel1.add(field5);
	   
	    
	       
		
		JComboBox<String> cb = new JComboBox<String>(messages);
		cb.setBackground(Color. PINK);
		cb.setForeground(Color. BLACK);
		JComboBox<String> cb1 = new JComboBox<String>(change_mes);
		cb1.setBackground(Color. GRAY);
		cb1.setForeground(Color. BLACK);
		JComboBox<String> cb2 = new JComboBox<String>(file);
		cb2.setBackground(Color. GRAY);
		cb2.setForeground(Color. BLACK);
        cb.setSelectedIndex(0);
        cb1.setSelectedIndex(0);
        cb2.setSelectedIndex(0);
  
        
        JTextField textField = new JTextField();
        textField.setColumns(40);
      
        ActionListener sbr = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				i = 0;
				for(Edge e1: graph.getEdges()) {
					GraphPanel.fillEdge(e1.getSrc(), e1.getDest(), e1.getWeight(), Color.BLACK, e1.arc);
				}
				textField.setText(" ");
				
			}
		};
        ActionListener actionListener5 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String t = textField.getText();
				String[] entryValues = t.split(" ");
				GraphPanel.removeNode();
				graph.deleteNode();
				
			}
		};
		
        ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String t = textField.getText();
				graph.readFromFile(t);
				GraphPanel.GraphPaint(graph);
				System.out.println("sdfsf" + graph.getEdgeNum());
			}
		};

		ActionListener actionListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				
				//int s = JOptionPane.showConfirmDialog(null, "Do you like bacon?");
				s = JOptionPane.showInputDialog(null,"Введите название вершины: ");
				System.out.println(s);
				//textField.setText("Введите название вершины: ");
				flag = true;
			}
		};
		
		ActionListener actionListener3 = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, myPanel);
				String t1 = field1.getText();
				String t2 = field2.getText();
				String t3 = field3.getText();
				
				String t = t1 + " " + t2 + " " + t3;
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
				field1.setText("");
				field2.setText("");
				field3.setText("");
        		
			}
		};
		ActionListener actionListener4 = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, myPanel1);
				String t4 = field4.getText();
				String t5 = field5.getText();
					
				String t = t4 + " " + t5;
				String[] entryValues = t.split(" ");
				int vertNum = Integer.parseInt(entryValues[0]);
				int edgeNum = Integer.parseInt(entryValues[1]);
				graph.clear();
				graph.generateGraph(vertNum,edgeNum);
				GraphPanel.GraphPaint(graph);
				
        		
			}
		};
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				i = 0;
				GraphPanel.clearGraph();
				graph.clear();
				
			}
		};
		ActionListener algorithm = new ActionListener() {
			
        	public void actionPerformed(ActionEvent e) {
        		
        			nextButton.setEnabled(false);
        			//runAlg.setEnabled(false);
        			String msg = (String)cb.getSelectedItem();
        			switch(msg) {
        			case "Результат" :Boruvka boruvka = new Boruvka();boruvka.boruvkaMST(graph, GraphPanel, false, textField); break;
        			case "Визуализация" : nextButton.setEnabled(true);break;
        				//runAlg.boruvkaMST(graph, GraphPanel);
        			}
        	}
		};
		ActionListener saveGr = new ActionListener() {
			
        	public void actionPerformed(ActionEvent e) {
        		
        			
        			String msg = (String)cb2.getSelectedItem();
        			switch(msg) {
        			case "Загрузить" :
        				String t = textField.getText();
        				graph.readFromFile(t);
        				GraphPanel.GraphPaint(graph);
        				break;
        			case "Сохранить" : 
        				String t1 = textField.getText();
        				try{
        					graph.saveGraph(t1);
        				}catch (IOException e1) {
        		            e1.printStackTrace();
        				}
        				break;
        				//runAlg.boruvkaMST(graph, GraphPanel);
        			}
        	}
		};
		ActionListener change = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String msg = (String)cb1.getSelectedItem();
				switch(msg) {
    			case "Удалить вершину":
    				int v = graph.getVertNum() - 1;
    				GraphPanel.removeNode();
    				graph.deleteNode();
    				int length = graph.getEdgeNum();
    				
    				for (int i = 0; i < length; i ++) {
    					if( graph.getEdges()[i] != null) {
    						if ( (graph.getEdges()[i].getSrc() == v) || (graph.getEdges()[i].getDest() == v)) {
    							GraphPanel.removeEdge(graph.getEdges()[i].getSrc(), graph.getEdges()[i].getDest(), graph.getEdges()[i].getWeight(), Color.WHITE);
								graph.deleteEdgeNum(i);	
								
    						}
    					}
    						
    
    				}
    				for (int i = 0; i < length; i++) {
    					if (graph.getEdges()[i] == null)
    						graph.deleteEdge_();
    				}
    				
    				break;
    			case "Удалить ребро" :
    				GraphPanel.removeEdge();
    				graph.deleteEdge();
    				break;
    			case "Очистить полотно" :
    				i = 0;
    				GraphPanel.clearGraph();
    				graph.clear();
    				break;
    				
    				
    				
    			}
				
				
			}
				
		};
		ActionListener vizualize = new ActionListener() {
			
        	public void actionPerformed(ActionEvent e) {
        
        		Boruvka boruvka = new Boruvka();
        		ArrayList<Edge> sorted = new ArrayList<Edge>();
        		sorted = boruvka.boruvkaMST(graph, GraphPanel, true, textField);
        
        		if(i > sorted.size() - 1) i = 0;
        		else{GraphPanel.fillEdge(sorted.get(i).getSrc(), sorted.get(i).getDest(), sorted.get(i).getWeight(), Color.RED, sorted.get(i).arc);i++;
        		textField.setText("Edge (" + graph.getVertNames()[sorted.get(i-1).getSrc()] + ", " + graph.getVertNames()[sorted.get(i-1).getDest()] +") added to the MST"); 
        		}System.out.println(i + ", " +  sorted.size());
        		if(i == sorted.size()) {textField.setText("Final weight of MST: " + boruvka.MSTweight); nextButton.setEnabled(false);};
        	}
		};
		
		MouseAdapter ml = new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		x = e.getX();
        		y = e.getY();
        		if (flag) {
        			String[] t = s.split(" ");
        			String str = t[0];
        			for (int i = 0; i < graph.getVertNum(); i++) {
        				if (graph.getVertNames()[i].equals(str)) {flag = false; return;};
        			}
        			System.out.println(e.getX() + "," + e.getY());
        			GraphPanel.addNode(str.toString(), x, y);
        			graph.addNode(str.toString(), x, y);
			
        		}
        	}

			@Override
			public void mouseReleased(MouseEvent e) {
				GraphPanel.setDragging(false);
				//GraphPanel.isSelecting(e.getX(), e.getY());
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (GraphPanel.isSelecting(e.getX(), e.getY())) {
					GraphPanel.setDragging(true);
				}
				
			}
		};
		
		 MouseMotionAdapter drag = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (GraphPanel.isDragging()) {
					GraphPanel.setCoordinates(e.getX(), e.getY());
				}
			}
		 };
		 
		JLabel author = new JLabel("Boruvka's algorithm vizualizer designed by Ivchenko A., 2020");
		author.setForeground(Color.WHITE);
		author.setBounds(440, 513, 400, 15);
		add(author);
		
        	button1.addActionListener(actionListener1);
		button2.addActionListener(actionListener2);
		button3.addActionListener(actionListener3);
		button4.addActionListener(actionListener4);
		button5.addActionListener(actionListener5);
		button.addActionListener(actionListener);
		throwoff.addActionListener(sbr);
		nextButton.addActionListener(vizualize);
		cb.addActionListener(algorithm);
		cb1.addActionListener(change);
		cb2.addActionListener(saveGr);
		
		
		contents.add(cb2);
        //contents.add(button1);
    	contents.add(button2);
    	contents.add(button3);
    	contents.add(button4);
    	//contents.add(button5);
        contents.add(cb1);
    	//contents.add(cb);
        text.add(textField);
        text.add(throwoff);
        //text.add(button);
        text.add(cb);
        text.add(nextButton);
        //text.add(runAlg);
        
        add(text, BorderLayout.SOUTH);
        add(contents, BorderLayout.NORTH);
        add( GraphPanel, BorderLayout.CENTER);
        GraphPanel.addMouseListener(ml);
        GraphPanel.addMouseMotionListener(drag);
        
        pack();
        setSize(800, 600);
        setVisible(true);
        
	}
}
