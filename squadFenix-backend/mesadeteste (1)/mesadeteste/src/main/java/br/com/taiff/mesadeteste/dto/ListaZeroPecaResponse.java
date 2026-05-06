package br.com.taiff.mesadeteste.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.taiff.mesadeteste.model.ZeroPeca;

public class ListaZeroPecaResponse {
	
	private Long id;
	
	private int x;
	private int y;
	private int z;
	private int r;
	
	public ListaZeroPecaResponse(ZeroPeca zeroPeca) {
		id = zeroPeca.getId();
		x = zeroPeca.getX();
		y = zeroPeca.getY();
		z = zeroPeca.getZ();
		r = zeroPeca.getR();
	};
	
	 public static List<ListaZeroPecaResponse> toModel(List<ZeroPeca> zeroPeca) {

	        return zeroPeca.stream().map(ListaZeroPecaResponse::new).collect(Collectors.toList());
	    };

	public Long getId() {
		return id;
	};

	public int getX() {
		return x;
	};

	public int getY() {
		return y;
	};

	public int getZ() {
		return z;
	};

	public int getR() {
		return r;
	};
	    
	    

	
	

}
