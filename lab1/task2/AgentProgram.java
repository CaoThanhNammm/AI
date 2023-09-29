package lab1.task2;

import java.util.Random;

public class AgentProgram {
	public Action execute(Percept p) {// location, status
		Random rd = new Random();
		Action[] square = {Environment.MOVE_RIGHT, Environment.MOVE_LEFT, Environment.MOVE_UP, Environment.MOVE_DOWN};
		int i = rd.nextInt(4);
		
		if (p.getLocationState() == Environment.LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else {
			return square[i];
		}
	}
}
