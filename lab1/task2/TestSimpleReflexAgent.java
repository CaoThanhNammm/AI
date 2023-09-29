package lab1.task2;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(Environment.LocationState.CLEAN, Environment.LocationState.DIRTY, Environment.LocationState.DIRTY, Environment.LocationState.DIRTY);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, Environment.LOCATION_A);

		env.stepUntilDone();
	}
}
