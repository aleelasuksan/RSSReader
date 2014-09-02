package view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.xml.bind.JAXBException;
import model.Item;
import model.RSS;
import controller.RSStoJava;
import javax.swing.JFrame;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

/**
 * A GUI class for RSS Reader.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class RSSReaderGUI {

	/** Frame of UI */
	private JFrame frame = new JFrame("RSS Reader");
	/** current RSS content */
	private RSS rss;
	/** pane to display detail of rss's item */
	private JEditorPane pane = new JEditorPane();
	/** label to describe channel's information */
	private JLabel channelLabel;
	/** RSS Reader label */
	private JLabel prog;
	/** text field to get input from user */
	private JTextField text;
	/** button to fetch url from text field */
	private JButton fetchButton;
	/** container to pack prog, text and fetchButton together with FlowLayout */
	private Container titleContainer;
	/** container to pack channel label with FlowLayout */
	private Container channelContainer;
	/** list to carry items that fetched from RSS */
	private JList feedList;
	/** scroll pane for list */
	private JScrollPane listScroller;
	/** scroll pane for editor pane */
	private JScrollPane paneScroller;
	/** container to pack listScroller and paneScroller together with FlowLayout */
	private Container contentContainer;
	
	/**
	 * A Constructor to create UI.
	 * @throws MalformedURLException
	 * @throws JAXBException
	 */
	public RSSReaderGUI() throws MalformedURLException, JAXBException {
		pane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		pane.setEditable(false);
		initComponent();
	}
	
	/**
	 * initialize component of UI.
	 * @throws MalformedURLException
	 * @throws JAXBException
	 */
	public void initComponent() throws MalformedURLException, JAXBException {
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		channelLabel = new JLabel("Channel Title: Description");
		prog = new JLabel("RSS Reader");
		text = new JTextField(50);
		text.setText("http://feeds.bbci.co.uk/news/rss.xml");
		fetchButton = new JButton("Fetch");
		fetchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text.getText()!=null)
					unmarshal(text.getText());
			}
		});
		text.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					fetchButton.doClick();
				}
			}
		});
		titleContainer = new Container();
		titleContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		titleContainer.add(prog);
		titleContainer.add(text);
		titleContainer.add(fetchButton);
		channelContainer = new Container();
		channelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		channelContainer.add(channelLabel);
		frame.add(titleContainer);
		frame.add(channelContainer);
		feedList = new JList();
		feedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		feedList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					updateItemDetail(((JList)e.getSource()).getSelectedIndex());
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
		});
		listScroller = new JScrollPane(feedList);
		frame.add(listScroller);
		listScroller.setPreferredSize(new Dimension(500,400));
		paneScroller = new JScrollPane(pane);
		paneScroller.setPreferredSize(new Dimension(500,400));
		contentContainer = new Container();
		contentContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		contentContainer.add(listScroller);
		contentContainer.add(paneScroller);
		makePaneClickable();
		frame.add(contentContainer);
		frame.setSize(1024,600);
		frame.setPreferredSize(new Dimension(1024,600));
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Convert items's data to list model so it can use with JList
	 * @return ListModel<String> of each item's title.
	 */
	public ListModel<String> getItemArray() {
		DefaultListModel<String> list = new DefaultListModel<String>();
		for(Item item : rss.getChannel().getItemList()) {
			list.addElement(item.getTitle());
		}
		return list;
	}
	
	/**
	 * Update item's detail to pane.
	 * @param index of item for update to pane.
	 * @throws IOException
	 */
	public void updateItemDetail(int index) throws IOException {
		List<Item> items = rss.getChannel().getItemList();
		Item item = items.get(index);
		//pane.setPage(new URL(item.getLink()));
		pane.setText(String.format("<FONT size =\"30\"><B><U>%s</B></U></FONT><br><br>Publish Date: %s<br><br>%s<br><br><HTML><FONT size=\"16\" color=\"#000099\"><U>Click to visit website.</U></FONT></HTML>", item.getTitle(), item.getPubDate(), item.getDescription()));
	}
	
	/**
	 * Open Link in Browser.
	 * @param index of item to open a link.
	 */
	public void openURL(int index) {
		if(index>-1) {
			if(Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI(rss.getChannel().getItemList().get(index).getLink()));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void makePaneClickable() {
		pane.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pane.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				openURL(feedList.getSelectedIndex());
			}
		});
	}
	/**
	 * unmarshal RSS to Java.
	 * @param url to unmarshal.
	 */
	public void unmarshal(String url) {
		RSStoJava converter = new RSStoJava();
		try {
			rss = converter.unmarshal(url);
			feedList.setModel(getItemArray());
			listScroller.updateUI();
			updateChannelLabel();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * update channel label.
	 */
	public void updateChannelLabel() {
		channelLabel.setText(rss.getChannel().getTitle()+": "+rss.getChannel().getDescription());
	}
}
