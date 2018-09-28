package eval.id32;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class tableGUI extends JFrame{
	JTable table;
	public tableGUI(Object[][] data, Object[] s){
		this.setSize(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		table = new JTable(data, s);
		table.setPreferredScrollableViewportSize(new Dimension(200, 100));
		table.setFillsViewportHeight(true);
		
		JScrollPane p3 = new JScrollPane(table);
		
		this.add(p3, BorderLayout.CENTER);
	}

}
