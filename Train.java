package com.example.ex007_trainmgmt_draxler.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    private int id;
    private List<String> stations = new ArrayList<>();
    private String type;

    public void addStation(String name) {
        if (!stations.contains(name)) {
            stations.add(name);
        }
    }
}
