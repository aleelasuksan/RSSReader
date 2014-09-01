package model;

import javax.xml.bind.annotation.*;

/**
 * A RSS element for RSS
 * @author Atit Leelasuksan 5510546221
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RSS {

	/** channel of this RSS */
	private Channel channel = null;
	
	/**
	 * Default Constructor to work with JAXB
	 */
	protected RSS() {
		
	}
	
	/**
	 * Retrieve RSS's channel
	 * @return RSS's channel
	 */
	public Channel getChannel() {
		return channel;
	}

}
