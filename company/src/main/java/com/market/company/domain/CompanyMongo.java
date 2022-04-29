/*
 * package com.market.company.domain;
 * 
 * import javax.persistence.Id;
 * 
 * import org.springframework.data.mongodb.core.mapping.Document;
 * 
 * import lombok.AllArgsConstructor; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Document("company") public class CompanyMongo {
 * 
 * // @MongoId() // @Indexed(unique = true)
 * 
 * @Id private String companyCode;
 * 
 * private String companyName;
 * 
 * private String companyCEO;
 * 
 * private Double companyTurnOver;
 * 
 * private String companyWebsite;
 * 
 * private String stockExchange;
 * 
 * public String getCompanyCode() { return companyCode; }
 * 
 * public void setCompanyCode(String companyCode) { this.companyCode =
 * companyCode; }
 * 
 * public String getCompanyName() { return companyName; }
 * 
 * public void setCompanyName(String companyName) { this.companyName =
 * companyName; }
 * 
 * public String getCompanyCEO() { return companyCEO; }
 * 
 * public void setCompanyCEO(String companyCEO) { this.companyCEO = companyCEO;
 * }
 * 
 * public Double getCompanyTurnOver() { return companyTurnOver; }
 * 
 * public void setCompanyTurnOver(Double companyTurnOver) { this.companyTurnOver
 * = companyTurnOver; }
 * 
 * public String getCompanyWebsite() { return companyWebsite; }
 * 
 * public void setCompanyWebsite(String companyWebsite) { this.companyWebsite =
 * companyWebsite; }
 * 
 * public String getStockExchange() { return stockExchange; }
 * 
 * public void setStockExchange(String stockExchange) { this.stockExchange =
 * stockExchange; }
 * 
 * }
 */