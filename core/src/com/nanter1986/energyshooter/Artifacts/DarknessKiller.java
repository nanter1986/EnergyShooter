package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 22/2/2017.
 */

public class DarknessKiller extends Artifact {

    public DarknessKiller() {
        this.name="Darkness Killer";
        this.description="Boosts attack vs only dark enemies by 50%";
    }

    @Override
    public void work(PlayerShip ship) {
        ship.damageFactorDark*=1.5;
    }
}
