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
	
	protected Vector2D vN;
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
	
	public double distanceToCenter(Vector2D from)
	{
		return Math.sqrt(Math.pow((this.from().x-from.x),2) + Math.pow((this.from().y-from.y),2));		
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
	
	
	
	public Vector2D calculateNormal(Vector2D norm)
	{
		double R = Math.sqrt(radius.x);
		vN = new Vector2D(norm.x/R, norm.y/R);
		vN.normalize();
		return vN;
		
	}
	
	public Vector2D from() { return Xy; }
	public Vector2D to() { return radius; }
	public Vector2D center() { return Xy; }


	
	}
	
	
	


