package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
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
	private JEditorPane pane = new JEditorPane();
	
	public RSSReaderGUI() throws MalformedURLException, JAXBException {
		RSStoJava converter = new RSStoJava();
		rss = converter.unmarshal("http://feeds.bbci.co.uk/news/rss.xml");
		pane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		pane.setEditable(false);
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
		JScrollPane scroll = new JScrollPane(jlist);
		c.gridy = 2;
		frame.add(scroll,c);
		c.gridx = GridBagConstraints.RELATIVE;
		pane.setPreferredSize(new Dimension(100,180));
		JScrollPane scrollForPane = new JScrollPane(pane);
		frame.add(scrollForPane,c);
		jlist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					updateTextArea(((JList)e.getSource()).getSelectedIndex());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.setSize(800,600);
		frame.setVisible(true);
	}
	
	public ListModel<String> getItemArray() {
		DefaultListModel<String> list = new DefaultListModel<String>();
		for(Item item : rss.getChannel().getItemList()) {
			list.addElement(item.getTitle());
		}
		return list;
	}
	
	public void updateTextArea(int index) throws IOException {
		List<Item> items = rss.getChannel().getItemList();
		Item item = items.get(index);
		System.out.println(item.getLink());
		URL url = new URL(item.getLink());
		pane.setText(item.getTitle()+"<br>"+item.getDescription());
	}
}
