package com.market.company.service.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.market.company.exception.CustomRuntimeException;
import com.market.company.repository.CompanyRepository;
import com.market.company.repository.StockRepository;

@Service
public class CompanyDeletionServiceImpl {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private StockRepository stockRepository;

	private Logger log = LoggerFactory.getLogger(CompanyDeletionServiceImpl.class);

	public ResponseEntity<String> deleteCompanyDetails(String companyCode) {

		log.info("Inside CompanyDeletionServiceImpl service with company code: {}", companyCode);

		try {
			stockRepository.deleteByCompanyCode(companyCode);
			companyRepository.deleteByCompanyCode(companyCode);
		} catch (Exception e) {
			log.error("Exception while deleting company details with code:{} DESC:{}", companyCode, e.getMessage());
			throw new CustomRuntimeException("Something went wrong while trying to delete a company", e);
		}

		log.info(" Deletion completed for company with company code: {}", companyCode);
		return new ResponseEntity<String>("Company details deleted successfully", HttpStatus.OK);
	}
}
