package ai.fritz.camera;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.Image;
import android.util.Log;
import android.util.Size;

import java.util.List;

import ai.fritz.core.Fritz;
import ai.fritz.poseestimationmodel.PoseEstimationOnDeviceModel;
import ai.fritz.vision.FritzVision;
import ai.fritz.vision.FritzVisionImage;
import ai.fritz.vision.FritzVisionOrientation;
import ai.fritz.vision.ImageRotation;
import ai.fritz.vision.filter.OneEuroFilterMethod;
import ai.fritz.vision.poseestimation.FritzVisionPosePredictor;
import ai.fritz.vision.poseestimation.FritzVisionPosePredictorOptions;
import ai.fritz.vision.poseestimation.FritzVisionPoseResult;
import ai.fritz.vision.poseestimation.Keypoint;
import ai.fritz.vision.poseestimation.Pose;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends LiveCameraActivity {

    private static final String API_KEY = "bbe75c73f8b24e63bc05bf81ed9d2829";

    FritzVisionPosePredictor predictor;
    FritzVisionImage visionImage;
    FritzVisionPoseResult poseResult;
    int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    boolean collision = false;

    @Override
    protected void initializeFritz() {
        // TODO: Uncomment this and modify your api key above.
        Fritz.configure(this, API_KEY);
    }

    @Override
    protected void setupPredictor() {
        // STEP 1: Get the predictor and set the options.
        // ----------------------------------------------
        // A FritzOnDeviceModel object is available when a model has been
        // successfully downloaded and included with the app.
        FritzVisionPosePredictorOptions options = new FritzVisionPosePredictorOptions();
        options.maxPosesToDetect = 25;

        //FritzVisionPosePredictorOptions posePredictorOptions = new FritzVisionPosePredictorOptions();
        //posePredictorOptions.smoothingOptions = new OneEuroFilterMethod();

        //init model and predictor
        PoseEstimationOnDeviceModel poseEstimationOnDeviceModel = new PoseEstimationOnDeviceModel();
        predictor = FritzVision.PoseEstimation.getPredictor(poseEstimationOnDeviceModel, options);


        // ----------------------------------------------
        // END STEP 1
    }

    @Override
    protected void setupImageForPrediction(Image image) {
        // Set the rotation
        ImageRotation imageRotation = FritzVisionOrientation.getImageRotationFromCamera(this, cameraId);
        // STEP 2: Create the FritzVisionImage object from media.Image
        // ------------------------------------------------------------------------
        visionImage = FritzVisionImage.fromMediaImage(image, imageRotation);
        // ------------------------------------------------------------------------
        // END STEP 2
    }

    @Override
    protected void runInference() {
        // STEP 3: Run predict on the image
        // ---------------------------------------------------
        poseResult = predictor.predict(visionImage);
        // ----------------------------------------------------
        // END STEP 3
    }

    @Override
    protected void showResult(Canvas canvas, Size cameraSize) {

        //draw crosshair
        int radius = 40;
        int color = Color.CYAN;


        color = (color == Color.BLUE) ? Color.CYAN: Color.RED;



        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(2f);

        canvas.drawPaint(paint);
        canvas.drawCircle(width / 2, height / 2 - 160, radius, paint);


        if (collision) {
            paint.setColor(Color.RED);
            canvas.drawPaint(paint);
            canvas.drawCircle(width / 2, height / 2 - 160, radius, paint);
        }
        else {
            paint.setColor(Color.CYAN);
            canvas.drawPaint(paint);
            canvas.drawCircle(width / 2, height / 2 - 160, radius, paint);
        }


        //on touch for crosshair

        // STEP 4: Draw the prediction result
        // ----------------------------------
        if (poseResult != null) {
            List<Pose> poses = poseResult.getPoses();


            for (Pose pose : poses) {
                pose.draw(canvas);
            }

        }
        // ----------------------------------
        // END STEP 4
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        String text = "Hit at x = " + motionEvent.getX() + " and y = " + motionEvent.getY();
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);


        if (poseResult != null) {
            List<Pose> poses = poseResult.getPoses();

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                for (Pose pose : poses) {
                    Keypoint[] keypoints = pose.getKeypoints();
                    String partName = keypoints[0].getPartName();
                    PointF keypointPosition = keypoints[0].getPosition();

                    if (motionEvent.getX() == pose.getBounds().getWidth() && motionEvent.getY() == pose.getBounds().getHeight()) {
                        collision = true;
                        text = "Hit!";

                    }
                }

                if(motionEvent.getX() <= 950 && motionEvent.getX() >= 450 && motionEvent.getY() <= 1550 && motionEvent.getY() >= 1400) {
                    //collision = circle.contains(x,y);
                    Log.d("touch", "down at $x $y");
                    toast.setMargin(10, 10);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    return true;
                }
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                collision = false;
                toast.cancel();
            }

        }
        return false;
    }
}
