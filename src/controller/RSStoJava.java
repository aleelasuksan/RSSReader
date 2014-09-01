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
 * The Converter Class, convert RSS XML to Java data via JAXB.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class RSStoJava {

	/** Unmarshaller  */
	private Unmarshaller unm;
	
	/**
	 * A Constructor to create JAXBContext and initialize unmarshaller.
	 */
	public RSStoJava() {
		try {
			JAXBContext context = JAXBContext.newInstance( RSS.class, Channel.class, Item.class );
			unm = context.createUnmarshaller();
		} catch (JAXBException je) {
			je.printStackTrace();
		}
	}
	
	/**
	 * Unmarshall given url from RSS to Java data.
	 * @param url to unmarshal
	 * @return RSS class that contain data from url
	 * @throws JAXBException
	 * @throws MalformedURLException
	 */
	public RSS unmarshal(String url) throws JAXBException, MalformedURLException {
		URL uri = new URL(url);
		return (RSS)unm.unmarshal(uri);
	}
}
