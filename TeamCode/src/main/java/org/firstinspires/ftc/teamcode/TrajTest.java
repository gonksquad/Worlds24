package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;

import android.drm.DrmStore;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.ThreeDeadWheelLocalizer;
import org.firstinspires.ftc.teamcode.TwoDeadWheelLocalizer;

import org.firstinspires.ftc.teamcode.Hardware;


@Autonomous
public class TrajTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        waitForStart();
//        Hardware hardware = new Hardware(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(12, 60, Math.toRadians(270)));
        while (opModeIsActive()) {
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(12, 60, Math.toRadians(270)))
                            .splineTo(new Vector2d(10, 35), Math.toRadians(180))
                            .waitSeconds(2)
                            .strafeTo(new Vector2d(40, 35))
                            .waitSeconds(2)
                            .strafeTo(new Vector2d(45, 60))
                            .strafeTo(new Vector2d(60, 60))
                            .build()
            );
//            hardware.leftSlide.setPower(0.75);
//            hardware.rightSlide.setPower(0.75);
//            sleep(200);
//            hardware.boxRotation.setPosition(0);
//            sleep(500);
//            hardware.leftSlide.setPower(0);
//            hardware.rightSlide.setPower(0);
//            sleep(500);
//            hardware.door.setPosition(0);
//            sleep(2000);
        }
    }
}