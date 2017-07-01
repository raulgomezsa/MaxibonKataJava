package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by raul on 1/07/17.
 */

@RunWith(JUnitQuickcheck.class) public class DevelopersProperties {
    private static int count;
//    @Property public void WhenDeveloperIsCreatedTheDeveloperIsNotNull(int numMaxibons, String developerName ) {
//        Developer developer = new Developer(developerName, numMaxibons);
//
//        System.out.println((count++) + " " + developerName + " " + developer.getName());
//        assertTrue(new Developer(developerName, numMaxibons) !=null );
//    }

    @Property public void WhenDeveloperIsCreatedTheNumberOfMaxibonsIsGreaterThanZero(int numOfMaxibons) {
        Developer developer = new Developer("Any Name", numOfMaxibons);

//        System.out.println((count++) + " " + numOfMaxibons + " " + developer.getNumberOfMaxibonsToGrab());
        assertTrue(developer.getNumberOfMaxibonsToGrab() >=0 );
    }

    @Property public void WhenDeveloperIsCreatedTheNameOfDeveloperIsTheSame( String developerName ) {
        Developer developer = new Developer(developerName, 3);

//        System.out.println((count++) + " " + developerName + " " + developer.getName());
//        assertTrue(developer.getName().equals(developerName) );
        assertEquals(developer.getName(), developerName );
        //Este tipo de test se usa para conectar con base de datos o para login
    }


}
