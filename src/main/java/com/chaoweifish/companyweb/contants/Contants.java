package com.chaoweifish.companyweb.contants;

import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class Contants {
    private final String NEW ="new";
    private final String STABLE = "stable";
    private final String EDIT = "edit";
    private final String DELETE = "delete";
    private final String PICTURE_PATH="C:"+ File.separator+"chaoweifish"+File.separator+"img";

    public String getNEW() {
        return NEW;
    }

    public String getSTABLE() {
        return STABLE;
    }

    public String getEDIT() {
        return EDIT;
    }

    public String getDELETE() {
        return DELETE;
    }

    public String getPICTURE_PATH() {
        return PICTURE_PATH;
    }
}
