package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.Ploop;

import java.util.concurrent.TimeUnit;

@TeleOp

public class DannyTeleop extends LinearOpMode {
    Hardware hardware;
    double speed = 1;
    Ploop looper;
    double cyclecount = 0;
    double loop = 0;
    public double numPixels;

    private static ElapsedTime myStopwatch = new ElapsedTime();
    private static ElapsedTime armdelay = new ElapsedTime();

    Boolean keepPixels = true;

    private double time;
    private double teem;
    private boolean CanMove = true;
    private boolean BoxDelay = true;
    public String armpos = "down";

    public void runOpMode() {

        /* init */
        telemetry.addData("Status", "Initialized");

        hardware = new Hardware(hardwareMap);

        hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        time = 0;

        waitForStart();

        myStopwatch.reset();


        while (opModeIsActive()) {

            /* Drive Control */
            {
                double x = -gamepad1.left_stick_x * speed;
                double y = -gamepad1.left_stick_y * speed;
                double turn = -gamepad1.right_stick_x * speed;

                double theta = Math.atan2(y, x);
                double power = Math.hypot(x, y);
                double sin = Math.sin(theta - Math.PI / 4);
                double cos = Math.cos(theta - Math.PI / 4);
                double max = Math.max(Math.abs(sin), Math.abs(cos));
                hardware.rightFront.setPower(power * cos / max + turn);
                hardware.leftFront.setPower(power * sin / max - turn);
                hardware.rightRear.setPower(power * sin / max + turn);
                hardware.leftRear.setPower(power * cos / max - turn);
                if ((power + Math.abs(turn)) > 1) {
                    hardware.leftFront.setPower((hardware.leftFront.getPower()) / (power + turn));
                    hardware.rightFront.setPower((hardware.rightFront.getPower()) / (power + turn));
                    hardware.rightRear.setPower((hardware.rightRear.getPower()) / (power + turn));
                    hardware.leftRear.setPower((hardware.leftRear.getPower()) / (power + turn));
                }
            }

            /* Speed Control */
            {
                speed = (gamepad1.right_trigger * 0.7) + 0.3;
            }

            //debugging
//            if (gamepad1.x){
//                hardware.door.setPosition(0.25);
//                hardware.boxRotation.setPosition(0);
//            }
//            if (gamepad1.y){
//                hardware.door.setPosition(0);
//                hardware.boxRotation.setPosition(1);
//            }

            // Arm up intake sequence
            if (gamepad2.a) {
                hardware.door.setPosition(0);
                hardware.leftSlide.setPower(-1);
                //hardware.rightSlide.setPower(-0.75);
                hardware.boxRotation.setPosition(hardware.INTAKE_ANGLE);
                hardware.intake.setPower(1);
                armpos = "down";
            }

            if (gamepad2.b) {
                hardware.door.setPosition(0.25);
                hardware.intake.setPower(0);
                hardware.leftSlide.setPower(1);
                //hardware.rightSlide.setPower(1);
                armdelay.reset();
                CanMove = false;
                armpos = "up";
            }
            if (armdelay.time(TimeUnit.MILLISECONDS) > 500){
                CanMove = true;
                if (armpos == "up") {
                    hardware.boxRotation.setPosition(hardware.DROP_ANGLE);
                }
            }


            telemetry.addData("timeinms", (armdelay.time(TimeUnit.MILLISECONDS)));

            if (gamepad2.x) {
                hardware.door.setPosition(0);
            }

            if (gamepad1.b) {
                hardware.intake.setPower(-1);
            }
            //set powers to pull up hooks
            hardware.verticalActuatorL.setPower(-(gamepad2.left_stick_y) * 5);
            hardware.rightSlide.setPower(-(gamepad2.left_stick_y) * 5);


            telemetry.addData("speed", speed);

            if (CanMove) {
                if (gamepad2.right_trigger != 0) {
                    hardware.leftSlide.setPower(0.75);
                    //hardware.rightSlide.setPower(0.5);
                } else if (gamepad2.left_trigger != 0) {
                    hardware.leftSlide.setPower(-0.5);
                    //hardware.rightSlide.setPower(-0.35);
                } else {
                    hardware.leftSlide.setPower(0.14);
                    //hardware.rightSlide.setPower(0);
                }
            }

            telemetry.addData("leftslidepower", hardware.leftSlide.getPower());
            //telemetry.addData("rightslidepower", hardware.rightSlide.getPower());

            // plane
            if (gamepad1.y && gamepad2.y) {
                hardware.plane.setPower(1);
//                try {
//                    Thread.sleep(100);
//                } catch (Exception e) {
//                    telemetry.addData("error", e);
//                }
            }else{
                hardware.plane.setPower(-0.1);
            }

            if (gamepad2.left_bumper) {
                hardware.verticalActuatorL.setPower(1);
                hardware.rightSlide.setPower(1);
            } else if (gamepad2.right_bumper) {
                hardware.verticalActuatorL.setPower(-1);
                hardware.rightSlide.setPower(-1);
                hardware.intake.setPower(0);
            } else {
                hardware.verticalActuatorL.setPower(0);
                hardware.rightSlide.setPower(0);
            }

            numPixels = 0;

//            telemetry.addData("TopSlot: ", hardware.topSlot.getDistance(DistanceUnit.CM));
//            telemetry.addData("BottomSlot: ", hardware.bottomSlot.getDistance(DistanceUnit.CM));

//            if (hardware.topSlot.getDistance(DistanceUnit.CM) <= 3.5) {
//                numPixels += 1;
//            }
//
//            if (hardware.bottomSlot.getDistance(DistanceUnit.CM) <= 3.5) {
//                numPixels += 1;
//            }

            time = myStopwatch.time(TimeUnit.SECONDS);

            hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BLACK);
            telemetry.addData("color", hardware.blinkinLedDriver.getConnectionInfo());
            if ((time >= 105) && (time <= 110)) {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RED_ORANGE);
            }
            if ((time >= 110) && (time <= 115)) {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RED);
            }
            if ((time >= 115) && (time <= 120)) {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.STROBE_RED);
            }
//            if ((hardware.bottomSlot.getDistance(DistanceUnit.CM) <= 3.5) && time <= 120) {
//                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RED);
//            }

            telemetry.addData("Stopwatch timer: ", myStopwatch.time(TimeUnit.SECONDS));
//            telemetry.addData("arm delay timer: ", armdelay.time(TimeUnit.SECONDS));
//            telemetry.addData("Stopwatch timer variable: ", teem);

            telemetry.update();
        }
    }

    public void speedloop() {
        if (gamepad1.dpad_down) {
            speed = 0.35;
        }
        if (gamepad1.dpad_left) {
            speed = 0.5;
        }
        if (gamepad1.dpad_up) {
            speed = 0.8;
        }
        if (gamepad1.dpad_right) {
            speed = 1;
        }

        telemetry.addData("speed", speed);
    }

    public void slideloop() {
        hardware.leftSlide.setPower(0);
        //hardware.rightSlide.setPower(0);

        hardware.leftSlide.setPower(gamepad2.right_trigger);
        //hardware.rightSlide.setPower(gamepad2.right_trigger);

        hardware.leftSlide.setPower(-gamepad2.left_trigger);
        //hardware.rightSlide.setPower(-gamepad2.left_trigger);
    }

    public void intakeloop() {
        hardware.intake.setPower(0);
        hardware.intake.setPower(0);

        hardware.intake.setPower(gamepad1.right_trigger);
        hardware.intake.setPower(gamepad1.right_trigger);

        hardware.intake.setPower(-gamepad1.left_trigger);
        hardware.intake.setPower(-gamepad1.left_trigger);
    }

    public void doorloop() {
        if (gamepad2.a) {
            hardware.door.setPosition(1);
        } else if (gamepad2.b) {
            hardware.door.setPosition(0);
        }
    }
}