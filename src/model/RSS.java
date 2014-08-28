package model;

import javax.xml.bind.annotation.*;

/**
 * 
 * @author Atit Leelasuksan
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RSS {

	private Channel channel = null;
	
	protected RSS() {
		
	}
	
	public RSS(Channel channel, String uri) {
		this.channel = channel;
	}
	
	public Channel getChannel() {
		return channel;
	}

}
