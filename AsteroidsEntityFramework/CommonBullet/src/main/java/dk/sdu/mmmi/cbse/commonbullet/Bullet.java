package dk.sdu.mmmi.cbse.commonbullet;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

public class Bullet extends Entity {
    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        float x = ((PositionPart) this.getPart(PositionPart.class)).getX();
        float y = ((PositionPart) this.getPart(PositionPart.class)).getY();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(x, y, 2);
        sr.end();
    }

    /**
     * used to identify if shooter of the bullet
     */
    public void setSignificantBits(String shooterID){

    }
}
