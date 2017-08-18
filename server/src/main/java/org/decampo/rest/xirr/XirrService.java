package org.decampo.rest.xirr;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.decampo.rest.ServiceException;
import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;

/**
 * REST wrapper for org.decampo.xirr.Xirr.
 */
@Path("")
public class XirrService {
    @GET
    public String ping() {
        return "xirr service is alive";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public XirrResult xirr(TxRecord[] records) {
        try {
            // Convert TxRecords into Transactions
            final List<Transaction> tx = Stream.of(records)
                .map(TxRecord::toTransaction)
                .collect(Collectors.toList());
            final double xirr = new Xirr(tx).xirr();
            // Wrap result in result object
            return new XirrResult(xirr);
        } catch (IllegalArgumentException iae) {
            // Convert IAEs thrown by Xirr into ServiceExceptions for the
            // exception mapper
            throw new ServiceException(iae);
        }
    }
}
