package com.demo.bank.resources;

import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.model.Account;
import com.demo.bank.model.TransactionType;
import com.demo.bank.service.AccountService;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;

    @GET
    public Response listAccounts() {
        return Response.ok(accountService.listAll()).build();
    }

    @POST
    public Response create(final Account account) throws URISyntaxException {
        accountService.create(account);
        return Response.created(new URI(String.format("accounts/account/%d", account.getAccountNumber()))).build();
    }

    @POST
    @Path("{toAccountNumber}/credit")
    public Response credit(@PathParam("toAccountNumber") final Long toAccountNumber, final TransactionDTO transactionDTO) {
        try {
            accountService.doTransfer(transactionDTO.getFromAccount(), toAccountNumber, transactionDTO.getAmount(), TransactionType.C);
            return Response.created(null).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{accountNumber}/transactions")
    @RolesAllowed("transactions")
    @NoCache
    public Response listTransactions(@PathParam("accountNumber") final Long accountNumber) {
        List<TransactionDTO> lst = accountService.listTransactions(accountNumber);
        return Response.ok(lst).build();
    }
}
