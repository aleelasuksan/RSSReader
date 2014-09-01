package view;

import java.net.MalformedURLException;
import java.util.List;

import javax.xml.bind.JAXBException;

import controller.RSStoJava;

import model.Item;
import model.RSS;

public class main {

	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, JAXBException {
		RSSReaderGUI gui = new RSSReaderGUI();
	}

}
