package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Channel;
import model.Item;
import model.RSS;

/**
 * 
 * @author Atit Leelasuksan
 *
 */
public class RSStoJava {

	private Unmarshaller unm;
	
	public RSStoJava() {
		try {
			JAXBContext context = JAXBContext.newInstance( RSS.class, Channel.class, Item.class );
			unm = context.createUnmarshaller();
		} catch (JAXBException je) {
			je.printStackTrace();
		}
	}
	
	public RSS unmarshal(String url) throws JAXBException, MalformedURLException {
		URL uri = new URL(url);
		return (RSS)unm.unmarshal(uri);
	}
}
