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
import simple.Node;

public class GUI extends JFrame{

	
	private static final long serialVersionUID = 1L;
	public int i = 0;
	protected GraphPainter GraphPanel;
	protected JPanel contents;
	protected JPanel text;
	protected Graph graph;
	protected Edge[] sorted;
	private boolean flag = false;
	//private int vert;
	private String s;
	private boolean deleteVert, del_edge, change_w;
	private Node vert_for_delete;
	
	
	boolean f1 = false;
	ArrayList<Edge> sorted1 = new ArrayList<Edge>();//вместо sorted внутри ждущего объекта для обычного алгоритма и алгоритма по шагам
	int weight_mst = 0;
	
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
		
		JButton backButton = new JButton(" Назад ");
		backButton.setBackground(Color. LIGHT_GRAY);
		backButton.setForeground(Color. BLACK);
		
		JButton nextButton = new JButton("Вперед");
		 nextButton.setBackground(Color. LIGHT_GRAY);
		 nextButton.setForeground(Color. BLACK);
		
		nextButton.setEnabled(false);
		backButton.setEnabled(false);

		String[] messages = {"Применить алгоритм", "Результат", "Визуализация"};
		String[] change_mes = {"Изменить", "Удалить вершину", "Удалить ребро", "Изменить вес ребра", "Очистить панель"};
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
        textField.setColumns(30);
      
        ActionListener sbr = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				i = 0;
				for(int j = 0; j < graph.getEdges().length; j++) {
				//for(Edge e1: graph.getEdges()) {
					if(graph.getEdges()[j] != null) {
					if (graph.getEdges()[j].getSrc() != -1) {
						GraphPanel.fillEdge(graph.getEdges()[j].getSrc(), graph.getEdges()[j].getDest(),graph.getEdges()[j].getWeight(), Color.BLACK, graph.getEdges()[i].arc);
				}}
				}
				textField.setText("");
				
				
			}
		};
        ActionListener actionListener5 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String t = textField.getText();
				//String[] entryValues = t.split(" ");
				//GraphPanel.removeNode();
				//graph.deleteNode();
				
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
				deleteVert = false;
				del_edge = false;
				//int s = JOptionPane.showConfirmDialog(null, "Do you like bacon?");
				s = JOptionPane.showInputDialog(null,"Введите название вершины: ");
				System.out.println(s);
				//textField.setText("Введите название вершины: ");
				if(s == null) flag = false;
				else if (s.length() != 0) flag = true;
    			
			}
		};
		
		ActionListener actionListener3 = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, myPanel);
				String t1 = field1.getText();
				String t2 = field2.getText();
				String t3 = field3.getText();
				if (t1 == null || t2 == null || t3 == null) return;
				if (t1.length() == 0 || t2 .length() == 0 || t3.length() == 0) return;
				String t = t1 + " " + t2 + " " + t3;
				String[] entryValues = t.split(" ");
				int src = 0;
				int dest = 0;
				
				//System.out.println(entryValues[0]);
				//System.out.println(entryValues[1]);
				System.out.println("AAAA" + graph.getVertNames().length + " " + GraphPanel.nodes.size());
				for(int i = 0; i < graph.getVertNames().length; i++) {
					if ((graph.getVertNames()[i]!= null)){
						System.out.println(graph.getVertNames()[i] + i);
						if((graph.getVertNames()[i]).equals(entryValues[0])) {
							src = i;
						}
						else if((graph.getVertNames()[i]).equals(entryValues[1])) {
							dest = i;
						}
					}
						
				}
				System.out.println(src + " " + dest);
				if (src == dest) return;
				
				graph.addEdge(src, dest, Integer.parseInt(entryValues[2]));
				GraphPanel.addEdge(src, dest, Integer.parseInt(entryValues[2]));
				
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
				
				if (t4 == null || t5 == null) return;
				if (t4.length() == 0 || t5 .length() == 0) return;
				
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
        			backButton.setEnabled(false);
        			//runAlg.setEnabled(false);
        			String msg = (String)cb.getSelectedItem();
        			switch(msg) {
        			case "Результат" :
        				if(f1 == false){Boruvka boruvka = new Boruvka();sorted1 = boruvka.boruvkaMST(graph,  textField);

        					for(int i = 0; i < sorted1.size();i++){
        						if (sorted1.get(i) != null) {
        							if (sorted1.get(i).getSrc() != -1)
        							GraphPanel.fillEdge(sorted1.get(i).getSrc(), sorted1.get(i).getDest(), sorted1.get(i).getWeight(), Color.PINK, sorted1.get(i).arc);
							}}
							break;
        				}
        				else{
        					
							for(int i = 0; i < sorted1.size();i++){
								if (sorted1.get(i) != null) {
									if (sorted1.get(i).getSrc() != -1)
										GraphPanel.fillEdge(sorted1.get(i).getSrc(), sorted1.get(i).getDest(), sorted1.get(i).getWeight(), Color.PINK, sorted1.get(i).arc);
							}}
							break;

						}
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
    				deleteVert = true;
    				change_w = false;
    				del_edge = false;
    				/*
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
    				*/
    				break;
    			case "Изменить вес ребра" :
    				change_w = true;
    				del_edge = false;
    				deleteVert = false;
    				break;
    				
    			case "Удалить ребро" :
    				//GraphPanel.removeEdge();
    				//graph.deleteEdge();
    				del_edge = true;
    				deleteVert = false;
    				change_w = false;
    				
    				break;
    			case "Очистить панель" :
    				i = 0;
    				GraphPanel.clearGraph();
    				graph.clear();
    				break;
    			case "Изменить":
    				del_edge = false;
    				deleteVert = false;
    				change_w = false;
    				
    				
    			}
				
				
			}
				
		};
		ActionListener vizualize = new ActionListener() {
			
        	public void actionPerformed(ActionEvent e) {
        
        		if(!f1) {
					Boruvka boruvka = new Boruvka();
					sorted1 = boruvka.boruvkaMST(graph, textField);
					weight_mst = boruvka.MSTweight;
					f1 = true;

				}


        		if(i > sorted1.size() - 1) i = 0;
        		else{
        			if (sorted1.get(i) != null) {
        				GraphPanel.fillEdge(sorted1.get(i).getSrc(), sorted1.get(i).getDest(), sorted1.get(i).getWeight(), Color.RED, sorted1.get(i).arc);
        				i++;
        				backButton.setEnabled(true);
        				textField.setText("Step №" + (i+1) + ": Edge (" + graph.getVertNames()[sorted1.get(i-1).getSrc()] + ", " + graph.getVertNames()[sorted1.get(i-1).getDest()] +") added to the MST");
        			}
        			System.out.println(i + ", " +  sorted1.size());
        		}
        		if(i == sorted1.size()){ textField.setText("Final weight of MST: " + weight_mst);nextButton.setEnabled(false);}
        		}
		};
		
		ActionListener vizualize_step_back = new ActionListener() {

			public void actionPerformed(ActionEvent e) {



				if(i > 0 ) {
					i--;

					if (sorted1.get(i) != null) {	
					GraphPanel.fillEdge(sorted1.get(i).getSrc(), sorted1.get(i).getDest(), sorted1.get(i).getWeight(), Color.BLACK, sorted1.get(i).arc);
					textField.setText("Step №" + (i+1) + ": Edge (" + graph.getVertNames()[sorted1.get(i).getSrc()] + ", " + graph.getVertNames()[sorted1.get(i).getDest()] +") added to the MST");
				}}
				if( i == 0){
					backButton.setEnabled(false);
				}
				if( i < sorted1.size()){
					nextButton.setEnabled(true);
				}


			}
		};
		
		MouseAdapter ml = new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(change_w) {
        			if(GraphPanel.isEdgeSelectingCheck(e.getX(), e.getY())){
        				String new_w_str = JOptionPane.showInputDialog(null,"Новый вес: ");
        				if (new_w_str == null) return;
        				if (new_w_str.length() == 0) return;
        				int new_w = Integer.parseInt(new_w_str);
        				Edge edge = GraphPanel.isEdgeSelectingChange(e.getX(), e.getY(), new_w);
        				for (int i = 0; i < graph.factEdgeNum; i++) {
        					if(graph.getEdges()[i] != null) {
        						if((graph.getEdges()[i].getSrc() == edge.getSrc())&&(graph.getEdges()[i].getDest() == edge.getDest())) {
        							graph.change_weight(i, new_w);
        						}
        					}
        				}
        			}
    			}
        		if(del_edge) {
        	
        			Edge r = GraphPanel.isEdgeSelecting(e.getX(), e.getY());
        			if (r != null) {
						
						GraphPanel.removeEdge(r);
						ArrayList<Edge> list= GraphPanel.delEdges;
						graph.reduceEdgeNum(1, list);
        			}
        			
        		}
        		if(deleteVert) {
        			GraphPanel.red = 0;
        			Node m = GraphPanel.isNodeSelecting(e.getX(), e.getY());
        			if (m != null) {
        						graph.deleteNode(m);
        						GraphPanel.removeNode(m);
        						ArrayList<Edge> list= GraphPanel.delEdges;
        						graph.reduceEdgeNum(GraphPanel.red, list);
        			}
        			
        			
        			
        			//int length = graph.getEdgeNum();
        			//GraphPanel.removeNode(vert_for_delete);
        			//graph.deleteNode(vert_for_delete);
        			
        			/*
        			for (int i = 0; i < length; i ++) {
    					if( graph.getEdges()[i] != null) {
    						if ( (graph.getVertNames()[graph.getEdges()[i].getSrc()] == vert_for_delete.name) || (graph.getVertNames()[graph.getEdges()[i].getDest()] == vert_for_delete.name) ) {
    							System.out.println(graph.getEdges()[i].getSrc() + "," + graph.getEdges()[i].getDest());
    							GraphPanel.removeEdge(graph.getEdges()[i].getSrc(), graph.getEdges()[i].getDest(), graph.getEdges()[i].getWeight());
    							graph.deleteEdgeNum(i);	
    							graph.reduceEdgeNum();
    						}
    					}
        			}
        			for (int i = 0; i < length; i ++) {
    					if( graph.getEdges()[i] != null) {
    						System.out.println("ne null")	;
								
    						}
    					else
    						System.out.println("null");

        			}
        			*/
    				//deleteVert = false;
        		}
        		if (s != null) {
        		if (s.length() != 0 ) {
        		if (flag) {
        			
        			String[] t = s.split(" ");
        			String str = t[0];
        			if (str == null) return;
    				if (str.length() == 0) return;
    				
        			for (int i = 0; i < graph.getVertNames().length; i++) {
        				if(graph.getVertNames()[i] != null) {
        				if (graph.getVertNames()[i].equals(str)) {flag = false; return;};
        			}}
        			
        			System.out.println(e.getX() + "," + e.getY());
        			GraphPanel.addNode(str.toString(),e.getX(), e.getY());
        			graph.addNode(str.toString(), e.getX(), e.getY());
        			
        			s = "";
        			flag = false;
        		}
        		}
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
		backButton.addActionListener(vizualize_step_back);
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
        text.add(backButton);
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
