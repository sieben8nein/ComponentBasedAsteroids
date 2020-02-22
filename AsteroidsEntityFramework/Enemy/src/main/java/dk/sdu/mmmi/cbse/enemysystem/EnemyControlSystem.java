package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;

import java.util.Random;

@SuppressWarnings("Duplicates")
public class EnemyControlSystem implements IEntityProcessingService {
    private float cummulativeData;
    private boolean[] commands;
    private boolean shooting;
    private int counter;

    public EnemyControlSystem(){
        this.cummulativeData = 0;
        this.commands = new boolean[3];
        commands[0] = false;
        commands[1] = true;
        commands[2] = true;
        shooting = false;
        counter = 0;
    }
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);
            movingPart.setLeft(commands[0]);
            movingPart.setRight(commands[1]);
            movingPart.setUp(commands[2]);

            if(shooting){
                BulletSPI bulletService = SPILocator.locateAll(BulletSPI.class).get(0);
                Entity bullet = bulletService.createBullet(enemy, gameData);
                world.addEntity(bullet);
                shooting = false;
            }



            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);

            updateShape(enemy);
            updateAI(gameData.getDelta());
        }
    }

    private void updateAI(float delta){
        counter++;
        Random random = new Random();
        if(counter % 10 <= 2){
            commands[0] = random.nextBoolean();
            commands[1] = random.nextBoolean();
            commands[2] = random.nextBoolean();

        }
        if(counter % 20 == 2){
            shooting = random.nextBoolean();
        }
        if(counter > 1000){
            counter = 0;
        }


    }



    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
