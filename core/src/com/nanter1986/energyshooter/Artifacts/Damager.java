package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 21/2/2017.
 */

public class Damager extends Artifact {

    public Damager() {
        this.name="Damager";
        this.description="Boosts attack by 10%";
    }

    @Override
    public void work(PlayerShip ship) {
        ship.damageFactor*=1.1;
    }
}
