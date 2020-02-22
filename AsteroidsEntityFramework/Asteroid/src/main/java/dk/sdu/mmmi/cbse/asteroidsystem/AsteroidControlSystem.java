package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.commonasteroidsplitter.AsteroidSplitterSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;

import java.util.Random;
@SuppressWarnings("Duplicates")
public class AsteroidControlSystem implements IEntityProcessingService {
    private float cummulativeData;
    private boolean[] commands;
    private int corners;


    public AsteroidControlSystem(){
        this.cummulativeData = 0;
        this.commands = new boolean[3];
        commands[0] = false;
        commands[1] = true;
        commands[2] = true;
        corners = 6;
    }
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);
            movingPart.setLeft(commands[0]);
            movingPart.setRight(commands[1]);
            movingPart.setUp(true);


            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            lifePart.process(gameData, asteroid);

            checkSplit(asteroid, world);

            updateShape(asteroid);
            updateAI(gameData.getDelta());
        }
    }

    private void checkSplit(Entity asteroid, World world) {
        LifePart lifePart = asteroid.getPart(LifePart.class);

        if(lifePart.getLife() <= lifePart.getInitialLife()/2){
            AsteroidSplitterSPI splitter = SPILocator.locateAll(AsteroidSplitterSPI.class).get(0);
            splitter.splitAsteroid(asteroid, world);
            world.removeEntity(asteroid);
        }
    }

    private void updateAI(float delta){
        cummulativeData += delta;
        Random random = new Random();
        if((cummulativeData*100) % 10 <= 2){
            commands[0] = random.nextBoolean();
            commands[1] = random.nextBoolean();
            commands[2] = random.nextBoolean();
        }
    }



    private void updateShape(Entity asteroid) {
        PositionPart position = asteroid.getPart(PositionPart.class);

        float x = position.getX();
        float y = position.getY();
        float radius = asteroid.getRadius();
        float radians = position.getRadians();
        float angle = 0;
        float[] shapex = new float[corners];
        float[] shapey = new float[corners];

        for (int i = 0; i < corners; i++) {
            shapex[i] = x + (float) Math.cos(angle + radians) * radius;
            shapey[i] = y + (float) Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f / corners;
        }

        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);
    }
}
