package dk.sdu.mmmi.cbse.asteroidsystem;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

import java.util.Random;

public class Asteroid extends Entity {
    private float height;
    private float width;
    private float minSize = 4;
    private float maxSize = 8;

    public Asteroid(){
        Random random = new Random();
        width = minSize + random.nextFloat() * (maxSize - minSize);
        height = minSize + random.nextFloat() * (maxSize - minSize);
    }
    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        float x = ((PositionPart) this.getPart(PositionPart.class)).getX();
        float y = ((PositionPart) this.getPart(PositionPart.class)).getY();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y, width, height);
        sr.end();

    }
}
