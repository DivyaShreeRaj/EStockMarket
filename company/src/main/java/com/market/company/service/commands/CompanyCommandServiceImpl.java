/*
 * package com.market.company.service.commands;
 * 
 * import java.util.UUID; import java.util.concurrent.CompletableFuture;
 * 
 * import org.axonframework.commandhandling.gateway.CommandGateway; import
 * org.axonframework.eventhandling.EventHandler; import
 * org.springframework.stereotype.Service;
 * 
 * import com.market.company.commands.DeleteCompanyCommand; import
 * com.market.company.commands.RegisterCompanyCommand; import
 * com.market.company.events.CompanyRegisteredEvent;
 * 
 * @Service public class CompanyCommandServiceImpl implements
 * CompanyCommandService {
 * 
 * private final CommandGateway commandGateway;
 * 
 * public CompanyCommandServiceImpl(CommandGateway commandGateway) {
 * this.commandGateway = commandGateway; }
 * 
 * @EventHandler
 * 
 * @Override public CompletableFuture<String>
 * registerCompany(CompanyRegisteredEvent companyRegisteredEvent) { return
 * commandGateway.send(new RegisterCompanyCommand(UUID.randomUUID().toString(),
 * companyRegisteredEvent.getCompanyCode(),
 * companyRegisteredEvent.getCompanyName(),
 * companyRegisteredEvent.getCompanyCEO(),
 * companyRegisteredEvent.getCompanyTurnOver(),
 * companyRegisteredEvent.getCompanyWebsite(),
 * companyRegisteredEvent.getStockExchange())); }
 * 
 * @EventHandler
 * 
 * @Override public CompletableFuture<String> deleteCompanyDetails(String
 * companyCode) { return commandGateway.send(new
 * DeleteCompanyCommand(UUID.randomUUID().toString(), companyCode)); }
 * 
 * }
 */