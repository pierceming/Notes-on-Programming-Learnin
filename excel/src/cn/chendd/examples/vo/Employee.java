package cn.chendd.examples.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Employee {

	private String name;
	private Date birthDate;
	private BigDecimal payment;
	private BigDecimal bonus;
	
	public Employee() {
		super();
	}

	public Employee(String name, Date birthDate, BigDecimal payment,
			BigDecimal bonus) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.payment = payment;
		this.bonus = bonus;
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
	 * @return Returns the birthDate.
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate The birthDate to set.
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return Returns the payment.
	 */
	public BigDecimal getPayment() {
		return payment;
	}

	/**
	 * @param payment The payment to set.
	 */
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	/**
	 * @return Returns the bonus.
	 */
	public BigDecimal getBonus() {
		return bonus;
	}

	/**
	 * @param bonus The bonus to set.
	 */
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	
}
