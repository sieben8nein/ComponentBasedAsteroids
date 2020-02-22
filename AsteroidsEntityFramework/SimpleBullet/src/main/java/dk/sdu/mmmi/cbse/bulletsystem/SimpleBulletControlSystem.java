package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;

import java.util.UUID;

public class SimpleBulletControlSystem implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {

            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);
            movingPart.setUp(true);

            if (lifePart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            lifePart.process(gameData, bullet);

            setShape(bullet);
        }
    }


    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        PositionPart startPos = shooter.getPart(PositionPart.class);
        String shooterIDLastCharacters = shooter
                .getID()
                .substring(shooter
                        .getID()
                        .length() - 3);
        float speed = 200;
        float rotationSpeed = 5;
        float x = startPos.getX();
        float y = startPos.getY();
        float radians = startPos.getRadians();
        UUID unChangedID = UUID.randomUUID();
        String customID = unChangedID
                .toString()
                .substring(0, unChangedID.toString().length()-3)
                .concat(shooterIDLastCharacters);
        Entity bullet = new Bullet();
        bullet.setID(customID);
        bullet.setRadius(3);
        bullet.add(new MovingPart(0, Integer.MAX_VALUE, speed, 5));
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new LifePart(100, 1));



        return bullet;
    }

    private void setShape(Entity bullet){
        float[] shapex = bullet.getShapeX();
        float[] shapey = bullet.getShapeY();
        PositionPart positionPart = bullet.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = x;
        shapey[0] = y;

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5));
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5));

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);
    }
}
