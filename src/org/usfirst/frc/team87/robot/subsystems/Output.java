package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Output extends Subsystem {
	private Relay fuelOut;
	private DigitalInput limitTop;
	private DigitalInput limitBottom;

	public Output() {
		fuelOut = new Relay(RobotMap.FUEL_OUT);
		limitTop = new DigitalInput(RobotMap.LIMITTOP);
		limitBottom = new DigitalInput(RobotMap.LIMITBOTTOM);
	}

	public void output(int speed) {
		if (speed == 1 && !limitTop.get()) {
			fuelOut.set(Relay.Value.kForward);
		} else if (speed == -1 && !limitBottom.get()) {
			fuelOut.set(Relay.Value.kReverse);
		} else {
			fuelOut.set(Relay.Value.kOff);
		}
	}

	public boolean getTopLimit() {
		return limitTop.get();
	}

	public void initDefaultCommand() {
	}
}
