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
		this.active = true;
	}
	

	public Boolean active;
	
	
	public boolean isObstacle(){
		return true;
	}
	
	public Obstacle(double x, double y, double r)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.active = true;
	}
	
	
	public void render() {
		render(false);
	}
	
	public void render(boolean renderNormals) {
		GameCanvas.filledCircle(Xy, radius.x);
		//unsure what this does...
		//if (renderNormals) {
			//int midX = (int)((vA.x + vB.x) / 2);
			//int midY = (int)((vA.y + vB.y) / 2);
			
			//GameCanvas.line(midX, midY, (int)(midX + (vN.x * 5)), (int)(midY + (vN.y * 5)));}
		}
	
	
	
	public Vector2D calculateNormal(Vector2D norm)
	{
		double R = Math.sqrt(radius.x);
		vN = new Vector2D(norm.x/R, norm.y/R);
		return vN;
		
	}
	
	
	public Vector2D from() { return Xy; }
	public Vector2D to() { return radius; }
	public Vector2D center() { return Xy; }


	
	}
	
	
	


