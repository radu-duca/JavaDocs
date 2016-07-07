package ro.teamnet.zerotohero.oop.graphicshape.runapp;

import ro.teamnet.zerotohero.oop.graphicshape.Circle;
import ro.teamnet.zerotohero.oop.graphicshape.Circles;

/**
 * Created by user on 6/30/2016.
 */
public class RunApp {


    public static void main(String[] args) {
        Circles c = new Circles();
        c.getAreaPub();
        System.out.println("The default circle area is " + c.getAreaPub()) ;
    }

    }

