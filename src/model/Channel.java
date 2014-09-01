package model;

import java.util.List;
import javax.xml.bind.annotation.*;
/**
 * A Channel Element for RSS
 * Consist of Title, link and description for this channel.
 * @author Atit Leelasuksan 5510546221
 *
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Channel {

	/** title of this channel */
	private String title;
	/** link of this channel */
	private String link;
	/** description of this channel */
	private String description;
	/** item list of this channel */
	@XmlElement(name = "item")
	private List<Item> itemList = null;
	
	/**
	 * Default Constructor to work with JAXB.
	 */
	protected Channel() {
		title = "";
		link = "";
		description = "";
	}
	
	/**
	 * Retrieve channel's title
	 * @return channel's title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Retrieve channel's link
	 * @return channel's link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * Retrieve channel's description
	 * @return channel's description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Retrieve channel's items
	 * @return channel's items
	 */
	public List<Item> getItemList() {
		return itemList;
	}
}
