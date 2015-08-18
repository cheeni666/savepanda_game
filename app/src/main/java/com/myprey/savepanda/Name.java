package com.myprey.savepanda;

/**
 * Created by Ramanan on 7/3/2015.
 */
public class Name {
    private int _id;
    private String _name;
    private int _score;

    public Name(){
    }

    public Name(String name){
        this._name = name;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_score(int score) {
        this._score = score;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }
    public int get_score() {
        return _score;
    }
}
