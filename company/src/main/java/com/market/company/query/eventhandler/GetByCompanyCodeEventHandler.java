package com.market.company.query.eventhandler;

import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.company.domain.Company;
import com.market.company.exception.CompanyNotFoundException;
import com.market.company.query.GetByCompanyCodeQuery;
import com.market.company.repository.CompanyRepository;
import com.market.company.repository.StockRepository;
import com.market.company.response.CompanyInfoResponse;
import com.market.company.service.CompanyInfoServiceImpl;

@Component
public class GetByCompanyCodeEventHandler {

	private final Logger log = LoggerFactory.getLogger(CompanyInfoServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StockRepository stockRepository;

	@QueryHandler
	public CompanyInfoResponse getCompanyDetails(GetByCompanyCodeQuery getByCompanyCodeQuery) {

		log.debug("Inside getCompanyDetails() of CompanyInfoServiceImpl");

		CompanyInfoResponse companyInfoResponse = new CompanyInfoResponse();

		Company company = companyRepository.findByCompanyCode(getByCompanyCodeQuery.getCompanyCode());
		if (company == null) {
			throw new CompanyNotFoundException();
		}

		companyInfoResponse.setCompanyCode(company.getCompanyCode());
		companyInfoResponse.setCompanyName(company.getCompanyName());
		companyInfoResponse.setCompanyCEO(company.getCompanyCEO());
		companyInfoResponse.setCompanyTurnOver(company.getCompanyTurnOver());
		companyInfoResponse.setCompanyWebsite(company.getCompanyWebsite());
		companyInfoResponse.setStockExchange(company.getStockExchange());

		companyInfoResponse
				.setLatestStockPrice(stockRepository.getLatestStockPrice(getByCompanyCodeQuery.getCompanyCode()));

		return companyInfoResponse;
	}

}
