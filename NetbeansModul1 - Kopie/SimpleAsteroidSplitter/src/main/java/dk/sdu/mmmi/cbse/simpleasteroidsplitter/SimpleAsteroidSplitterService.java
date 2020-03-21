package dk.sdu.mmmi.cbse.simpleasteroidsplitter;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.commonasteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.commonasteroidsplitter.AsteroidSplitterSPI;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = AsteroidSplitterSPI.class)})
public class SimpleAsteroidSplitterService implements AsteroidSplitterSPI {
    private Lookup lookup = Lookup.getDefault();
    
    @Override
    public void splitAsteroid(Entity asteroid, World world) {
        LifePart initLife = asteroid.getPart(LifePart.class);
        if(initLife.getInitialLife() < 25){
            world.removeEntity(asteroid);
            return;
        }

        PositionPart parentPos = asteroid.getPart(PositionPart.class);
        MovingPart parentMove = asteroid.getPart(MovingPart.class);
        int life = (int) Math.floor(initLife.getInitialLife()/2);
        float speed = parentMove.getSpeed() + 50;
        float radius = asteroid.getRadius()/2;
        float radians = parentPos.getRadians();




        world.addEntity(createAsteroid(parentPos, asteroid.getRadius(), radius, radians -0.5f, life, speed));
        world.addEntity(createAsteroid(parentPos, asteroid.getRadius(), radius, radians +0.5f, life, speed));

    }

    private  Entity createAsteroid(PositionPart parentPos, float parentRadius, float radius, float radians, int life, float speed){

        Entity newAsteroid = ((AsteroidSPI)lookup.lookupAll(AsteroidSPI.class).toArray()[0]).createAsteroid(parentPos.getX(), parentPos.getY());
        newAsteroid.setRadius(radius);
        float newradians = radians;
        float by = (float) Math.sin(newradians) * parentRadius * radius;
        float bx = (float) Math.cos(newradians) * parentRadius * radius;
        PositionPart astPositionPart2 = new PositionPart(parentPos.getX() + bx, parentPos.getY() + by, radians);

        newAsteroid.add(new MovingPart(0, 5000, speed, 0));
        newAsteroid.add(astPositionPart2);
        newAsteroid.add(new LifePart(life, 100));
        return newAsteroid;
    }
}
