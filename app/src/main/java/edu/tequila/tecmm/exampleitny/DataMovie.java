package edu.tequila.tecmm.exampleitny;

/**
 * Description:
 * Copyright 2017 TecMM Tequila
 * Created by luiscobian on 10/23/17.
 * Edit by ---- on 10/23/17
 */

public class DataMovie {

    private int id;
    private String name;
    private int idDrawable;
    private String pathImg;
    private String plot;
    private String comments;

    public DataMovie(){

    }


    public DataMovie(String name) {
        this.name = name;
        this.idDrawable = R.drawable.ic_popcorn;
    }

    public DataMovie(String name, int idDrawable) {
        this.name = name;
        this.idDrawable = idDrawable;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public boolean checkImage(){
        if(getPathImg().equals("none")) {
            setIdDrawable(R.drawable.ic_popcorn);
            return true;
        }else
            return false;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
