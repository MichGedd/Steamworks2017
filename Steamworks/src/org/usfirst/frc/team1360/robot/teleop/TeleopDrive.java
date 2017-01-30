package org.usfirst.frc.team1360.robot.teleop;

import org.usfirst.frc.team1360.robot.IO.HumanInput;
import org.usfirst.frc.team1360.robot.IO.RobotOutput;

public class TeleopDrive implements TeleopComponent {
	
	private static TeleopDrive instance;
	private HumanInput humanInput;
	private RobotOutput robotOutput;
	private boolean isShifted = false;
	
	TeleopDrive()
	{
		humanInput = HumanInput.getInstance();
		robotOutput = RobotOutput.getInstance();
	}

	public static TeleopDrive getInstance()
	{
		if (instance == null)
			instance = new TeleopDrive();
		
		return instance;
	}
	
	@Override
	public void calculate() {
		double speed = humanInput.getDriveRight() - humanInput.getDriveLeft();
		double turn = humanInput.getTurn();
		boolean shift = humanInput.getShiftSpeed();
		
		if(shift && !isShifted)
		{
			isShifted = true;
			robotOutput.shiftSpeed(true);
		}
		else if (shift && isShifted)
		{
			isShifted = false;
			robotOutput.shiftSpeed(false);
		}
		
		robotOutput.arcadeDrive(speed, turn);
	}

	@Override
	public void disable() {
		robotOutput.tankDrive(0, 0);
		
	}

}
