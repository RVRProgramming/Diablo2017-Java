package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Output extends Subsystem {
	static Relay fuelOut;

	public Output() {
		fuelOut = new Relay(RobotMap.FUEL_OUT);
	}

	public void output(int speed) {
		if (speed == 1) {
			fuelOut.set(Relay.Value.kForward);
		} else if (speed == -1) {
			fuelOut.set(Relay.Value.kReverse);
		} else {
			fuelOut.set(Relay.Value.kOff);
		}
	}

	public void initDefaultCommand() {
	}
}
