package raven.goals;

import raven.game.RavenBot;
import raven.math.Geometry;
import raven.math.Vector2D;
import raven.math.Obstacle;

public class Goal_Hide extends Goal<RavenBot> {

	Vector2D current_dest;
	Obstacle os;
	public Goal_Hide(RavenBot m_pOwner, Obstacle os) {
		super(m_pOwner, Goal.GoalType.goal_hide);
		this.os = os;
	}

	@Override
	public void activate() {
		m_iStatus = Goal.CurrentStatus.active;
	//	m_pOwner.getSteering().hideOn();//implement hide in raven steering
		current_dest = os.from();
		//if (m_pOwner.getPathPlanner().RequestPathToPosition(current_dest)) {
		//	AddSubgoal(new Goal_SeekToPosition(m_pOwner, current_dest));}
		//AddSubgoal(new Goal_MoveToPosition(m_pOwner, current_dest));
		m_pOwner.getSteering().setTarget(current_dest);
		m_pOwner.getSteering().seekOn();	
	}

	@Override
	public raven.goals.Goal.CurrentStatus process(double delta) {
		activateIfInactive();
		return m_iStatus;
	}

	@Override
	public void terminate() {
	//	m_pOwner.getSteering().hideOff();
		m_iStatus = Goal.CurrentStatus.completed;
	}

}
