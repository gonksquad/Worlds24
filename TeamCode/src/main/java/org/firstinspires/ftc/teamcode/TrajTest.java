package org.firstinspires.ftc.teamcode;

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

@Autonomous
public class TrajTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        waitForStart();
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-36, -60, Math.toRadians(90)));
        while (opModeIsActive()) {
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                            .splineTo(new Vector2d(-34, -36), Math.toRadians(0))
                            .waitSeconds(2)
                            .strafeTo(new Vector2d(-34, -12))
                            .strafeTo(new Vector2d(-33.9, -12))
                            .splineTo(new Vector2d(-12,-12), Math.toRadians(0))
                            .splineTo(new Vector2d(30,-12), Math.toRadians(0))
                            .splineTo(new Vector2d(40,-36), Math.toRadians(180))
                            .build()
            );
        }
    }
}

