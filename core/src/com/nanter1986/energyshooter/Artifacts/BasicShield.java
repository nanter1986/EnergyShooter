package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 21/2/2017.
 */

public class BasicShield extends Artifact {

    public BasicShield() {
        this.name="Basic Shield";
    }

    @Override
    public void work(PlayerShip ship) {
        ship.shield*=1.1;
    }
}
