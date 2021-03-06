package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 22/2/2017.
 */

public class IceKiller extends Artifact {

    public IceKiller() {
        this.name="Ice Killer";
        this.description="Boosts attack vs only ice enemies by 50%";
    }

    @Override
    public void work(PlayerShip ship) {
        ship.damageFactorIce*=1.5;
    }
}
