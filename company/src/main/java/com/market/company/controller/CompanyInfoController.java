
package com.market.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.company.domain.Company;
import com.market.company.service.CompanyInfoService;

@RestController
@RequestMapping(value = "/api/v1.0/market/company/")
public class CompanyInfoController {

	@Autowired
	private CompanyInfoService companyService;

	@GetMapping(value = "info/{companyCode}")
	public @ResponseBody Company getCompanyDetails(@PathVariable String companyCode) {
		return companyService.getCompanyDetails(companyCode);
	}

	@GetMapping(value = "getall")
	public @ResponseBody List<Company> getallCompanyDetails() {
		return companyService.getallCompanyDetails();
	}

}
