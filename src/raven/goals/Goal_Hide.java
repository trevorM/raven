package raven.goals;

import raven.game.RavenBot;

public class Goal_Hide extends Goal<RavenBot> {

	public Goal_Hide(RavenBot m_pOwner) {
		super(m_pOwner, Goal.GoalType.goal_hide);
	}

	@Override
	public void activate() {
		m_iStatus = Goal.CurrentStatus.active;
	//	m_pOwner.getSteering().hideOn();//implement hide in raven steering
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
