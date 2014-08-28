package controller;

import java.net.MalformedURLException;
import java.util.List;

import javax.xml.bind.JAXBException;

import model.Item;
import model.RSS;

public class main {

	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, JAXBException {
		RSStoJava converter = new RSStoJava();
		RSS rss = converter.unmarshal("http://feeds.bbci.co.uk/news/rss.xml");
		System.out.printf("Channel Title: %s\nDescription: %s\nLink: %s\n", rss.getChannel().getTitle(), rss.getChannel().getDescription(), rss.getChannel().getLink());
		List<Item> item = rss.getChannel().getItemList();
		for(Item i: item) {
			System.out.printf("Item Title: %s\nDescription: %s\nLink: %s\nPublish Date: %s\n", i.getTitle(), i.getDescription(), i.getLink(), i.getPubDate());
		}
	}

}
