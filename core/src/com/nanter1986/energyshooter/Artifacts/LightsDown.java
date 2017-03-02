package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 22/2/2017.
 */

public class LightsDown extends Artifact {

    public LightsDown() {
        this.name="Lights Down";
        this.description="Boosts attack vs only light enemies by 50%";
    }

    @Override
    public void work(PlayerShip ship) {
        ship.damageFactorLight*=1.5;
    }
}
