package com.mygdx.game.desktop.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionListener implements ContactListener {
	
	// called when two fixtures start to collide
	public void beginContact (Contact c) 
	{
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
	
		System.out.println(fa.getUserData() + " , " + fb.getUserData());
	}
	
	// called when two fixtures no longer collide
	public void endContact(Contact c)
	{
		System.out.println("No longer colliding");
	}
	
	public void preSolve(Contact c, Manifold m	)
	{
		
	}
	
	
	
	public void postSolve(Contact c, ContactImpulse ci)
	{
		
	}

}
