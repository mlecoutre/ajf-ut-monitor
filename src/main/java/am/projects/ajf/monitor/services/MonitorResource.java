package am.projects.ajf.monitor.services;

import am.projects.ajf.monitor.model.MonitorUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * User: mlecoutre
 * Date: 13/09/13
 * Time: 10:17
 */
@Path("/Monitor")
@Produces(MediaType.APPLICATION_JSON)
public class MonitorResource {


    private static final Logger logger = LoggerFactory.getLogger(MonitorResource.class);

    @POST
    @Path("/push")
    public boolean pushMonitorUnit(@PathParam("monitorUnit") MonitorUnit monitorUnit) {

        return true;

    }

}