package eu.codlab.chat.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

@SuppressLint("AppCompatCustomView")
public class SimpleRoundedFrameLayout extends FrameLayout {
    private Path path = new Path();
    private RectF rect = new RectF();
    private boolean isCircle;

    public SimpleRoundedFrameLayout(@NonNull Context context) {
        super(context);

        init(context, null);
    }

    public SimpleRoundedFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public SimpleRoundedFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleRoundedFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {
        isCircle = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateSize(w, h);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    public SimpleRoundedFrameLayout setIsCircle(boolean isCircle) {
        this.isCircle = isCircle;

        invalidatePath();
        return this;
    }

    private void invalidatePath() {
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            updateSize(width, height);
            postInvalidate();
        }
    }

    private void updateSize(int width, int height) {
        if (isCircle) {
            float halfWidth = width / 2f;
            float halfHeight = height / 2f;
            path.reset();
            path.addCircle(halfWidth, halfHeight, Math.min(halfWidth, halfHeight), Path.Direction.CW);
            path.close();
        } else {
            path.reset();
        }
    }
}
