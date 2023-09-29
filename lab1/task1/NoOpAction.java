package lab1.task1;

public class NoOpAction extends Action{
	public static final NoOpAction NO_OP = new NoOpAction();

	@Override
	public boolean isNoOp() {
		return true;
	}
}
