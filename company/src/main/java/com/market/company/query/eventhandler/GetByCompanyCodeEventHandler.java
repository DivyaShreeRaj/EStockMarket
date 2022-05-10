package com.market.company.query.eventhandler;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.company.domain.Company;
import com.market.company.domain.CompanyDocument;
import com.market.company.domain.StockDocument;
import com.market.company.exception.CompanyNotFoundException;
import com.market.company.query.GetByCompanyCodeQuery;
import com.market.company.repository.CompanyMongoRepository;
import com.market.company.repository.CompanyRepository;
import com.market.company.repository.StockMongoRepository;
import com.market.company.repository.StockRepository;
import com.market.company.response.CompanyInfoResponse;

@Component
public class GetByCompanyCodeEventHandler {

	private final Logger log = LoggerFactory.getLogger(GetByCompanyCodeEventHandler.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private CompanyMongoRepository companyMongoRepository;

	@Autowired
	private StockMongoRepository stockMongoRepository;

	/**
	 * getCompanyDetailsV2 - Get details of provided company code from MongoDB Atlas
	 * 
	 * @param getByCompanyCodeQuery getByCompanyCodeQuery
	 * @return CompanyInfoResponse
	 */
	@QueryHandler
	public CompanyInfoResponse getCompanyDetailsV2(GetByCompanyCodeQuery getByCompanyCodeQuery) {

		log.debug("Inside getCompanyDetails() of CompanyInfoServiceImpl");

		CompanyInfoResponse companyInfoResponse = new CompanyInfoResponse();

		CompanyDocument company = companyMongoRepository.findByCompanyCode(getByCompanyCodeQuery.getCompanyCode());
		if (company == null) {
			log.error("Company details not found");
			return companyInfoResponse;
			// throw new CompanyNotFoundException();
		}

		companyInfoResponse.setCompanyCode(company.getCompanyCode());
		companyInfoResponse.setCompanyName(company.getCompanyName());
		companyInfoResponse.setCompanyCEO(company.getCompanyCEO());
		companyInfoResponse.setCompanyTurnOver(company.getCompanyTurnOver());
		companyInfoResponse.setCompanyWebsite(company.getCompanyWebsite());
		companyInfoResponse.setStockExchange(company.getStockExchange());

		List<StockDocument> stockPriceList = stockMongoRepository
				.getLatestStockPrice(getByCompanyCodeQuery.getCompanyCode());

		if (stockPriceList != null && !stockPriceList.isEmpty()) {
			companyInfoResponse.setLatestStockPrice(stockPriceList.get(0).getStockPrice());
		}

		return companyInfoResponse;
	}

	/**
	 * getCompanyDetails - not in use, this will read company details from mysql
	 * 
	 * @param getByCompanyCodeQuery getByCompanyCodeQuery
	 * @return CompanyInfoResponse
	 */
	// @QueryHandler
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
