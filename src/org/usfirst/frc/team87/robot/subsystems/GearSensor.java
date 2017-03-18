package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;
import org.usfirst.frc.team87.robot.commands.AutoGearDataCollection;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSensor extends Subsystem {
	static Ultrasonic ultra;

	public GearSensor() {
		ultra = new Ultrasonic(RobotMap.ULTRA_OUT, RobotMap.ULTRA_IN);
	}

	public void ultraSetup() {
		ultra.setAutomaticMode(true);
	}

	public double getUltra() {
		return ultra.getRangeInches();
	}

	public void update() {
		RobotMap.ULTRATOTAL += getUltra();
		RobotMap.ULTRATIMES += 1;
	}

	public void checkForGear() {
		if (getUltra() > 5 * (RobotMap.ULTRATOTAL / RobotMap.ULTRATIMES)) {
			RobotMap.HOLDINGGEAR = false;
		} else {
			RobotMap.HOLDINGGEAR = true;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new AutoGearDataCollection());
	}
}
