package br.com.taiff.mesadeteste.model;

import java.text.DecimalFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Janela {
	
    private Long id;
    private float mediaT1;
    private float mediaT2 ;
    private float mediaT3;
    private float mediaTA;
    
    public Janela(Long id,float mediaT1, float mediaT2, float mediaT3, float mediaTA) {
    	this.id = id;
        this.mediaT1 = mediaT1;
        this.mediaT2 = mediaT2;
        this.mediaT3 = mediaT3;
        this.mediaTA = mediaTA;
    }
    
    public Long getId() {
		return id;
	}
    
    public float getMediaT1() {
        return mediaT1;
    }

	public void setMediaT1(float mediaT1) {
        this.mediaT1 = mediaT1;
    }
	
	public float getMediaT2() {
	    return mediaT2;
	}

	public void setMediaT2(float mediaT2) {
	    this.mediaT2 = mediaT2;
	}
	
	public float getMediaT3() {
	    return mediaT3;
	}

	public void setMediaT3(float mediaT3) {
	    this.mediaT3 = mediaT3;
	}
	
	public float getMediaTA() {
	    return mediaTA;
	}

	public void setMediaTA(float mediaTA) {
	    this.mediaTA = mediaTA;
	}

    public float getMediaM() {
    	return(mediaT1 + mediaT2 + mediaT3)/3;
    }

    public float getMediaCor() {
    	 return getMediaM() + mediaTA -25;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
    }

}
