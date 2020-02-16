package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {
    private float cummulativeData;
    private boolean[] commands;

    public AsteroidControlSystem(){
        this.cummulativeData = 0;
        this.commands = new boolean[3];
        commands[0] = false;
        commands[1] = true;
        commands[2] = true;
    }
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            movingPart.setLeft(commands[0]);
            movingPart.setRight(commands[1]);
            movingPart.setUp(commands[2]);

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
            updateAI(gameData.getDelta());
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



    private void updateShape(Entity entity) {
    }
}
