package io.spring.workshop.stockdetails;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class TradingCompany {

	@Id
	private String ticker;

	private String description;

	public TradingCompany() {
	}

	public TradingCompany(String ticker) {
		this.description = ticker;
		this.ticker = ticker;
	}

	public TradingCompany(String description, String ticker) {
		this.description = description;
		this.ticker = ticker;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TradingCompany that = (TradingCompany) o;
		return Objects.equals(ticker, that.ticker) &&
				Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ticker, description);
	}

	@Override
	public String toString() {
		return "TradingCompany{" +
				"ticker='" + ticker + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}

