package com.mygdx.game.desktop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.desktop.Game;
import com.mygdx.game.desktop.handlers.GameStateManager;

import static com.mygdx.game.desktop.handlers.Box2DVariables.pixelsPerMeter;


public class Play extends GameState	
{
	
	private World world;
	private Box2DDebugRenderer b2dr;
	private Matrix4 projectionMatrix;
	
	private OrthographicCamera b2dCam;
	
	public Play(GameStateManager gsm)
	{
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		b2dr = new Box2DDebugRenderer();
		projectionMatrix = new Matrix4();
		
		// create platform
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / pixelsPerMeter, 120/ pixelsPerMeter);
		bdef.type = BodyType.StaticBody;
		
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / pixelsPerMeter, 5 / pixelsPerMeter);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		
		// create ball 
		bdef.position.set(153 / pixelsPerMeter, 220 / pixelsPerMeter);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		CircleShape cshape = new CircleShape();
		cshape.setRadius(5 / pixelsPerMeter);
		fdef.shape = cshape;
		body.createFixture(fdef);
		
		// create falling box
		bdef.position.set(160 / pixelsPerMeter, 200 / pixelsPerMeter);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		shape.setAsBox(5 / pixelsPerMeter, 5/ pixelsPerMeter);
		fdef.shape = shape;
		fdef.restitution = .7f;
		body.createFixture(fdef);
		
		// set up box2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Game.V_WIDTH / pixelsPerMeter, Game.V_HEIGHT / pixelsPerMeter);
		
	}
	
	public void handleInput()
	{
		
	}
	
	public void update(float dt)
	{
		world.step(dt, 6, 2);
	}
	
	public void render()
	{
		// clear screen
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		
		// draw box2d world
		b2dr.render(world, b2dCam.combined);
	}

	public void dispose()
	{
		
	}

}

