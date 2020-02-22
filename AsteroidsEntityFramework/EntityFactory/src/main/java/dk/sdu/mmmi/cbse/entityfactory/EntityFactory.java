package dk.sdu.mmmi.cbse.entityfactory;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.util.AbstractFactory;

public class EntityFactory implements AbstractFactory<Entity> {


    @Override
    public Entity create(String entityType) {
        if("Asteroid".equals(entityType)){
            //return SPILocator.locateAll(AsteroidSPI.class).get(0).createAsteroid()
        }

        return null;
    }
}
