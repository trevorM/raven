package raven.goals;

import raven.game.RavenBot;
import raven.game.RavenObject;
import raven.goals.Goal.GoalType;
import raven.math.Vector2D;
import raven.ui.GameCanvas;

public class HideGoal_Evaluator extends Goal_Evaluator {

	public HideGoal_Evaluator(Double inp) {
		super(inp, GoalType.goal_hide);
	}

	//---------------------- CalculateDesirability -------------------------------------
	//-----------------------------------------------------------------------------
	public double calculateDesirability(RavenBot pBot)
	{
		
		if(pBot.health() < 10){
			
			//value used to tweak the desirability
			Double Tweaker = 0.2;

			//the desirability of hiding is proportional to the amount
			//of health remaining and inversely proportional to the distance from the
			//nearest instance of a hidding spot.
			double Desirability = Tweaker * (1-RavenFeature.Health(pBot)) / 
			(RavenFeature.DistanceToItem(pBot, RavenObject.HIDE));

			//ensure the value is in the range 0 to 1
			RavenFeature.Clamp(Desirability, 0, 1);

			//bias the value according to the personality of the bot
			Desirability *= getBias();

			return Desirability;
		}
		else
			return 0.0;
	}

	//----------------------------- SetGoal ---------------------------------------
	//-----------------------------------------------------------------------------
	public void setGoal(RavenBot pBot) {

		try{
			pBot.getBrain().addGoal_hide();
		}catch(Exception ex){
			System.out.println( ex.getMessage());
		}
	}

	//-------------------------- RenderInfo ---------------------------------------
	//-----------------------------------------------------------------------------
	public void RenderInfo(Vector2D Position, RavenBot pBot)
	{
			GameCanvas.textAtPos(Position, "Hide: " + String.valueOf((calculateDesirability(pBot))));
			String s = String.valueOf(RavenFeature.DistanceToItem(pBot,RavenObject.HIDE));
			Position.add(new Vector2D(0,15));
			GameCanvas.textAtPos(Position, s);
			return;


	}

}