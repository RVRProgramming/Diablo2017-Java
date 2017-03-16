package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	static VictorSP fuelIn;

	public Intake() {
		fuelIn = new VictorSP(RobotMap.FUEL_IN);
	}

	public void intake(double speed) {
		fuelIn.setSpeed(speed);
	}

	public void initDefaultCommand() {
	}
}
