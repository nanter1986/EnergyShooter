package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 21/2/2017.
 */

public class Speeder extends Artifact {

    public Speeder() {
        this.name="Speeder";
    }

    @Override
    public void work(PlayerShip ship) {
        ship.spaceshipSpeed*=1.1;
    }
}
