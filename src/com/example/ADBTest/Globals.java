package com.example.ADBTest;

/**
 * Created by yngvi on 22.9.2014.
 */
public class Globals {

    private final static Globals mInstance = new Globals();
    public Globals getInstance() {
        return mInstance;
    }
    private Globals() {};

    //public StudentsAdapter mSA = new StudentsAdapter();

}
