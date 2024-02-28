package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Client implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String identifyCode;
	private String number;
	private String endereco;
	private String email;
	private Date date;

	private City city;
	
	public Client() {
	}
	

	public Client(Integer id, String name, String identifyCode, String number, String endereco, String email,
			Date date, City city) {
		this.id = id;
		this.name = name;
		this.identifyCode = identifyCode;
		this.number = number;
		this.endereco = endereco;
		this.setEmail(email);
		this.date = date;
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
