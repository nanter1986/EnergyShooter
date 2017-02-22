package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 22/2/2017.
 */

public class FireKiller extends Artifact {

    public FireKiller() {
        this.name="Fire Killer";
    }

    @Override
    public void work(PlayerShip ship) {
        ship.damageFactorFire*=1.5;
    }
}
