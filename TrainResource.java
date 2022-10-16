package com.example.ex007_trainmgmt_draxler;

import com.example.ex007_trainmgmt_draxler.db.TrainDatabaseStatic;
import com.example.ex007_trainmgmt_draxler.pojo.Train;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.net.URI;
import java.util.List;

@Path("/trains")
public class TrainResource {

    @POST
    @Consumes("application/json")
    public Response addTrain(Train train) {
        try {
            TrainDatabaseStatic.getTheInstance().addTrain(train);
            return Response.created(URI.create("trains/" + train.getId())).entity(TrainDatabaseStatic.getTheInstance().getById(train.getId())).build();
        } catch (KeyAlreadyExistsException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

    @POST
    @Consumes("application/json")
    @Path("/{id}")
    public Response addTrainStation(@PathParam("id") int id, String name) {
        try {
            TrainDatabaseStatic.getTheInstance().addStation(id, name);
            return Response.ok().build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getStations(@PathParam("id") int id) {
        try {
            Train train = TrainDatabaseStatic.getTheInstance().getById(id);
            System.out.println(train.getStations());
            return Response.ok(train.getStations()).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getTrains() {
        List<Train> trains = TrainDatabaseStatic.getTheInstance().trains;
        return Response.ok(trains).build();
    }


}