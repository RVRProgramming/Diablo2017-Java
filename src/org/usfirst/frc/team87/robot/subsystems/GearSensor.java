package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;
import org.usfirst.frc.team87.robot.commands.AutoGearDataCollection;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSensor extends Subsystem {
	private Ultrasonic ultra;

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

	public boolean checkForGear() {
		if (getUltra() > 5 * (RobotMap.ULTRATOTAL / RobotMap.ULTRATIMES)) {
			return false;
		} else {
			return true;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new AutoGearDataCollection());
	}
}
