package ru.company.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.company.shared.Message;

@Path("/api/hellos")
public interface HelloClient extends RestService {

    @GET
    public void getHellos(MethodCallback<List<String>> callback);

    @GET
    @Path("/{id}")
    public void getHellos(@PathParam("id") String id, MethodCallback<String> callback);


    @POST
    public void getPostHellos(Message id, MethodCallback<Message> callback);
}