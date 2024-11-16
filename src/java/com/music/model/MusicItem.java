/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.music.model;

/**
 *
 * @author USER
 */
public class MusicItem implements java.io.Serializable{
    private String title;
    private String year;
    private String genre;
    public String artist;
    
    //constructor
    public MusicItem(String title, String year, String genre, String artist){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.artist = artist;
    }
    
    //getter method
    public String getTitle(){
        return title;
    }
    public String getYear(){
        return year;
    }public String getGenre(){
        return genre;
    }
    public String getArtist(){
        return artist;
    }
    
    //mutator
    public void setArtist(String artist){
        this.artist = artist;
    }
    
}
