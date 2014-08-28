package model;

import javax.xml.bind.annotation.*;
/**
 * 
 * @author Atit Leelasuksan
 *
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Item {

	private String title;
	private String link;
	private String description;
	private String pubDate;
	private String source;
	
	protected Item() {
		title = "";
		link = "";
		description = "";
		pubDate = "";
		source = "";
	}
	
	public Item(String title, String link, String description, String pubDate, String source) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
		this.source = source;
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
	
	public String getPubDate() {
		return pubDate;
	}
	
	public String getSource() {
		return source;
	}
}
