package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 21/2/2017.
 */

public class EnergyBoosterOne extends Artifact {

    public EnergyBoosterOne() {
        this.name="Energy Booster";
        this.description="Gain energy 10% faster";

    }


    @Override
    public void work(PlayerShip ship) {
        ship.energyDrawn*=1.1;
    }
}
