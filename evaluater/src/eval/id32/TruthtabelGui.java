package eval.id32;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTable;

public class TruthtabelGui {

	private JFrame frame;
	private JTable table;
	int get = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TruthtabelGui window = new TruthtabelGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TruthtabelGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel p = new JPanel();
		Box box = Box.createHorizontalBox();
		frame = new JFrame();
		frame.setSize(500,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton b = new JButton("Evaluate");
		JButton b2 = new JButton("Eqaulity");
		JButton b3 = new JButton("Taut\\Contr");
		JTextField text = new JTextField();
		JButton button = new JButton("Table");
		JPanel p2 = new JPanel();
		Box x = Box.createVerticalBox();
		JLabel l = new JLabel();
		
		x.add(text);
		x.add(button);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				x.add(l);
				p2.add(x);
					
				frame.add(p2 , BorderLayout.WEST);
				frame.revalidate();

			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2.add(x);
				frame.add(p2 , BorderLayout.WEST);
				frame.revalidate();

			}
		});
	
	
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				CallTable.getInstance().setTable(text.getText());
				Expression t = CallTable.getInstance().getTables().get(get);
				Hashtable<String, Boolean> d = t.getTruthTable();
				Set<String> keys = d.keySet();
				Object[][] data = new Object[(int) Math.pow(t.getVariables().size()+10, 2.0)][keys.size()] ;
				int j = 0;
				for(String s : keys){
					for(int i = 0 ; i < s.length(); i++ ){
						data[j][i] = s.charAt(i);
						
					}
					j++;
				}
				int index =  t.getVariables().size();
				j= 0;
				for(String s : keys){
					Boolean b = d.get(s);
					int v ;
					if(b){
						v =1;
					} else {
						v = 0;
					}
					data[j][index] = v;
					j++;
				}
				
				Object[] s = new Object[t.getVariables().size()+1];
				int i = 0;
				for(Object k : t.getVariables()){
					s[i] = k;
					i++;
				}

				s[i] = "Value";
				 new tableGUI(data, s);
				 if(CallTable.getInstance().testForTaut(get)){
					 l.setText("It's a tautology");
				 }else if(CallTable.getInstance().testForContr(get)){
					 l.setText("It's a Contradiction");
				 } else {
					 l.setText("It's not a tautology nor a Contradiction");

				 }
					 get++;

			}
			
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button2 = new JButton("check");
				JTextField text3 = new JTextField();
				 JLabel label = new JLabel();

				JPanel pe = new JPanel();
				JTextField text2 = new JTextField();
				Box s1 = Box.createVerticalBox();
				s1.add(text3);
				s1.add(text2);
				s1.add(button2);
				s1.add(label);
				pe.add(s1);
				frame.add(pe , BorderLayout.WEST);
				frame.revalidate();
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						CallTable.getInstance().setTable(text3.getText());
						CallTable.getInstance().setTable(text2.getText());

						Expression t = CallTable.getInstance().getTables().get(get);
						get++;
						Expression t2 = CallTable.getInstance().getTables().get(get);

						Hashtable<String, Boolean> d = t.getTruthTable();
						Hashtable<String, Boolean> d2 = t2.getTruthTable();

						Set<String> keys = d.keySet();
						Set<String> keys2 = d2.keySet();

						Object[][] data = new Object[(int) Math.pow(t.getVariables().size(), 2.0)][keys.size()] ;
						Object[][] data2 = new Object[(int) Math.pow(t2.getVariables().size(), 2.0)][keys2.size()] ;

						int j = 0;
						for(String s : keys){
							for(int i = 0 ; i < s.length(); i++ ){
								data[j][i] = s.charAt(i);
								
							}
							j++;
						}
						
						int j2 = 0;
						for(String s : keys2){
							for(int i = 0 ; i < s.length(); i++ ){
								data2[j2][i] = s.charAt(i);
								
							}
							j2++;
						}
						
						int index =  t.getVariables().size();
						j= 0;
						for(String s : keys){
							Boolean b = d.get(s);
							int v ;
							if(b){
								v =1;
							} else {
								v = 0;
							}
							data[j][index] = v;
							j++;
						}
						int index2 =  t2.getVariables().size();
						j2= 0;
						for(String s : keys2){
							Boolean b = d2.get(s);
							int v ;
							if(b){
								v =1;
							} else {
								v = 0;
							}
							data2[j2][index2] = v;
							j2++;
						}
						
						Object[] s = new Object[t.getVariables().size()+1];
						int i = 0;
						for(Object k : t.getVariables()){
							s[i] = k;
							i++;
						}
						
						Object[] s2 = new Object[t2.getVariables().size()+1];
						int i2 = 0;
						for(Object k : t2.getVariables()){
							s2[i2] = k;
							i2++;
						}

						s[i] = "Value";
						s2[i2] = "Value";

						 new tableGUI(data, s);
						 new tableGUI(data2, s2);
						 
						 if(CallTable.getInstance().checkEquality(get-1, get)){
							 label.setText("They're equal");
						 } else {
							 label.setText("They're not equal");
						 }

					/*	table = new JTable(data, s);
						table.setPreferredScrollableViewportSize(new Dimension(200, 100));
						table.setFillsViewportHeight(true);
						
						JScrollPane p3 = new JScrollPane(table);
						
						frame.add(p3, BorderLayout.CENTER);
						frame.revalidate();*/
					}
					
				});

			}
		});
		box.add(b);
		box.add(b3);
		box.add(b2);
		p.add(box);
		frame.add(p, BorderLayout.NORTH);
		frame.setVisible(true);
		
	}
}
