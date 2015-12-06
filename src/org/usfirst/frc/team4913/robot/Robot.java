package org.usfirst.frc.team4913.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {
	RobotDrive myRobot;
	RobotDrive arms;
	Joystick stick;
	Joystick armStick;
	int autoLoopCounter;
	Victor armMotor2;
	Victor armMotor;
	Victor rollingMotorRight;
	Victor rollingMotorLeft;

	public Robot() {
		myRobot = new RobotDrive(1, 0);
		stick = new Joystick(0);
		armStick = new Joystick(1);
		armMotor = new Victor(2);
		armMotor2 = new Victor(3);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		rollingMotorRight = new Victor(5);
		rollingMotorLeft = new Victor(4);
	}

	/**
	 * Drive left & right motors for 2 seconds then stop
	 */
	public void autonomous() {

		// turn box into arms
		// keep this code as is
		myRobot.setSafetyEnabled(false);
	
		for (int i = 0; i < 2; i++) {
			armMotor.set(.5);
			armMotor2.set(-.5);
		}

		Timer.delay(2);

		myRobot.drive(-.2, 0);
		Timer.delay(.7);


		myRobot.drive(0, 0);
		armMotor.set(-0.3);
		armMotor2.set(0.3);

		Timer.delay(2);

		// keep code okay until here*/

		myRobot.drive(.3, 0);
		Timer.delay(2.5); //2.5 for going over platforms
		
		myRobot.drive(0, 0);


		armMotor.set(.5);
		armMotor2.set(-.5);
		Timer.delay(1);
		
		myRobot.drive(.1, 0);
		Timer.delay(1);
		armMotor.set(0);
		armMotor2.set(0);
		myRobot.drive(0, 0);

//just move backward and robot is next to the line
/*
 * myRobot.setSafetyEnabled(false);
 * myRobot.drive(.3, 0);
		Timer.delay(1.3);
 * 		myRobot.drive(0, 0);

 */
		
		
	
//pick up totes but not over a platform
		/*
		 * myRobot.setSafetyEnabled(false);
		 for (int i = 0; i < 2; i++) {
			armMotor.set(.5);
			armMotor2.set(-.5);
		}

		Timer.delay(2);

		myRobot.drive(-.2, 0);
		Timer.delay(.7);


		myRobot.drive(0, 0);
		armMotor.set(-0.3);
		armMotor2.set(0.3);

		Timer.delay(2);

		myRobot.drive(.3, 0);
		Timer.delay(2.2); //2.2 for not going over platforms
		
		myRobot.drive(0, 0);


		armMotor.set(.5);
		armMotor2.set(-.5);
		Timer.delay(1);
		
		myRobot.drive(.1, 0);
		Timer.delay(1);
		armMotor.set(0);
		armMotor2.set(0);
		myRobot.drive(0, 0);

		 * 
		 * 
		 */
	}

	/**
	 * Runs the motors with arcade steering.
	 */
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);

		while (isOperatorControl() && isEnabled()) {
			myRobot.arcadeDrive(stick);

			if (armStick.getRawButton(8)) {
				while (!armStick.getRawButton(9)) {
					myRobot.arcadeDrive(stick);
					armMotor.set(armStick.getZ());
					armMotor2.set(-armStick.getZ());
				}
			}

			if (armStick.getRawButton(9)) {
				armMotor.set(0);
				armMotor2.set(0);
			}
			
			while (stick.getRawButton(4)) {//turn left
				myRobot.tankDrive(-.3, 0);
			}
			
			while (stick.getRawButton(3)) {//turn right
				myRobot.tankDrive(0, -.3);
			}
		/*	boolean rollingMotorsOn = false;
			if (armStick.getRawButton(3)) {
				rollingMotorsOn = true;
			}

			while (rollingMotorsOn) {
				myRobot.arcadeDrive(stick);
				if (armStick.getRawButton(2)) {
					rollingMotorsOn = false;
				}
				rollingMotorLeft.set(.4);
				rollingMotorRight.set(-.4);
			}

			boolean leftRollingMotorsOn = false;
			if (armStick.getRawButton(4)) {
				leftRollingMotorsOn = true;
			}
			while (leftRollingMotorsOn) {
				myRobot.arcadeDrive(stick);
				if (armStick.getRawButton(2)) {
					leftRollingMotorsOn = false;
					rollingMotorsOn = false;
				}
				rollingMotorLeft.set(.4);

			}
			boolean rightRollingMotorsOn = false;
			if (armStick.getRawButton(5)) {
				rightRollingMotorsOn = true;
			}
			while (rightRollingMotorsOn) {
				myRobot.arcadeDrive(stick);
				if (armStick.getRawButton(2)) {
					rightRollingMotorsOn = false;
					rollingMotorsOn = false;
				}
				rollingMotorRight.set(-.4);

			}

			if (!rollingMotorsOn) {
				rollingMotorLeft.set(0);
				rollingMotorRight.set(0);
			}*/
			/* for arm going up, will automatically go to 60 degrees */
			/*
			 * while (stick.getRawButton(5)) { armMotor.set(1);
			 * armMotor2.set(-1); myRobot.arcadeDrive(stick); } if
			 * (!stick.getRawButton(5)) { armMotor.set(0); armMotor2.set(0); }
			 * /* for arm going down, must press down button
			 */

			/*
			 * while (stick.getRawButton(3)) { armMotor.set(-.8);
			 * armMotor2.set(.8); myRobot.arcadeDrive(stick); }
			 * 
			 * if (!stick.getRawButton(3)) { armMotor.set(0); armMotor2.set(0);
			 * } while (stick.getRawButton(4)) { armMotor.set(-1);
			 * armMotor2.set(1); myRobot.arcadeDrive(stick); }
			 * 
			 * if (!stick.getRawButton(4)) { armMotor.set(0); armMotor2.set(0);
			 * } while (stick.getRawButton(2)) { armMotor.set(0.8);
			 * armMotor2.set(-0.8); myRobot.arcadeDrive(stick); }
			 * 
			 * if (!stick.getRawButton(2)) { armMotor.set(0); armMotor2.set(0);
			 * }
			 * 
			 * // code for stall current
			 */

			// boolean stallCurrent = false;

			/*
			 * if (armStick.getRawButton(1)) { stallCurrent = !stallCurrent; }
			 * while (stallCurrent) { armMotor.set(.4); armMotor2.set(-.4); if
			 * (armStick.getRawButton(1)) { stallCurrent = !stallCurrent; } }
			 */
			armMotor.set(0);
			armMotor2.set(0);
		}
	}

	/**
	 * Runs during test mode
	 */
	public void test() {
	}
}
