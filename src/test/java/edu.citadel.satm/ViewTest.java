package edu.citadel.satm;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ViewTest {
    View view;

    @Before
    public void setUp() throws AWTException { // setup tests constructor
        view = new View("");
        Robot r = new Robot();
    }


}
