package cn.chendd.examples.vo;

public class User {

	private String name;
	private String sex;
	private Double payment;
	
	private Integer mergerRows;
	
	public User() {
		super();
	}
	public User(String name, String sex, Double payment) {
	
		super();
		this.name = name;
		this.sex = sex;
		this.payment = payment;
	}
	
	public User(String name, String sex, Double payment , Integer mergerRows) {
		
		super();
		this.name = name;
		this.sex = sex;
		this.payment = payment;
		this.mergerRows = mergerRows;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return Returns the payment.
	 */
	public Double getPayment() {
		return payment;
	}
	/**
	 * @param payment The payment to set.
	 */
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	/**
	 * @return Returns the mergerRows.
	 */
	public Integer getMergerRows() {
		return mergerRows;
	}
	/**
	 * @param mergerRows The mergerRows to set.
	 */
	public void setMergerRows(Integer mergerRows) {
		this.mergerRows = mergerRows;
	}
	
}
