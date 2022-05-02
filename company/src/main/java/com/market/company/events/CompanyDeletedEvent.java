package com.market.company.events;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CompanyDeletedEvent {

	private Long id;
	private String companyCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public CompanyDeletedEvent(Long id, String companyCode) {
		super();
		this.id = id;
		this.companyCode = companyCode;
	}

	public CompanyDeletedEvent() {
	}
}
