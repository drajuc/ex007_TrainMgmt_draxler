package com.example.ex007_trainmgmt_draxler.db;


import com.example.ex007_trainmgmt_draxler.pojo.Train;
import jakarta.ws.rs.NotFoundException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TrainDatabaseStatic {

    public List<Train> trains = new ArrayList<>();

    private TrainDatabaseStatic() {
        trains.add(new Train(0, new ArrayList<String>(Arrays.asList("Kaindorf", "Lebring", "Wildon", "Werndorf", "Feldkirchen", "Don Bosco", "Graz")), "S-Bahn"));
        trains.add(new Train(1, new ArrayList<String>(Arrays.asList("Kaindorf", "Don Bosco", "Graz")), "REX"));
        trains.add(new Train(2, new ArrayList<String>(Arrays.asList("Leibnitz", "Graz")), "Intercity"));

    }

    private static TrainDatabaseStatic theInstance = null;

    public synchronized static TrainDatabaseStatic getTheInstance() {
        if (theInstance == null) {
            theInstance = new TrainDatabaseStatic();
        }
        return theInstance;
    }

    public void addStation(int id, String name) {
        exists(id).addStation(name);

    }

    public void addTrain(Train train) {
        if (!trains.contains(train)) {
            trains.add(train);
        } else {
            throw new KeyAlreadyExistsException();
        }
    }

    private Train exists(int id) {
        Optional<Train> train = trains.stream().filter(t -> t.getId() == id).findFirst();
        return train.orElseThrow(() -> {
            return new NotFoundException();
        });

    }

    public Train getById(int id) {
        return exists(id);
    }
}
