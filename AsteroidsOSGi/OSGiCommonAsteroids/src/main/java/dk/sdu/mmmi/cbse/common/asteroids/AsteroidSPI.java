package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;

public interface AsteroidSPI {
    public Entity createAsteroid(float x, float y);

}
