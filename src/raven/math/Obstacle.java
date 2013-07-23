package raven.math;

import raven.ui.GameCanvas;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import raven.utils.Log;

@XStreamAlias("Obstacle")
public class Obstacle  {
	
	@XStreamAlias("from")
	protected Vector2D Xy;
	protected double x;
	protected double y;
	@XStreamAlias("to")
	protected Vector2D radius;
	
	protected double r;
	
	public Obstacle(Vector2D a, Vector2D b)
	{
		Xy = new Vector2D(a);
		radius = new Vector2D(b);	
		r = radius.x;
		x = Xy.x;
		y = Xy.y;
	}
	

	public Boolean active;
	
	public Obstacle() {
		this.active = true;
	}
	
	public boolean isObstacle(){
		return true;
	}
	
	public Obstacle(double x, double y, double r)
	{
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	
	public void render() {
		render(false);
	}
	
	public void render(boolean renderNormals) {
		GameCanvas.filledCircle(Xy, radius.x); 
		}
	public Vector2D from() { return Xy; }
	public Vector2D to() { return radius; }

	
	}
	
	
	


