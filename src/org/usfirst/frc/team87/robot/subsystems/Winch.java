package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	private CANTalon winchL;
	private CANTalon winchR;
	private Relay led;

	public Winch() {
		winchL = new CANTalon(RobotMap.WINCHL);
		winchR = new CANTalon(RobotMap.WINCHR);
		led = new Relay(RobotMap.LED);
	}

	public void climb(double speed) {
		if ((speed < -RobotMap.WINCHTHRESHOLD || speed > RobotMap.WINCHTHRESHOLD) && DriverStation.getInstance().isOperatorControl()) {
			winchL.set(speed);
			winchR.set(-speed);
		} else {
			winchL.set(0);
			winchR.set(0);
		}
	}

	public void ledPower(boolean power) {
		if (power) {
			led.set(Relay.Value.kForward);
		} else {
			led.set(Relay.Value.kOff);
		}
	}

	public void initDefaultCommand() {
	}
}
