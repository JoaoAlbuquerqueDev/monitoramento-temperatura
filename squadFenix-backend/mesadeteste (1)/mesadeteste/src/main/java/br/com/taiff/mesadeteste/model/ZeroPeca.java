package br.com.taiff.mesadeteste.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ZeroPeca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@OneToOne(cascade = {CascadeType.DETACH})
	@JoinColumn(name = "TESTE_ID")
	private Teste teste;

	@Column(nullable = false)
	private int x;
	@Column(nullable = false)
	private int y;
	@Column(nullable = false)
	private int z;
	@Column(nullable = false)
	private int r;







	public ZeroPeca( int x, int y, int z, int r)  {
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;

	}

	 @Deprecated
	public ZeroPeca() {
	}

	public Long getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getR() {
		return r;
	}


}
