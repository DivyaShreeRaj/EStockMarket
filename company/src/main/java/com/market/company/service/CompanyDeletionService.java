package com.market.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.market.company.controller.CompanyDeletionController;
import com.market.company.exception.CustomRuntimeException;
import com.market.company.repository.CompanyRepository;

@Service
public class CompanyDeletionService {

	@Autowired
	private CompanyRepository companyRepository;

	private Logger log = LoggerFactory.getLogger(CompanyDeletionService.class);

	public ResponseEntity<String> deleteCompanyDetails(String companyCode) {

		log.info("Inside CompanyDeletionService service with company code: {}", companyCode);

		try {
			companyRepository.deleteByCompanyCode(companyCode);
		} catch (Exception e) {
			throw new CustomRuntimeException("Something went wrong while trying to delete a company", e);
		}

		log.info(" Deletion completed for company with company code: {}", companyCode);
		return new ResponseEntity<String>("Company details deleted successfully", HttpStatus.OK);
	}
}
