package webLgn.web;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

//JBoss Resteasy
@Path("/")
//@SecurityDomain("JBossWS")
public class MyCtrl {
	@Context
	  private HttpServletRequest httpRequest;
	
    @GET
    @Path("/")
    public Response test() {
        return Response.status(200).entity("test page ok").build();
    }
    
    @POST
    @Path("/login")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
     
        try {
        	System.out.println("username: " + username + "; password: " + password);
//        	httpRequest.login("admin", "jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
//        	httpRequest.login("admin", "admin");
        	httpRequest.login(username, password);

            System.out.println("Login Success for: " + username);
//            LOGGER.info("Login Success for: " + username);
            return Response.seeOther(new URI("../")).build();

        } catch (Exception e) {
//            LOGGER.error("Login Exception: " + e.getMessage());
            System.out.println("Login Exceptio for: " + e.getMessage());
        }
     
        return null;
    }
     
    @GET
    @Path("/logout")
    public Response logout() {
        try {
        	if (httpRequest.getSession(false) != null) {
        		httpRequest.getSession(false).invalidate();// remove session.
            }
        	httpRequest.logout();
        	return Response.seeOther(new URI("../login")).build();
        } catch (Exception e) {
        	System.out.println("Logout Exception: " + e.getMessage());
//            LOGGER.error("Logout Exception: " + e.getMessage());
        }
        return null;
    }
    
//    public static Response seeOther(String url) {
//        try {
//            return Response.seeOther(new URI(url)).build();
//        } catch (URISyntaxException e) {
//            return null;
//        }
//    }

}
