package model;

import java.util.List;
import javax.xml.bind.annotation.*;
/**
 * 
 * @author Atit Leelasuksan
 *
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Channel {

	private String title;
	private String link;
	private String description;
	@XmlElement(name = "item")
	private List<Item> itemList = null;
	
	protected Channel() {
		title = "";
		link = "";
		description = "";
	}
	
	public Channel(List<Item> listItem) {
		this.itemList = listItem;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
}
