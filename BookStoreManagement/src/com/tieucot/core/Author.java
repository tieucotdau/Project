package com.tieucot.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Author {
	private long id;
	private String name;
	private Date dob;
	private long soldNumber;// truong nay de tinh so luong ban
	private double revenue;

	public Author(long id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	public Author(ResultSet resultSet) throws SQLException{
		this.setId(resultSet.getLong("id"));
		this.setName(resultSet.getString("name"));
		this.setDob(resultSet.getDate("dob"));
	}

	public Author(long id, String name, Date dob, double revenueShare) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;

	}

	public Author() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getDobString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(dob);
	}

	public void setDobString(String dob) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	
		try {
			this.dob = sdf.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(long soldNumber) {
		this.soldNumber = soldNumber;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", dob=" + dob + ", soldNumber=" + soldNumber + ", revenue="
				+ revenue + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(revenue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (soldNumber ^ (soldNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(revenue) != Double.doubleToLongBits(other.revenue))
			return false;
		if (soldNumber != other.soldNumber)
			return false;
		return true;
	}

	public int calculateAge() {
		Date now = new Date();
		// 01-01-1970
		long nowInMilisecond = now.getTime();
		long dobMiliSecond = dob.getTime();
		long dif = nowInMilisecond - dobMiliSecond;
		long age = dif / 1000l / (60 * 60 * 24 * 365);
		return (int) age;

	}
	public static Comparator<Author> compare = new Comparator<Author>() {
		public int compare(Author a1, Author a2) {
			return (int) -(a1.getRevenue() - a2.getRevenue());
		}
	};
}