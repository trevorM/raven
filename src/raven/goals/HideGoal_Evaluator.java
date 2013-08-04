package raven.goals;

import java.util.List;

import raven.game.RavenBot;
import raven.game.RavenGame;
import raven.game.RavenObject;
import raven.game.interfaces.IRavenBot;
import raven.goals.Goal.GoalType;
import raven.math.Geometry;
import raven.math.Obstacle;
import raven.math.Vector2D;
import raven.ui.GameCanvas;
import raven.utils.Log;

public class HideGoal_Evaluator extends Goal_Evaluator {

	protected RavenGame world;
	public HideGoal_Evaluator(Double inp) {
		super(inp, GoalType.goal_hide);
	}

	//---------------------- CalculateDesirability -------------------------------------
	//-----------------------------------------------------------------------------
	public double calculateDesirability(RavenBot pBot)
	{
		this.world = pBot.getWorld();
		boolean sensed = true;
		
		//for(IRavenBot opp: pBot.getSensoryMem().getListOfRecentlySensedOpponents())
			//sensed = (pBot.getSensoryMem().isOpponentWithinFOV(opp));		
		
			if(pBot.health() < 100 && sensed)
			{
			//value used to tweak the desirability
			Double Tweaker = 1000.8;

			//the desirability of hiding is proportional to the amount
			//of health remaining and inversely proportional to the distance from the
			//nearest instance of a hiding spot.
			
			//We need to get the distance between the bot and the obstacle
			//maybe use obstacle.from() and ravenbot's pos
			
			double distanceToObstacle = 10000;
			
			if(world.getMap().getObstacles()!=null)
			distanceToObstacle = Geometry.minDistanceToCenterOfObstacle(pBot.pos(), world.getMap().getObstacles(), world.getMap().getWalls());
			
			double Desirability = Tweaker * (1-RavenFeature.Health(pBot)) / 
			(distanceToObstacle);
			//ensure the value is in the range 0 to 1
			RavenFeature.Clamp(Desirability, 0, 1);

			//bias the value according to the personality of the bot
			Desirability *= getBias();
			Log.info("Distance to obstacle " + distanceToObstacle);
			Log.info("Desire = " + Desirability);
			
			
			return Desirability;
		}
		else
		//	return 1000;
			return 0.0;
	}

	//----------------------------- SetGoal ---------------------------------------
	//-----------------------------------------------------------------------------
	public void setGoal(RavenBot pBot) {
		Obstacle os = new Obstacle();
		if(pBot.getWorld().getMap().getObstacles()!=null)
		os = Geometry.closestObstacle(pBot.pos(), pBot.getWorld().getMap().getObstacles(), pBot.getWorld().getMap().getWalls());
	
		try{
			pBot.getBrain().addGoal_hide(os);
		}catch(Exception ex){
			System.out.println( ex.getMessage());
		
		}
	}

	//-------------------------- RenderInfo ---------------------------------------
	//-----------------------------------------------------------------------------
	//helps track what bots are doing by displaying desirabilitys and goals in terminol
	public void RenderInfo(Vector2D Position, RavenBot pBot)
	{
			GameCanvas.textAtPos(Position, "Hide: " + String.valueOf((calculateDesirability(pBot))));
			String s = String.valueOf(RavenFeature.DistanceToItem(pBot,RavenObject.OBSTACLE));
			Position.add(new Vector2D(0,15));
			GameCanvas.textAtPos(Position, s);
			return;


	}

}