package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.xml.bind.JAXBException;

import model.Item;
import model.RSS;
import controller.RSStoJava;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RSSReaderGUI {

	private JFrame frame = new JFrame("RSS Reader");
	private RSS rss;
	
	public RSSReaderGUI() throws MalformedURLException, JAXBException {
		RSStoJava converter = new RSStoJava();
		rss = converter.unmarshal("http://feeds.bbci.co.uk/news/rss.xml");
		initComponent();
	}
	
	public void initComponent() throws MalformedURLException, JAXBException {
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel prog = new JLabel("RSS Reader");
		JTextField text = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		frame.add(prog,c);
		c.gridy = 1;
		frame.add(text,c);
		JList jlist = new JList(getItemArray());
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		/*for(Item item : items) {
			Container group = new Container();
			group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
			JLabel label = new JLabel(item.getTitle());
			JLabel label2 = new JLabel(item.getDescription());
			group.add(label);
			group.add(label2);
			jlist.add(label);
		}*/
		jlist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JScrollPane scroll = new JScrollPane(jlist);
		c.gridy = 2;
		frame.add(scroll,c);
		c.gridx = GridBagConstraints.RELATIVE;
		JTextArea textarea = new JTextArea();
		textarea.setPreferredSize(new Dimension(100,180));
		frame.add(textarea,c);
		frame.setPreferredSize(new Dimension(600,400));
		frame.setVisible(true);
	}
	
	public ListModel<String> getItemArray() {
		DefaultListModel<String> list = new DefaultListModel<String>();
		for(Item item : rss.getChannel().getItemList()) {
			list.addElement(item.getTitle());
		}
		return list;
	}
}
