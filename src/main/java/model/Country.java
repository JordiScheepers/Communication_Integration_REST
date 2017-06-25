package model;

public class Country {
	protected String nameCountry;
	protected String codeCountry;
	protected Month month;

	/**
	 * Gets the value of the nameCountry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNameCountry() {
		return nameCountry;
	}

	/**
	 * Sets the value of the nameCountry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNameCountry(String value) {
		this.nameCountry = value;
	}

	/**
	 * Gets the value of the codeCountry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodeCountry() {
		return codeCountry;
	}

	/**
	 * Sets the value of the codeCountry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodeCountry(String value) {
		this.codeCountry = value;
	}

	/**
	 * Gets the value of the month property.
	 * 
	 * @return possible object is
	 *         {@link nl.hu.fnt.gsos.wsinterface.RequestClimat.Country.Month }
	 * 
	 */
	public Month getMonth() {
		return month;
	}

	/**
	 * Sets the value of the month property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link nl.hu.fnt.gsos.wsinterface.RequestClimat.Country.Month }
	 * 
	 */
	public void setMonth(Month value) {
		this.month = value;
	}

}