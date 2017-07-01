package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.java.util.ArrayListGenerator;
import com.pholser.junit.quickcheck.generator.java.util.ListGenerator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by raul on 1/07/17.
 */

@RunWith(JUnitQuickcheck.class) public class KarumiHQsProperties {
    //No tiene sentido usarlo porque siempre comprueba el mismo developer, con diferente numero de Maxibons a coger
    @Property public void whenDeveloperGrabMaxibonTheNumberOfMaxibonIsGreatterThanTwo (int numMaxibons) {
        Developer developer = new Developer("Any Name", numMaxibons);

        KarumiHQs karumiHQs = new KarumiHQs();
        karumiHQs.openFridge(developer);
        assertTrue (karumiHQs.getMaxibonsLeft()>=2);
    }

    //Hace el test anterior con datos totalmente aleatorios
    @Property public void whenDeveloperGrabMaxibonTheNumberOfMaxibonIsGreatterThanTwoWithGenerator (@From(DevelopersGenerator.class) Developer developer) {
//        System.out.println("Developer " + developer.getName() + " " + developer.getNumberOfMaxibonsToGrab());

        KarumiHQs karumiHQs = new KarumiHQs();
        karumiHQs.openFridge(developer);
        assertTrue (karumiHQs.getMaxibonsLeft()>=2);
    }

    //Hace el test anterior con datos aleatorios acotados
    @Property public void whenDeveloperGrabMaxibonTheNumberOfMaxibonIsGreatterThanTwoWithGeneratorKarumi (@From(KarumiesGenerator.class) Developer developer) {
//        System.out.println("Developer " + developer.getName() + " " + developer.getNumberOfMaxibonsToGrab());

        KarumiHQs karumiHQs = new KarumiHQs();
        karumiHQs.openFridge(developer);
        assertTrue (karumiHQs.getMaxibonsLeft()>=2);
    }

    //Hace el test con genete que coge entre 8 y 10 Maxibons aleatoriamente
    @Property public void whenDevelopersGrabMaxibonsNumberOfMaxibonIsGreatterOrEqualTen (@From(HungryDevelopersGenerator.class) Developer developer) {
        KarumiHQs karumiHQs = new KarumiHQs();
//        System.out.println("1. Developer " + developer.getName() + " " + developer.getNumberOfMaxibonsToGrab() + " " + karumiHQs.getMaxibonsLeft());
        karumiHQs.openFridge(developer);
//        System.out.println("2. Developer " + developer.getName() + " " + developer.getNumberOfMaxibonsToGrab() + " " + karumiHQs.getMaxibonsLeft());
        assertTrue (karumiHQs.getMaxibonsLeft()>=10);
    }

    //Mirar este test de la rama master
    @Property public void whenListDevelopersGrabMaxibonsNumberOfMaxibonIsGreatterTwo (List<@From(KarumiesGenerator.class) Developer> developers) {
        KarumiHQs karumiHQs = new KarumiHQs();
        int numMaxibons = pendingMaxibons(karumiHQs.getMaxibonsLeft(), developers);
        karumiHQs.openFridge(developers);
        System.out.println("Developer " + numMaxibons + " " + karumiHQs.getMaxibonsLeft());
        assertEquals (numMaxibons,karumiHQs.getMaxibonsLeft());
    }

    private int pendingMaxibons(int initial, List<Developer> list){
        int num=initial;
        for (Developer dev: list) {
            System.out.println("2. Developer " + dev.getName() + " " + dev.getNumberOfMaxibonsToGrab() + " " + num);
            num+=dev.getNumberOfMaxibonsToGrab();
        }
        num=num%10;

        return num;
    }
}
