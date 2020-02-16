package dk.sdu.mmmi.cbse.collisiondetectionsystem;

import dk.sdu.mmmi.cbse.asteroidsystem.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Arrays;
import java.util.Collections;

public class CollisionDetectionControlSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities(Player.class).get(0);
        for (Entity enemy : world.getEntities(Enemy.class)) {
            System.out.println(getDistance(player, enemy));
            if(getDistance(player, enemy) < 16){
                world.removeEntity(enemy);
            }
        }


        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            System.out.println(getDistance(player, asteroid));
            if(getDistance(player, asteroid) < 16){
                world.removeEntity(asteroid);
            }
        }

    }

    public float getDistance(Entity entity1, Entity entity2){
        float x1 = ((PositionPart) entity1.getPart(PositionPart.class)).getX();
        float y1 = ((PositionPart) entity1.getPart(PositionPart.class)).getY();
        float x2 = ((PositionPart) entity2.getPart(PositionPart.class)).getX();
        float y2 = ((PositionPart) entity2.getPart(PositionPart.class)).getY();
        return (float) Math.sqrt((Math.pow(x2-x1, 2) + (Math.pow(y2-y1, 2))));
    }
}
