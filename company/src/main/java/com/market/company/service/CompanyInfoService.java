package com.market.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.company.domain.Company;
import com.market.company.repository.CompanyRepository;

@Service
public class CompanyInfoService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> getallCompanyDetails() {
		List<Company> CompanyDetails = new ArrayList<>();
		companyRepository.findAll().forEach(CompanyDetails::add);
		return CompanyDetails;
	}

	public Company getCompanyDetails(String companyCode) {
		return companyRepository.findByCompanyCode(companyCode);
	}

}
