package com.nanter1986.energyshooter.Artifacts;

import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 21/2/2017.
 */
public abstract class Artifact {
    public String name;
    public abstract void work(PlayerShip ship);
}
