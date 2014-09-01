package model;

import javax.xml.bind.annotation.*;
/**
 * An item element for RSS.
 * Consist of title, link, description, publish date, source.
 * @author Atit Leelasuksan 5510546221
 *
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Item {

	/** title of an item */
	private String title;
	/** link of an item */
	private String link;
	/** description of an item */
	private String description;
	/** publish date of an item */
	private String pubDate;
	/** source of an item */
	private String source;
	
	/**
	 * Default Constructor to work with JAXB
	 */
	protected Item() {
		title = "";
		link = "";
		description = "";
		pubDate = "";
		source = "";
	}
	
	
	/**
	 * Retrieve item's title
	 * @return item's title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Retrieve item's link
	 * @return item's link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * Retrieve item's description
	 * @return item's description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Retrieve item's publish date
	 * @return item's publish date
	 */
	public String getPubDate() {
		return pubDate;
	}
	
	/**
	 * Retrieve item's source
	 * @return item's source
	 */
	public String getSource() {
		return source;
	}
}
