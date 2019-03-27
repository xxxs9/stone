package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-24 - 23:56
 * @description :
 */
@Data
public class LenFromulaReachNew {
    private String id;
    private List<String> materialId;
    private String materialNumber;
    private String depotAudi;
    private String state;
    private String reachUser;
    private String reachTime;
}
