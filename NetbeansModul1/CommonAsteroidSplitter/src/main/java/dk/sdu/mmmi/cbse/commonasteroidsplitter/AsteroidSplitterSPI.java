package dk.sdu.mmmi.cbse.commonasteroidsplitter;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public interface AsteroidSplitterSPI {
    void splitAsteroid(Entity asteroid, World world);
}
